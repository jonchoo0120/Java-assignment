/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Class.Order;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class AcceptTask extends javax.swing.JFrame {

    /**
     * Creates new form AcceptTask
     */
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private ArrayList<Order> orderList = Order.loadorders();
    private String curRunner = "E01";
    
    public AcceptTask() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        tableModel = Order.startAcceptTable(orderList, curRunner);
        jTable1.setModel(tableModel);
        jTable1.setDefaultEditor(Object.class, null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        accept = new javax.swing.JButton();
        decline = new javax.swing.JButton();
        saveAndExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "OrderID", "Address", "Customer Name", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        accept.setText("Accept");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        decline.setText("Decline");
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });

        saveAndExit.setText("Save & Exit");
        saveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveAndExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(decline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(accept)
                .addGap(72, 72, 72)
                .addComponent(decline)
                .addGap(67, 67, 67)
                .addComponent(saveAndExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
       int row = jTable1.getSelectedRow();
       if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please selected an order to accept!");
            return; //popup pls select a columnmm 
       }
       String orderID = jTable1.getValueAt(row, 0).toString();
        tableModel.removeRow(row);
       
       Order order= Order.getOrderByID(orderID,Order.getOrderList());
       order.setStatus("waiting_restaurant");
       Order.saveToFile(Order.getOrderList());
       JOptionPane.showMessageDialog(this, "Accepted!");
    }//GEN-LAST:event_acceptActionPerformed

    private void saveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndExitActionPerformed
        MenuGUI menuGUI = new MenuGUI();
        dispose();
    }//GEN-LAST:event_saveAndExitActionPerformed

    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
       int row = jTable1.getSelectedRow();
       if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please selected an order to decline!");
            return; //popup pls select a columnmm 
       }
       String orderID = jTable1.getValueAt(row, 0).toString();
        tableModel.removeRow(row);
       
       Order order= Order.getOrderByID(orderID,Order.getOrderList());
       //Order.getRandomRunner(curRunner);
       Order.saveToFile(Order.getOrderList());
       JOptionPane.showMessageDialog(this, "Declined!");
    }//GEN-LAST:event_declineActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JButton decline;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton saveAndExit;
    // End of variables declaration//GEN-END:variables
}
