package nl.zwolle;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.zwolle.gameClasses.Speler;



@Controller
public class ZeeSlagController {
	
	
	@RequestMapping("/")
	public String beginGame(Model model, HttpSession session) {
		
		if (session.getAttribute("player") != null){
							
			model.addAttribute("name", ((Speler)session.getAttribute("player")).getNaam());
						
		}
		
		return "start";
	}
	
	
	@RequestMapping(value="/startGame", method=RequestMethod.POST)
	public String startGame(Model model, HttpSession session, String name, int dimensionX, int dimensionY, int boats) {
		System.out.println("hhf");
		Speler player1 = new Speler(name, dimensionX, dimensionY);
		session.setAttribute("player", player1);
		
		model.addAttribute("dimensionX", dimensionX);
		model.addAttribute("dimensionY", dimensionY);
		model.addAttribute("numberOfBoats", boats);
		
		
		return "placeBoats";
	}
	
	
	
	

}
