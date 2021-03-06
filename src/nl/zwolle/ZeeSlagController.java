package nl.zwolle;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.zwolle.gameClasses.Computer;
import nl.zwolle.gameClasses.Speler;




@Controller
public class ZeeSlagController {

	@RequestMapping("/")
	public String beginGame(Model model, HttpSession session) {

		if (session.getAttribute("player1") != null){

			model.addAttribute("name", ((Speler)session.getAttribute("player1")).getNaam());

		}

		return "start";
	}


	@RequestMapping(value="/startGame", method=RequestMethod.POST)
	public String startGame(Model model, HttpSession session, String name, String opponent, int dimensionX, int dimensionY, int boats) {

		Speler player1 = new Speler(name, dimensionX, dimensionY);
		player1.setHoeveelheidBoten(boats);


		if(opponent.equals("computer")){

			Speler ai = new Computer(dimensionX, dimensionY);
			session.setAttribute("player1", player1);
			session.setAttribute("player2", ai);

			return "placeBoats";
		}


		//computer leeg maken bij de multiplayer function
		session.setAttribute("player2", null);

		session.setAttribute("player1",ZeeSlagDOA.saveSpeler(player1));
		List<Speler> gameList = ZeeSlagDOA.hosts();
		model.addAttribute("gameList", gameList);

		return "waitingRoom"; //else return to multiplayer waitingroom page.


	}


	@RequestMapping("/waitingRoomHost")
	public String HostGame(Model model, HttpSession session) {


		// verander status speler, in database en daarna in session.

		Speler tempSpeler = (Speler)session.getAttribute("player1");

		tempSpeler.setHost(true);

		session.setAttribute("player1", ZeeSlagDOA.updateSpeler(tempSpeler));


		// haal nieuwe lijst op en zet hem in n
		List<Speler> gameList = ZeeSlagDOA.hosts();
		model.addAttribute("gameList", gameList);

		return "waitingRoom";


	}

	@RequestMapping("/waitingRoomJoin")
	public synchronized String joinGame(Model model, HttpSession session, Integer opponent ) {


		//kijk of speler en opponent niet al coupled is EN als hij niet zichzelf is misschien join knop weghalen


		if (opponent != null){
			Speler tempSpeler = (Speler)session.getAttribute("player1");
			Speler tempSpeler2 = ZeeSlagDOA.find(opponent);

			if(opponent != tempSpeler.getId() && !tempSpeler2.isCoupled() && !tempSpeler.isCoupled()){



				//zet tegenstander voor huidige speler

				tempSpeler.setCoupled(true);
				tempSpeler.setOpponentId(opponent);

				//zet deze speler als tegenstander de opponent

				tempSpeler2.setCoupled(true);
				tempSpeler2.setOpponentId(((Speler)session.getAttribute("player1")).getId());

				//bepaal wie er begint:

				if(Math.random()< 0.50){
					tempSpeler.setHisTurn(true);
					tempSpeler2.setHisTurn(false);
				}else{
					tempSpeler2.setHisTurn(true);
					tempSpeler.setHisTurn(false);
				}

				//schrijf beide spelers weg naar database en update session
				session.setAttribute("player1", ZeeSlagDOA.updateSpeler(tempSpeler));
				ZeeSlagDOA.updateSpeler(tempSpeler2);

				return "placeBoats";

			}

		}
		//TODO spelers meegeven

		return "waitingRoom";


	}

	@RequestMapping("/waitingRoomRefresh")
	public String refreshGame(Model model, HttpSession session) {


		// kijk if coupled en stuur door
		Speler tempSpeler = (Speler)session.getAttribute("player1");

		Speler tempSpeler2 = ZeeSlagDOA.find(tempSpeler.getId());
		tempSpeler =null;


		if (tempSpeler2.isCoupled() && tempSpeler2.getOpponentId()!= -1){

			session.setAttribute("player1", tempSpeler2);

			return "placeBoats";
			//TODO spelers meegeven
		}



		//anders terug naar waitingroom
		// haal nieuwe lijst op en zet hem in n

		List<Speler> gameList = ZeeSlagDOA.hosts();
		model.addAttribute("gameList", gameList);
		return "waitingRoom";

	}


