package nl.zwolle;
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

import nl.zwolle.gameClasses.Apples;
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
			
		//database test
		ZeeSlagDOA.saveSpeler(player1);
		
		
		session.setAttribute("player1", player1);
		model.addAttribute("player1", player1);

		model.addAttribute("name", ((Speler)session.getAttribute("player1")).getNaam());
		model.addAttribute("dimensionX", dimensionX);
		model.addAttribute("dimensionY", dimensionY);
		model.addAttribute("numberOfBoats", boats);
		
		if(opponent.equals("computer")){
			
			Speler ai = new Computer(dimensionX, dimensionY);
			session.setAttribute("player2", ai);
			
			return "placeBoats";
		}
		
		
		return "waitingRoom"; //else return to multiplayer waitingroom page.
		
		
	}
	
	@RequestMapping(value="/placeBoats", method=RequestMethod.POST)
	public String processPlacedBoat(HttpSession session, String xCoordinate,String yCoordinate){
		
		int x = Integer.parseInt(xCoordinate);
		int y = Integer.parseInt(yCoordinate);
		
		
		
		return null;
		
	}
	
	
	
	@RequestMapping("/waitingRoom")
	public String checkForSecondPlayer(Model model, HttpSession session) {
		
		//kijk of er 2 active spelers in de database zitten
		//dan naar placeBoats
		
		//anders, als jij de enige active speler beent:
		model.addAttribute("name", ((Speler)session.getAttribute("player1")).getNaam());
		model.addAttribute("dimensionX", ((Speler)session.getAttribute("player1")).getBord().getBordBreedte());
		model.addAttribute("dimensionY", ((Speler)session.getAttribute("player1")).getBord().getBordLengte());
		model.addAttribute("numberOfBoats", ((Speler)session.getAttribute("player1")).getHoeveelheidBoten());
		
		
		
		
		
		
		return "waitingRoom";
	}
	
	
	
	

}
