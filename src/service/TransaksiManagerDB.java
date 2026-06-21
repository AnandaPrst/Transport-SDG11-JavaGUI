/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static int getTotalTransaksi() {

    int total = 0;

    try {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT COUNT(*) FROM transaksi";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getInt(1);
        }

    } catch (SQLException e) {
    }
    

    return total;
}
    
    public static double getTotalPendapatan() {

    double total = 0;

    try {
        Connection conn = DBConnection.getConnection();

        String sql = "SELECT SUM(tarif) FROM transaksi";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getDouble(1);
        }

    } catch (SQLException e) {
    }

    return total;
}
    
    public static double getTotalEmisi() {

    double total = 0;

    try {
        Connection conn = DBConnection.getConnection();

        String sql =
        "SELECT IFNULL(SUM(emisi_hemat),0) FROM transaksi";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            total = rs.getDouble(1);
        }

    } catch (SQLException e) {
    }

    return total;
}
    
    public static ResultSet getAllTransaksi() {

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT * FROM transaksi ORDER BY tanggal DESC";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        return ps.executeQuery();

    } catch (SQLException e) {
    }

    return null;
}
    
    public static boolean deleteTransaksi(int idTransaksi) {

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "DELETE FROM transaksi WHERE id_transaksi=?";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setInt(1, idTransaksi);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
    }

    return false;
}
    
    public static ResultSet searchByTanggal(String tanggal) {

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT t.id_transaksi, " +
        "u.username, " +
        "t.tanggal, " +
        "t.kendaraan, " +
        "t.kategori, " +
        "t.tarif, " +
        "t.emisi_hemat " +
        "FROM transaksi t " +
        "JOIN users u ON t.id_user = u.id_user " +
        "WHERE DATE(t.tanggal)=STR_TO_DATE(?,'%d-%m-%Y')";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setString(1, tanggal);

        return ps.executeQuery();

    } catch(SQLException e) {
        e.printStackTrace();
    }

    return null;
}
    
    public static double getPendapatanHariIni() {

    double total = 0;

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT IFNULL(SUM(tarif),0) " +
        "FROM transaksi " +
        "WHERE DATE(tanggal)=CURDATE()";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()){
            total = rs.getDouble(1);
        }

    } catch(SQLException e){
    }

    return total;
}
    
    public static int getTransaksiHariIni() {

    int total = 0;

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT COUNT(*) " +
        "FROM transaksi " +
        "WHERE DATE(tanggal)=CURDATE()";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()){
            total = rs.getInt(1);
        }

    } catch(SQLException e){
    }

    return total;
}
    
    public static ResultSet getAllTransaksiAdmin() {

      try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
           "SELECT t.id_transaksi, t.tanggal, " +
    "u.username, t.kendaraan, t.kategori, " +
    "t.tarif, t.emisi_hemat " +
    "FROM transaksi t " +
    "JOIN users u ON t.id_user = u.id_user " +
    "ORDER BY t.tanggal DESC";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        return ps.executeQuery();

    } catch(SQLException e) {
        e.printStackTrace();
    }

    return null;
}
    
    public static ResultSet searchByUsername(String username) {

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT t.id_transaksi, " +
        "u.username, " +
        "t.tanggal, " +
        "t.kendaraan, " +
        "t.kategori, " +
        "t.tarif, " +
        "t.emisi_hemat " +
        "FROM transaksi t " +
        "JOIN users u ON t.id_user = u.id_user " +
        "WHERE u.username LIKE ?";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setString(1, "%" + username + "%");

        return ps.executeQuery();

    } catch (SQLException e) {
    }

    return null;
}
    
    public static boolean updateTransaksi(
        int idTransaksi,
        String kendaraan,
        String kategori,
        double tarif) {

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "UPDATE transaksi " +
        "SET kendaraan=?, kategori=?, tarif=? " +
        "WHERE id_transaksi=?";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setString(1, kendaraan);
        ps.setString(2, kategori);
        ps.setDouble(3, tarif);
        ps.setInt(4, idTransaksi);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
    }

    return false;
}
    
    public static ResultSet searchTransaksi(
        String username,
        String tanggal) {

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT t.id_transaksi, " +
        "u.username, " +
        "t.tanggal, " +
        "t.kendaraan, " +
        "t.kategori, " +
        "t.tarif, " +
        "t.emisi_hemat " +
        "FROM transaksi t " +
        "JOIN users u ON t.id_user=u.id_user " +
        "WHERE u.username LIKE ? " +
        "AND DATE(t.tanggal)=?";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ps.setString(1, "%" + username + "%");
        ps.setString(2, tanggal);

        return ps.executeQuery();

    } catch (SQLException e) {
    }

    return null;
}
    
    public static double getPendapatanBulanIni() {

    double total = 0;

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT IFNULL(SUM(tarif),0) " +
        "FROM transaksi " +
        "WHERE MONTH(tanggal)=MONTH(CURDATE()) " +
        "AND YEAR(tanggal)=YEAR(CURDATE())";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()){
            total = rs.getDouble(1);
        }

    } catch(SQLException e){
    }

    return total;
}
    
    public static int getTotalPenumpang() {

    int total = 0;

    try {

        Connection conn =
        DBConnection.getConnection();

        String sql =
        "SELECT COUNT(*) " +
        "FROM users " +
        "WHERE role='penumpang'";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next()){
            total = rs.getInt(1);
        }

    } catch(SQLException e){
    }

    return total;
}
    
    public static ResultSet getRecentTransaksi() {

    try {

        Connection conn = DBConnection.getConnection();

        String sql =
"SELECT t.id_transaksi, t.tanggal, "
+ "u.username, t.kendaraan, "
+ "t.kategori, t.tarif, t.emisi_hemat "
+ "FROM transaksi t "
+ "JOIN users u ON t.id_user=u.id_user "
+ "ORDER BY t.tanggal DESC "
+ "LIMIT 10";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        return ps.executeQuery();

    } catch(SQLException e) {
    }

    return null;
}
    
    public static ResultSet getTopKendaraan() {

    try {

        Connection conn = DBConnection.getConnection();

        String sql =
        "SELECT kendaraan, COUNT(*) AS jumlah "
        + "FROM transaksi "
        + "GROUP BY kendaraan "
        + "ORDER BY jumlah DESC "
        + "LIMIT 1";

        PreparedStatement ps =
        conn.prepareStatement(sql);

        return ps.executeQuery();

    } catch(SQLException e) {
    }

    return null;
}
    

    
}