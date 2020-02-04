package sample.ClassBusiness;

import javafx.scene.image.ImageView;

public class Genre {
    private int id;
    private String genre;
    private ImageView image;

    public Genre(int id, String genre, ImageView image) {
        this.id = id;
        this.genre = genre;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}