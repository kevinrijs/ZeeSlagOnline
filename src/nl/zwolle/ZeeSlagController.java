package nl.zwolle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class ZeeSlagController {
	
	
	@RequestMapping("/")
	public String overzicht(Model model) {
		model.addAttribute("hoi", "hoi");
		return "start";
	}
	
	
	
	

}