	@RequestMapping(value="/placeBoats", method=RequestMethod.POST)
	public String processPlacedBoat(Model model,HttpSession session, int xCoordinate,int yCoordinate,boolean orientation,int boatType){

		session.setAttribute("type"+boatType, boatType);

		System.out.println(xCoordinate+" "+yCoordinate);
		int x = xCoordinate;
		int y = yCoordinate;

		Speler player = (Speler) session.getAttribute("player1");

		player.nieuweBoot(x, y, orientation, boatType);



		if (session.getAttribute("player2") == null){ // player2=computer

			//als geen computer
			session.setAttribute("player1",ZeeSlagDOA.updateSpeler(player));

		}else{
			//als wel computer
			session.setAttribute("player1", player);
		}


		//alle boten geplaats?
		if(player.getBootArray().size() >=3){

			//ja, zet deze speler op ready
			player.setReadyToPlay(true);



			//als er multiplayer gespeeld wordt
			if (session.getAttribute("player2") == null){

				//als multiplayer
				session.setAttribute("player1",ZeeSlagDOA.updateSpeler(player));

				//todo while loop totdat speler zijn boten heeft geplaatst.
				while(!ZeeSlagDOA.find(player.getOpponentId()).isReadyToPlay()){


					try {
						Thread.sleep(3500);                 //1000 milliseconds is one second.
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}


				}
				session.setAttribute("opponent", ZeeSlagDOA.find(player.getOpponentId()));
				return "gameRoom";

			}else{

				// als geen multiplayer
				session.setAttribute("player1", player);
				Computer computer = (Computer)session.getAttribute("player2");
				computer.computerPlaatstBoten(3, player.getBord().getBordBreedte(), player.getBord().getBordLengte());
				session.setAttribute("player2", computer);
				return "gameRoomAI";
			}





		}else{


			return "placeBoats";}


	}


	@RequestMapping(value="/shoot", method = RequestMethod.POST)
	public @ResponseBody Speler shootMethod(Model model, HttpSession session, Integer x, Integer y) {
		//reset error
		model.addAttribute("error","");
		//haal speler sessie op
		Speler player1 = (Speler)ZeeSlagDOA.find(((Speler) session.getAttribute("player1")).getId()); 

		// als computer is tegenstander
		if (session.getAttribute("player2") != null){
			//haal ai op
			Computer ai = (Computer) session.getAttribute("player2");

			//shiet op bord computer check if gelukt

			if(player1.schietOpVakje(ai.getBord(), x, y)){
				//zoja, computer schiet op jou, sla beide op in session

				ai.schietOpVakje(player1.getBord());

				session.setAttribute("player1", player1);
				session.setAttribute("player2", ai);

			} else{

				model.addAttribute("error", "You clicked on a square that already contained a boat, please try again");


			};


		}else{

			// anders: haal bord tegenstander op

			Speler opponent = ZeeSlagDOA.find(player1.getOpponentId());

			// schietopvakje bord tegenstander en kijk of het gelukt is, check coordinaten

			if(player1.isHisTurn()){

				if(player1.schietOpVakje(opponent.getBord(), x, y)){

					// als wel , geef beurt aan opponent, geen beurt voor deze speler meer, kijk of opponent heeft verloren, 
					// pas opponent en huidige speler  aan in de session en database

					opponent.setHisTurn(true);
					player1.setHisTurn(false);
					//TODO zet dit als voorwaarde in javascript opponent.spelerHeeftVerloren();
					
					//return true als gelukt is

				}else{


					model.addAttribute("error", "You clicked on a square that already contained a boat, please try again");

				}

			}else{
				model.addAttribute("error","Not Your turn");
			}
			
			session.setAttribute("player1",ZeeSlagDOA.updateSpeler(player1));
			ZeeSlagDOA.updateSpeler(opponent);

		}


		return player1;




	}

	@RequestMapping(value="/getComputer", method = RequestMethod.GET) // haalt ook opponent op
	public @ResponseBody Speler getComputer(HttpSession session){

		if (session.getAttribute("player2") != null){
			Computer ai = (Computer) session.getAttribute("player2");
			return ai;
		}
		Speler player1 = (Speler)session.getAttribute("player1");
		Speler opponent = (Speler)ZeeSlagDOA.find(player1.getOpponentId());
		return opponent;
	}

	@RequestMapping(value="/getPlayer", method = RequestMethod.GET)
	public @ResponseBody Speler getSpeler(HttpSession session){

		//als tegen de computer
		if (session.getAttribute("player2") != null){		
			Speler player = (Speler) session.getAttribute("player1");
			return player;
		}
		// anders als tegen opponent

		Speler player = (Speler)ZeeSlagDOA.find(((Speler) session.getAttribute("player1")).getId()); 
		return player;

	}

	//	@RequestMapping(value="/getOpponent", method = RequestMethod.GET)
	//	public @ResponseBody Speler getOpponent(HttpSession session){
	//		Speler player = (Speler) session.getAttribute("opponent");
	//		return player;
	//	}

}
