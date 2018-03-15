package vn.uits.bai2.model;

import io.realm.RealmObject;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 3/15/18.
 */

public class Student extends RealmObject {

    private String name;
    private String numberPhone;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String numberPhone) {
        this.name = name;
        this.numberPhone = numberPhone;
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
