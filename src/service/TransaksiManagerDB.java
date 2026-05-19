/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Transaksi;

public class TransaksiManagerDB {

    public static void tambahTransaksi(int idUser, Transaksi t) {

        String sql = "INSERT INTO transaksi(id_user, tanggal, kendaraan, kategori, jarak_km, halte, tarif, eta_menit, emisi_hemat) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            ps.setTimestamp(2, new java.sql.Timestamp(t.getTanggal().getTime()));
            ps.setString(3, t.getKendaraan());
            ps.setString(4, t.getKategori());
            ps.setDouble(5, t.getJarakKm());
            ps.setInt(6, t.getHalte());
            ps.setDouble(7, t.getTarif());
            ps.setDouble(8, t.getEtaMenit());
            ps.setDouble(9, t.getEmisiHemat());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Tambah transaksi gagal: " + e.getMessage());
        }
    }

    public static double totalBiaya(int idUser) {

        String sql = "SELECT IFNULL(SUM(tarif),0) AS total FROM transaksi WHERE id_user=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }

        } catch (Exception e) {
            System.out.println("Total biaya gagal: " + e.getMessage());
        }

        return 0;
    }

    public static double totalEmisi(int idUser) {

        String sql = "SELECT IFNULL(SUM(emisi_hemat),0) AS total FROM transaksi WHERE id_user=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUser);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }

        } catch (Exception e) {
            System.out.println("Total emisi gagal: " + e.getMessage());
        }

        return 0;
    }
}