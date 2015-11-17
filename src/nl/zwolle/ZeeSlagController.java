package nl.zwolle;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import nl.zwolle.gameClasses.Boot;
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
	public synchronized String joinGame(Model model, HttpSession session, int opponent ) {


		//kijk of speler en opponent niet al coupled is EN als hij niet zichzelf is misschien join knop weghalen


		Speler tempSpeler = (Speler)session.getAttribute("player1");
		Speler tempSpeler2 = ZeeSlagDOA.find(opponent);

		if(opponent != 0 &&  opponent != tempSpeler.getId() && !tempSpeler2.isCoupled() && !tempSpeler.isCoupled()){



			//zet tegenstander voor huidige speler

			tempSpeler.setCoupled(true);
			tempSpeler.setOpponentId(opponent);

			//zet deze speler als tegenstander de opponent

			tempSpeler2.setCoupled(true);
			tempSpeler2.setOpponentId(((Speler)session.getAttribute("player1")).getId());

			//schrijf beide spelers weg naar database en update session
			session.setAttribute("player1", ZeeSlagDOA.updateSpeler(tempSpeler));
			ZeeSlagDOA.updateSpeler(tempSpeler2);

		}
		//TODO spelers meegeven

		return "placeBoats";


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


	//BASTIAAN, mergen ging niet helemaal lekker denk. hieronder jouw 2? controllers. klopt dit?

	@RequestMapping(value="/placeBoats", method=RequestMethod.POST)
	public String processPlacedBoat(HttpSession session, int xCoordinate,int yCoordinate,boolean orientation,int boatType){

		session.setAttribute("type"+boatType, boatType);

		System.out.println(xCoordinate+" "+yCoordinate);
		int x = xCoordinate;
		int y = yCoordinate;

		Speler player = (Speler) session.getAttribute("player1");
		player.nieuweBoot(x, y, orientation, boatType);
		


		return "placeBoats";

	}

	

}
