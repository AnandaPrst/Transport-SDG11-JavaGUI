/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Angkot extends Kendaraan {

    public Angkot() {
        super("Angkot", 5000, 25);
    }

    @Override
    public double hitungTarif(double jarakKm, int halte) {
        return tarifDasar;
    }
}
