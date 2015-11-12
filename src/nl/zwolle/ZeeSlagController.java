package nl.zwolle;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		
		session.setAttribute("player1", player1);
		
		
		model.addAttribute("dimensionX", dimensionX);
		model.addAttribute("dimensionY", dimensionY);
		model.addAttribute("numberOfBoats", boats);
		
		if(opponent.equals("computer")){
			
			Speler ai = new Computer(dimensionX, dimensionY);
			session.setAttribute("player2", ai);
			
			return "placeBoats";
		}
		
		
		return "placeBoats"; //else return to multiplayer waitingroom page.
		
	}
	
	
	
	

}
