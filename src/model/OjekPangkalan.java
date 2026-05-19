/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class OjekPangkalan extends Kendaraan {

    public OjekPangkalan() {
        super("Ojek Pangkalan", 3500, 35);
    }

    @Override
    public double hitungTarif(double jarakKm, int halte) {
        return (tarifDasar * jarakKm) + 2000;
    }
}