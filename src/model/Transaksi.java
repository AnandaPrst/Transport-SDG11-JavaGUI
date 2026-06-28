/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Transaksi {
    private Date tanggal;
    private String kendaraan;
    private String detailKendaraan;
    private String kategori;
    private double jarak;
    private int halte;
    private double tarif;
    private double etaJam;
    private double emisiHemat;

    public Transaksi(Date tanggal, String kendaraan, String detailKendaraan, String kategori,
            double jarak, int halte, double tarif, double etaJam, double emisiHemat) {
        this.tanggal = tanggal;
        this.kendaraan = kendaraan;
        this.detailKendaraan = detailKendaraan;
        this.kategori = kategori;
        this.jarak = jarak;
        this.halte = halte;
        this.tarif = tarif;
        this.etaJam = etaJam;
        this.emisiHemat = emisiHemat;
    }

    public Date getTanggal() { return tanggal; }
    public String getKendaraan() { return kendaraan; }
    public String getDetailKendaraan() { return detailKendaraan; }
    public String getKategori() { return kategori; }
    public double getJarak() { return jarak; }
    public int getHalte() { return halte; }
    public double getTarif() { return tarif; }
    public double getEtaJam() { return etaJam; }
    public double getEmisiHemat() { return emisiHemat; }

    public double getJarakKm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getEtaMenit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
