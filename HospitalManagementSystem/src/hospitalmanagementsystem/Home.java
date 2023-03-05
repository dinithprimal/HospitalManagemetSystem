/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmanagementsystem;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dinith
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        showTime();
        getConnection();
        //jButtonAdmission.setBackground(Color.DARK_GRAY);
        
        
    }
    
    
    
    private Connection con;
    PreparedStatement pst;
    int admissionSaveOption = 0;
    int InOutPatient = 0;
    int DoctorCahnnel = 1;
    int BedRoom = 0;
    
    void getConnection(){
    
        con = new DBConnector().giveConnection();
        
        System.out.println(con);
        
    }
    
    void wardNoCombo(){
        
        String av ="Available";
        
        jComboBoxAdmissionWardNo.removeAllItems();
        jComboBoxAdmissionWardNo.addItem("Select Ward");
        
        try {
            
            String sql = "SELECT DISTINCT ward_wordno FROM bed WHERE Availability = '"+av+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                jComboBoxAdmissionWardNo.addItem(rs.getString("ward_wordno"));
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }
    
    void roomNoCombo(){
        
        String av ="Available";
        
        jComboBoxAdmissionRoomNo.removeAllItems();
        jComboBoxAdmissionRoomNo.addItem("Select Room");
        
        try {
            
            String sql = "SELECT roomno FROM room WHERE Availability = '"+av+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                jComboBoxAdmissionRoomNo.addItem(rs.getString("roomno"));
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }
    
    String GuardianID(){
    
        String guardianID = "";
        
        try{
        
            String str = getDate();
            
            String sql = "SELECT COUNT(*) FROM guardian";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            int count;
            if(rs.next()){
                
                count = Integer.parseInt(rs.getString("COUNT(*)"));
                
                count++;
                System.out.println(count);
                str = str.replaceAll("-", "");
                
                guardianID = str+count;
                
            }else{
                
                JOptionPane.showMessageDialog(this, "Coudn't Generate Admission ID\nPlease Click \"Admission\" Again", "Warning", JOptionPane.WARNING_MESSAGE);
            
            }
            
            
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        return guardianID;
    }
    
    String createAdmissionID(){
        
        String admissionID = "";
        
        try{
        
            String str = getDate();
            
            
            
            
            String sql = "SELECT COUNT(*) FROM admission WHERE admissiondate = '"+str+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            int count;
            if(rs.next()){
                
                count = Integer.parseInt(rs.getString("COUNT(*)"));
                
                count++;
                System.out.println(count);
                str = str.replaceAll("-", "");
                
                admissionID = str+"HMS"+count;
                
            }else{
                
                JOptionPane.showMessageDialog(this, "Coudn't Generate Admission ID\nPlease Click \"Admission\" Again", "Warning", JOptionPane.WARNING_MESSAGE);
            
            }
            
            
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        return admissionID;
        
    }
    
    String getDate(){
    
        String date = labelDate.getText();
        date = date.replaceAll(" ", "");
        
        return date;
        
    }
    
    
    void showTime(){
        
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh : mm : ss  a");
                SimpleDateFormat sd = new SimpleDateFormat("yyyy - MM - dd");
                labelTime.setText(s.format(d));
                labelDate.setText(sd.format(d));
            }
        }).start();
        
    }
    
    /*void enebleDoctorAddButton(){
        
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println("hi");
                if (jTextFieldDoctorRegNo.getText()==null|jTextFieldDoctorFirstName.getText()==null|
                        jTextFieldDoctorSecondName.getText()==null|jTextFieldDoctorSpeciality.getName()==null|
                        /*jTextAreaDoctorAddress.getText()==null|jTextFieldDoctorTP1.getText()==null|
                        jTextFieldDoctorTP2.getText()==null){
                    
                    jButtonAddDoctor.setEnabled(false);
                
                }else{
                    
                    jButtonAddDoctor.setEnabled(true);
                    
                }
                try {
                    //wait();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    
    }**/
    
    void closeWindow(){
        
        WindowEvent windowClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        buttonLogOut = new javax.swing.JButton();
        buttonMinimize = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButtonAdmission = new javax.swing.JButton();
        jButtonDiognosis = new javax.swing.JButton();
        jButtonPatient = new javax.swing.JButton();
        jButtonPayments = new javax.swing.JButton();
        jButtonDoctor = new javax.swing.JButton();
        jButtonTreatment = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        parentPannel = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        admissionPanel = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jCheckBoxAdmissionInPatient = new javax.swing.JCheckBox();
        jCheckBoxAdmissionOutPatient = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldAdmissionPatientNIC = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldAdmissionPatientFirstName = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldAdmissionPatientSecondName = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaAdmissionPatientAddress = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldAdmissionPatientTP1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldAdmissionPatientTP2 = new javax.swing.JTextField();
        jTextFieldAdmissionPatientDateofBirth = new javax.swing.JTextField();
        jTextFieldAdmissionPatientAge = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldAdmissionDoctorName = new javax.swing.JTextField();
        jCheckBoxAdmissionDoctorChannel = new javax.swing.JCheckBox();
        jButtonAdmissionSearchDoctor = new javax.swing.JButton();
        jCheckBoxAdmissionWardSelect = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        jComboBoxAdmissionWardNo = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        jComboBoxAdmissionWardBedNo = new javax.swing.JComboBox();
        jCheckBoxAdmissionRoomSelect = new javax.swing.JCheckBox();
        jLabel39 = new javax.swing.JLabel();
        jComboBoxAdmissionRoomNo = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        jComboBoxAdmissionRoomPrivacy = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldAdmissionDoctorRegNo = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldAdmissionGuardianName = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaAdmissionGuardianAddress = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        jTextFieldAdmissionGuardianRelationship = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldAdmissionGurdianTP = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabelAdmissionID = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButtonAdmissionSave = new javax.swing.JButton();
        jTextFieldAdmissionReson = new javax.swing.JTextField();
        diognosisPanel = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jPanel56 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel58 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        ptientPanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPatientNIC = new javax.swing.JTextField();
        jTextFieldPatientFirstName = new javax.swing.JTextField();
        jTextFieldPatientSecondName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPatientAddress = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPatientTP1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldPatientTP2 = new javax.swing.JTextField();
        jButtonPatientEdit = new javax.swing.JButton();
        buttonAddNewPatient = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButtonAddPatient = new javax.swing.JButton();
        jButtonPatientUpdate = new javax.swing.JButton();
        jButtonPatientDelete = new javax.swing.JButton();
        jButtonPatientCancel = new javax.swing.JButton();
        jTextFieldPatientDateofBirth = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPatientAge = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldPatientSearchName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldPatientSearchNIC = new javax.swing.JTextField();
        jLabelPatientViewRowCount = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableViewPatient = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox<>();
        paymentDetailsPanel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel73 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel74 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel75 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        doctorPanel = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jTextFieldDoctorRegNo = new javax.swing.JTextField();
        jTextFieldDoctorFirstName = new javax.swing.JTextField();
        jTextFieldDoctorSecondName = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jTextFieldDoctorTP1 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jTextFieldDoctorTP2 = new javax.swing.JTextField();
        jButtonDoctorEdit = new javax.swing.JButton();
        buttonAddNewDoctor = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jButtonAddDoctor = new javax.swing.JButton();
        jButtonDoctorUpdate = new javax.swing.JButton();
        jButtonDoctorDelete = new javax.swing.JButton();
        jButtonDoctorCancel = new javax.swing.JButton();
        jTextFieldDoctorSpeciality = new javax.swing.JTextField();
        jComboBoxDoctorJobType = new javax.swing.JComboBox();
        jLabel84 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaDoctorAddress = new javax.swing.JTextArea();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jTextFieldDoctorSearchName = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jTextFieldDoctorSearchRegNo = new javax.swing.JTextField();
        jLabelDoctorViewRowCount = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableDoctorView = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        treatmentPanel = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jTextFieldTreatmentCode = new javax.swing.JTextField();
        jTextFieldTreatmentName = new javax.swing.JTextField();
        jTextFieldTreatmentUnitPrice = new javax.swing.JTextField();
        jButtonTreatmentEdit = new javax.swing.JButton();
        buttonAddNewTreatment = new javax.swing.JButton();
        jPanel66 = new javax.swing.JPanel();
        jButtonTreatmntAddTreatment = new javax.swing.JButton();
        jButtonTreatmentUpdate = new javax.swing.JButton();
        jButtonTreatmentDelete = new javax.swing.JButton();
        jButtonTreatmentCancel = new javax.swing.JButton();
        jLabel115 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaTreatmentDescription = new javax.swing.JTextArea();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jTextFieldTreatmentSearchName = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jTextFieldTreatmentSearchCode = new javax.swing.JTextField();
        jLabelTreatmentViewRowCount = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableTreatmentView = new javax.swing.JTable();
        jPanel70 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        labelDate = new javax.swing.JLabel();
        labelTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setText("Hospital Management System");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        buttonLogOut.setBackground(new java.awt.Color(204, 0, 0));
        buttonLogOut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonLogOut.setText("Log Out");
        buttonLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        buttonMinimize.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonMinimize.setText("Minimize");
        buttonMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMinimizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButtonAdmission.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAdmission.setText("Admission");
        jButtonAdmission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAdmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdmissionActionPerformed(evt);
            }
        });

        jButtonDiognosis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonDiognosis.setText("Diognosis");
        jButtonDiognosis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDiognosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDiognosisActionPerformed(evt);
            }
        });

        jButtonPatient.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonPatient.setText("Patient");
        jButtonPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientActionPerformed(evt);
            }
        });

        jButtonPayments.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonPayments.setText("Payments");
        jButtonPayments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPaymentsActionPerformed(evt);
            }
        });

        jButtonDoctor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonDoctor.setText("Doctor");
        jButtonDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorActionPerformed(evt);
            }
        });

        jButtonTreatment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonTreatment.setText("Treatment");
        jButtonTreatment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTreatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Ward/Room/Bed");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton39.setText("User Acounts");

        jButton40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton40.setText("Reports");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAdmission, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jButtonDiognosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPayments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdmission, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDiognosis, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        parentPannel.setBackground(new java.awt.Color(255, 255, 255));
        parentPannel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        parentPannel.setLayout(new java.awt.CardLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("WELCOME");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
        );

        parentPannel.add(jPanel60, "card8");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("Admission");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient's Details"));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Admission Type :");

        buttonGroup1.add(jCheckBoxAdmissionInPatient);
        jCheckBoxAdmissionInPatient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBoxAdmissionInPatient.setText("In Patient");
        jCheckBoxAdmissionInPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdmissionInPatientActionPerformed(evt);
            }
        });

        buttonGroup1.add(jCheckBoxAdmissionOutPatient);
        jCheckBoxAdmissionOutPatient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBoxAdmissionOutPatient.setText("Out Patient");
        jCheckBoxAdmissionOutPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdmissionOutPatientActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Patient's NIC No. :");

        jTextFieldAdmissionPatientNIC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAdmissionPatientNICFocusLost(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("First Name :");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Second Name :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Date of Birth :");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Age :");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Address :");

        jTextAreaAdmissionPatientAddress.setColumns(20);
        jTextAreaAdmissionPatientAddress.setRows(5);
        jScrollPane3.setViewportView(jTextAreaAdmissionPatientAddress);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Telephone 1 :");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Telephone 2 :");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                            .addComponent(jCheckBoxAdmissionInPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jCheckBoxAdmissionOutPatient)
                            .addGap(2, 2, 2))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldAdmissionPatientSecondName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextFieldAdmissionPatientFirstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAdmissionPatientNIC, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldAdmissionPatientTP2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jTextFieldAdmissionPatientTP1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldAdmissionPatientAge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jTextFieldAdmissionPatientDateofBirth, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jCheckBoxAdmissionOutPatient)
                    .addComponent(jCheckBoxAdmissionInPatient))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextFieldAdmissionPatientNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextFieldAdmissionPatientFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextFieldAdmissionPatientSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextFieldAdmissionPatientDateofBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextFieldAdmissionPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextFieldAdmissionPatientTP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextFieldAdmissionPatientTP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Admit Details"));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Doctor Name :");

        jTextFieldAdmissionDoctorName.setEnabled(false);

        jCheckBoxAdmissionDoctorChannel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBoxAdmissionDoctorChannel.setText("Channeling a Doctor");
        jCheckBoxAdmissionDoctorChannel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdmissionDoctorChannelActionPerformed(evt);
            }
        });

        jButtonAdmissionSearchDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonAdmissionSearchDoctor.setText("Search Doctor");
        jButtonAdmissionSearchDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAdmissionSearchDoctor.setEnabled(false);
        jButtonAdmissionSearchDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdmissionSearchDoctorActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCheckBoxAdmissionWardSelect);
        jCheckBoxAdmissionWardSelect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBoxAdmissionWardSelect.setText("Ward Details");
        jCheckBoxAdmissionWardSelect.setEnabled(false);
        jCheckBoxAdmissionWardSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdmissionWardSelectActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Ward No. :");

        jComboBoxAdmissionWardNo.setToolTipText("");
        jComboBoxAdmissionWardNo.setEnabled(false);
        jComboBoxAdmissionWardNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAdmissionWardNoItemStateChanged(evt);
            }
        });
        jComboBoxAdmissionWardNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxAdmissionWardNoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBoxAdmissionWardNoMouseReleased(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Bed No. :");

        jComboBoxAdmissionWardBedNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Bed" }));
        jComboBoxAdmissionWardBedNo.setEnabled(false);

        buttonGroup2.add(jCheckBoxAdmissionRoomSelect);
        jCheckBoxAdmissionRoomSelect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBoxAdmissionRoomSelect.setText("Room Details");
        jCheckBoxAdmissionRoomSelect.setEnabled(false);
        jCheckBoxAdmissionRoomSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdmissionRoomSelectActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Room No. :");

        jComboBoxAdmissionRoomNo.setEnabled(false);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Privacy Level :");

        jComboBoxAdmissionRoomPrivacy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "VIP", "VVIP" }));
        jComboBoxAdmissionRoomPrivacy.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Doctor Reg. No :");

        jTextFieldAdmissionDoctorRegNo.setEnabled(false);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAdmissionWardNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAdmissionWardBedNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAdmissionRoomNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAdmissionRoomPrivacy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAdmissionSearchDoctor))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jCheckBoxAdmissionRoomSelect)
                            .addComponent(jCheckBoxAdmissionWardSelect)
                            .addComponent(jCheckBoxAdmissionDoctorChannel)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdmissionDoctorName, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextFieldAdmissionDoctorRegNo))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxAdmissionDoctorChannel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextFieldAdmissionDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldAdmissionDoctorRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jButtonAdmissionSearchDoctor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxAdmissionWardSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jComboBoxAdmissionWardNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBoxAdmissionWardBedNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAdmissionRoomSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jComboBoxAdmissionRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jComboBoxAdmissionRoomPrivacy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Guardian's Details"));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Guardian Name :");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Address :");

        jTextAreaAdmissionGuardianAddress.setColumns(20);
        jTextAreaAdmissionGuardianAddress.setRows(5);
        jScrollPane4.setViewportView(jTextAreaAdmissionGuardianAddress);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Relationship to Patient :");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Telephone No. :");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdmissionGuardianName, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextFieldAdmissionGuardianRelationship)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAdmissionGurdianTP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextFieldAdmissionGuardianName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextFieldAdmissionGuardianRelationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextFieldAdmissionGurdianTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Admission"));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Admission ID :");

        jLabelAdmissionID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelAdmissionID.setText("jLabel46");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Reason :");

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setText("Cancel");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButtonAdmissionSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonAdmissionSave.setText("Save");
        jButtonAdmissionSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAdmissionSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdmissionSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jButtonAdmissionSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdmissionSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel47))
                .addGap(52, 52, 52)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelAdmissionID, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jTextFieldAdmissionReson))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(jLabelAdmissionID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jTextFieldAdmissionReson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout admissionPanelLayout = new javax.swing.GroupLayout(admissionPanel);
        admissionPanel.setLayout(admissionPanelLayout);
        admissionPanelLayout.setHorizontalGroup(
            admissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        admissionPanelLayout.setVerticalGroup(
            admissionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admissionPanelLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parentPannel.add(admissionPanel, "card3");

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel92.setText("Diognosis");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setText("Invoice ID :");

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel94.setText("jLabel50");

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel95.setText("Patient ID :");

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel96.setText("jLabel52");

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton29.setText("Search");
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setText("Patient Name :");

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel98.setText("jLabel54");

        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel117.setText("Admission ID :");

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel118.setText("jLabel118");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel93)
                .addGap(18, 18, 18)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel95)
                .addGap(18, 18, 18)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel97)
                .addGap(18, 18, 18)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel117)
                .addGap(18, 18, 18)
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton29)
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(jLabel94)
                    .addComponent(jLabel95)
                    .addComponent(jLabel96)
                    .addComponent(jLabel97)
                    .addComponent(jLabel98)
                    .addComponent(jButton29)
                    .addComponent(jLabel117)
                    .addComponent(jLabel118))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Treatment"));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setText("Treatment Code :");

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel100.setText("Treatment Name :");

        jTextField29.setText("jTextField29");

        jTextField30.setText("jTextField30");

        jButton30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton30.setText("Search");
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel101.setText("Quantity :");

        jTextField31.setText("jTextField31");

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel102.setText("Remark :");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane9.setViewportView(jTextArea6);

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel103.setText("Amount :");

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel104.setText("jLabel104");

        jButton31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton31.setText("Cancel");
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton32.setText("Add");
        jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel99)
                            .addComponent(jLabel100)
                            .addComponent(jLabel101))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField30, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jTextField29)
                            .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton30)
                .addGap(18, 18, 18)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104))
                .addGap(18, 18, 18)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel105.setText("Net Amount :");

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel106.setText("jLabel106");

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel105)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105)
                    .addComponent(jLabel106))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton33.setText("Save");
        jButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout diognosisPanelLayout = new javax.swing.GroupLayout(diognosisPanel);
        diognosisPanel.setLayout(diognosisPanelLayout);
        diognosisPanelLayout.setHorizontalGroup(
            diognosisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        diognosisPanelLayout.setVerticalGroup(
            diognosisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diognosisPanelLayout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parentPannel.add(diognosisPanel, "card6");

        ptientPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Patients");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Patient's NIC No :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("First Name :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Second Name :");

        jTextFieldPatientNIC.setEnabled(false);

        jTextFieldPatientFirstName.setEnabled(false);

        jTextFieldPatientSecondName.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Date of Birth :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Address :");

        jTextAreaPatientAddress.setColumns(20);
        jTextAreaPatientAddress.setRows(5);
        jTextAreaPatientAddress.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaPatientAddress);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Telephone 1 :");

        jTextFieldPatientTP1.setEnabled(false);
        jTextFieldPatientTP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPatientTP1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Telephone 2 :");

        jTextFieldPatientTP2.setEnabled(false);

        jButtonPatientEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonPatientEdit.setText("Edit");
        jButtonPatientEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPatientEdit.setEnabled(false);
        jButtonPatientEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientEditActionPerformed(evt);
            }
        });

        buttonAddNewPatient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonAddNewPatient.setText("Add New");
        buttonAddNewPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonAddNewPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewPatientActionPerformed(evt);
            }
        });

        jButtonAddPatient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonAddPatient.setText("Add");
        jButtonAddPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAddPatient.setEnabled(false);
        jButtonAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPatientActionPerformed(evt);
            }
        });

        jButtonPatientUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonPatientUpdate.setText("Update");
        jButtonPatientUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPatientUpdate.setEnabled(false);
        jButtonPatientUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientUpdateActionPerformed(evt);
            }
        });

        jButtonPatientDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonPatientDelete.setText("Delete");
        jButtonPatientDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPatientDelete.setEnabled(false);
        jButtonPatientDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientDeleteActionPerformed(evt);
            }
        });

        jButtonPatientCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonPatientCancel.setText("Cancel");
        jButtonPatientCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPatientCancel.setEnabled(false);
        jButtonPatientCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPatientCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAddPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPatientCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPatientDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButtonPatientUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPatientUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPatientDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPatientCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextFieldPatientDateofBirth.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Age");

        jTextFieldPatientAge.setEnabled(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonAddNewPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldPatientTP2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                        .addComponent(jTextFieldPatientTP1, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPatientFirstName)
                                    .addComponent(jTextFieldPatientSecondName)
                                    .addComponent(jTextFieldPatientNIC)
                                    .addComponent(jTextFieldPatientDateofBirth)
                                    .addComponent(jTextFieldPatientAge))))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPatientEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPatientEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddNewPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldPatientNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPatientFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPatientSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldPatientDateofBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPatientTP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPatientTP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Search By"));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Name :");

        jTextFieldPatientSearchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPatientSearchNameKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("NIC :");

        jTextFieldPatientSearchNIC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldPatientSearchNICMouseClicked(evt);
            }
        });
        jTextFieldPatientSearchNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPatientSearchNICKeyReleased(evt);
            }
        });

        jLabelPatientViewRowCount.setText(" ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(55, 55, 55)
                .addComponent(jTextFieldPatientSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(55, 55, 55)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelPatientViewRowCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPatientSearchNIC, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldPatientSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldPatientSearchNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelPatientViewRowCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableViewPatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient's NIC", "First Name", "Second Name", "Date of Birth", "Age", "Address", "T.P. 1", "T.P. 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableViewPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableViewPatientMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableViewPatient);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Details"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Check In Date :");

        jLabel15.setText("jLabel15");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Ward No :");

        jLabel17.setText("jLabel17");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Bed No :");

        jLabel19.setText("jLabel19");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Admission ID :");

        jLabel22.setText("jLabel22");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Patient's NIC :");

        jLabel24.setText("jLabel24");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Check Out Date :");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setText("View Payment Details");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 0, 0));
        jButton14.setText("Check Out");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel20))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addGap(18, 23, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout ptientPanelLayout = new javax.swing.GroupLayout(ptientPanel);
        ptientPanel.setLayout(ptientPanelLayout);
        ptientPanelLayout.setHorizontalGroup(
            ptientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ptientPanelLayout.setVerticalGroup(
            ptientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptientPanelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parentPannel.add(ptientPanel, "card2");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel48.setText("Payment Details");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Invoice ID :");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("jLabel50");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Patient ID :");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("jLabel52");

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setText("Search");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Patient Name :");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("jLabel54");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(18, 18, 18)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(jLabel53)
                .addGap(18, 18, 18)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jButton18)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54)
                    .addComponent(jButton18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Treatment"));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Treatment Code :");

        jTextField17.setText("jTextField17");

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setText("Search");
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Treatment Name :");

        jTextField18.setText("jTextField18");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Quantity :");

        jTextField19.setText("jTextField19");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Amount :");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("jLabel59");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Unit Price :");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("jLabel61");

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setText("Cancel");
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton21.setText("ADD");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton19))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jTextField18)
                            .addComponent(jTextField17))))
                .addGap(15, 15, 15))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder("Payments"));

        jPanel39.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel64.setText(" Total :");

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("jLabel63");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel66.setText("Net Amount :");

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel65.setText("jLabel65");

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setText("Payed Amount :");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("jLabel68");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 51, 51));
        jLabel69.setText("Balance :");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 51, 51));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("jLabel70");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel69)
                    .addComponent(jLabel67)
                    .addComponent(jLabel64)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel63))
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jLabel65))
                .addGap(18, 18, 18)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70))
                .addContainerGap())
        );

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("Payment ID :");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("jLabel71");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setText("Payment Type :");

        buttonGroup3.add(jCheckBox6);
        jCheckBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox6.setText("Full Payment");

        buttonGroup3.add(jCheckBox7);
        jCheckBox7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox7.setText("Sub Payment");

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("Payment Method :");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText("Discount for Total :");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("Amount :");

        jTextField20.setText("jTextField20");

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton22.setText("Cancel");
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton23.setText("Procead");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73)
                    .addComponent(jLabel62)
                    .addComponent(jLabel75))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jLabel72)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox6))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout paymentDetailsPanelLayout = new javax.swing.GroupLayout(paymentDetailsPanel);
        paymentDetailsPanel.setLayout(paymentDetailsPanelLayout);
        paymentDetailsPanelLayout.setHorizontalGroup(
            paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paymentDetailsPanelLayout.setVerticalGroup(
            paymentDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentDetailsPanelLayout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parentPannel.add(paymentDetailsPanel, "card4");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel78.setText("Doctor");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));

        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel47.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel47FocusLost(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("Doctor Reg. No :");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("First Name :");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setText("Second Name :");

        jTextFieldDoctorRegNo.setEnabled(false);

        jTextFieldDoctorFirstName.setEnabled(false);
        jTextFieldDoctorFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDoctorFirstNameActionPerformed(evt);
            }
        });

        jTextFieldDoctorSecondName.setEnabled(false);

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel82.setText("Speciality :");

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setText("Job Type :");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel85.setText("Telephone 1 :");

        jTextFieldDoctorTP1.setEnabled(false);
        jTextFieldDoctorTP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDoctorTP1ActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel86.setText("Telephone 2 :");

        jTextFieldDoctorTP2.setEnabled(false);

        jButtonDoctorEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonDoctorEdit.setText("Edit");
        jButtonDoctorEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDoctorEdit.setEnabled(false);
        jButtonDoctorEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorEditActionPerformed(evt);
            }
        });

        buttonAddNewDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonAddNewDoctor.setText("Add New");
        buttonAddNewDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonAddNewDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewDoctorActionPerformed(evt);
            }
        });

        jButtonAddDoctor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonAddDoctor.setText("Add");
        jButtonAddDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAddDoctor.setEnabled(false);
        jButtonAddDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDoctorActionPerformed(evt);
            }
        });

        jButtonDoctorUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonDoctorUpdate.setText("Update");
        jButtonDoctorUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDoctorUpdate.setEnabled(false);
        jButtonDoctorUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorUpdateActionPerformed(evt);
            }
        });

        jButtonDoctorDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonDoctorDelete.setText("Delete");
        jButtonDoctorDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDoctorDelete.setEnabled(false);
        jButtonDoctorDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorDeleteActionPerformed(evt);
            }
        });

        jButtonDoctorCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonDoctorCancel.setText("Cancel");
        jButtonDoctorCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonDoctorCancel.setEnabled(false);
        jButtonDoctorCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAddDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDoctorCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jButtonDoctorDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButtonDoctorUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDoctorUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDoctorDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDoctorCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextFieldDoctorSpeciality.setEnabled(false);

        jComboBoxDoctorJobType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Staff", "Visiting" }));
        jComboBoxDoctorJobType.setEnabled(false);

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setText("Address :");

        jTextAreaDoctorAddress.setColumns(20);
        jTextAreaDoctorAddress.setRows(5);
        jTextAreaDoctorAddress.setEnabled(false);
        jScrollPane7.setViewportView(jTextAreaDoctorAddress);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(jLabel79)
                            .addComponent(jLabel81)
                            .addComponent(jLabel82)
                            .addComponent(jLabel83)
                            .addComponent(jLabel85)
                            .addComponent(jLabel86)
                            .addComponent(jLabel84)))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonAddNewDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDoctorFirstName)
                                    .addComponent(jTextFieldDoctorSecondName)
                                    .addComponent(jTextFieldDoctorRegNo)
                                    .addComponent(jTextFieldDoctorSpeciality)
                                    .addComponent(jComboBoxDoctorJobType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldDoctorTP2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                        .addComponent(jTextFieldDoctorTP1, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDoctorEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDoctorEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddNewDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(jTextFieldDoctorRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(jTextFieldDoctorFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jTextFieldDoctorSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jTextFieldDoctorSpeciality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(jComboBoxDoctorJobType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDoctorTP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85))
                .addGap(18, 18, 18)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDoctorTP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addGap(18, 18, 18)
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel49.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder("Search By"));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setText("Name :");

        jTextFieldDoctorSearchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDoctorSearchNameKeyReleased(evt);
            }
        });

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setText("Reg. No :");

        jTextFieldDoctorSearchRegNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDoctorSearchRegNoKeyReleased(evt);
            }
        });

        jLabelDoctorViewRowCount.setText(" ");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87)
                .addGap(55, 55, 55)
                .addComponent(jTextFieldDoctorSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel88)
                .addGap(55, 55, 55)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelDoctorViewRowCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDoctorSearchRegNo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(jTextFieldDoctorSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88)
                    .addComponent(jTextFieldDoctorSearchRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelDoctorViewRowCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTableDoctorView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reg. No", "First Name", "Second Name", "Specialty", "Job Type", "Address", "T.P. 1", "T.P. 1"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDoctorView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDoctorViewMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableDoctorView);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel90.setText("Join Date :");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("jLabel76");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText("Availability :");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setText("jLabel91");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90)
                .addGap(57, 57, 57)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel77)
                .addGap(57, 57, 57)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(jLabel76)
                    .addComponent(jLabel91)
                    .addComponent(jLabel77))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout doctorPanelLayout = new javax.swing.GroupLayout(doctorPanel);
        doctorPanel.setLayout(doctorPanelLayout);
        doctorPanelLayout.setHorizontalGroup(
            doctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        doctorPanelLayout.setVerticalGroup(
            doctorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorPanelLayout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parentPannel.add(doctorPanel, "card5");

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel107.setText("Treatment");

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));

        jPanel65.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel108.setText("Treatment Code :");

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel109.setText("Treatment Name :");

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel110.setText("Unit Price :");

        jTextFieldTreatmentCode.setEnabled(false);
        jTextFieldTreatmentCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTreatmentCodeActionPerformed(evt);
            }
        });

        jTextFieldTreatmentName.setEnabled(false);

        jTextFieldTreatmentUnitPrice.setEnabled(false);

        jButtonTreatmentEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonTreatmentEdit.setText("Edit");
        jButtonTreatmentEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTreatmentEdit.setEnabled(false);
        jButtonTreatmentEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentEditActionPerformed(evt);
            }
        });

        buttonAddNewTreatment.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonAddNewTreatment.setText("Add New");
        buttonAddNewTreatment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonAddNewTreatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewTreatmentActionPerformed(evt);
            }
        });

        jButtonTreatmntAddTreatment.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonTreatmntAddTreatment.setText("Add");
        jButtonTreatmntAddTreatment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTreatmntAddTreatment.setEnabled(false);
        jButtonTreatmntAddTreatment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmntAddTreatmentActionPerformed(evt);
            }
        });

        jButtonTreatmentUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonTreatmentUpdate.setText("Update");
        jButtonTreatmentUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTreatmentUpdate.setEnabled(false);
        jButtonTreatmentUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentUpdateActionPerformed(evt);
            }
        });

        jButtonTreatmentDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonTreatmentDelete.setText("Delete");
        jButtonTreatmentDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTreatmentDelete.setEnabled(false);
        jButtonTreatmentDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentDeleteActionPerformed(evt);
            }
        });

        jButtonTreatmentCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonTreatmentCancel.setText("Cancel");
        jButtonTreatmentCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonTreatmentCancel.setEnabled(false);
        jButtonTreatmentCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreatmentCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonTreatmntAddTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonTreatmentCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTreatmentDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButtonTreatmentUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTreatmntAddTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTreatmentUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTreatmentDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTreatmentCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel115.setText("Description :");

        jTextAreaTreatmentDescription.setColumns(20);
        jTextAreaTreatmentDescription.setRows(5);
        jTextAreaTreatmentDescription.setEnabled(false);
        jScrollPane11.setViewportView(jTextAreaTreatmentDescription);

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel109)
                            .addComponent(jLabel108)
                            .addComponent(jLabel110)
                            .addComponent(jLabel115)))
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonAddNewTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldTreatmentUnitPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jTextFieldTreatmentName, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldTreatmentCode, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                        .addComponent(jButtonTreatmentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTreatmentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddNewTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(jTextFieldTreatmentCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(jTextFieldTreatmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(jTextFieldTreatmentUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel115)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel67.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel68.setBorder(javax.swing.BorderFactory.createTitledBorder("Search By"));

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel111.setText("Name :");

        jTextFieldTreatmentSearchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTreatmentSearchNameKeyReleased(evt);
            }
        });

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel112.setText("Code :");

        jTextFieldTreatmentSearchCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTreatmentSearchCodeKeyReleased(evt);
            }
        });

        jLabelTreatmentViewRowCount.setText(" ");

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel111)
                .addGap(55, 55, 55)
                .addComponent(jTextFieldTreatmentSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel112)
                .addGap(55, 55, 55)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTreatmentViewRowCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTreatmentSearchCode, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(jTextFieldTreatmentSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel112)
                    .addComponent(jTextFieldTreatmentSearchCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelTreatmentViewRowCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane12MouseClicked(evt);
            }
        });

        jTableTreatmentView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Treatment Code", "Treatment Name", "Unit Price", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTreatmentView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTreatmentViewMouseClicked(evt);
            }
        });
        jTableTreatmentView.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableTreatmentViewKeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(jTableTreatmentView);
        if (jTableTreatmentView.getColumnModel().getColumnCount() > 0) {
            jTableTreatmentView.getColumnModel().getColumn(0).setHeaderValue("Treatment Code");
            jTableTreatmentView.getColumnModel().getColumn(1).setHeaderValue("Treatment Name");
            jTableTreatmentView.getColumnModel().getColumn(2).setHeaderValue("Unit Price");
            jTableTreatmentView.getColumnModel().getColumn(3).setHeaderValue("Description");
        }

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12)
                .addContainerGap())
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel70.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel114.setText("Date Added :");

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel116.setText("jLabel116");

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel114)
                .addGap(70, 70, 70)
                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(jLabel116))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout treatmentPanelLayout = new javax.swing.GroupLayout(treatmentPanel);
        treatmentPanel.setLayout(treatmentPanelLayout);
        treatmentPanelLayout.setHorizontalGroup(
            treatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        treatmentPanelLayout.setVerticalGroup(
            treatmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(treatmentPanelLayout.createSequentialGroup()
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parentPannel.add(treatmentPanel, "card7");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelDate.setText("labelDate");

        labelTime.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTime.setText("labelTime");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDate, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(labelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(parentPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parentPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMinimizeActionPerformed
        // TODO add your handling code here:
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_buttonMinimizeActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        // TODO add your handling code here:
        closeWindow();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void jTextFieldPatientTP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPatientTP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPatientTP1ActionPerformed

    private void jButtonPatientUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientUpdateActionPerformed
        
        if("".equals(jTextFieldPatientFirstName.getText())|
                "".equals(jTextFieldPatientSecondName.getText())|"".equals(jTextFieldPatientDateofBirth.getText())|
                "".equals(jTextFieldPatientAge.getText())|"".equals(jTextAreaPatientAddress.getText())|
                "".equals(jTextFieldPatientTP1.getText())|"".equals(jTextFieldPatientTP2.getText())){
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
            
            try {
                
                String sql = "UPDATE Patient SET fistname = ?,secondname =?,dateofbirth =?,age =?,address=?,tp1 =?,tp2=? WHERE NIC = '"+jTextFieldPatientNIC.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, jTextFieldPatientFirstName.getText());
                pst.setString(2, jTextFieldPatientSecondName.getText());
                pst.setString(3, jTextFieldPatientDateofBirth.getText());
                pst.setString(4, jTextFieldPatientAge.getText());
                pst.setString(5, jTextAreaPatientAddress.getText());
                pst.setString(6, jTextFieldPatientTP1.getText());
                pst.setString(7, jTextFieldPatientTP2.getText());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Updated");
                
                jTextFieldPatientNIC.setText("");
                jTextFieldPatientNIC.setEnabled(false);
                jTextFieldPatientFirstName.setText("");
                jTextFieldPatientFirstName.setEnabled(false);
                jTextFieldPatientSecondName.setText("");
                jTextFieldPatientSecondName.setEnabled(false);
                jTextFieldPatientDateofBirth.setText("");
                jTextFieldPatientDateofBirth.setEnabled(false);
                jTextFieldPatientAge.setText("");
                jTextFieldPatientAge.setEnabled(false);
                jTextAreaPatientAddress.setText("");
                jTextAreaPatientAddress.setEnabled(false);
                jTextFieldPatientTP1.setText("");
                jTextFieldPatientTP1.setEnabled(false);
                jTextFieldPatientTP2.setText("");
                jTextFieldPatientTP2.setEnabled(false);
                jButtonPatientCancel.setEnabled(false);
                jButtonAddPatient.setEnabled(false);
                jButtonPatientDelete.setEnabled(false);
                jButtonPatientUpdate.setEnabled(false);
                jButtonPatientEdit.setEnabled(false);
                buttonAddNewPatient.setEnabled(true);
                jLabelPatientViewRowCount.setText(" ");
                jTextFieldPatientSearchName.setEnabled(true);
                jTextFieldPatientSearchNIC.setEnabled(true);
                DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
                tm.setRowCount(0);
                
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        }
        
    }//GEN-LAST:event_jButtonPatientUpdateActionPerformed

    private void jCheckBoxAdmissionInPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdmissionInPatientActionPerformed
        
        jCheckBoxAdmissionWardSelect.setEnabled(true);
        jCheckBoxAdmissionRoomSelect.setEnabled(true);
        InOutPatient = 0;
        
    }//GEN-LAST:event_jCheckBoxAdmissionInPatientActionPerformed

    private void jTextFieldDoctorTP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDoctorTP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDoctorTP1ActionPerformed

    private void jButtonDoctorUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorUpdateActionPerformed
        
        if("".equals(jTextFieldDoctorFirstName.getText())|
                "".equals(jTextFieldDoctorSecondName.getText())|"".equals(jTextFieldDoctorSpeciality.getText())|
                "".equals(jTextAreaDoctorAddress.getText())|"".equals(jTextFieldDoctorTP1.getText())|
                "".equals(jTextFieldDoctorTP2.getText())){
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
            
            try {
                
                String sql = "UPDATE Doctor SET firstname = ?,secondname =?,speciality =?,jobtype =?,address=?,tp1 =?,tp2=? WHERE reg_no = '"+jTextFieldDoctorRegNo.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, jTextFieldDoctorFirstName.getText());
                pst.setString(2, jTextFieldDoctorSecondName.getText());
                pst.setString(3, jTextFieldDoctorSpeciality.getText());
                pst.setString(4, String.valueOf(jComboBoxDoctorJobType.getSelectedItem()));
                pst.setString(5, jTextAreaDoctorAddress.getText());
                pst.setString(6, jTextFieldDoctorTP1.getText());
                pst.setString(7, jTextFieldDoctorTP2.getText());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Updated");
                
                jTextFieldDoctorRegNo.setText("");
                jTextFieldDoctorRegNo.setEnabled(false);
                jTextFieldDoctorFirstName.setText("");
                jTextFieldDoctorFirstName.setEnabled(false);
                jTextFieldDoctorSecondName.setText("");
                jTextFieldDoctorSecondName.setEnabled(false);
                jTextFieldDoctorSpeciality.setText("");
                jTextFieldDoctorSpeciality.setEnabled(false);
                jComboBoxDoctorJobType.setEnabled(false);
                jTextAreaDoctorAddress.setText("");
                jTextAreaDoctorAddress.setEnabled(false);
                jTextFieldDoctorTP1.setText("");
                jTextFieldDoctorTP1.setEnabled(false);
                jTextFieldDoctorTP2.setText("");
                jTextFieldDoctorTP2.setEnabled(false);
                jButtonDoctorCancel.setEnabled(false);
                jButtonAddDoctor.setEnabled(false);
                jButtonDoctorDelete.setEnabled(false);
                jButtonDoctorUpdate.setEnabled(false);
                jButtonDoctorEdit.setEnabled(false);
                buttonAddNewDoctor.setEnabled(true);
                jLabelDoctorViewRowCount.setText(" ");
                jTextFieldDoctorSearchName.setEnabled(true);
                jTextFieldDoctorSearchRegNo.setEnabled(true);
                DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
                tm.setRowCount(0);
                
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        }
        
    }//GEN-LAST:event_jButtonDoctorUpdateActionPerformed

    private void jButtonTreatmentUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentUpdateActionPerformed
        
        if("".equals(jTextFieldTreatmentName.getText())|
                "".equals(jTextFieldTreatmentCode.getText())|"".equals(jTextFieldTreatmentUnitPrice.getText())|
                "".equals(jTextAreaTreatmentDescription.getText())){
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
            
            try {
                
                String sql = "UPDATE Treatment SET treatmentname = ?,unitprice =?,description =? WHERE treatmentcode = '"+jTextFieldTreatmentCode.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, jTextFieldTreatmentName.getText());
                pst.setString(2, jTextFieldTreatmentUnitPrice.getText());
                pst.setString(3, jTextAreaTreatmentDescription.getText());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Updated");
                
                jTextFieldTreatmentCode.setText("");
                jTextFieldTreatmentCode.setEnabled(false);
                jTextFieldTreatmentName.setText("");
                jTextFieldTreatmentName.setEnabled(false);
                jTextFieldTreatmentUnitPrice.setText("");
                jTextFieldTreatmentUnitPrice.setEnabled(false);
                jTextAreaTreatmentDescription.setText("");
                jTextAreaTreatmentDescription.setEnabled(false);
                jButtonTreatmentCancel.setEnabled(false);
                jButtonTreatmntAddTreatment.setEnabled(false);
                jButtonTreatmentDelete.setEnabled(false);
                jButtonTreatmentUpdate.setEnabled(false);
                jButtonTreatmentEdit.setEnabled(false);
                buttonAddNewTreatment.setEnabled(true);
                jLabelTreatmentViewRowCount.setText(" ");
                jTextFieldTreatmentSearchName.setEnabled(true);
                jTextFieldTreatmentSearchCode.setEnabled(true);
                DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
                tm.setRowCount(0);
                
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        }
        
    }//GEN-LAST:event_jButtonTreatmentUpdateActionPerformed

    private void jButtonAdmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdmissionActionPerformed
        
        parentPannel.removeAll();
        parentPannel.add(admissionPanel);
        parentPannel.repaint();
        parentPannel.revalidate();
        
        jButtonAdmission.setBackground(Color.DARK_GRAY);
        jButtonDiognosis.setBackground(Color.lightGray);
        jButtonPatient.setBackground(Color.lightGray);
        jButtonPayments.setBackground(Color.lightGray);
        jButtonDoctor.setBackground(Color.lightGray);
        jButtonTreatment.setBackground(Color.lightGray);
        
        jLabelAdmissionID.setText(createAdmissionID());
        wardNoCombo();
        roomNoCombo();
        
    }//GEN-LAST:event_jButtonAdmissionActionPerformed

    private void jButtonDiognosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDiognosisActionPerformed
        
        parentPannel.removeAll();
        parentPannel.add(diognosisPanel);
        parentPannel.repaint();
        parentPannel.revalidate();
        
        jButtonAdmission.setBackground(Color.lightGray);
        jButtonDiognosis.setBackground(Color.DARK_GRAY);
        jButtonPatient.setBackground(Color.lightGray);
        jButtonPayments.setBackground(Color.lightGray);
        jButtonDoctor.setBackground(Color.lightGray);
        jButtonTreatment.setBackground(Color.lightGray);
        
    }//GEN-LAST:event_jButtonDiognosisActionPerformed

    private void jButtonPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientActionPerformed
        
        parentPannel.removeAll();
        parentPannel.add(ptientPanel);
        parentPannel.repaint();
        parentPannel.revalidate();
        
        jButtonAdmission.setBackground(Color.lightGray);
        jButtonDiognosis.setBackground(Color.lightGray);
        jButtonPatient.setBackground(Color.DARK_GRAY);
        jButtonPayments.setBackground(Color.lightGray);
        jButtonDoctor.setBackground(Color.lightGray);
        jButtonTreatment.setBackground(Color.lightGray);
        
    }//GEN-LAST:event_jButtonPatientActionPerformed

    private void jButtonPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPaymentsActionPerformed
        
        parentPannel.removeAll();
        parentPannel.add(paymentDetailsPanel);
        parentPannel.repaint();
        parentPannel.revalidate();
        
        jButtonAdmission.setBackground(Color.lightGray);
        jButtonDiognosis.setBackground(Color.lightGray);
        jButtonPatient.setBackground(Color.lightGray);
        jButtonPayments.setBackground(Color.DARK_GRAY);
        jButtonDoctor.setBackground(Color.lightGray);
        jButtonTreatment.setBackground(Color.lightGray);
        
    }//GEN-LAST:event_jButtonPaymentsActionPerformed

    private void jButtonDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorActionPerformed
        
        parentPannel.removeAll();
        parentPannel.add(doctorPanel);
        parentPannel.repaint();
        parentPannel.revalidate();
        
        jButtonAdmission.setBackground(Color.lightGray);
        jButtonDiognosis.setBackground(Color.lightGray);
        jButtonPatient.setBackground(Color.lightGray);
        jButtonPayments.setBackground(Color.lightGray);
        jButtonDoctor.setBackground(Color.DARK_GRAY);
        jButtonTreatment.setBackground(Color.lightGray);
        
        
        
    }//GEN-LAST:event_jButtonDoctorActionPerformed

    private void jButtonTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentActionPerformed
        
        parentPannel.removeAll();
        parentPannel.add(treatmentPanel);
        parentPannel.repaint();
        parentPannel.revalidate();
        
        jButtonAdmission.setBackground(Color.lightGray);
        jButtonDiognosis.setBackground(Color.lightGray);
        jButtonPatient.setBackground(Color.lightGray);
        jButtonPayments.setBackground(Color.lightGray);
        jButtonDoctor.setBackground(Color.lightGray);
        jButtonTreatment.setBackground(Color.DARK_GRAY);
        
    }//GEN-LAST:event_jButtonTreatmentActionPerformed

    private void jTextFieldDoctorFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDoctorFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDoctorFirstNameActionPerformed

    private void buttonAddNewDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewDoctorActionPerformed
        
        jTextFieldDoctorRegNo.setEnabled(true);
        jTextFieldDoctorFirstName.setEnabled(true);
        jTextFieldDoctorSecondName.setEnabled(true);
        jTextFieldDoctorSpeciality.setEnabled(true);
        jComboBoxDoctorJobType.setEnabled(true);
        jTextAreaDoctorAddress.setEnabled(true);
        jTextFieldDoctorTP1.setEnabled(true);
        jTextFieldDoctorTP2.setEnabled(true);
        jButtonDoctorCancel.setEnabled(true);
        jButtonAddDoctor.setEnabled(true);
        jTextFieldDoctorSearchName.setText("");
        jTextFieldDoctorSearchName.setEnabled(false);
        jTextFieldDoctorSearchRegNo.setText("");
        jTextFieldDoctorSearchRegNo.setEnabled(false);
        jLabelDoctorViewRowCount.setText(" ");
        DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_buttonAddNewDoctorActionPerformed

    private void jButtonDoctorCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorCancelActionPerformed
        
        jTextFieldDoctorRegNo.setText("");
        jTextFieldDoctorRegNo.setEnabled(false);
        jTextFieldDoctorFirstName.setText("");
        jTextFieldDoctorFirstName.setEnabled(false);
        jTextFieldDoctorSecondName.setText("");
        jTextFieldDoctorSecondName.setEnabled(false);
        jTextFieldDoctorSpeciality.setText("");
        jTextFieldDoctorSpeciality.setEnabled(false);
        jComboBoxDoctorJobType.setEnabled(false);
        jTextAreaDoctorAddress.setText("");
        jTextAreaDoctorAddress.setEnabled(false);
        jTextFieldDoctorTP1.setText("");
        jTextFieldDoctorTP1.setEnabled(false);
        jTextFieldDoctorTP2.setText("");
        jTextFieldDoctorTP2.setEnabled(false);
        jButtonDoctorCancel.setEnabled(false);
        jButtonAddDoctor.setEnabled(false);
        buttonAddNewDoctor.setEnabled(true);
        jButtonDoctorEdit.setEnabled(false);
        jButtonDoctorDelete.setEnabled(false);
        jButtonDoctorUpdate.setEnabled(false);
        jLabelDoctorViewRowCount.setText(" ");
        jTextFieldDoctorSearchRegNo.setEnabled(true);
        jTextFieldDoctorSearchName.setEnabled(true);
        DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_jButtonDoctorCancelActionPerformed

    private void jButtonAddDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDoctorActionPerformed
        
        if("".equals(jTextFieldDoctorRegNo.getText())|"".equals(jTextFieldDoctorFirstName.getText())|
                "".equals(jTextFieldDoctorSecondName.getText())|"".equals(jTextFieldDoctorSpeciality.getText())|
                "".equals(jTextAreaDoctorAddress.getText())|"".equals(jTextFieldDoctorTP1.getText())|
                "".equals(jTextFieldDoctorTP2.getText())){
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
            
            try {
                
                String sql = "Insert into Doctor(reg_no,firstname,secondname,speciality,jobtype,address,tp1,tp2,joindate) value(?,?,?,?,?,?,?,?,?)";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, jTextFieldDoctorRegNo.getText());
                pst.setString(2, jTextFieldDoctorFirstName.getText());
                pst.setString(3, jTextFieldDoctorSecondName.getText());
                pst.setString(4, jTextFieldDoctorSpeciality.getText());
                pst.setString(5, String.valueOf(jComboBoxDoctorJobType.getSelectedItem()));
                pst.setString(6, jTextAreaDoctorAddress.getText());
                pst.setString(7, jTextFieldDoctorTP1.getText());
                pst.setString(8, jTextFieldDoctorTP2.getText());
                pst.setString(9, getDate());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Saved");
                
                jTextFieldDoctorRegNo.setText("");
                jTextFieldDoctorRegNo.setEnabled(false);
                jTextFieldDoctorFirstName.setText("");
                jTextFieldDoctorFirstName.setEnabled(false);
                jTextFieldDoctorSecondName.setText("");
                jTextFieldDoctorSecondName.setEnabled(false);
                jTextFieldDoctorSpeciality.setText("");
                jTextFieldDoctorSpeciality.setEnabled(false);
                jComboBoxDoctorJobType.setEnabled(false);
                jTextAreaDoctorAddress.setText("");
                jTextAreaDoctorAddress.setEnabled(false);
                jTextFieldDoctorTP1.setText("");
                jTextFieldDoctorTP1.setEnabled(false);
                jTextFieldDoctorTP2.setText("");
                jTextFieldDoctorTP2.setEnabled(false);
                jButtonDoctorCancel.setEnabled(false);
                jButtonAddDoctor.setEnabled(false);
                jTextFieldDoctorSearchRegNo.setEnabled(true);
                jTextFieldDoctorSearchName.setEnabled(true);
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        }
        
    }//GEN-LAST:event_jButtonAddDoctorActionPerformed

    private void jTextFieldDoctorSearchNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDoctorSearchNameKeyReleased
        
        try {
            
            String sql = "SELECT * FROM doctor WHERE firstname LIKE '%"+jTextFieldDoctorSearchName.getText()+"%'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
            tm.setRowCount(0);
            
            int i = 0;
            
            while(rs.next()) {
                
                Object[] o= {rs.getString("reg_no"),rs.getString("firstname"),rs.getString("secondname"),
                rs.getString("speciality"),rs.getString("jobtype"),rs.getString("address"),
                rs.getString("tp1"),rs.getString("tp2")};
                
                tm.addRow(o);
                
                i++;
                
            }
            
            if("".equals(jTextFieldDoctorSearchName.getText())){
            
                tm.setRowCount(0);
                i=0;
        
            }
            
            jLabelDoctorViewRowCount.setText(i+" Row(s) are Selected");
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
         
    }//GEN-LAST:event_jTextFieldDoctorSearchNameKeyReleased

    private void jTextFieldDoctorSearchRegNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDoctorSearchRegNoKeyReleased
        
        try {
            
            String sql = "SELECT * FROM doctor WHERE reg_no LIKE '%"+jTextFieldDoctorSearchRegNo.getText()+"%'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
            tm.setRowCount(0);
            
            int i = 0;
            
            while(rs.next()) {
                
                Object[] o= {rs.getString("reg_no"),rs.getString("firstname"),rs.getString("secondname"),
                rs.getString("speciality"),rs.getString("jobtype"),rs.getString("address"),
                rs.getString("tp1"),rs.getString("tp2")};
                
                tm.addRow(o);
                
                i++;
                
            }
            
            
            
            if("".equals(jTextFieldDoctorSearchRegNo.getText())){
            
                tm.setRowCount(0);
                i=0;
        
            }
            
            jLabelDoctorViewRowCount.setText(i+" Row(s) are Selected");
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
        
    }//GEN-LAST:event_jTextFieldDoctorSearchRegNoKeyReleased

    private void jTableDoctorViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDoctorViewMouseClicked
        
        try {
            
            buttonAddNewDoctor.setEnabled(false);
            jButtonDoctorCancel.setEnabled(true);
            jButtonDoctorEdit.setEnabled(true);
            
            int row = jTableDoctorView.getSelectedRow();
            String reg_no = (jTableDoctorView.getModel().getValueAt(row, 0)).toString();
            
            String sql = "SELECT * FROM doctor WHERE reg_no = '"+reg_no+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                
                jTextFieldDoctorRegNo.setText(rs.getString("reg_no"));
                jTextFieldDoctorFirstName.setText(rs.getString("firstname"));
                jTextFieldDoctorSecondName.setText(rs.getString("secondname"));
                jTextFieldDoctorSpeciality.setText(rs.getString("speciality"));
                jComboBoxDoctorJobType.setSelectedItem(rs.getString("jobtype"));
                jTextAreaDoctorAddress.setText(rs.getString("address"));
                jTextFieldDoctorTP1.setText(rs.getString("tp1"));
                jTextFieldDoctorTP2.setText(rs.getString("tp2"));
                
            }
            
            jTextFieldDoctorSearchName.setText("");
            jTextFieldDoctorSearchRegNo.setText("");
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }//GEN-LAST:event_jTableDoctorViewMouseClicked

    private void jButtonDoctorEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorEditActionPerformed
        
        jTextFieldDoctorFirstName.setEnabled(true);
        jTextFieldDoctorSecondName.setEnabled(true);
        jTextFieldDoctorSpeciality.setEnabled(true);
        jComboBoxDoctorJobType.setEnabled(true);
        jTextAreaDoctorAddress.setEnabled(true);
        jTextFieldDoctorTP1.setEnabled(true);
        jTextFieldDoctorTP2.setEnabled(true);
        jButtonDoctorCancel.setEnabled(true);
        jButtonDoctorDelete.setEnabled(true);
        jButtonDoctorUpdate.setEnabled(true);
        jTextFieldDoctorSearchName.setText("");
        jTextFieldDoctorSearchRegNo.setText("");
        jTextFieldDoctorSearchRegNo.setEnabled(false);
        jTextFieldDoctorSearchName.setEnabled(false);
        jLabelDoctorViewRowCount.setText(" ");
        DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_jButtonDoctorEditActionPerformed

    private void jButtonDoctorDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorDeleteActionPerformed
        
        try {
                
                String sql = "DELETE FROM Doctor WHERE reg_no = '"+jTextFieldDoctorRegNo.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Deleted");
                
                jTextFieldDoctorRegNo.setText("");
                jTextFieldDoctorRegNo.setEnabled(false);
                jTextFieldDoctorFirstName.setText("");
                jTextFieldDoctorFirstName.setEnabled(false);
                jTextFieldDoctorSecondName.setText("");
                jTextFieldDoctorSecondName.setEnabled(false);
                jTextFieldDoctorSpeciality.setText("");
                jTextFieldDoctorSpeciality.setEnabled(false);
                jComboBoxDoctorJobType.setEnabled(false);
                jTextAreaDoctorAddress.setText("");
                jTextAreaDoctorAddress.setEnabled(false);
                jTextFieldDoctorTP1.setText("");
                jTextFieldDoctorTP1.setEnabled(false);
                jTextFieldDoctorTP2.setText("");
                jTextFieldDoctorTP2.setEnabled(false);
                jButtonDoctorCancel.setEnabled(false);
                jButtonAddDoctor.setEnabled(false);
                jButtonDoctorDelete.setEnabled(false);
                jButtonDoctorUpdate.setEnabled(false);
                jButtonDoctorEdit.setEnabled(false);
                buttonAddNewDoctor.setEnabled(true);
                jLabelDoctorViewRowCount.setText(" ");
                jTextFieldDoctorSearchRegNo.setEnabled(true);
                jTextFieldDoctorSearchName.setEnabled(true);
                DefaultTableModel tm = (DefaultTableModel)jTableDoctorView.getModel();
                tm.setRowCount(0);
                
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        
        
    }//GEN-LAST:event_jButtonDoctorDeleteActionPerformed

    private void jPanel47FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel47FocusLost
        
        
        
    }//GEN-LAST:event_jPanel47FocusLost

    private void jTextFieldTreatmentCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTreatmentCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTreatmentCodeActionPerformed

    private void buttonAddNewTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewTreatmentActionPerformed
        
        jTextFieldTreatmentCode.setEnabled(true);
        jTextFieldTreatmentName.setEnabled(true);
        jTextFieldTreatmentUnitPrice.setEnabled(true);
        jTextAreaTreatmentDescription.setEnabled(true);
        jButtonTreatmentCancel.setEnabled(true);
        jButtonTreatmntAddTreatment.setEnabled(true);
        jTextFieldTreatmentSearchName.setText("");
        jTextFieldTreatmentSearchName.setEnabled(false);
        jTextFieldTreatmentSearchCode.setText("");
        jTextFieldTreatmentSearchCode.setEnabled(false);
        jLabelTreatmentViewRowCount.setText(" ");
        DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_buttonAddNewTreatmentActionPerformed

    private void jButtonTreatmentCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentCancelActionPerformed
        
        jTextFieldTreatmentCode.setText("");
        jTextFieldTreatmentCode.setEnabled(false);
        jTextFieldTreatmentName.setText("");
        jTextFieldTreatmentName.setEnabled(false);
        jTextFieldTreatmentUnitPrice.setText("");
        jTextFieldTreatmentUnitPrice.setEnabled(false);
        jTextAreaTreatmentDescription.setText("");
        jTextAreaTreatmentDescription.setEnabled(false);
        jButtonTreatmentCancel.setEnabled(false);
        jButtonTreatmntAddTreatment.setEnabled(false);
        buttonAddNewTreatment.setEnabled(true);
        jButtonTreatmentEdit.setEnabled(false);
        jButtonTreatmentDelete.setEnabled(false);
        jButtonTreatmentUpdate.setEnabled(false);
        jLabelTreatmentViewRowCount.setText(" ");
        jTextFieldTreatmentSearchCode.setEnabled(true);
        jTextFieldTreatmentSearchName.setEnabled(true);
        DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_jButtonTreatmentCancelActionPerformed

    private void jButtonTreatmntAddTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmntAddTreatmentActionPerformed
        
        if("".equals(jTextFieldTreatmentCode.getText())|"".equals(jTextFieldTreatmentName.getText())|
                "".equals(jTextFieldTreatmentUnitPrice.getText())|
                "".equals(jTextAreaTreatmentDescription.getText())){
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
            
            try {
                
                String sql = "Insert into Treatment(treatmentCode,treatmentName,unitPrice,description,dateAdded) value(?,?,?,?,?)";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, jTextFieldTreatmentCode.getText());
                pst.setString(2, jTextFieldTreatmentName.getText());
                pst.setString(3, jTextFieldTreatmentUnitPrice.getText());
                pst.setString(4, jTextAreaTreatmentDescription.getText());
                pst.setString(5, getDate());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Saved");
                
                jTextFieldTreatmentCode.setText("");
                jTextFieldTreatmentCode.setEnabled(false);
                jTextFieldTreatmentName.setText("");
                jTextFieldTreatmentName.setEnabled(false);
                jTextFieldTreatmentUnitPrice.setText("");
                jTextFieldTreatmentUnitPrice.setEnabled(false);
                jTextAreaTreatmentDescription.setText("");
                jTextAreaTreatmentDescription.setEnabled(false);
                jButtonTreatmentCancel.setEnabled(false);
                jButtonTreatmntAddTreatment.setEnabled(false);
                jTextFieldTreatmentSearchCode.setEnabled(true);
                jTextFieldTreatmentSearchName.setEnabled(true);
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        }
        
    }//GEN-LAST:event_jButtonTreatmntAddTreatmentActionPerformed

    private void jTextFieldTreatmentSearchNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTreatmentSearchNameKeyReleased
        
        try {
            
            String sql = "SELECT * FROM Treatment WHERE treatmentName LIKE '%"+jTextFieldTreatmentSearchName.getText()+"%'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
            tm.setRowCount(0);
            
            int i = 0;
            
            while(rs1.next()) {
                
                Object[] o= {rs1.getString("treatmentCode"),rs1.getString("treatmentName"),rs1.getString("unitPrice"),
                rs1.getString("description")};
                
                tm.addRow(o);
                
                i++;
                
            }
            
            if("".equals(jTextFieldTreatmentSearchName.getText())){
            
                tm.setRowCount(0);
                i=0;
        
            }
            
            jLabelTreatmentViewRowCount.setText(i+" Row(s) are Selected");
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
        
    }//GEN-LAST:event_jTextFieldTreatmentSearchNameKeyReleased

    private void jTextFieldTreatmentSearchCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTreatmentSearchCodeKeyReleased
        
        try {
            
            String sql = "SELECT * FROM Treatment WHERE treatmentCode LIKE '%"+jTextFieldTreatmentSearchCode.getText()+"%'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
            tm.setRowCount(0);
            
            int i = 0;
            
            while(rs1.next()) {
                
                Object[] o= {rs1.getString("treatmentCode"),rs1.getString("treatmentName"),rs1.getString("unitPrice"),
                rs1.getString("description")};
                
                tm.addRow(o);
                
                i++;
                
            }
            
            if("".equals(jTextFieldTreatmentSearchCode.getText())){
            
                tm.setRowCount(0);
                i=0;
        
            }
            
            jLabelTreatmentViewRowCount.setText(i+" Row(s) are Selected");
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
        
    }//GEN-LAST:event_jTextFieldTreatmentSearchCodeKeyReleased

   
    private void jScrollPane12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane12MouseClicked
        
        
        
    }//GEN-LAST:event_jScrollPane12MouseClicked

    private void jTableTreatmentViewKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableTreatmentViewKeyReleased
        
        
    }//GEN-LAST:event_jTableTreatmentViewKeyReleased

    
    private void jTableTreatmentViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTreatmentViewMouseClicked
        
        try {
            
            buttonAddNewTreatment.setEnabled(false);
            jButtonTreatmentCancel.setEnabled(true);
            jButtonTreatmentEdit.setEnabled(true);
            
            int row = jTableTreatmentView.getSelectedRow();
            String trt_code = (jTableTreatmentView.getModel().getValueAt(row, 0)).toString();
            System.out.println(trt_code);
            String sql = "SELECT * FROM Treatment WHERE treatmentCode = '"+trt_code+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
            
            if(rs1.next()){
                
                jTextFieldTreatmentCode.setText(rs1.getString("treatmentCode"));
                jTextFieldTreatmentName.setText(rs1.getString("treatmentName"));
                jTextFieldTreatmentUnitPrice.setText(rs1.getString("unitPrice"));
                jTextAreaTreatmentDescription.setText(rs1.getString("description"));
                
            }
            
            jTextFieldTreatmentSearchName.setText("");
            jTextFieldTreatmentSearchCode.setText("");
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }//GEN-LAST:event_jTableTreatmentViewMouseClicked

    private void jButtonTreatmentEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentEditActionPerformed
        
        jTextFieldTreatmentName.setEnabled(true);
        jTextFieldTreatmentUnitPrice.setEnabled(true);
        jTextAreaTreatmentDescription.setEnabled(true);
        jButtonTreatmentCancel.setEnabled(true);
        jButtonTreatmentDelete.setEnabled(true);
        jButtonTreatmentUpdate.setEnabled(true);
        jTextFieldTreatmentSearchName.setText("");
        jTextFieldTreatmentSearchCode.setText("");
        jTextFieldTreatmentSearchName.setEnabled(false);
        jTextFieldTreatmentSearchCode.setEnabled(false);
        jLabelTreatmentViewRowCount.setText(" ");
        DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_jButtonTreatmentEditActionPerformed

    private void jButtonTreatmentDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTreatmentDeleteActionPerformed
        
        try {
                
                String sql = "DELETE FROM treatment WHERE treatmentcode = '"+jTextFieldTreatmentCode.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Deleted");
                
                jTextFieldTreatmentCode.setText("");
                jTextFieldTreatmentCode.setEnabled(false);
                jTextFieldTreatmentName.setText("");
                jTextFieldTreatmentName.setEnabled(false);
                jTextFieldTreatmentUnitPrice.setText("");
                jTextFieldTreatmentUnitPrice.setEnabled(false);
                jTextAreaTreatmentDescription.setText("");
                jTextAreaTreatmentDescription.setEnabled(false);
                jButtonTreatmentCancel.setEnabled(false);
                jButtonTreatmntAddTreatment.setEnabled(false);
                jButtonTreatmentDelete.setEnabled(false);
                jButtonTreatmentUpdate.setEnabled(false);
                jButtonTreatmentEdit.setEnabled(false);
                buttonAddNewTreatment.setEnabled(true);
                jLabelTreatmentViewRowCount.setText(" ");
                jTextFieldTreatmentSearchName.setEnabled(true);
                jTextFieldTreatmentSearchCode.setEnabled(true);
                DefaultTableModel tm = (DefaultTableModel)jTableTreatmentView.getModel();
                tm.setRowCount(0);
                
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
        
    }//GEN-LAST:event_jButtonTreatmentDeleteActionPerformed

    private void buttonAddNewPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewPatientActionPerformed
        
        jTextFieldPatientNIC.setEnabled(true);
        jTextFieldPatientFirstName.setEnabled(true);
        jTextFieldPatientSecondName.setEnabled(true);
        jTextFieldPatientDateofBirth.setEnabled(true);
        jTextFieldPatientAge.setEnabled(true);
        jTextAreaPatientAddress.setEnabled(true);
        jTextFieldPatientTP1.setEnabled(true);
        jTextFieldPatientTP2.setEnabled(true);
        jButtonPatientCancel.setEnabled(true);
        jButtonAddPatient.setEnabled(true);
        jTextFieldPatientSearchName.setText("");
        jTextFieldPatientSearchName.setEnabled(false);
        jTextFieldPatientSearchNIC.setText("");
        jTextFieldPatientSearchNIC.setEnabled(false);
        jLabelPatientViewRowCount.setText(" ");
        DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_buttonAddNewPatientActionPerformed

    private void jButtonPatientCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientCancelActionPerformed
        
        jTextFieldPatientNIC.setText("");
        jTextFieldPatientNIC.setEnabled(false);
        jTextFieldPatientFirstName.setText("");
        jTextFieldPatientFirstName.setEnabled(false);
        jTextFieldPatientSecondName.setText("");
        jTextFieldPatientSecondName.setEnabled(false);
        jTextFieldPatientDateofBirth.setText("");
        jTextFieldPatientDateofBirth.setEnabled(false);
        jTextFieldPatientAge.setText("");
        jTextFieldPatientAge.setEnabled(false);
        jTextAreaPatientAddress.setText("");
        jTextAreaPatientAddress.setEnabled(false);
        jTextFieldPatientTP1.setText("");
        jTextFieldPatientTP1.setEnabled(false);
        jTextFieldPatientTP2.setText("");
        jTextFieldPatientTP2.setEnabled(false);
        jButtonPatientCancel.setEnabled(false);
        jButtonAddPatient.setEnabled(false);
        buttonAddNewPatient.setEnabled(true);
        jButtonPatientEdit.setEnabled(false);
        jButtonPatientDelete.setEnabled(false);
        jButtonPatientUpdate.setEnabled(false);
        jLabelPatientViewRowCount.setText(" ");
        jTextFieldPatientSearchName.setEnabled(true);
        jTextFieldPatientSearchNIC.setEnabled(true);
        DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_jButtonPatientCancelActionPerformed

    private void jButtonAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPatientActionPerformed
        
        if("".equals(jTextFieldPatientNIC.getText())|"".equals(jTextFieldPatientFirstName.getText())|
                "".equals(jTextFieldPatientSecondName.getText())|"".equals(jTextFieldPatientDateofBirth.getText())|
                "".equals(jTextAreaPatientAddress.getText())|"".equals(jTextFieldPatientTP1.getText())|
                "".equals(jTextFieldPatientTP2.getText())){
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
            
            try {
                
                String sql = "Insert into Patient(nic,fistname,secondname,dateofbirth,age,address,tp1,tp2) value(?,?,?,?,?,?,?,?)";
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, jTextFieldPatientNIC.getText());
                pst.setString(2, jTextFieldPatientFirstName.getText());
                pst.setString(3, jTextFieldPatientSecondName.getText());
                pst.setString(4, jTextFieldPatientDateofBirth.getText());
                pst.setString(5, jTextFieldPatientAge.getText());
                pst.setString(6, jTextAreaPatientAddress.getText());
                pst.setString(7, jTextFieldPatientTP1.getText());
                pst.setString(8, jTextFieldPatientTP2.getText());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Saved");
                
                jTextFieldPatientNIC.setText("");
                jTextFieldPatientNIC.setEnabled(false);
                jTextFieldPatientFirstName.setText("");
                jTextFieldPatientFirstName.setEnabled(false);
                jTextFieldPatientSecondName.setText("");
                jTextFieldPatientSecondName.setEnabled(false);
                jTextFieldPatientDateofBirth.setText("");
                jTextFieldPatientDateofBirth.setEnabled(false);
                jTextFieldPatientAge.setText("");
                jTextFieldPatientAge.setEnabled(false);
                jTextAreaPatientAddress.setText("");
                jTextAreaPatientAddress.setEnabled(false);
                jTextFieldPatientTP1.setText("");
                jTextFieldPatientTP1.setEnabled(false);
                jTextFieldPatientTP2.setText("");
                jTextFieldPatientTP2.setEnabled(false);
                jButtonPatientCancel.setEnabled(false);
                jButtonAddPatient.setEnabled(false);
                jTextFieldPatientSearchNIC.setEnabled(true);
                jTextFieldPatientSearchName.setEnabled(true);
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
            
        }
        
    }//GEN-LAST:event_jButtonAddPatientActionPerformed

    private void jTextFieldPatientSearchNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientSearchNameKeyReleased
        
        try {
            
            String sql = "SELECT * FROM Patient WHERE fistname LIKE '%"+jTextFieldPatientSearchName.getText()+"%'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
            tm.setRowCount(0);
            
            int i = 0;
            
            while(rs.next()) {
                
                Object[] o= {rs.getString("NIC"),rs.getString("fistname"),rs.getString("secondname"),
                rs.getString("dateofbirth"),rs.getString("age"),rs.getString("address"),
                rs.getString("tp1"),rs.getString("tp2")};
                
                tm.addRow(o);
                
                i++;
                
            }
            
            if("".equals(jTextFieldPatientSearchName.getText())){
            
                tm.setRowCount(0);
                i=0;
        
            }
            
            jLabelPatientViewRowCount.setText(i+" Row(s) are Selected");
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
        
    }//GEN-LAST:event_jTextFieldPatientSearchNameKeyReleased

    private void jTextFieldPatientSearchNICKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientSearchNICKeyReleased
        
        try {
            
            String sql = "SELECT * FROM Patient WHERE nic LIKE '%"+jTextFieldPatientSearchNIC.getText()+"%'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
            tm.setRowCount(0);
            
            int i = 0;
            
            while(rs.next()) {
                
                Object[] o= {rs.getString("NIC"),rs.getString("fistname"),rs.getString("secondname"),
                rs.getString("dateofbirth"),rs.getString("age"),rs.getString("address"),
                rs.getString("tp1"),rs.getString("tp2")};
                
                tm.addRow(o);
                
                i++;
                
            }
            
            if("".equals(jTextFieldPatientSearchNIC.getText())){
            
                tm.setRowCount(0);
                i=0;
        
            }
            
            jLabelPatientViewRowCount.setText(i+" Row(s) are Selected");
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
        
    }//GEN-LAST:event_jTextFieldPatientSearchNICKeyReleased

    private void jTextFieldPatientSearchNICMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPatientSearchNICMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPatientSearchNICMouseClicked

    private void jTableViewPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableViewPatientMouseClicked
        
        try {
            
            buttonAddNewPatient.setEnabled(false);
            jButtonPatientCancel.setEnabled(true);
            jButtonPatientEdit.setEnabled(true);
            
            int row = jTableViewPatient.getSelectedRow();
            String NIC = (jTableViewPatient.getModel().getValueAt(row, 0)).toString();
            
            String sql = "SELECT * FROM patient WHERE nic = '"+NIC+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery();
            
            if(rs2.next()){
                
                jTextFieldPatientNIC.setText(rs2.getString("NIC"));
                jTextFieldPatientFirstName.setText(rs2.getString("fistname"));
                jTextFieldPatientSecondName.setText(rs2.getString("secondname"));
                jTextFieldPatientDateofBirth.setText(rs2.getString("dateofbirth"));
                jTextFieldPatientAge.setText(rs2.getString("age"));
                jTextAreaPatientAddress.setText(rs2.getString("address"));
                jTextFieldPatientTP1.setText(rs2.getString("tp1"));
                jTextFieldPatientTP2.setText(rs2.getString("tp2"));
                
            }
            
            jTextFieldPatientSearchName.setText("");
            jTextFieldPatientSearchNIC.setText("");
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }//GEN-LAST:event_jTableViewPatientMouseClicked

    private void jButtonPatientEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientEditActionPerformed
        
        jTextFieldPatientFirstName.setEnabled(true);
        jTextFieldPatientSecondName.setEnabled(true);
        jTextFieldPatientDateofBirth.setEnabled(true);
        jTextFieldPatientAge.setEnabled(true);
        jTextAreaPatientAddress.setEnabled(true);
        jTextFieldPatientTP1.setEnabled(true);
        jTextFieldPatientTP2.setEnabled(true);
        jButtonPatientCancel.setEnabled(true);
        jButtonPatientDelete.setEnabled(true);
        jButtonPatientUpdate.setEnabled(true);
        jTextFieldPatientSearchName.setText("");
        jTextFieldPatientSearchNIC.setText("");
        jTextFieldPatientSearchName.setEnabled(false);
        jTextFieldPatientSearchNIC.setEnabled(false);
        jLabelPatientViewRowCount.setText(" ");
        DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
        tm.setRowCount(0);
        
    }//GEN-LAST:event_jButtonPatientEditActionPerformed

    private void jButtonPatientDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPatientDeleteActionPerformed
        
        try {
                
                String sql = "DELETE FROM Patient WHERE nic = '"+jTextFieldPatientNIC.getText()+"'";
                pst = (PreparedStatement) con.prepareStatement(sql);
                
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Successfully Deleted");
                
                jTextFieldPatientNIC.setText("");
                jTextFieldPatientNIC.setEnabled(false);
                jTextFieldPatientFirstName.setText("");
                jTextFieldPatientFirstName.setEnabled(false);
                jTextFieldPatientSecondName.setText("");
                jTextFieldPatientSecondName.setEnabled(false);
                jTextFieldPatientDateofBirth.setText("");
                jTextFieldPatientDateofBirth.setEnabled(false);
                jTextFieldPatientAge.setText("");
                jTextFieldPatientAge.setEnabled(false);
                jTextAreaPatientAddress.setText("");
                jTextAreaPatientAddress.setEnabled(false);
                jTextFieldPatientTP1.setText("");
                jTextFieldPatientTP1.setEnabled(false);
                jTextFieldPatientTP2.setText("");
                jTextFieldPatientTP2.setEnabled(false);
                jButtonPatientCancel.setEnabled(false);
                jButtonAddPatient.setEnabled(false);
                jButtonPatientDelete.setEnabled(false);
                jButtonPatientUpdate.setEnabled(false);
                jButtonPatientEdit.setEnabled(false);
                buttonAddNewPatient.setEnabled(true);
                jLabelPatientViewRowCount.setText(" ");
                jTextFieldPatientSearchName.setEnabled(true);
                jTextFieldPatientSearchNIC.setEnabled(true);
                DefaultTableModel tm = (DefaultTableModel)jTableViewPatient.getModel();
                tm.setRowCount(0);
                
                
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, e);
            }
        
    }//GEN-LAST:event_jButtonPatientDeleteActionPerformed

    private void jTextFieldAdmissionPatientNICFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAdmissionPatientNICFocusLost
        
        try {
            
            String sql = "SELECT * FROM patient WHERE nic = '"+jTextFieldAdmissionPatientNIC.getText()+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery();
            
            if(rs2.next()){
                
                admissionSaveOption = 1;
                
                jTextFieldAdmissionPatientFirstName.setText(rs2.getString("fistname"));
                jTextFieldAdmissionPatientSecondName.setText(rs2.getString("secondname"));
                jTextFieldAdmissionPatientDateofBirth.setText(rs2.getString("dateofbirth"));
                jTextFieldAdmissionPatientAge.setText(rs2.getString("age"));
                jTextAreaAdmissionPatientAddress.setText(rs2.getString("address"));
                jTextFieldAdmissionPatientTP1.setText(rs2.getString("tp1"));
                jTextFieldAdmissionPatientTP2.setText(rs2.getString("tp2"));
                
                jTextFieldAdmissionPatientNIC.setEditable(false);
                jTextFieldAdmissionPatientFirstName.setEditable(false);
                jTextFieldAdmissionPatientSecondName.setEditable(false);
                jTextFieldAdmissionPatientDateofBirth.setEditable(false);
                jTextFieldAdmissionPatientAge.setEditable(false);
                jTextAreaAdmissionPatientAddress.setEditable(false);
                jTextFieldAdmissionPatientTP1.setEditable(false);
                jTextFieldAdmissionPatientTP2.setEditable(false);
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        
    }//GEN-LAST:event_jTextFieldAdmissionPatientNICFocusLost

    private void jComboBoxAdmissionWardNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAdmissionWardNoItemStateChanged
        
        
        
    }//GEN-LAST:event_jComboBoxAdmissionWardNoItemStateChanged

    private void jComboBoxAdmissionWardNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxAdmissionWardNoMouseClicked
        
        String av ="Available";
        
        String slct = (String) jComboBoxAdmissionWardNo.getSelectedItem();
        
        jComboBoxAdmissionWardBedNo.removeAllItems();
        jComboBoxAdmissionWardBedNo.addItem("Select Bed");
        
        try {
            
            String sql = "SELECT bedno FROM bed WHERE ward_wordno = '"+slct+"' AND Availability = '"+av+"'";
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                jComboBoxAdmissionWardBedNo.addItem(rs.getString("bedno"));
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }//GEN-LAST:event_jComboBoxAdmissionWardNoMouseClicked

    private void jComboBoxAdmissionWardNoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxAdmissionWardNoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAdmissionWardNoMouseReleased

    private void jCheckBoxAdmissionOutPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdmissionOutPatientActionPerformed
        
        jCheckBoxAdmissionWardSelect.setEnabled(false);
        jCheckBoxAdmissionRoomSelect.setEnabled(false);
        InOutPatient = 1;
        
    }//GEN-LAST:event_jCheckBoxAdmissionOutPatientActionPerformed

    private void jCheckBoxAdmissionDoctorChannelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdmissionDoctorChannelActionPerformed
        
        if(jCheckBoxAdmissionDoctorChannel.isSelected()){
            
            jTextFieldAdmissionDoctorName.setEnabled(true);
            jTextFieldAdmissionDoctorRegNo.setEnabled(true);
            jButtonAdmissionSearchDoctor.setEnabled(true);
            DoctorCahnnel = 0;
            
        }else{
        
            jTextFieldAdmissionDoctorName.setEnabled(false);
            jTextFieldAdmissionDoctorRegNo.setEnabled(false);
            jButtonAdmissionSearchDoctor.setEnabled(false);
            DoctorCahnnel = 1;
            
        }
        
    }//GEN-LAST:event_jCheckBoxAdmissionDoctorChannelActionPerformed

    SearchDoctor sd = new SearchDoctor();
    
    private void jButtonAdmissionSearchDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdmissionSearchDoctorActionPerformed
        
        sd.setVisible(true);
        
    }//GEN-LAST:event_jButtonAdmissionSearchDoctorActionPerformed

    private void jButtonAdmissionSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdmissionSaveActionPerformed
        
        if((!jCheckBoxAdmissionInPatient.isSelected()&!jCheckBoxAdmissionOutPatient.isSelected())|"".equals(jTextFieldAdmissionPatientNIC.getText())
                |"".equals(jTextFieldAdmissionPatientFirstName.getText())|"".equals(jTextFieldAdmissionPatientSecondName.getText())
                |"".equals(jTextFieldAdmissionPatientDateofBirth.getText())|"".equals(jTextFieldAdmissionPatientAge.getText())
                |"".equals(jTextAreaAdmissionPatientAddress.getText())|"".equals(jTextFieldAdmissionPatientTP1.getText())
                |"".equals(jTextFieldAdmissionPatientTP2.getText())|"".equals(jTextFieldAdmissionReson.getText())
                |(jCheckBoxAdmissionDoctorChannel.isSelected()&("".equals(jTextFieldAdmissionDoctorRegNo.getText())|"".equals(jTextFieldAdmissionDoctorName.getText())))
                |(jCheckBoxAdmissionWardSelect.isSelected()&("Select Ward".equals((String)jComboBoxAdmissionWardNo.getSelectedItem())|"Select Bed".equals((String)jComboBoxAdmissionWardBedNo.getSelectedItem())))
                |(jCheckBoxAdmissionRoomSelect.isSelected()&("Select Room".equals((String)jComboBoxAdmissionRoomNo.getSelectedItem())))
                |(jCheckBoxAdmissionInPatient.isSelected()&(!jCheckBoxAdmissionWardSelect.isSelected()&!jCheckBoxAdmissionRoomSelect.isSelected()))){
        
            
            JOptionPane.showMessageDialog(this, "Please Fill All the Fields", "Warning Message", JOptionPane.ERROR_MESSAGE);
        
        }else{
        
        
        
            try{
                String sql;
                String docReg = jTextFieldAdmissionDoctorRegNo.getText();
                String roomNo = (String) jComboBoxAdmissionRoomNo.getSelectedItem();


                if(admissionSaveOption == 1){

                    if(InOutPatient == 1){

                        if(DoctorCahnnel == 1){
                            
                             System.out.println("1 1 1");

                            docReg = "DCT000";

                            sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                    + "value(?,?,?,?,?,?,?,?,?)";
                            pst = (PreparedStatement) con.prepareStatement(sql);
                            pst.setString(1, jLabelAdmissionID.getText());
                            pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                            pst.setString(3, docReg);
                            pst.setString(4, "111");
                            pst.setString(5, "111");
                            pst.setString(6, jTextFieldAdmissionReson.getText());
                            pst.setString(7, getDate());
                            pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                            pst.setString(9, jCheckBoxAdmissionOutPatient.getText());

                            pst.execute();

                        }else if(DoctorCahnnel == 0){
                            
                             System.out.println("1 1 0");

                            sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                    + "value(?,?,?,?,?,?,?,?,?)";
                            pst = (PreparedStatement) con.prepareStatement(sql);
                            pst.setString(1, jLabelAdmissionID.getText());
                            pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                            pst.setString(3, jTextFieldAdmissionDoctorRegNo.getText());
                            pst.setString(4, "111");
                            pst.setString(5, "111");
                            pst.setString(6, jTextFieldAdmissionReson.getText());
                            pst.setString(7, getDate());
                            pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                            pst.setString(9, jCheckBoxAdmissionOutPatient.getText());

                            pst.execute();

                        }

                    }else if(InOutPatient == 0){

                        if(DoctorCahnnel == 1){

                            if(BedRoom == 1){
                                
                                 System.out.println("1 0 1 1");
                                
                                System.out.println("");

                                docReg = "DCT000";

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE room SET availability = ? WHERE roomno = '"+(String) jComboBoxAdmissionRoomNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, docReg);
                                pst.setString(4, (String) jComboBoxAdmissionRoomNo.getSelectedItem());
                                pst.setString(5, "111");
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }else if(BedRoom == 0){
                                
                                 System.out.println("1 0 1 0");

                                docReg = "DCT000";

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE bed SET availability = ? WHERE bedno = '"+(String)jComboBoxAdmissionWardBedNo.getSelectedItem()+"' AND ward_wordno"
                                        + "= '"+(String)jComboBoxAdmissionWardNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, docReg);
                                pst.setString(4, "111");
                                pst.setString(5, (String) jComboBoxAdmissionWardBedNo.getSelectedItem());
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }

                        }else if(DoctorCahnnel == 0){

                            if(BedRoom == 1){
                                
                                 System.out.println("1 0 0 1");

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE room SET availability = ? WHERE roomno = '"+(String) jComboBoxAdmissionRoomNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, jTextFieldAdmissionDoctorRegNo.getText());
                                pst.setString(4, (String) jComboBoxAdmissionRoomNo.getSelectedItem());
                                pst.setString(5, "111");
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }else if(BedRoom == 0){
                                
                                 System.out.println("1 0 0 0");

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE bed SET availability = ? WHERE bedno = '"+(String)jComboBoxAdmissionWardBedNo.getSelectedItem()+"' AND ward_wordno"
                                        + "= '"+(String)jComboBoxAdmissionWardNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, jTextFieldAdmissionDoctorRegNo.getText());
                                pst.setString(4, "111");
                                pst.setString(5, (String) jComboBoxAdmissionWardBedNo.getSelectedItem());
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }

                        }

                    }

                }else if(admissionSaveOption == 0){

                    sql = "Insert into Patient(nic,fistname,secondname,dateofbirth,age,address,tp1,tp2) value(?,?,?,?,?,?,?,?)";
                    pst = (PreparedStatement) con.prepareStatement(sql);
                    pst.setString(1, jTextFieldAdmissionPatientNIC.getText());
                    pst.setString(2, jTextFieldAdmissionPatientFirstName.getText());
                    pst.setString(3, jTextFieldAdmissionPatientSecondName.getText());
                    pst.setString(4, jTextFieldAdmissionPatientDateofBirth.getText());
                    pst.setString(5, jTextFieldAdmissionPatientAge.getText());
                    pst.setString(6, jTextAreaAdmissionPatientAddress.getText());
                    pst.setString(7, jTextFieldAdmissionPatientTP1.getText());
                    pst.setString(8, jTextFieldAdmissionPatientTP2.getText());

                    pst.execute();

                    if(InOutPatient == 1){

                        if(DoctorCahnnel == 1){
                            
                             System.out.println("0 1 1");

                            docReg = "DCT000";

                            sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                    + "value(?,?,?,?,?,?,?,?,?)";
                            pst = (PreparedStatement) con.prepareStatement(sql);
                            pst.setString(1, jLabelAdmissionID.getText());
                            pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                            pst.setString(3, docReg);
                            pst.setString(4, "111");
                            pst.setString(5, "111");
                            pst.setString(6, jTextFieldAdmissionReson.getText());
                            pst.setString(7, getDate());
                            pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                            pst.setString(9, jCheckBoxAdmissionOutPatient.getText());

                            pst.execute();

                        }else if(DoctorCahnnel == 0){
                            
                             System.out.println("0 1 0");

                            sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                    + "value(?,?,?,?,?,?,?,?,?)";
                            pst = (PreparedStatement) con.prepareStatement(sql);
                            pst.setString(1, jLabelAdmissionID.getText());
                            pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                            pst.setString(3, jTextFieldAdmissionDoctorRegNo.getText());
                            pst.setString(4, "111");
                            pst.setString(5, "111");
                            pst.setString(6, jTextFieldAdmissionReson.getText());
                            pst.setString(7, getDate());
                            pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                            pst.setString(9, jCheckBoxAdmissionOutPatient.getText());

                            pst.execute();

                        }

                    }else if(InOutPatient == 0){

                        if(DoctorCahnnel == 1){

                            if(BedRoom == 1){
                                
                                 System.out.println("0 0 1 1");

                                docReg = "DCT000";

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE room SET availability = ? WHERE roomno = '"+(String) jComboBoxAdmissionRoomNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, docReg);
                                pst.setString(4, (String) jComboBoxAdmissionRoomNo.getSelectedItem());
                                pst.setString(5, "111");
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }else if(BedRoom == 0){
                                
                                 System.out.println("0 0 1 0");

                                docReg = "DCT000";

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE bed SET availability = ? WHERE bedno = '"+(String)jComboBoxAdmissionWardBedNo.getSelectedItem()+"' AND ward_wordno"
                                        + "= '"+(String)jComboBoxAdmissionWardNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, docReg);
                                pst.setString(4, "111");
                                pst.setString(5, (String) jComboBoxAdmissionWardBedNo.getSelectedItem());
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }

                        }else if(DoctorCahnnel == 0){

                            if(BedRoom == 1){
                                
                                 System.out.println("0 0 0 1");

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE room SET availability = ? WHERE roomno = '"+(String) jComboBoxAdmissionRoomNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, jTextFieldAdmissionDoctorRegNo.getText());
                                pst.setString(4, (String) jComboBoxAdmissionRoomNo.getSelectedItem());
                                pst.setString(5, "111");
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }else if(BedRoom == 0){
                                
                                 System.out.println("0 0 0 0");

                                sql = "INSERT INTO admission (admission_id,patient_nic,doctor_reg_no,room_roomno,bed_bedno,reason,admissiondate,privacy,admissiontype)"
                                        + "value(?,?,?,?,?,?,?,?,?)";

                                String sql2 = "UPDATE bed SET availability = ? WHERE bedno = '"+(String)jComboBoxAdmissionWardBedNo.getSelectedItem()+"' AND ward_wordno"
                                        + "= '"+(String)jComboBoxAdmissionWardNo.getSelectedItem()+"'";

                                pst = (PreparedStatement) con.prepareStatement(sql);
                                pst.setString(1, jLabelAdmissionID.getText());
                                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
                                pst.setString(3, jTextFieldAdmissionDoctorRegNo.getText());
                                pst.setString(4, "111");
                                pst.setString(5, (String) jComboBoxAdmissionWardBedNo.getSelectedItem());
                                pst.setString(6, jTextFieldAdmissionReson.getText());
                                pst.setString(7, getDate());
                                pst.setString(8, (String) jComboBoxAdmissionRoomPrivacy.getSelectedItem());
                                pst.setString(9, jCheckBoxAdmissionInPatient.getText());

                                pst.execute();

                                pst = (PreparedStatement) con.prepareStatement(sql2);
                                pst.setString(1, "Uavailable");

                                pst.execute();

                            }

                        }

                    }

                }

