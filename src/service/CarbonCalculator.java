/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

public class CarbonCalculator {

    public static double hitungPenghematan(double jarakKm) {
        double emisiMobil = jarakKm * 0.20;
        double emisiAngkutan = jarakKm * 0.08;
        return emisiMobil - emisiAngkutan;
    }

    // overload
    public static double hitungPenghematan(double jarakKm, String kendaraanPribadi) {
        double emisiPribadi;

        if (kendaraanPribadi.equalsIgnoreCase("motor")) {
            emisiPribadi = jarakKm * 0.12;
        } else {
            emisiPribadi = jarakKm * 0.20;
        }

        double emisiAngkutan = jarakKm * 0.08;
        return emisiPribadi - emisiAngkutan;
    }
}
