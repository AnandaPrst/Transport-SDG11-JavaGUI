/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Admin;
import model.Penumpang;
import model.User;

public class UserManagerDB {

    public static boolean registerPenumpang(String email, String username, String password) {

        String cekSql = "SELECT id_user FROM users WHERE username=? OR email=?";
        String insertSql = "INSERT INTO users(email, username, password, role, green_points) VALUES (?, ?, ?, 'penumpang', 0)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psCek = conn.prepareStatement(cekSql)) {

            psCek.setString(1, username);
            psCek.setString(2, email);

            ResultSet rs = psCek.executeQuery();

            // jika ada data berarti username/email sudah dipakai
            if (rs.next()) {
                return false;
            }

            try (PreparedStatement psInsert = conn.prepareStatement(insertSql)) {
                psInsert.setString(1, email);
                psInsert.setString(2, username);
                psInsert.setString(3, password);

                psInsert.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            System.out.println("Register gagal: " + e.getMessage());
            return false;
        }
    }

    public static User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idUser = rs.getInt("id_user");
                String email = rs.getString("email");
                String role = rs.getString("role");
                int greenPoints = rs.getInt("green_points");

                if (role.equalsIgnoreCase("admin")) {
                    return new Admin(idUser, email, username, password);
                } else {
                    return new Penumpang(idUser, email, username, password, greenPoints);
                }
            }

        } catch (Exception e) {
            System.out.println("Login gagal: " + e.getMessage());
        }

        return null;
    }

    public static boolean updatePassword(String username, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE username=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);

            int hasil = ps.executeUpdate();
            return hasil > 0;

        } catch (Exception e) {
            System.out.println("Update password gagal: " + e.getMessage());
            return false;
        }
    }

    public static void updateGreenPoints(int idUser, int greenPoints) {

        String sql = "UPDATE users SET green_points=? WHERE id_user=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, greenPoints);
            ps.setInt(2, idUser);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Update points gagal: " + e.getMessage());
        }
    }
}