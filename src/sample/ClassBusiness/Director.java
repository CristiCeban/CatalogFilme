package sample.ClassBusiness;

import javafx.scene.image.ImageView;

public class Director {
    private int id;
    private String name;
    private int year;
    private String about;
    private ImageView image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Director(int id, String name, int year, String about, ImageView image) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.about = about;
        this.image = image;
    }
}