//                sql = "INSERT INTO guardian (GuardianID,Patient_NIC,Name,Relationship,address,TP) values (?,?,?,?,?,?)";
//                pst = (PreparedStatement) con.prepareStatement(sql);
//                pst.setString(1, GuardianID());
//                pst.setString(2, jTextFieldAdmissionPatientNIC.getText());
//                pst.setString(3, jTextFieldAdmissionGuardianName.getText());
//                pst.setString(4, jTextFieldAdmissionGuardianRelationship.getText());
//                pst.setString(5, jTextAreaAdmissionGuardianAddress.getText());
//                pst.setString(6, jTextFieldAdmissionGurdianTP.getText());

                //pst.execute();

                jCheckBoxAdmissionInPatient.setSelected(false);
                jCheckBoxAdmissionOutPatient.setSelected(false);
                jTextFieldAdmissionPatientNIC.setText("");
                jTextFieldAdmissionPatientFirstName.setText("");
                jTextFieldAdmissionPatientSecondName.setText("");
                jTextFieldAdmissionPatientDateofBirth.setText("");
                jTextFieldAdmissionPatientAge.setText("");
                jTextAreaAdmissionPatientAddress.setText("");
                jTextFieldAdmissionPatientTP1.setText("");
                jTextFieldAdmissionPatientTP2.setText("");
                jCheckBoxAdmissionDoctorChannel.setSelected(false);
                jTextFieldAdmissionDoctorName.setText("");
                jTextFieldAdmissionDoctorName.setEnabled(false);
                jTextFieldAdmissionDoctorRegNo.setText("");
                jTextFieldAdmissionDoctorRegNo.setEnabled(false);
                jCheckBoxAdmissionWardSelect.setSelected(false);
                jCheckBoxAdmissionRoomSelect.setSelected(false);
                jComboBoxAdmissionWardNo.setSelectedIndex(0);
                jComboBoxAdmissionWardNo.setEnabled(false);
                jComboBoxAdmissionWardBedNo.setSelectedIndex(0);
                jComboBoxAdmissionWardBedNo.setEnabled(false);
                jComboBoxAdmissionRoomNo.setSelectedIndex(0);
                jComboBoxAdmissionRoomNo.setEnabled(false);
                jComboBoxAdmissionRoomPrivacy.setSelectedIndex(0);
                jComboBoxAdmissionRoomPrivacy.setEnabled(false);
                jTextFieldAdmissionReson.setText("");
                jTextFieldAdmissionGuardianName.setText("");
                jTextFieldAdmissionGuardianRelationship.setText("");
                jTextAreaAdmissionGuardianAddress.setText("");
                jTextFieldAdmissionGurdianTP.setText("");
                jLabelAdmissionID.setText(createAdmissionID());
                
                JOptionPane.showMessageDialog(this, "Successfully Saved");

            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_jButtonAdmissionSaveActionPerformed

    private void jCheckBoxAdmissionWardSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdmissionWardSelectActionPerformed
        
        jComboBoxAdmissionWardNo.setEnabled(true);
        jComboBoxAdmissionWardBedNo.setEnabled(true);
        jComboBoxAdmissionRoomNo.setEnabled(false);
        jComboBoxAdmissionRoomPrivacy.setEnabled(false);
        BedRoom = 0;
        
    }//GEN-LAST:event_jCheckBoxAdmissionWardSelectActionPerformed

    private void jCheckBoxAdmissionRoomSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdmissionRoomSelectActionPerformed
        
        jComboBoxAdmissionWardNo.setEnabled(false);
        jComboBoxAdmissionWardBedNo.setEnabled(false);
        jComboBoxAdmissionRoomNo.setEnabled(true);
        jComboBoxAdmissionRoomPrivacy.setEnabled(true);
        BedRoom = 1;
        
    }//GEN-LAST:event_jCheckBoxAdmissionRoomSelectActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Home h = new Home();
                h.setVisible(true);
                h.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                Dimension h1 = Toolkit.getDefaultToolkit().getScreenSize();
                System.out.println(h1);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel admissionPanel;
    private javax.swing.JButton buttonAddNewDoctor;
    private javax.swing.JButton buttonAddNewPatient;
    private javax.swing.JButton buttonAddNewTreatment;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonMinimize;
    private javax.swing.JPanel diognosisPanel;
    private javax.swing.JPanel doctorPanel;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAddDoctor;
    private javax.swing.JButton jButtonAddPatient;
    private javax.swing.JButton jButtonAdmission;
    private javax.swing.JButton jButtonAdmissionSave;
    private javax.swing.JButton jButtonAdmissionSearchDoctor;
    private javax.swing.JButton jButtonDiognosis;
    private javax.swing.JButton jButtonDoctor;
    private javax.swing.JButton jButtonDoctorCancel;
    private javax.swing.JButton jButtonDoctorDelete;
    private javax.swing.JButton jButtonDoctorEdit;
    private javax.swing.JButton jButtonDoctorUpdate;
    private javax.swing.JButton jButtonPatient;
    private javax.swing.JButton jButtonPatientCancel;
    private javax.swing.JButton jButtonPatientDelete;
    private javax.swing.JButton jButtonPatientEdit;
    private javax.swing.JButton jButtonPatientUpdate;
    private javax.swing.JButton jButtonPayments;
    private javax.swing.JButton jButtonTreatment;
    private javax.swing.JButton jButtonTreatmentCancel;
    private javax.swing.JButton jButtonTreatmentDelete;
    private javax.swing.JButton jButtonTreatmentEdit;
    private javax.swing.JButton jButtonTreatmentUpdate;
    private javax.swing.JButton jButtonTreatmntAddTreatment;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBoxAdmissionDoctorChannel;
    private javax.swing.JCheckBox jCheckBoxAdmissionInPatient;
    private javax.swing.JCheckBox jCheckBoxAdmissionOutPatient;
    private javax.swing.JCheckBox jCheckBoxAdmissionRoomSelect;
    private javax.swing.JCheckBox jCheckBoxAdmissionWardSelect;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox jComboBoxAdmissionRoomNo;
    private javax.swing.JComboBox jComboBoxAdmissionRoomPrivacy;
    private javax.swing.JComboBox jComboBoxAdmissionWardBedNo;
    private javax.swing.JComboBox jComboBoxAdmissionWardNo;
    private javax.swing.JComboBox jComboBoxDoctorJobType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelAdmissionID;
    private javax.swing.JLabel jLabelDoctorViewRowCount;
    private javax.swing.JLabel jLabelPatientViewRowCount;
    private javax.swing.JLabel jLabelTreatmentViewRowCount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTableDoctorView;
    private javax.swing.JTable jTableTreatmentView;
    private javax.swing.JTable jTableViewPatient;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextAreaAdmissionGuardianAddress;
    private javax.swing.JTextArea jTextAreaAdmissionPatientAddress;
    private javax.swing.JTextArea jTextAreaDoctorAddress;
    private javax.swing.JTextArea jTextAreaPatientAddress;
    private javax.swing.JTextArea jTextAreaTreatmentDescription;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    public static javax.swing.JTextField jTextFieldAdmissionDoctorName;
    public static javax.swing.JTextField jTextFieldAdmissionDoctorRegNo;
    private javax.swing.JTextField jTextFieldAdmissionGuardianName;
    private javax.swing.JTextField jTextFieldAdmissionGuardianRelationship;
    private javax.swing.JTextField jTextFieldAdmissionGurdianTP;
    private javax.swing.JTextField jTextFieldAdmissionPatientAge;
    private javax.swing.JTextField jTextFieldAdmissionPatientDateofBirth;
    private javax.swing.JTextField jTextFieldAdmissionPatientFirstName;
    private javax.swing.JTextField jTextFieldAdmissionPatientNIC;
    private javax.swing.JTextField jTextFieldAdmissionPatientSecondName;
    private javax.swing.JTextField jTextFieldAdmissionPatientTP1;
    private javax.swing.JTextField jTextFieldAdmissionPatientTP2;
    private javax.swing.JTextField jTextFieldAdmissionReson;
    private javax.swing.JTextField jTextFieldDoctorFirstName;
    private javax.swing.JTextField jTextFieldDoctorRegNo;
    private javax.swing.JTextField jTextFieldDoctorSearchName;
    private javax.swing.JTextField jTextFieldDoctorSearchRegNo;
    private javax.swing.JTextField jTextFieldDoctorSecondName;
    private javax.swing.JTextField jTextFieldDoctorSpeciality;
    private javax.swing.JTextField jTextFieldDoctorTP1;
    private javax.swing.JTextField jTextFieldDoctorTP2;
    private javax.swing.JTextField jTextFieldPatientAge;
    private javax.swing.JTextField jTextFieldPatientDateofBirth;
    private javax.swing.JTextField jTextFieldPatientFirstName;
    private javax.swing.JTextField jTextFieldPatientNIC;
    private javax.swing.JTextField jTextFieldPatientSearchNIC;
    private javax.swing.JTextField jTextFieldPatientSearchName;
    private javax.swing.JTextField jTextFieldPatientSecondName;
    private javax.swing.JTextField jTextFieldPatientTP1;
    private javax.swing.JTextField jTextFieldPatientTP2;
    private javax.swing.JTextField jTextFieldTreatmentCode;
    private javax.swing.JTextField jTextFieldTreatmentName;
    private javax.swing.JTextField jTextFieldTreatmentSearchCode;
    private javax.swing.JTextField jTextFieldTreatmentSearchName;
    private javax.swing.JTextField jTextFieldTreatmentUnitPrice;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelTime;
    private javax.swing.JPanel parentPannel;
    private javax.swing.JPanel paymentDetailsPanel;
    private javax.swing.JPanel ptientPanel;
    private javax.swing.JPanel treatmentPanel;
    // End of variables declaration//GEN-END:variables
}