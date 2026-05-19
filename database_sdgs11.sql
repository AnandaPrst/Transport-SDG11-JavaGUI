DROP DATABASE IF EXISTS db_transport_sdg11;
CREATE DATABASE db_transport_sdg11;
USE db_transport_sdg11;

-- =========================
-- TABLE USERS
-- =========================
CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(100) NOT NULL,
    ROLE ENUM('admin','penumpang') DEFAULT 'penumpang',
    green_points INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLE TRANSAKSI
-- =========================
CREATE TABLE transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    tanggal DATETIME DEFAULT CURRENT_TIMESTAMP,
    kendaraan VARCHAR(50) NOT NULL,
    kategori VARCHAR(20) NOT NULL,
    jarak_km DOUBLE NOT NULL,
    halte INT DEFAULT 0,
    tarif DOUBLE NOT NULL,
    eta_menit DOUBLE NOT NULL,
    emisi_hemat DOUBLE NOT NULL,

    CONSTRAINT fk_transaksi_user FOREIGN KEY (id_user)
        REFERENCES users(id_user)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- =========================
-- INSERT ADMIN DEFAULT
-- =========================
INSERT INTO users(email, username, PASSWORD, ROLE, green_points)
VALUES ('admin@gmail.com', 'admin', 'admin123', 'admin', 0);