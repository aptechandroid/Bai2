package vn.uits.bai2.model;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 3/15/18.
 */

public class Contact {
    private int id;
    private String name;
    private String numberPhone;

    public Contact() {
    }

    public Contact(int id, String name, String numberPhone) {
        this.id = id;
        this.name = name;
        this.numberPhone = numberPhone;
    }

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

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
