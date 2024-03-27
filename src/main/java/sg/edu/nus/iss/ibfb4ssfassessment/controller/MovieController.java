package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;

@Controller
@RequestMapping
public class MovieController {

    @Autowired
    DatabaseService databaseService;

    // // TODO: Task 8
    @GetMapping(path={"/listing"})
    public ModelAndView displayMovies(HttpSession session) {

        ModelAndView mav = new ModelAndView();
        if(null == session.getAttribute("login")) {
            mav.setViewName("redirect:/");
            System.out.println("No HttpSession established. Redirect to login page...");
            return mav;
        }

        System.out.println("displayMovies(): Login Info: >>>" + session.getAttribute("login").toString());
        List<Movie> movieList = databaseService.getAllMovies();
        mav.addObject("moviesList", movieList); 
        mav.setViewName("view2");

        return mav;
    }

    // // TODO: Task 9
    @GetMapping(path={"/book/{id}"})
    public ModelAndView bookMovie(HttpSession session, @PathVariable("id") String id)  {

        ModelAndView mav = new ModelAndView("");
        if(null == session.getAttribute("login")) {
            mav.setViewName("redirect:/");
            System.out.println("No HttpSession established. Redirect to login page...");
            return mav;
        }

        Login login = (Login) session.getAttribute("login");
        Integer userAge =  login.getAge();

        System.out.println("bookMovie(): Login Info >>>" + login.toString());
        System.out.println("bookMovie(): MovieID >>>" + id);

        Movie movieToBook = databaseService.getMovieById(Integer.parseInt(id));

        if(movieToBook.getRated().equals("R") && userAge < 18 || movieToBook.getRated().equals("PG-13") && userAge < 13) {
            mav.setViewName("BookError");
            return mav;
        }

        mav.setViewName("BookSuccess");
        mav.addObject("movieName", movieToBook.getTitle());
        databaseService.incrementMovieCount(movieToBook);

        return mav;
    }


}
