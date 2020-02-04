package sample.ClassBusiness;

import javafx.scene.image.ImageView;

public class Movies {
    private int id;
    private String title;
    private int year;
    private ImageView image;
    private String certificate;
    private int runtime;
    private double imdb_Rating;
    private String description;
    private int metascore;
    private int votes;
    private int gross;
    private String movieScore;

    public Movies(int id, String title, int year, ImageView image, String certificate, int runtime, double imdb_Rating, String description, int metascore, int votes, int gross, String movieScore) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
        this.certificate = certificate;
        this.runtime = runtime;
        this.imdb_Rating = imdb_Rating;
        this.description = description;
        this.metascore = metascore;
        this.votes = votes;
        this.gross = gross;
        this.movieScore = movieScore;
    }
    public Movies(int id, String title, int year, ImageView image, String certificate, int runtime, double imdb_Rating, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
        this.certificate = certificate;
        this.runtime = runtime;
        this.imdb_Rating = imdb_Rating;
        this.description = description;
        this.metascore = 0;
        this.votes = 0;
        this.gross = 0;
        this.movieScore = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getImdb_Rating() {
        return imdb_Rating;
    }

    public void setImdb_Rating(double imdb_Rating) {
        this.imdb_Rating = imdb_Rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getGross() {
        return gross;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }

    public String getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(String movieScore) {
        this.movieScore = movieScore;
    }
}
