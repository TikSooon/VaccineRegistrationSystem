/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccineregistrationsystem;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vaccineregistrationsystem.VaccineRegistrationSystem.AccessMode;

/**
 *
 * @author DELL
 */
public class ManageAppointmentPage extends javax.swing.JFrame {

    /**
     * Creates new form ManageAppointment
     */
    private ArrayList<Appointment> appointmentList;
    private String searchIC;
    
    public ManageAppointmentPage() {
        initComponents();
        this.setVisible(true);
        
       
    }
    
    private void displayData(){
        DefaultTableModel model = (DefaultTableModel) tableAppointment.getModel();
       
        if(searchIC == null){
            appointmentList = Appointment.getAllAppointments();
        }else{
           appointmentList = Appointment.searchbyPeoplePartialICNo(searchIC);
        }
       
        model.setRowCount(0);
       
        for(int i = 0; i < appointmentList.size() ; i++){
            Appointment appointment = appointmentList.get(i);
            String ID = String.valueOf(appointment.getAppointmentID());
            String date = UIUtils.formatDate(appointment.getDate());
            String time = appointment.getTime();
            
            int peopleID = appointment.getPeopleID();
            People p = People.getPeopleByID(peopleID);
            String ic = p.getNric();
            String name = p.getName();
            
            int centreID = appointment.getCentreID();
            String centreName = Centre.searchByID(centreID).getCentreName();
            
            String status = appointment.getStatus();
         
            model.addRow(new Object[]{ID,date,time,name,ic,centreName, status}); 
        }
        
        tableAppointment.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAppointment = new javax.swing.JTable();
        btnView = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnViewAll = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Appointment");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Manage Appointment");

        tableAppointment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Date", "Time", "Name", "IC/ Passport No", "Centre", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableAppointment);

        btnView.setBackground(new java.awt.Color(93, 93, 90));
        btnView.setForeground(new java.awt.Color(255, 255, 255));
        btnView.setText("View");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(93, 93, 90));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(93, 93, 90));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(93, 93, 90));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("IC/Passport No:");

        btnSearch.setBackground(new java.awt.Color(93, 93, 90));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnViewAll.setBackground(new java.awt.Color(93, 93, 90));
        btnViewAll.setForeground(new java.awt.Color(255, 255, 255));
        btnViewAll.setText("View All");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vaccineregistrationsystem/images/appointment-icon.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAdd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnView)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDelete))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnViewAll))
                            .addComponent(jLabel1))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnViewAll))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnView)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
       int appointmentID = Appointment.getNextID();
       AppointmentDetailPage page = new AppointmentDetailPage(AccessMode.NEW, appointmentID);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
       int appointmentID = getSelectedAppointmentID();
       if(appointmentID == -1){
           return ;
       }
       AppointmentDetailPage page = new AppointmentDetailPage(AccessMode.VIEW, appointmentID);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       int appointmentID = getSelectedAppointmentID();
       if(appointmentID == -1){
           return ;
       }
       if(!appointmentIsPending(appointmentID)){
            JOptionPane.showMessageDialog(null, "This appointment cannot be edited. It is either cancelled or completed.");
            return;
       }
       
       AppointmentDetailPage page = new AppointmentDetailPage(AccessMode.EDIT, appointmentID);
    }//GEN-LAST:event_btnEditActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        displayData();
    }//GEN-LAST:event_formWindowActivated

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
       int confirmDelete = JOptionPane.showConfirmDialog(null, "Confirm delete this appointment record");
        
       if(confirmDelete != JOptionPane.OK_OPTION){
           return ;
       }
       
       int appointmentID = getSelectedAppointmentID();
       if(appointmentID == -1){
           return;
       }
       
       boolean result = Appointment.deleteAppointment(appointmentID);
       
       if(!result){
           JOptionPane.showMessageDialog(null, 
                   "Deletion Failed. Appointment has been completed", "", JOptionPane.ERROR_MESSAGE);

       }else{
           displayData();
       }
       
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchIC = txtSearch.getText().trim();
        displayData();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed
        // TODO add your handling code here:
        searchIC = null;
        txtSearch.setText("");
        displayData();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private boolean appointmentIsPending(int appointmentID){
        Appointment app = Appointment.searchbyAppointmentID(appointmentID);
        
        return app.getStatus().equals("Pending");
    }
    
    private int getSelectedAppointmentID(){
        int column = 0;
        int row = tableAppointment.getSelectedRow();
        
        if(row != -1){
            String value = tableAppointment.getModel().getValueAt(row, column).toString();
            return Integer.parseInt(value);
        }else{
            JOptionPane.showMessageDialog(null, "Please select an appointment first");
        }
        return -1;
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageAppointmentPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnView;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAppointment;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
