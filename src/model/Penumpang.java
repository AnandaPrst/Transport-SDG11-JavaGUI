/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
package model;

public class Penumpang extends User {

    private String email;
    private int greenPoints;

    public Penumpang(int idUser, String email, String username, String password, int greenPoints) {
        super(idUser, username, password);
        this.email = email;
        this.greenPoints = greenPoints;
    }

    public String getEmail() {
        return email;
    }

    public int getGreenPoints() {
        return greenPoints;
    }

    public void setGreenPoints(int greenPoints) {
        this.greenPoints = greenPoints;
    }

    public void tambahPoints(int points) {
        this.greenPoints += points;
    }
}