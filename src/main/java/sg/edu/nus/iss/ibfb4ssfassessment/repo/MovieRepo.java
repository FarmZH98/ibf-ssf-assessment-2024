package sg.edu.nus.iss.ibfb4ssfassessment.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

@Repository
public class MovieRepo {

    @Autowired
    @Qualifier(Util.REDIS_ONE)
    RedisTemplate<String, String> template;

    //utils.KEY_TASK, taskID, task
    HashOperations<String, String, String> hashOps;

    public void saveMovie(Movie movie) {
        hashOps = template.opsForHash();
        hashOps.put(Util.KEY_MOVIE, movie.getMovieId().toString(), movie.toJsonString());
    }

    public long getNumberOfMovies() {
        hashOps = template.opsForHash();
        return hashOps.size(Util.KEY_MOVIE);
    }

    public String getJsonMovieById(Integer movieId) {
        hashOps = template.opsForHash();
        return hashOps.get(Util.KEY_MOVIE, movieId.toString());
    }

    public List<String> getAllMovies() {
        hashOps = template.opsForHash();
        Map<String,String> rawMap = hashOps.entries(Util.KEY_MOVIE);

        List<String> moviesList = new LinkedList<>();

        for (var entry : rawMap.entrySet()) {
            moviesList.add(entry.getValue());
        }

        return moviesList;
    }
}
