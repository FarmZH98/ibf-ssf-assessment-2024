package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MovieRepo;

@Service
public class DatabaseService {

    @Autowired
    MovieRepo movieRepo;

    // TODO: Task 2 (Save to Redis Map)
    public void saveRecord(Movie movie) {
        movieRepo.saveMovie(movie);
    }

    // TODO: Task 3 (Map or List - comment where necesary)
    // renamed method as accordance to instructions in assessment
    public long getNumberOfMovies() {
        return movieRepo.getNumberOfMovies();
    }

    // public Movie getMovie(Integer index) {
    //     return repo.getMovie(index);
    // }

    // TODO: Task 4 (Map)
    public Movie getMovieById(Integer movieId) {
        String movieJson = movieRepo.getJsonMovieById(movieId);

        return Movie.jsonToMovie(movieJson);
    }

    // // TODO: Task 5
    public List<Movie> getAllMovies() {
        List<String> moviesJsonList = movieRepo.getAllMovies();

        List<Movie> moviesList = new LinkedList<>();

        for(int i=0; i<moviesJsonList.size(); ++i) {
            moviesList.add(Movie.jsonToMovie(moviesJsonList.get(i)));
        }

        return moviesList;
    }
}
