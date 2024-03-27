package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {


    // TODO: Task 6
    @GetMapping(path={"/", "/login"})
    public String login(HttpSession session, Model model) {
        
        if(null == session.getAttribute("login")) {
            model.addAttribute("login", new Login());
        } else {
            model.addAttribute("login", (Login) session.getAttribute("login"));
        }

        return "view0";

    }

    // TODO: Task 7
    @PostMapping(path={"/login"})
    public ModelAndView processlogin(HttpSession session,  @RequestBody @Valid @ModelAttribute Login login, BindingResult binding) {

        ModelAndView mav = new ModelAndView("view1");

        if (binding.hasErrors()) {
            System.out.println("binding error: >> " + binding.getAllErrors());
            mav.setViewName("view0.html");
            mav.addObject("login", login);
            return mav;
        }

        
        session.setAttribute("login", login);

        System.out.println("login: >>> " + session.getAttribute("login").toString());

        return mav;
    }
    

    // For the logout button shown on View 2
    // On logout, session should be cleared
    @GetMapping(path = {"/logout"})
    public ModelAndView logout(HttpSession session) {

        ModelAndView mav = new ModelAndView("redirect:/");
        session.invalidate();
        
        return mav;
    }
    
}
