package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.io.StringReader;
import java.util.Date;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Movie {

    private Integer movieId;
    private String title;
    private String year;
    private String rated;
    private Long releaseDate;
    private String runTime;
    private String genre;
    private String director;
    private Double rating;
    private Date formattedReleaseDate;
    private Integer count;

    public Movie() {
    }

    public Movie(Integer movieId, String title, String year, String rated, Long releaseDate, String runTime, String genre,
            String director, Double rating, Integer count) {
        this.movieId = movieId;
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.releaseDate = releaseDate;
        this.formattedReleaseDate = new Date(releaseDate);
        this.runTime = runTime;
        this.genre = genre;
        this.director = director;
        this.rating = rating;
        this.count = count;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getFormattedReleaseDate() {
        return formattedReleaseDate;
    }

    public void setFormattedReleaseDate(Date formattedReleaseDate) {
        this.formattedReleaseDate = formattedReleaseDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void addCount() {
        this.count++;
    }

    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", title=" + title + ", year=" + year + ", rated=" + rated
                + ", releaseDate=" + releaseDate + ", runTime=" + runTime + ", genre=" + genre + ", director="
                + director + ", rating=" + rating + ", formattedReleaseDate=" + formattedReleaseDate + ", count="
                + count + "]";
    }

    //save time as epoch
    public String toJsonString() {

        JsonObject movieAsJson = Json.createObjectBuilder()
                        .add("movieId", movieId)
                        .add("title", title)
                        .add("year", year)
                        .add("rated", rated)
                        .add("releaseDate", releaseDate)
                        .add("runTime", runTime)
                        .add("genre", genre)
                        .add("director", director)
                        .add("rating", rating)
                        .add("count", count)
                        .build();

        return movieAsJson.toString();
    }
    
    public static Movie jsonToMovie(String movieJson) {

        JsonReader reader = Json.createReader(new StringReader(movieJson));
        JsonObject jObject = reader.readObject();
        Movie movieToReturn = new Movie();
        movieToReturn.setMovieId(jObject.getInt("movieId"));
        movieToReturn.setTitle(jObject.getString("title"));
        movieToReturn.setYear(jObject.getString("year"));
        movieToReturn.setRated(jObject.getString("rated"));
        movieToReturn.setReleaseDate(jObject.getJsonNumber("releaseDate").longValue());
        movieToReturn.setFormattedReleaseDate(new Date(movieToReturn.getReleaseDate()));
        movieToReturn.setRunTime(jObject.getString("runTime"));
        movieToReturn.setGenre(jObject.getString("genre"));
        movieToReturn.setDirector(jObject.getString("director"));
        movieToReturn.setRating(jObject.getJsonNumber("rating").doubleValue());
        movieToReturn.setCount(jObject.getInt("count"));

        //System.out.println("taskToReturn: " + taskToReturn.toString());

        return movieToReturn;
    }
}
