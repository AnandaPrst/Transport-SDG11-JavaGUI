/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
package model;

public class Admin extends User {

    private String email;

    public Admin(int idUser, String email, String username, String password) {
        super(idUser, username, password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
