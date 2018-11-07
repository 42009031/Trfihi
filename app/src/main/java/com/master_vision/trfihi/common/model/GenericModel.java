package com.master_vision.trfihi.common.model;

public class GenericModel {

    private String id;
    private int image;
    private String name;

    public GenericModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public GenericModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenericModel(String id, int image) {
        this.id = id;
        this.image = image;
    }

    public GenericModel(String id, int image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
