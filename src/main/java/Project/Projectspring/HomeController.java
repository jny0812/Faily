package Project.Projectspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Home(Model model) throws Exception {

        return "Home";
    }

    @RequestMapping(value = "/join",method = RequestMethod.GET)
    public String Join(Model model) throws Exception {

        return "Join/Join";
    }
}
