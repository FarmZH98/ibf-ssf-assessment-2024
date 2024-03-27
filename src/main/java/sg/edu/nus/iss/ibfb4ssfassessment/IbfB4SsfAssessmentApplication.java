package sg.edu.nus.iss.ibfb4ssfassessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;
import sg.edu.nus.iss.ibfb4ssfassessment.service.FileService;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

// TODO: Put in the necessary code as described in Task 1 & Task 2
@SpringBootApplication
public class IbfB4SsfAssessmentApplication implements CommandLineRunner {

	@Autowired
	DatabaseService databaseService;

	@Autowired
	FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(IbfB4SsfAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Movie> moviesList = fileService.readFile(Util.movieFilePath);

		for(int i=0; i<moviesList.size(); ++i) {
			//System.out.println(moviesList.get(i).toString());
			databaseService.saveRecord(moviesList.get(i));
		}
		// System.out.println("Number of movies in Redis >>> " + databaseService.getNumberOfMovies());
		// System.out.println("Testing getting movie with ID from REDIS >>> " + databaseService.getMovieById(12341));
		System.out.println("Testing getting all movies from REDIS >>> " + databaseService.getAllMovies());
	}

}
