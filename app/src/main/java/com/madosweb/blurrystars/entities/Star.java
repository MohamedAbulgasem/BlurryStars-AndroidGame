package com.madosweb.blurrystars.entities;

public class Star {

    private int id;
    private String name;
    private int image;
    private int blurredImage;

    public Star(int id, String name, int image, int blurredImage) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.blurredImage = blurredImage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getBlurredImage() {
        return blurredImage;
    }

}
