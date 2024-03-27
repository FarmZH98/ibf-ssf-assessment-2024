package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;

@Service
public record FileService() {

    // TODO: Task 1
    public List<Movie> readFile(String fileName) {
        String jsonString = "";
        List<Movie> movieList = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String temp = br.readLine();
            jsonString += temp;

            while(temp != null) {
                temp = br.readLine();
                jsonString += temp;
            }
            br.close();
            
            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonArray dataArray = reader.readArray();
            for(int i=0; i<dataArray.size(); ++i) {
                JsonObject data = dataArray.get(i).asJsonObject();
                Integer id = data.getInt("Id");
                String title = data.getString("Title");
                String year = data.getString("Year");
                String rated = data.getString("Rated");
                Long released = data.getJsonNumber("Released").longValue(); //date in epoch format!!**
                String runtime = data.getString("Runtime");
                String genre = data.getString("Genre");
                String director = data.getString("Director");
                Double rating = data.getJsonNumber("Rating").doubleValue();
                Integer count = data.getInt("Count");
                Movie movie = new Movie(id, title, year, rated, released, runtime, genre, director, rating, count);
                //taskRepo.saveTask(task);
                //System.out.println(task.toString());
                movieList.add(movie);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //taskRepo.getAllTasks();
        return movieList;
    }

}
