/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Bus extends Kendaraan {

    public Bus() {
        super("Bus", 2000, 40);
    }

    @Override
    public double hitungTarif(double jarakKm, int halte) {
        return tarifDasar * jarakKm;
    }
}
