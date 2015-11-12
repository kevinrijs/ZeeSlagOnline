package nl.zwolle;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ZeeSlagController {
	
	
	@RequestMapping("/")
	public String beginGame(Model model, HttpSession session) {
		model.addAttribute("hoi", "hoi");
		return "start";
	}
	
	
	
	

}
