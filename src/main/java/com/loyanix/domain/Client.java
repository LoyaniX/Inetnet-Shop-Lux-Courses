package com.loyanix.domain;

import java.util.Objects;

public class Client {

    private long id;
    private String name;
    private String surname;
    private int age;
    private String emeil;
    private String phone;

    public Client() {
    }

    public Client(String name, String surname, int age, String emeil, String phone) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.emeil = emeil;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmeil() {
        return emeil;
    }

    public void setEmeil(String emeil) {
        this.emeil = emeil;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", emeil='" + emeil + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
