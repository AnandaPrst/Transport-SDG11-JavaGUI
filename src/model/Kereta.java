/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Kereta extends Kendaraan {

    public Kereta() {
        super("Kereta", 3000, 60);
    }

    @Override
    public double hitungTarif(double jarakKm, int halte) {
        return tarifDasar * halte;
    }
}