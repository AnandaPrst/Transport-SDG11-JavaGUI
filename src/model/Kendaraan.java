/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class Kendaraan {
    protected String nama;
    protected double tarifDasar;
    protected double kecepatan; // km/jam

    public Kendaraan(String nama, double tarifDasar, double kecepatan) {
        this.nama = nama;
        this.tarifDasar = tarifDasar;
        this.kecepatan = kecepatan;
    }

    public String getNama() {
        return nama;
    }

    public double getTarifDasar() {
        return tarifDasar;
    }

    public double getKecepatan() {
        return kecepatan;
    }

    // override di subclass
    public abstract double hitungTarif(double jarakKm, int halte);

    public double hitungETA(double jarakKm) {
        return jarakKm / kecepatan;
    }
}
