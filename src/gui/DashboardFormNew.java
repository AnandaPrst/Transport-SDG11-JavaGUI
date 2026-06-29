/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.awt.Color;
import org.jfree.chart.block.BlockBorder;
import java.awt.BasicStroke;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Penumpang;
import service.TransaksiManagerDB;

/**
 *
 * @author user
 */
public class DashboardFormNew extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardFormNew.class.getName());

    /**
     * Creates new form DashboardFormNew
     */
    private final Penumpang penumpang;

    public DashboardFormNew(Penumpang penumpang) {
        initComponents();
        this.penumpang = penumpang;
        setLocationRelativeTo(null);
        tampilkanData();
        tampilChartPembayaran();
        tampilChartCarbon();
        tampilLeaderboard();
    }

    private void tampilkanData() {
        JLabelUsername.setText("" + penumpang.getUsername());
        jLabelGreenPoints.setText(penumpang.getGreenPoints() + " PTS");

        double totalBayar = TransaksiManagerDB.totalBiaya(penumpang.getIdUser());
        double totalEmisi = TransaksiManagerDB.totalEmisi(penumpang.getIdUser());

        jLabelTotalPembayaran.setText("Rp " + String.format("%,.0f", totalBayar));
        jLabelCarbonSaving.setText(String.format("%.2f", totalEmisi) + " kg CO₂");
    }
    
    private void tampilChartPembayaran() {
        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        try {
            ResultSet rs
                    = TransaksiManagerDB.getChartPembayaran(
                            penumpang.getIdUser());
            while (rs.next()) {
                dataset.addValue(
                        rs.getDouble("total_pembayaran"),
                        "Pembayaran",
                        rs.getString("tanggal"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFreeChart chart
                = ChartFactory.createLineChart(
                        "Pembayaran Harian",
                        "Tanggal",
                        "Rp",
                        dataset);

        ChartPanel chartPanel
                = new ChartPanel(chart);
        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = chart.getCategoryPlot();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null);

        plot.setRangeGridlinePaint(new Color(230, 230, 230));
        plot.setDomainGridlinePaint(new Color(230, 230, 230));
        
        
        LineAndShapeRenderer renderer
                = (LineAndShapeRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(22, 163, 74));

        renderer.setSeriesStroke(
                0,
                new BasicStroke(3f)
        );

        chart.getLegend().setFrame(BlockBorder.NONE);

        panelChartPembayaran.removeAll();
        panelChartPembayaran.setLayout(new BorderLayout());
        panelChartPembayaran.add(chartPanel);

        panelChartPembayaran.validate();
    }

    private void tampilChartCarbon() {

        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        try {

            ResultSet rs
                    = TransaksiManagerDB.getChartCarbonSaving(
                            penumpang.getIdUser());

            while (rs.next()) {

                dataset.addValue(
                        rs.getDouble("total_emisi"),
                        "Carbon Saving",
                        rs.getString("tanggal"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart
                = ChartFactory.createLineChart(
                        "Carbon Saving",
                        "Tanggal",
                        "Kg CO₂",
                        dataset);

        ChartPanel cp
                = new ChartPanel(chart);
        
        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = chart.getCategoryPlot();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(null);

        plot.setRangeGridlinePaint(new Color(230, 230, 230));
        plot.setDomainGridlinePaint(new Color(230, 230, 230));
        
        LineAndShapeRenderer renderer
                = (LineAndShapeRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(22, 163, 74));

        renderer.setSeriesStroke(
                0,
                new BasicStroke(3f)
        );
        
        chart.setBorderVisible(false);

        plot.setOutlineVisible(false);

        chart.getLegend().setBorder(0, 0, 0, 0);

//        chart.getLegend().setFrame(BlockBorder.NONE);

        panelChartCarbon.removeAll();
        panelChartCarbon.setLayout(new BorderLayout());
        panelChartCarbon.add(cp);

        panelChartCarbon.validate();
    }

    private void tampilLeaderboard() {

        DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();

        try {

            ResultSet rs
                    = TransaksiManagerDB.getLeaderboard();

            while (rs.next()) {

                dataset.addValue(
                        rs.getInt("green_points"),
                        "Points",
                        rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart
                = ChartFactory.createBarChart(
                        "Leaderboard",
                        "User",
                        "Points",
                        dataset);

        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(22, 163, 74));
        plot.setOrientation(
                PlotOrientation.HORIZONTAL);

        ChartPanel cp
                = new ChartPanel(chart);
        
        chart.setBackgroundPaint(Color.WHITE);
        plot.setBackgroundPaint(Color.WHITE);

        plot.setOutlineVisible(false);

        plot.setRangeGridlinePaint(new Color(230, 230, 230));

        plot.setDomainGridlinePaint(new Color(230, 230, 230));

        panelLeaderboard.removeAll();
        panelLeaderboard.setLayout(new BorderLayout());
        panelLeaderboard.add(cp);

        panelLeaderboard.validate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SideBarPanel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnLogout5 = new javax.swing.JButton();
        MenuDashboard = new javax.swing.JPanel();
        lblDashboard5 = new javax.swing.JLabel();
        JLabelUsername = new javax.swing.JLabel();
        jLabelRole = new javax.swing.JLabel();
        MenuHitungTarif = new javax.swing.JPanel();
        lblDashboardHitungTarif = new javax.swing.JLabel();
        jPanelContent = new javax.swing.JPanel();
        jPanelHeaderDashboard = new javax.swing.JPanel();
        jLabelHeading = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Container = new javax.swing.JPanel();
        jPanelCardFrame = new javax.swing.JPanel();
        panelLeaderboard = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabelGreenPoints = new javax.swing.JLabel();
        panelChartCarbon = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelCarbonSaving = new javax.swing.JLabel();
        panelChartPembayaran = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelTotalPembayaran = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel11.setBackground(new java.awt.Color(0, 102, 51));

        btnLogout5.setBackground(new java.awt.Color(255, 0, 0));
        btnLogout5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout5.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout5.setText("Logout");
        btnLogout5.addActionListener(this::btnLogout5ActionPerformed);

        MenuDashboard.setBackground(new java.awt.Color(0, 153, 102));
        MenuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuDashboardMouseClicked(evt);
            }
        });

        lblDashboard5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDashboard5.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboard5.setText("Dashboard");
        lblDashboard5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboard5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuDashboardLayout = new javax.swing.GroupLayout(MenuDashboard);
        MenuDashboard.setLayout(MenuDashboardLayout);
        MenuDashboardLayout.setHorizontalGroup(
            MenuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboard5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuDashboardLayout.setVerticalGroup(
            MenuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuDashboardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDashboard5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JLabelUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLabelUsername.setForeground(new java.awt.Color(255, 255, 255));
        JLabelUsername.setText("Admin");

        jLabelRole.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelRole.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRole.setText("Selamat datang");

        MenuHitungTarif.setBackground(new java.awt.Color(0, 102, 51));
        MenuHitungTarif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuHitungTarifMouseClicked(evt);
            }
        });

        lblDashboardHitungTarif.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDashboardHitungTarif.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboardHitungTarif.setText("Hitung Tarif");
        lblDashboardHitungTarif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboardHitungTarifMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuHitungTarifLayout = new javax.swing.GroupLayout(MenuHitungTarif);
        MenuHitungTarif.setLayout(MenuHitungTarifLayout);
        MenuHitungTarifLayout.setHorizontalGroup(
            MenuHitungTarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuHitungTarifLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboardHitungTarif)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuHitungTarifLayout.setVerticalGroup(
            MenuHitungTarifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuHitungTarifLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDashboardHitungTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MenuDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MenuHitungTarif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(JLabelUsername))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnLogout5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabelRole)))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(JLabelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRole)
                .addGap(17, 17, 17)
                .addComponent(MenuDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuHitungTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                .addComponent(btnLogout5)
                .addContainerGap())
        );

        javax.swing.GroupLayout SideBarPanelLayout = new javax.swing.GroupLayout(SideBarPanel);
        SideBarPanel.setLayout(SideBarPanelLayout);
        SideBarPanelLayout.setHorizontalGroup(
            SideBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        SideBarPanelLayout.setVerticalGroup(
            SideBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(SideBarPanel, java.awt.BorderLayout.WEST);

        jPanelContent.setBackground(new java.awt.Color(240, 244, 241));
        jPanelContent.setPreferredSize(new java.awt.Dimension(700, 508));
        jPanelContent.setLayout(new java.awt.BorderLayout(20, 0));

        jPanelHeaderDashboard.setBackground(java.awt.Color.white);
        jPanelHeaderDashboard.setPreferredSize(new java.awt.Dimension(700, 80));

        jLabelHeading.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelHeading.setForeground(new java.awt.Color(27, 77, 62));
        jLabelHeading.setText("SmartFare Dashboard");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(189, 195, 199));

        javax.swing.GroupLayout jPanelHeaderDashboardLayout = new javax.swing.GroupLayout(jPanelHeaderDashboard);
        jPanelHeaderDashboard.setLayout(jPanelHeaderDashboardLayout);
        jPanelHeaderDashboardLayout.setHorizontalGroup(
            jPanelHeaderDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderDashboardLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabelHeading)
                .addGap(36, 407, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanelHeaderDashboardLayout.setVerticalGroup(
            jPanelHeaderDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHeading)
                .addGap(38, 38, 38)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelContent.add(jPanelHeaderDashboard, java.awt.BorderLayout.NORTH);

        Container.setLayout(new java.awt.BorderLayout());

        jPanelCardFrame.setBackground(new java.awt.Color(244, 246, 249));
        jPanelCardFrame.setPreferredSize(new java.awt.Dimension(700, 200));
        jPanelCardFrame.setLayout(new java.awt.GridLayout(3, 1, 25, 20));

        panelLeaderboard.setBackground(java.awt.Color.red);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Green Points");

        jLabelGreenPoints.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelGreenPoints.setText("Points");

        javax.swing.GroupLayout panelLeaderboardLayout = new javax.swing.GroupLayout(panelLeaderboard);
        panelLeaderboard.setLayout(panelLeaderboardLayout);
        panelLeaderboardLayout.setHorizontalGroup(
            panelLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeaderboardLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGreenPoints)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLeaderboardLayout.setVerticalGroup(
            panelLeaderboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeaderboardLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(52, 52, 52)
                .addComponent(jLabelGreenPoints)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanelCardFrame.add(panelLeaderboard);

        panelChartCarbon.setBackground(java.awt.Color.green);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Carbon Saving");

        jLabelCarbonSaving.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelCarbonSaving.setText("Carbon");

        javax.swing.GroupLayout panelChartCarbonLayout = new javax.swing.GroupLayout(panelChartCarbon);
        panelChartCarbon.setLayout(panelChartCarbonLayout);
        panelChartCarbonLayout.setHorizontalGroup(
            panelChartCarbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartCarbonLayout.createSequentialGroup()
                .addGroup(panelChartCarbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChartCarbonLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabelCarbonSaving))
                    .addGroup(panelChartCarbonLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        panelChartCarbonLayout.setVerticalGroup(
            panelChartCarbonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartCarbonLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(55, 55, 55)
                .addComponent(jLabelCarbonSaving)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanelCardFrame.add(panelChartCarbon);

        panelChartPembayaran.setBackground(java.awt.Color.blue);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Total Pembayaran");

        jLabelTotalPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelTotalPembayaran.setText("Pembayaran");

        javax.swing.GroupLayout panelChartPembayaranLayout = new javax.swing.GroupLayout(panelChartPembayaran);
        panelChartPembayaran.setLayout(panelChartPembayaranLayout);
        panelChartPembayaranLayout.setHorizontalGroup(
            panelChartPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartPembayaranLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelChartPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTotalPembayaran)
                    .addComponent(jLabel7))
                .addContainerGap(540, Short.MAX_VALUE))
        );
        panelChartPembayaranLayout.setVerticalGroup(
            panelChartPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartPembayaranLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jLabelTotalPembayaran)
                .addGap(22, 22, 22))
        );

        jPanelCardFrame.add(panelChartPembayaran);

        Container.add(jPanelCardFrame, java.awt.BorderLayout.CENTER);

        jPanelContent.add(Container, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogout5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogout5ActionPerformed
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogout5ActionPerformed

    private void lblDashboard5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboard5MouseClicked

    }//GEN-LAST:event_lblDashboard5MouseClicked

    private void MenuDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuDashboardMouseClicked
        DashboardFormNew dashboard = new DashboardFormNew(penumpang);
        dashboard.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_MenuDashboardMouseClicked

    private void lblDashboardHitungTarifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardHitungTarifMouseClicked
        HitungTarifFormNew h = new HitungTarifFormNew(penumpang);
        h.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_lblDashboardHitungTarifMouseClicked

    private void MenuHitungTarifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuHitungTarifMouseClicked
        HitungTarifFormNew h = new HitungTarifFormNew(penumpang);
        h.setVisible(true);
        this.dispose();                // TODO add your handling code here:
    }//GEN-LAST:event_MenuHitungTarifMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JLabel JLabelUsername;
    private javax.swing.JPanel MenuDashboard;
    private javax.swing.JPanel MenuHitungTarif;
    private javax.swing.JPanel SideBarPanel;
    private javax.swing.JButton btnLogout5;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCarbonSaving;
    private javax.swing.JLabel jLabelGreenPoints;
    private javax.swing.JLabel jLabelHeading;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelTotalPembayaran;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanelCardFrame;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelHeaderDashboard;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDashboard5;
    private javax.swing.JLabel lblDashboardHitungTarif;
    private javax.swing.JPanel panelChartCarbon;
    private javax.swing.JPanel panelChartPembayaran;
    private javax.swing.JPanel panelLeaderboard;
    // End of variables declaration//GEN-END:variables
}
