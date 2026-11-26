/*
 * The MIT License
 *
 * Copyright 2024-2026 Μαρέτσικος Χρῆστος.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author Μαρέτσικος Χρῆστος
*/

package PolytLex_plus;

import static PolytLex_plus.PolytLex_plus.readFromFile;
import static PolytLex_plus.PolytLex_plus.saveToFile;
import static PolytLex_plus.Select_Lexicon_Save.path;
import static PolytLex_plus.Select_Lexicon_Save.pathFakelos;
import static PolytLex_plus.Select_Lexicon_Save.pathGramma;
import static PolytLex_plus.Select_Lexicon_Save.pathPG;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.accessibility.AccessibleText.WORD;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AttributeSet;
import javax.swing.text.EditorKit;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;





public class Database extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Database.class.getName());

    /**
     * Creates new form Database
     */
    
    int numR=127;
    int numG=127;
    int numB=127;
    Color mycolor =  new Color(numR, numG, numB); 
    
    int BG_Color_btnR;
    int BG_Color_btnG;
    int BG_Color_btnB;
    Color BG_Color_btn = new Color (BG_Color_btnR, BG_Color_btnG, BG_Color_btnB);
    
    int FG_Color_btnR;
    int FG_Color_btnG;
    int FG_Color_btnB;
    Color FG_Color_btn = new Color (FG_Color_btnR, FG_Color_btnG, FG_Color_btnB);

    String setMyLang="Greek";
    String setMyLangChars="gr" ;
    String pathMyLang="./languages/Greek/database_gr.lang";
    String myForm="/database_";
     
    double brightness;
    Color selectItemColor;
    
    SimpleAttributeSet features1;
    MutableAttributeSet features2;
    Color backColor, foreColor;
    
    TableColumn mycolumn;
    
    int setMyTextSize=17;
    
    int numSCR;
    int numSCG;
    int numSCB;
    Color mySelColor =  new Color(numSCR, numSCG, numSCB); 
    Color backupMySelColor;
    
    int Yellow_NumR=255, Yellow_NumG=225, Yellow_NumB=100;
    Color mySelected_Color =  new Color(Yellow_NumR, Yellow_NumG, Yellow_NumB);
    
    Color selectedColor;
    
    
    public Database() {
        initComponents();
        
        JFrame.setDefaultLookAndFeelDecorated(true); //for Title and Icon for Linux
        
        Image small_logo = new ImageIcon(this.getClass().getResource("/PolytLexplus_logo_256x256.png")).getImage();
        this.setIconImage(small_logo);
        
        setDefaultCloseOperation(Database.DISPOSE_ON_CLOSE);
        
    }
    
        // SOS for ComboBox
    
   class CustomComboBoxRenderer extends DefaultListCellRenderer {
       public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected,  cellHasFocus);
        lbl.setBackground(BG_Color_btn);
        lbl.setForeground(FG_Color_btn);
        if(isSelected) setBackground(selectItemColor);
     
        return lbl;
         
        }
    }
    
    int k;
    int myValue;
    int selectedRow, selectedCol;
    String selectedWord;
    int totalRows, nowTotalRows, fixedRow;
    
    String setMyFont="Arial";
    String myLink;
    
    Font myFont;
    static String mySelectedFont, mySelectedFontSize;
    static int SelectedFontSize;
    
    String oper_system, os, osFont;
    Boolean flag_Bold=false;
    Boolean flag_addWords=false;
    int insertId;
    String noString="";
    int myTotalWords;
    
    static int addWords;
    
    int indexFont, indexFontSize, hereFont, hereFontSize;
    String currentFont, whereIsThisFont; 
    int currentFontSize;
    String whereIsThisFontSize; 

   //String myPathFakelos = ("./data/lexikochristi/");
   //String myPathPG = ("olatalexika_");
   //String myPathGramma=("ola_ta_grammata.dat");
   // String myPath = (myPathFakelos + myPathPG + myPathGramma); 
    
    int rowCount;
    
    //String path_userLex=("./data/lexikochristi/lc_ola_ta_grammata.dat");
    String path_for_letter_allLex=" ";
    String path_allLex=("./data/olatalexika/olatalexika_ola_ta_grammata.dat");
    
    Boolean compareFlag;
    Boolean manyWordsFlag;
    Boolean deleteFlag=false;
    
    static String name;
    static String stletter;
    
    String backupWord;
    
    String backupMyFolder, backupMyPath;
    String beforeWord="";
    
    static int flagRadioButton;
    boolean renumberFlag=false;
    
    String searchBackup;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser_1 = new javax.swing.JColorChooser();
        jFileChooser_1 = new javax.swing.JFileChooser();
        jPanel_Database_ = new javax.swing.JPanel();
        jLabel_Search_ = new javax.swing.JLabel();
        jTextField_searchTxt_ = new javax.swing.JTextField();
        jCheckBox_EnableEditing_ = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DB_ = new javax.swing.JTable();
        jLabel_Source_ = new javax.swing.JLabel();
        jLabel_totalRecords_ = new javax.swing.JLabel();
        jTextField_totalRecords_ = new javax.swing.JTextField();
        jButton_Save_ = new javax.swing.JButton();
        jButton_Font_Decrease_ = new javax.swing.JButton();
        jButton_Font_Increase_ = new javax.swing.JButton();
        jButton_OpenLink_ = new javax.swing.JButton();
        jTextField_Link_ = new javax.swing.JTextField();
        jComboBox_SystemFonts_ = new javax.swing.JComboBox<>();
        jComboBox_FontSize_ = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jButton_DeleteRecord_ = new javax.swing.JButton();
        jButton_AddRecord_ = new javax.swing.JButton();
        jButton_UpdateRecord_ = new javax.swing.JButton();
        jButton_Refresh_ = new javax.swing.JButton();
        jCheckBox_Enable_Asceding_Order_ = new javax.swing.JCheckBox();
        jButton_Asceding_Order_ = new javax.swing.JButton();
        jButton_AddΜultipleRecords_ = new javax.swing.JButton();
        jButton_TextForeColor_ = new javax.swing.JButton();
        jButton_TextBackColor_ = new javax.swing.JButton();
        jButton_Font_Bold_ = new javax.swing.JToggleButton();
        tButton_Italic_ = new javax.swing.JToggleButton();
        tButton_Underline_ = new javax.swing.JToggleButton();
        jButton_LeftAlignment_ = new javax.swing.JButton();
        jButton_CenterAlignment_ = new javax.swing.JButton();
        jButton_RightAlignment_ = new javax.swing.JButton();
        jButton_SelectLexiconSave_ = new javax.swing.JButton();
        jButtonColor_TextForeDefaultColor_ = new javax.swing.JButton();
        jButtonColor_TextBackDefaultColor_ = new javax.swing.JButton();
        jButton_Big_Letter_ = new javax.swing.JButton();
        jButton_Small_Letter_ = new javax.swing.JButton();
        jTextField_FontSize_ = new javax.swing.JTextField();
        jButton_X_Clear_ = new javax.swing.JButton();
        jButton_JustifiedAlignment_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Βάση Δεδομένων");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel_Database_.setForeground(new java.awt.Color(224, 244, 255));

        jLabel_Search_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_Search_.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Search_.setText("Ἀναζήτηση:");
        jLabel_Search_.setPreferredSize(new java.awt.Dimension(100, 40));

        jTextField_searchTxt_.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jTextField_searchTxt_.setToolTipText("Πληκτρολογῆστε τὴ λέξη ἢ μέρος τῆς λέξης");
        jTextField_searchTxt_.setMinimumSize(new java.awt.Dimension(40, 20));
        jTextField_searchTxt_.setPreferredSize(new java.awt.Dimension(237, 40));
        jTextField_searchTxt_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_searchTxt_MouseClicked(evt);
            }
        });
        jTextField_searchTxt_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchTxt_KeyReleased(evt);
            }
        });

        jCheckBox_EnableEditing_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox_EnableEditing_.setText("Εἰσαγωγὴ / Ἐπεξεργασία τοῦ λήμματος");
        jCheckBox_EnableEditing_.setPreferredSize(new java.awt.Dimension(300, 40));
        jCheckBox_EnableEditing_.addActionListener(this::jCheckBox_EnableEditing_ActionPerformed);

        jTable_DB_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable_DB_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Α/Α", "Λήμμα", "Βικιλεξικὸ", "Παρατηρήσεις", "Σχόλια"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_DB_.setColumnSelectionAllowed(true);
        jTable_DB_.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_DB_.setName(""); // NOI18N
        jTable_DB_.setRowHeight(22);
        jTable_DB_.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_DB_.setShowGrid(true);
        jTable_DB_.getTableHeader().setReorderingAllowed(false);
        jTable_DB_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DB_MouseClicked(evt);
            }
        });
        jTable_DB_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable_DB_KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_DB_);
        jTable_DB_.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable_DB_.getColumnModel().getColumnCount() > 0) {
            jTable_DB_.getColumnModel().getColumn(0).setResizable(false);
            jTable_DB_.getColumnModel().getColumn(0).setPreferredWidth(3);
            jTable_DB_.getColumnModel().getColumn(1).setResizable(false);
            jTable_DB_.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable_DB_.getColumnModel().getColumn(2).setResizable(false);
            jTable_DB_.getColumnModel().getColumn(2).setPreferredWidth(25);
            jTable_DB_.getColumnModel().getColumn(3).setResizable(false);
            jTable_DB_.getColumnModel().getColumn(3).setPreferredWidth(25);
            jTable_DB_.getColumnModel().getColumn(4).setResizable(false);
            jTable_DB_.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

        jLabel_Source_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_Source_.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Source_.setText("Βικιλεξικό:");
        jLabel_Source_.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel_totalRecords_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_totalRecords_.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_totalRecords_.setText("Σύνολο ἐγγραφῶν:");
        jLabel_totalRecords_.setPreferredSize(new java.awt.Dimension(200, 30));

        jTextField_totalRecords_.setEditable(false);
        jTextField_totalRecords_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_totalRecords_.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_totalRecords_.setText("0");
        jTextField_totalRecords_.setFocusable(false);
        jTextField_totalRecords_.setPreferredSize(new java.awt.Dimension(100, 30));

        jButton_Save_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton_Save_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diskette_save_icon.png"))); // NOI18N
        jButton_Save_.setToolTipText("Ἀποθήκευση");
        jButton_Save_.setEnabled(false);
        jButton_Save_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Save_.addActionListener(this::jButton_Save_ActionPerformed);

        jButton_Font_Decrease_.setText("A");
        jButton_Font_Decrease_.setToolTipText("Μείωση μεγέθους γραμματοσειρᾶς");
        jButton_Font_Decrease_.setEnabled(false);
        jButton_Font_Decrease_.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Font_Decrease_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Font_Decrease_.addActionListener(this::jButton_Font_Decrease_ActionPerformed);

        jButton_Font_Increase_.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton_Font_Increase_.setText("A");
        jButton_Font_Increase_.setToolTipText("Αὔξηση μεγέθους γραμματοσειρᾶς");
        jButton_Font_Increase_.setEnabled(false);
        jButton_Font_Increase_.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Font_Increase_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Font_Increase_.addActionListener(this::jButton_Font_Increase_ActionPerformed);

        jButton_OpenLink_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton_OpenLink_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/link_icon.png"))); // NOI18N
        jButton_OpenLink_.setToolTipText("Ἄνοιγμα τοῦ συνδέσμου");
        jButton_OpenLink_.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton_OpenLink_.addActionListener(this::jButton_OpenLink_ActionPerformed);

        jTextField_Link_.setEditable(false);
        jTextField_Link_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Link_.setText("https://el.wiktionary.org/wiki/");
        jTextField_Link_.setToolTipText("Σύνδεσμος");
        jTextField_Link_.setFocusable(false);
        jTextField_Link_.setPreferredSize(new java.awt.Dimension(343, 30));
        jTextField_Link_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_Link_KeyReleased(evt);
            }
        });

        jComboBox_SystemFonts_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_SystemFonts_.setMaximumRowCount(25);
        jComboBox_SystemFonts_.setToolTipText("Γραμματοσειρὰ");
        jComboBox_SystemFonts_.setEnabled(false);
        jComboBox_SystemFonts_.setPreferredSize(new java.awt.Dimension(270, 40));
        jComboBox_SystemFonts_.addActionListener(this::jComboBox_SystemFonts_ActionPerformed);

        jComboBox_FontSize_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_FontSize_.setMaximumRowCount(25);
        jComboBox_FontSize_.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "33", "37", "40", "44", "48", "52", "56", "72", "80" }));
        jComboBox_FontSize_.setSelectedIndex(9);
        jComboBox_FontSize_.setToolTipText("Μέγεθος γραμματοσειρᾶς");
        jComboBox_FontSize_.setEnabled(false);
        jComboBox_FontSize_.setPreferredSize(new java.awt.Dimension(86, 40));
        jComboBox_FontSize_.addActionListener(this::jComboBox_FontSize_ActionPerformed);

        textPane.setEditable(false);
        textPane.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textPaneMouseClicked(evt);
            }
        });
        textPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textPaneKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(textPane);

        jButton_DeleteRecord_.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_DeleteRecord_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data_delete_icon.png"))); // NOI18N
        jButton_DeleteRecord_.setToolTipText("Ἀφαίρεση ἐγγραφῆς");
        jButton_DeleteRecord_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_DeleteRecord_.addActionListener(this::jButton_DeleteRecord_ActionPerformed);

        jButton_AddRecord_.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_AddRecord_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data_insert_icon.png"))); // NOI18N
        jButton_AddRecord_.setToolTipText("Προσθήκη ἐγγραφῆς");
        jButton_AddRecord_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_AddRecord_.addActionListener(this::jButton_AddRecord_ActionPerformed);

        jButton_UpdateRecord_.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_UpdateRecord_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data_update_icon.png"))); // NOI18N
        jButton_UpdateRecord_.setToolTipText("Ἐνημέρωση ἐγγραφῆς");
        jButton_UpdateRecord_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_UpdateRecord_.addActionListener(this::jButton_UpdateRecord_ActionPerformed);

        jButton_Refresh_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reload_icon.png"))); // NOI18N
        jButton_Refresh_.setToolTipText("Ἀνανέωση");
        jButton_Refresh_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Refresh_.addActionListener(this::jButton_Refresh_ActionPerformed);

        jCheckBox_Enable_Asceding_Order_.setToolTipText("Ἐνεργοποίηση ταξινόμησης");
        jCheckBox_Enable_Asceding_Order_.addActionListener(this::jCheckBox_Enable_Asceding_Order_ActionPerformed);

        jButton_Asceding_Order_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/order_icon.png"))); // NOI18N
        jButton_Asceding_Order_.setToolTipText("Αὔξουσα ταξινόμηση τῶν λέξεων καὶ ἀναρίθμηση τῆς στήλης Α/Α");
        jButton_Asceding_Order_.setEnabled(false);
        jButton_Asceding_Order_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Asceding_Order_.addActionListener(this::jButton_Asceding_Order_ActionPerformed);

        jButton_AddΜultipleRecords_.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_AddΜultipleRecords_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data_many_insert_icon.png"))); // NOI18N
        jButton_AddΜultipleRecords_.setToolTipText("Προσθήκη πολλῶν ἐγγραφῶν");
        jButton_AddΜultipleRecords_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_AddΜultipleRecords_.addActionListener(this::jButton_AddΜultipleRecords_ActionPerformed);

        jButton_TextForeColor_.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_TextForeColor_.setText("A");
        jButton_TextForeColor_.setToolTipText("Χρῶμα γραμματοσειρᾶς");
        jButton_TextForeColor_.setEnabled(false);
        jButton_TextForeColor_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_TextForeColor_.addActionListener(this::jButton_TextForeColor_ActionPerformed);

        jButton_TextBackColor_.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_TextBackColor_.setText("A");
        jButton_TextBackColor_.setToolTipText("Χρῶμα ἐπισήμανσης κειμένου");
        jButton_TextBackColor_.setEnabled(false);
        jButton_TextBackColor_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_TextBackColor_.addActionListener(this::jButton_TextBackColor_ActionPerformed);

        jButton_Font_Bold_.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_Font_Bold_.setText("B");
        jButton_Font_Bold_.setToolTipText("Ἔντονη γραφὴ");
        jButton_Font_Bold_.setEnabled(false);
        jButton_Font_Bold_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Font_Bold_.addActionListener(this::jButton_Font_Bold_ActionPerformed);

        tButton_Italic_.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        tButton_Italic_.setText("I");
        tButton_Italic_.setToolTipText("Πλάγια γραφὴ");
        tButton_Italic_.setEnabled(false);
        tButton_Italic_.setPreferredSize(new java.awt.Dimension(40, 40));
        tButton_Italic_.addActionListener(this::tButton_Italic_ActionPerformed);

        tButton_Underline_.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tButton_Underline_.setText("U");
        tButton_Underline_.setToolTipText("Ὑπογράμμιση");
        tButton_Underline_.setEnabled(false);
        tButton_Underline_.setPreferredSize(new java.awt.Dimension(40, 40));
        tButton_Underline_.addActionListener(this::tButton_Underline_ActionPerformed);

        jButton_LeftAlignment_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/left_alignment_icon.png"))); // NOI18N
        jButton_LeftAlignment_.setToolTipText("Στοίχιση κειμένου ἀριστερὰ");
        jButton_LeftAlignment_.setEnabled(false);
        jButton_LeftAlignment_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_LeftAlignment_.addActionListener(this::jButton_LeftAlignment_ActionPerformed);

        jButton_CenterAlignment_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/center_alignment_icon.png"))); // NOI18N
        jButton_CenterAlignment_.setToolTipText("Στοίχηση στὸ κέντρο");
        jButton_CenterAlignment_.setEnabled(false);
        jButton_CenterAlignment_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_CenterAlignment_.addActionListener(this::jButton_CenterAlignment_ActionPerformed);

        jButton_RightAlignment_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/right_alignment_icon.png"))); // NOI18N
        jButton_RightAlignment_.setToolTipText("Στοίχιση κειμένου δεξιὰ");
        jButton_RightAlignment_.setEnabled(false);
        jButton_RightAlignment_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_RightAlignment_.addActionListener(this::jButton_RightAlignment_ActionPerformed);

        jButton_SelectLexiconSave_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lexicon_path_icon.png"))); // NOI18N
        jButton_SelectLexiconSave_.setToolTipText("Ἐπιλογὴ λεξικοῦ");
        jButton_SelectLexiconSave_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_SelectLexiconSave_.addActionListener(this::jButton_SelectLexiconSave_ActionPerformed);

        jButtonColor_TextForeDefaultColor_.setBackground(new java.awt.Color(255, 0, 0));
        jButtonColor_TextForeDefaultColor_.setToolTipText("Προεπιλεγμένο χρῶμα");
        jButtonColor_TextForeDefaultColor_.setEnabled(false);
        jButtonColor_TextForeDefaultColor_.setPreferredSize(new java.awt.Dimension(40, 15));
        jButtonColor_TextForeDefaultColor_.addActionListener(this::jButtonColor_TextForeDefaultColor_ActionPerformed);

        jButtonColor_TextBackDefaultColor_.setBackground(new java.awt.Color(255, 255, 0));
        jButtonColor_TextBackDefaultColor_.setToolTipText("Προεπιλεγμένο χρῶμα");
        jButtonColor_TextBackDefaultColor_.setEnabled(false);
        jButtonColor_TextBackDefaultColor_.setPreferredSize(new java.awt.Dimension(40, 15));
        jButtonColor_TextBackDefaultColor_.addActionListener(this::jButtonColor_TextBackDefaultColor_ActionPerformed);

        jButton_Big_Letter_.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton_Big_Letter_.setText("A");
        jButton_Big_Letter_.setToolTipText("Αὔξηση μεγέθους γραμματοσειρᾶς");
        jButton_Big_Letter_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Big_Letter_.addActionListener(this::jButton_Big_Letter_ActionPerformed);

        jButton_Small_Letter_.setText("A");
        jButton_Small_Letter_.setToolTipText("Μείωση μεγέθους γραμματοσειρᾶς");
        jButton_Small_Letter_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_Small_Letter_.addActionListener(this::jButton_Small_Letter_ActionPerformed);

        jTextField_FontSize_.setEditable(false);
        jTextField_FontSize_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_FontSize_.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_FontSize_.setToolTipText("Μέγεθος γραμματοσειρᾶς");
        jTextField_FontSize_.setFocusable(false);
        jTextField_FontSize_.setPreferredSize(new java.awt.Dimension(40, 40));

        jButton_X_Clear_.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton_X_Clear_.setText("X");
        jButton_X_Clear_.setToolTipText("Καθαρισμὸς");
        jButton_X_Clear_.addActionListener(this::jButton_X_Clear_ActionPerformed);

        jButton_JustifiedAlignment_.setIcon(new javax.swing.ImageIcon(getClass().getResource("/full_alignment_icon.png"))); // NOI18N
        jButton_JustifiedAlignment_.setToolTipText("Πλήρης στοίχιση");
        jButton_JustifiedAlignment_.setEnabled(false);
        jButton_JustifiedAlignment_.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton_JustifiedAlignment_.addActionListener(this::jButton_JustifiedAlignment_ActionPerformed);

        javax.swing.GroupLayout jPanel_Database_Layout = new javax.swing.GroupLayout(jPanel_Database_);
        jPanel_Database_.setLayout(jPanel_Database_Layout);
        jPanel_Database_Layout.setHorizontalGroup(
            jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Database_Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Database_Layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jLabel_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField_searchTxt_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_X_Clear_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Database_Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextField_FontSize_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Big_Letter_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Small_Letter_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_totalRecords_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_totalRecords_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_Database_Layout.createSequentialGroup()
                        .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_EnableEditing_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Save_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Database_Layout.createSequentialGroup()
                                .addComponent(jComboBox_SystemFonts_, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_FontSize_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Font_Increase_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Font_Decrease_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Database_Layout.createSequentialGroup()
                                .addComponent(jButton_LeftAlignment_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_CenterAlignment_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Database_Layout.createSequentialGroup()
                                .addComponent(jButton_Font_Bold_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tButton_Italic_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tButton_Underline_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_TextBackColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel_Database_Layout.createSequentialGroup()
                                .addComponent(jButton_RightAlignment_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_JustifiedAlignment_, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonColor_TextBackDefaultColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonColor_TextForeDefaultColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_TextForeColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_Database_Layout.createSequentialGroup()
                        .addComponent(jButton_Asceding_Order_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox_Enable_Asceding_Order_)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Refresh_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_DeleteRecord_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_AddΜultipleRecords_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_AddRecord_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_UpdateRecord_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_SelectLexiconSave_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Source_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_Link_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_OpenLink_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel_Database_Layout.setVerticalGroup(
            jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Database_Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton_Font_Decrease_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Font_Increase_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_SystemFonts_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_FontSize_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Font_Bold_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tButton_Italic_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tButton_Underline_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_TextBackColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_TextForeColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_Search_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_searchTxt_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_EnableEditing_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_X_Clear_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Database_Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel_Database_Layout.createSequentialGroup()
                        .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Database_Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonColor_TextForeDefaultColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonColor_TextBackDefaultColor_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton_RightAlignment_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_CenterAlignment_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_LeftAlignment_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton_JustifiedAlignment_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel_Database_Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Save_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel_Database_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton_OpenLink_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Link_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Big_Letter_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_DeleteRecord_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_AddRecord_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_UpdateRecord_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Refresh_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_Enable_Asceding_Order_)
                    .addComponent(jButton_Asceding_Order_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Small_Letter_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_AddΜultipleRecords_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Source_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_SelectLexiconSave_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_totalRecords_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_totalRecords_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_FontSize_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Database_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Database_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_DB_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DB_MouseClicked
        
        selectedCol=jTable_DB_.getSelectedColumn();
        selectedRow=jTable_DB_.getSelectedRow();
        //System.out.println("Θέση στήλης: " + selectedCol);
        //System.out.println("");

        if (selectedCol==0){
            textPane.setContentType("text/txt");
            //System.out.println("error: 00x2300");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
            return;
        }
        else if (selectedCol==1){
            textPane.setContentType("text/text");
            backupWord=textPane.getText();
            name=textPane.getText();
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
            
        }
        
        else if (selectedCol==2){
            textPane.setContentType("text/rtf");
            //System.out.println("error: 00x2302");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);  
            //System.out.println("");
        }

        else if (selectedCol==3){
            textPane.setContentType("text/rtf");
            //System.out.println("error: 00x2303");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
        }        
        
        else if (selectedCol==4){
            textPane.setContentType("text/rtf");
            //System.out.println("error: 00x2304");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
        }        
        
        else{
           
        }
                      
        DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
        textPane.setText(model.getValueAt(selectedRow, selectedCol).toString());
        selectedWord=(model.getValueAt(selectedRow, 1).toString());
        //System.out.println("Πραγματικὴ θέση ἐγγραφῆς " + selectedRow + ": " + selectedWord);
        jTextField_Link_.setText("https://el.wiktionary.org/wiki/" + model.getValueAt(selectedRow, 1).toString());
        nowTotalRows=jTable_DB_.getRowCount();
        
        
            if (nowTotalRows < totalRows){
                fixedRow=jTable_DB_.getSelectedRow();
                if (jTable_DB_.getRowSorter()!=null) {
                    fixedRow = jTable_DB_.getRowSorter().convertRowIndexToModel(fixedRow);
                    //System.out.println("Πραγματικὴ θέση ἐγγραφῆς: " + fixedRow + ": " + model.getValueAt(fixedRow, 1).toString());
                    jTextField_Link_.setText("https://el.wiktionary.org/wiki/" + model.getValueAt(fixedRow, 1).toString());
                    textPane.setText(model.getValueAt(fixedRow, selectedCol).toString());
                    selectedWord=(model.getValueAt(fixedRow, 1).toString()); 
                    //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows)); 
                    jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows));
                   
                   
                }
            }
            else {
                //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows)); 
                jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows));
                
            }
       
        //System.out.println("");
        //System.out.println("========================");
        //System.out.println(""); 
        
        textPane.setCaretPosition(0);
        
    }//GEN-LAST:event_jTable_DB_MouseClicked

    private void jCheckBox_EnableEditing_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_EnableEditing_ActionPerformed
        
        if (jCheckBox_EnableEditing_.isSelected()){
            textPane.setEditable(true);
            textPane.setBorder(BorderFactory.createLineBorder(Color.green, 2));
            jButton_Save_.setEnabled(true);
                
            jComboBox_SystemFonts_.setEnabled(true);
            jComboBox_FontSize_.setEnabled(true);
            jButton_Font_Increase_.setEnabled(true);
            jButton_Font_Decrease_.setEnabled(true);
            jButton_Font_Bold_.setEnabled(true);
            tButton_Italic_.setEnabled(true);        
            tButton_Underline_.setEnabled(true);
            jButton_TextBackColor_.setEnabled(true);        
            jButton_TextForeColor_.setEnabled(true);
            jButton_LeftAlignment_.setEnabled(true);
            jButton_CenterAlignment_.setEnabled(true);
            jButton_RightAlignment_.setEnabled(true);
            jButton_JustifiedAlignment_.setEnabled(true);
            jButtonColor_TextBackDefaultColor_.setEnabled(true); 
            jButtonColor_TextForeDefaultColor_.setEnabled(true);    
                
        }
        else {
            textPane.setEditable(false);
            textPane.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            jButton_Save_.setEnabled(false);
                
            jComboBox_SystemFonts_.setEnabled(false);
            jComboBox_FontSize_.setEnabled(false);
            jButton_Font_Increase_.setEnabled(false);
            jButton_Font_Decrease_.setEnabled(false);
            jButton_Font_Bold_.setEnabled(false);
            tButton_Italic_.setEnabled(false);        
            tButton_Underline_.setEnabled(false);
            jButton_TextBackColor_.setEnabled(false);        
            jButton_TextForeColor_.setEnabled(false);  
            jButton_LeftAlignment_.setEnabled(false);
            jButton_CenterAlignment_.setEnabled(false);
            jButton_RightAlignment_.setEnabled(false);
            jButton_JustifiedAlignment_.setEnabled(false);
            jButtonColor_TextBackDefaultColor_.setEnabled(false); 
            jButtonColor_TextForeDefaultColor_.setEnabled(false);    
                
            }
      
    }//GEN-LAST:event_jCheckBox_EnableEditing_ActionPerformed

    private void jButton_Save_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Save_ActionPerformed
        
        String dokimi=textPane.getText();
        DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
      
        jTable_DB_.setValueAt(dokimi, selectedRow, selectedCol);
        
        save2file();

            //System.out.println("");


            if (selectedCol==2) {
                saveSource1sql();
            }

            else if(selectedCol==3) {
                saveSource2sql();
            }

            else if (selectedCol==4) {
                saveSource3sql();
            }
            
            else {
            }

    }//GEN-LAST:event_jButton_Save_ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        textPane.setContentType("text/rtf");
        mySelectedFontSize="20";
        SelectedFontSize=20;
        
        File fileMyFont = new File("./settings/myfont.set");
        File fileMyLang = new File ("./settings/mylang.set");
        File fileMySelColor1 = new File("./settings/myselcolor1.set");

        File fileBG = new File("./settings/colorbg.set");
        File fileBG_btn = new File("./settings/colorbtn.set");
        File fileFG_btn = new File("./settings/colorlttr.set");
        
        
        try {
            
            Scanner sML = new Scanner(fileMyLang);
            setMyLang=(sML.nextLine());
            setMyLangChars=(sML.nextLine());
            pathMyLang="./languages/" + setMyLang + myForm + setMyLangChars +".lang";    
            //System.out.println("Γλώσσα: " + setMyLang + " [" + setMyLangChars + "] " + "\n");
            File fileSetMyLang= new File (pathMyLang);
            
            Scanner spathML = new Scanner(fileSetMyLang);
            
            setMyLang=(spathML.nextLine());
            this.setTitle(setMyLang);
                 
            setMyLang=(spathML.nextLine());
            jLabel_Search_.setText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jTextField_searchTxt_.setToolTipText(setMyLang);
                    
            setMyLang=(spathML.nextLine());
            jButton_X_Clear_.setToolTipText(setMyLang);        
       
            setMyLang=(spathML.nextLine());
            jCheckBox_EnableEditing_.setText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jComboBox_SystemFonts_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jComboBox_FontSize_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_Font_Increase_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_Font_Decrease_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_Font_Bold_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_Save_.setToolTipText(setMyLang);            
            
            setMyLang=(spathML.nextLine());
            jButton_LeftAlignment_.setToolTipText(setMyLang);

            setMyLang=(spathML.nextLine());
            jButton_CenterAlignment_.setToolTipText(setMyLang);

            setMyLang=(spathML.nextLine());
            jButton_RightAlignment_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_JustifiedAlignment_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            tButton_Italic_.setToolTipText(setMyLang);
                    
            setMyLang=(spathML.nextLine());       
            tButton_Underline_.setToolTipText(setMyLang);     
            
            setMyLang=(spathML.nextLine());
            jButton_TextBackColor_.setToolTipText(setMyLang);
             
            setMyLang=(spathML.nextLine());
            jButton_TextForeColor_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButtonColor_TextBackDefaultColor_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButtonColor_TextForeDefaultColor_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jLabel_totalRecords_.setText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_Asceding_Order_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jCheckBox_Enable_Asceding_Order_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_Refresh_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_DeleteRecord_.setToolTipText(setMyLang);
                                
            setMyLang=(spathML.nextLine());
            jButton_AddΜultipleRecords_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_AddRecord_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jButton_UpdateRecord_.setToolTipText(setMyLang);

            setMyLang=(spathML.nextLine());
            jButton_SelectLexiconSave_.setToolTipText(setMyLang);
            
            setMyLang=(spathML.nextLine());
            jLabel_Source_.setText(setMyLang);
            
            setMyLang=(spathML.nextLine());                    
            jTextField_Link_.setToolTipText(setMyLang);
              
            setMyLang=(spathML.nextLine());                    
            jButton_OpenLink_.setToolTipText(setMyLang);            
            
        //  ====================jTable====================    
            
            setMyLang=(spathML.nextLine());  
            mycolumn = jTable_DB_.getTableHeader().getColumnModel().getColumn(0);
            mycolumn.setHeaderValue(setMyLang);        

            setMyLang=(spathML.nextLine());  
            mycolumn = jTable_DB_.getTableHeader().getColumnModel().getColumn(1);
            mycolumn.setHeaderValue(setMyLang); 
            
            setMyLang=(spathML.nextLine());  
            mycolumn = jTable_DB_.getTableHeader().getColumnModel().getColumn(2);
            mycolumn.setHeaderValue(setMyLang); 

            setMyLang=(spathML.nextLine());  
            mycolumn = jTable_DB_.getTableHeader().getColumnModel().getColumn(3);
            mycolumn.setHeaderValue(setMyLang); 

            setMyLang=(spathML.nextLine());  
            mycolumn = jTable_DB_.getTableHeader().getColumnModel().getColumn(4);
            mycolumn.setHeaderValue(setMyLang);             
             
        //  ==============================================
            
            setMyLang=(spathML.nextLine());
            jTextField_FontSize_.setToolTipText(setMyLang);

            setMyLang=(spathML.nextLine());
            jButton_Big_Letter_.setToolTipText(setMyLang);

            setMyLang=(spathML.nextLine());
            jButton_Small_Letter_.setToolTipText(setMyLang);        
             
            //System.out.println("\n");
            Scanner sBG = new Scanner( fileBG);
            //System.out.println("Χρῶμα φόντου.");
            numR=(sBG.nextInt());
            //System.out.println(numR);
            numG=(sBG.nextInt());
            //System.out.println(numG);
            numB=(sBG.nextInt());
            //System.out.println(numB);
            //System.out.println("\n");
            mycolor =new Color(numR, numG, numB);
            jPanel_Database_.setBackground(mycolor);
            
            Scanner sBG_btn = new Scanner(fileBG_btn);
            //System.out.println("Χρῶμα κουμπιῶν");
            BG_Color_btnR=(sBG_btn.nextInt());
            //System.out.println(BG_Color_btnR);
            BG_Color_btnG=(sBG_btn.nextInt());
            //System.out.println(BG_Color_btnG);
            BG_Color_btnB=(sBG_btn.nextInt());
            //System.out.println(BG_Color_btnB);
            //System.out.println("\n");
            BG_Color_btn =new Color(BG_Color_btnR, BG_Color_btnG, BG_Color_btnB);
            
            Scanner sFG_btn = new Scanner(fileFG_btn);
            //System.out.println("Χρῶμα γραμμάτων");
            FG_Color_btnR=(sFG_btn.nextInt());
            //System.out.println(FG_Color_btnR);
            FG_Color_btnG=(sFG_btn.nextInt());
            //System.out.println(FG_Color_btnG);
            FG_Color_btnB=(sFG_btn.nextInt());
            //System.out.println(FG_Color_btnB);
            //System.out.println("\n");
            FG_Color_btn = new Color (FG_Color_btnR, FG_Color_btnG, FG_Color_btnB);
        
            jLabel_Search_.setForeground(FG_Color_btn);
            jTextField_searchTxt_.setForeground(FG_Color_btn);
            jCheckBox_EnableEditing_.setForeground(FG_Color_btn);
            jLabel_totalRecords_.setForeground(FG_Color_btn);
            jTextField_totalRecords_.setForeground(FG_Color_btn);
            jLabel_Source_.setForeground(FG_Color_btn);
            jTextField_Link_.setForeground(FG_Color_btn);
            
            jTextField_searchTxt_.setBackground(BG_Color_btn);
            jTextField_totalRecords_.setBackground(BG_Color_btn);
            jTextField_Link_.setBackground(BG_Color_btn);
 
            jButton_Font_Increase_.setForeground(FG_Color_btn);      
            jButton_Font_Decrease_.setForeground(FG_Color_btn); 
            jButton_Font_Bold_.setForeground(FG_Color_btn);     
   
            jComboBox_FontSize_.setBackground(BG_Color_btn);
            jButton_Font_Increase_.setBackground(BG_Color_btn);
            jButton_Font_Decrease_.setBackground(BG_Color_btn);
            jButton_Font_Bold_.setBackground(BG_Color_btn);
            
            jComboBox_SystemFonts_.setBackground(BG_Color_btn);
            jComboBox_SystemFonts_.setForeground(FG_Color_btn);     
            jComboBox_SystemFonts_.setRenderer(new Database.CustomComboBoxRenderer());
            
            jComboBox_FontSize_.setBackground(BG_Color_btn);
            jComboBox_FontSize_.setForeground(FG_Color_btn);     
            jComboBox_FontSize_.setRenderer(new Database.CustomComboBoxRenderer());

            jTextField_FontSize_.setText("17");
            jTextField_FontSize_.setBackground(BG_Color_btn);
            jTextField_FontSize_.setForeground(FG_Color_btn);
            
            jButton_Big_Letter_.setForeground(FG_Color_btn);
            jButton_Small_Letter_.setForeground(FG_Color_btn);
            jButton_Big_Letter_.setBackground(BG_Color_btn);
            jButton_Small_Letter_.setBackground(BG_Color_btn);
            
            isDark(BG_Color_btn);
            
            jButton_X_Clear_.setBackground(BG_Color_btn);
            jButton_Font_Increase_.setBackground(BG_Color_btn);
            jButton_Font_Decrease_.setBackground(BG_Color_btn);
            jButton_Font_Bold_.setBackground(BG_Color_btn);
            jButton_Save_.setBackground(BG_Color_btn);
            jButton_LeftAlignment_.setBackground(BG_Color_btn);
            jButton_CenterAlignment_.setBackground(BG_Color_btn);
            jButton_RightAlignment_.setBackground(BG_Color_btn);
            jButton_JustifiedAlignment_.setBackground(BG_Color_btn);
            tButton_Italic_.setBackground(BG_Color_btn);
            tButton_Underline_.setBackground(BG_Color_btn);
            jButton_TextBackColor_.setBackground(BG_Color_btn);
            jButton_TextForeColor_.setBackground(BG_Color_btn);
            jButton_Asceding_Order_.setBackground(BG_Color_btn);
            jButton_Refresh_.setBackground(BG_Color_btn);
            jButton_DeleteRecord_.setBackground(BG_Color_btn);
            jButton_AddΜultipleRecords_.setBackground(BG_Color_btn);
            jButton_AddRecord_.setBackground(BG_Color_btn);
            jButton_UpdateRecord_.setBackground(BG_Color_btn);
            jButton_SelectLexiconSave_.setBackground(BG_Color_btn);
            jButton_OpenLink_.setBackground(BG_Color_btn);

            jButton_X_Clear_.setForeground(FG_Color_btn); 
            jButton_Font_Increase_.setForeground(FG_Color_btn);  
            jButton_Font_Decrease_.setForeground(FG_Color_btn);  
            jButton_Font_Bold_.setForeground(FG_Color_btn);  
            jButton_Save_.setForeground(FG_Color_btn);  
            jButton_LeftAlignment_.setForeground(FG_Color_btn);  
            jButton_CenterAlignment_.setForeground(FG_Color_btn);  
            jButton_RightAlignment_.setForeground(FG_Color_btn);  
            jButton_JustifiedAlignment_.setForeground(FG_Color_btn); 
            tButton_Italic_.setForeground(FG_Color_btn);  
            tButton_Underline_.setForeground(FG_Color_btn);  
            jButton_TextBackColor_.setForeground(FG_Color_btn);  
            jButton_TextForeColor_.setForeground(FG_Color_btn);  
            jButton_Asceding_Order_.setForeground(FG_Color_btn);  
            jButton_Refresh_.setForeground(FG_Color_btn);  
            jButton_DeleteRecord_.setForeground(FG_Color_btn);  
            jButton_AddΜultipleRecords_.setForeground(FG_Color_btn);  
            jButton_AddRecord_.setForeground(FG_Color_btn);  
            jButton_UpdateRecord_.setForeground(FG_Color_btn);  
            jButton_SelectLexiconSave_.setForeground(FG_Color_btn);  
            jButton_OpenLink_.setForeground(FG_Color_btn);  

            jTable_DB_.setBackground(BG_Color_btn);
            jTable_DB_.setForeground(FG_Color_btn);
            jTable_DB_.setGridColor(FG_Color_btn);
          //jTable_DB_.setSelectionBackground(selectItemColor);

            
            Scanner sMSC1 = new Scanner( fileMySelColor1);
            //System.out.println("Ἐπιλεγμένο χρῶμα");
            numSCR=(sMSC1.nextInt());
            //System.out.println(numSCR);
            numSCG=(sMSC1.nextInt());
            //System.out.println(numSCG);
            numSCB=(sMSC1.nextInt());
            //System.out.println(numSCB);
            //System.out.println("\n");
            mySelColor =new Color(numSCR, numSCG, numSCB);
            selectedColor=mySelColor;
            mySelected_Color=mySelColor;
            
            jTable_DB_.setSelectionBackground(selectedColor);
            
            
            Scanner sMF = new Scanner(fileMyFont);
            setMyFont=(sMF.nextLine());
            Font myFont = new Font(setMyFont, Font.PLAIN, 17);
            jTable_DB_.setFont(myFont);
   
            //System.out.println("Γραμματοσειρά: " + setMyFont + "\n"  + "Μέγεθος: " + setMyTextSize);            
        }
        
          catch (FileNotFoundException ex) {
           
        }
        
        jTable_DB_.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = jTable_DB_.getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(140);
        columnModel.getColumn(1).setPreferredWidth(220);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(120);
        
        jTable_DB_.setRowHeight(24);
        
        
        jComboBox_SystemFonts_.setEnabled(false);
        jComboBox_FontSize_.setEnabled(false);
        jButton_Font_Increase_.setEnabled(false);
        jButton_Font_Decrease_.setEnabled(false);
        jButton_Font_Bold_.setEnabled(false);
        tButton_Italic_.setEnabled(false);        
        tButton_Underline_.setEnabled(false);
        jButton_TextBackColor_.setEnabled(false);        
        jButton_TextForeColor_.setEnabled(false);  
        jButton_LeftAlignment_.setEnabled(false);
        jButton_CenterAlignment_.setEnabled(false);
        jButton_RightAlignment_.setEnabled(false); 
        jButton_JustifiedAlignment_.setEnabled(false);
        jButtonColor_TextBackDefaultColor_.setEnabled(false); 
        jButtonColor_TextForeDefaultColor_.setEnabled(false);         
        
        flagRadioButton=5;
        pathFakelos=("./data/polytonikakeimena/");
        pathPG="pk_";
        
        try {        
      
            Scanner sMF = new Scanner(fileMyFont);
            setMyFont=(sMF.nextLine());
            Font myFont1 = new Font(setMyFont, Font.PLAIN, 10);
            textPane.setFont(myFont1);
        }   
        
        catch (FileNotFoundException ex) {
        }    
        
       textPane.setBorder(BorderFactory.createLineBorder(Color.red, 2));
       
        Java_SQLite sq  = new Java_SQLite();
        DefaultTableModel model = (DefaultTableModel) jTable_DB_.getModel();
        
        List<Constructors> polytlexList=sq.SelectAll();
        for (Constructors Polytlex:polytlexList) {
            model.addRow(new Object[] {Polytlex.myIndex(), Polytlex.myWord(), Polytlex.mySource1(), Polytlex.mySource2(),Polytlex.mySource3()});
        }
        jTable_DB_.setModel(model);
        
        totalRows=jTable_DB_.getRowCount();
        //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (totalRows));
        jTextField_totalRecords_.setText(String.format("%,d", totalRows));
        //System.out.println("");
        
        //System.out.println("");
        //System.out.println("Your OS name -> " + System.getProperty("os.name"));
        oper_system=System.getProperty("os.name");
        //Operating system version
        //System.out.println("Your OS version -> " + System.getProperty("os.version"));

        //Operating system architecture
        //System.out.println("Your OS Architecture -> " + System.getProperty("os.arch"));
        
        os = oper_system.toLowerCase();
	if((os.indexOf("win") >= 0)){
            //System.out.println("Τὸ Λειτουργικὸ Σύστημα εἶναι Windows!");
            osFont="Times New Roman";
        }
        else if ((os.indexOf("lin") >= 0)){
            //System.out.println("Τὸ Λειτουργικὸ Σύστημα εἶναι Linux!");
            osFont="Noto Sans";
        }
        
        else if ((os.indexOf("mac") >= 0)){
            //System.out.println("Τὸ Λειτουργικὸ Σύστημα εἶναι MacOS!");
            osFont="Times New Roman";
        }
        
        //System.out.println("");        
        
        DefaultListModel myfonts=new DefaultListModel();
        //System.out.println("Γιὰ νὰ γνωρίζετε τὰ διαθέσιμα ὀνόματα οἰκογενειῶν γραμματοσειρῶν"); 
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
  
        //System.out.println("Ἐμφάνιση τῶν ὀνομάτων οἰκογενειῶν γραμματοσειρῶν \n"); 
  
        // Array of all the fonts available in AWT 
        String fonts[] = ge.getAvailableFontFamilyNames(); 
        
        int j=0;
        for (String i : fonts) {
            
            myfonts.addElement(i);
            //System.out.println(j + ": " + i); 
            jComboBox_SystemFonts_.addItem(i);
            if (osFont.equals(i)) {
                indexFont=jComboBox_SystemFonts_.getItemCount();
                //System.out.println("Ἡ γραμματοσειρὰ " + osFont + " εἶναι ἐδὼ στὴ θέση: " + (indexFont-1));
                mySelectedFont=jComboBox_SystemFonts_.getSelectedItem().toString(); 
            }
            j++;
        }
        
        mySelectedFont=osFont;
        Font myFont = new Font(mySelectedFont, Font.PLAIN, SelectedFontSize);
        //System.out.println(mySelectedFont + " " + SelectedFontSize);
        jComboBox_SystemFonts_.setSelectedIndex(indexFont-1);
        textPane.setFont(myFont);
        
        for (j=0; j < jComboBox_FontSize_.getItemCount(); j++) {
            //System.out.println(j + " " + jComboBox_FontSize_.getItemAt(j));
            if ("20".equals(jComboBox_FontSize_.getItemAt(j))) {
                indexFontSize=j;
                //System.out.println("Τὸ μέγεθος 14 τῆς γραμματοσειρὰς εἶναι ἐδὼ στὴ θέση: " + indexFontSize);
            }
        }
        
        jComboBox_FontSize_.setSelectedIndex(indexFontSize);
            
    }//GEN-LAST:event_formWindowOpened

    private void jTextField_searchTxt_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchTxt_KeyReleased
    
        jTable_DB_.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(jTable_DB_.getModel());
        jTable_DB_.setRowSorter(rowSorter);
        
        rowSorter.setRowFilter((RowFilter.regexFilter(jTextField_searchTxt_.getText())));
        //System.out.println(jTextField_searchTxt_.getText());
        
        jTextField_Link_.setText("https://el.wiktionary.org/wiki/" + jTextField_searchTxt_.getText());
        
        if (jTextField_searchTxt_.getCaretPosition()!=0){
            jButton_AddΜultipleRecords_.setEnabled(false);
        }
        else {
            jButton_Refresh_.doClick();
     
            DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
            model.fireTableDataChanged();
            //jTable_DB_.clearSelection();
            // jTable_DB_.repaint();
            
            //System.out.println("Ἐκκαθάριση ἀναζήτησης.");
            //System.out.println("");
            //System.out.println("");
            
        }
        
    }//GEN-LAST:event_jTextField_searchTxt_KeyReleased

    private void jTextField_searchTxt_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_searchTxt_MouseClicked
        
        textPane.setText("");
        
        jTable_DB_.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(jTable_DB_.getModel());
        jTable_DB_.setRowSorter(rowSorter);
        
        rowSorter.setRowFilter((RowFilter.regexFilter(jTextField_searchTxt_.getText())));
        //System.out.println(jTextField_searchTxt_.getText());
        
        jTextField_Link_.setText("https://el.wiktionary.org/wiki/" + jTextField_searchTxt_.getText());
        jButton_AddΜultipleRecords_.setEnabled(false);
        
    }//GEN-LAST:event_jTextField_searchTxt_MouseClicked

    private void jTable_DB_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_DB_KeyReleased
        
        if  ( evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_RIGHT || evt.getKeyCode()==KeyEvent.VK_LEFT )  {
            
        selectedCol=jTable_DB_.getSelectedColumn();
        selectedRow=jTable_DB_.getSelectedRow();
        //System.out.println("Θέση στήλης: " + selectedCol);
        //System.out.println("");
        
        if (selectedCol==0){
            textPane.setContentType("text/txt");
            //System.out.println("error: 00x2300");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
            return;
        }
        else if (selectedCol==1){
            textPane.setContentType("text/text");
            backupWord=textPane.getText();
            name=textPane.getText();
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
        }
        
        else if (selectedCol==2){
            textPane.setContentType("text/rtf");
            //System.out.println("error: 00x2302");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);  
            //System.out.println("");
        }

        else if (selectedCol==3){
            textPane.setContentType("text/rtf");
            //System.out.println("error: 00x2303");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
        }        
        
        else if (selectedCol==4){
            textPane.setContentType("text/rtf");
            //System.out.println("error: 00x2304");
            backupWord=null;
            name=null;
            //System.out.println(backupWord);
            //System.out.println(name);
            //System.out.println("");
        }  
        
        else{
             textPane.setContentType("text/rtf");
        }
     
        DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
        textPane.setText(model.getValueAt(selectedRow, selectedCol).toString());
        selectedWord=(model.getValueAt(selectedRow, 1).toString());
        //System.out.println("Θέση ἐγγραφῆς " + selectedRow + ": " + selectedWord);
        jTextField_Link_.setText("https://el.wiktionary.org/wiki/" + model.getValueAt(selectedRow, 1).toString());
        nowTotalRows=jTable_DB_.getRowCount();
        
        
            if (nowTotalRows < totalRows){
                fixedRow=jTable_DB_.getSelectedRow();
                if (jTable_DB_.getRowSorter()!=null) {
                    fixedRow = jTable_DB_.getRowSorter().convertRowIndexToModel(fixedRow);
                    //System.out.println("Πραγματικὴ θέση ἐγγραφῆς: " + fixedRow + ": " + model.getValueAt(fixedRow, 1).toString());
                    jTextField_Link_.setText("https://el.wiktionary.org/wiki/" + model.getValueAt(fixedRow, 1).toString());
                    textPane.setText(model.getValueAt(fixedRow, selectedCol).toString());
                    selectedWord=(model.getValueAt(fixedRow, 1).toString()); 
                    //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows)); 
                    jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows));
               
                   
                }
            }
            else {
                //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows)); 
                jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows));
                
            }
        
        }
        
        //System.out.println("");
        //System.out.println("========================");
        //System.out.println("");
        
        textPane.setCaretPosition(0);
        
    }//GEN-LAST:event_jTable_DB_KeyReleased

    private void jButton_Font_Decrease_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Font_Decrease_ActionPerformed
        
       if (SelectedFontSize>1) {
            SelectedFontSize=SelectedFontSize-1;
            jComboBox_FontSize_.setEditable(true);
            jComboBox_FontSize_.setSelectedItem(String.valueOf(SelectedFontSize));
            //System.out.println(mySelectedFont + " " + SelectedFontSize);
            
            if (flag_Bold==false){
                myFont = new Font(mySelectedFont, Font.PLAIN, SelectedFontSize);
            }
            else {
                myFont = new Font(mySelectedFont, Font.BOLD, SelectedFontSize);
            }
            
            jComboBox_FontSize_.setEditable(false);
            textPane.setFont(myFont);
       }
       
       else {
           return;
       } 
       
    }//GEN-LAST:event_jButton_Font_Decrease_ActionPerformed

    private void jButton_Font_Increase_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Font_Increase_ActionPerformed
        
       if (SelectedFontSize<80) {
            SelectedFontSize=SelectedFontSize+1;
            jComboBox_FontSize_.setEditable(true);
            jComboBox_FontSize_.setSelectedItem(String.valueOf(SelectedFontSize));
            //System.out.println(mySelectedFont + " " + SelectedFontSize);
            
            if (flag_Bold==false){
                myFont = new Font(mySelectedFont, Font.PLAIN, SelectedFontSize);
            }
            else {
                myFont = new Font(mySelectedFont, Font.BOLD, SelectedFontSize);
            }
            
            jComboBox_FontSize_.setEditable(false);
            textPane.setFont(myFont);
       }
       
       else {
           return;
       }
        
    }//GEN-LAST:event_jButton_Font_Increase_ActionPerformed

    private void jButton_OpenLink_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OpenLink_ActionPerformed
       
        myLink=jTextField_Link_.getText();
        
        try{
            Desktop.getDesktop().browse(new URL(myLink).toURI());
        }
        catch (Exception e) { 
        }
                
    }//GEN-LAST:event_jButton_OpenLink_ActionPerformed

    private void jTextField_Link_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Link_KeyReleased
        
        
        String str = jTextField_Link_.getText();

        // Desired word to be removed
        //String word = "https://el.wiktionary.org/wiki/";

        // Replace all words by "" string
        // using replaceAll() method
        str = str.replaceAll("https://el.wiktionary.org/wiki/", "");

        // Trim the string using trim() method
        str = str.trim();

        // Printing the final string
        System.out.print(str);
        
    }//GEN-LAST:event_jTextField_Link_KeyReleased

    private void jComboBox_SystemFonts_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SystemFonts_ActionPerformed
       
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        mySelectedFont=jComboBox_SystemFonts_.getSelectedItem().toString();
   
        StyleConstants.setFontFamily(features1, mySelectedFont);
        textPane.setCharacterAttributes(features1, true);
        
    }//GEN-LAST:event_jComboBox_SystemFonts_ActionPerformed

    private void jComboBox_FontSize_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_FontSize_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        mySelectedFontSize=jComboBox_FontSize_.getSelectedItem().toString();
        SelectedFontSize=Integer.parseInt(mySelectedFontSize);

        StyleConstants.setFontSize(features1, SelectedFontSize);
        textPane.setCharacterAttributes(features1, true);
        
    }//GEN-LAST:event_jComboBox_FontSize_ActionPerformed

    private void jButton_DeleteRecord_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteRecord_ActionPerformed

        if (backupWord==null){
            //System.out.println(backupWord);
            return;
        }
        else {
            
        }
        
        backupWord=textPane.getText();

        
        try {
        
            String url="jdbc:sqlite:./database/sqlitedatabase.db";
            String sql="delete from polytlex where id=?";
        
            Connection conn=null; 
            conn=DriverManager.getConnection(url);
            PreparedStatement pst = conn.prepareStatement(sql);
            
            DefaultTableModel model = (DefaultTableModel) jTable_DB_.getModel();
            
            selectedRow=jTable_DB_.getSelectedRow();
            nowTotalRows=jTable_DB_.getRowCount();
            
            if (nowTotalRows < totalRows){
            fixedRow=jTable_DB_.getSelectedRow();
            //System.out.println(fixedRow);
                if (jTable_DB_.getRowSorter()!=null) {
                    fixedRow = jTable_DB_.getRowSorter().convertRowIndexToModel(fixedRow);
                    //System.out.println("Πραγματικὴ θέση ἐγγραφῆς: " + fixedRow + ": " + model.getValueAt(fixedRow, 1).toString());

                    nowTotalRows=jTable_DB_.getRowCount();
                    //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows)); 
                    jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows));
                    myValue=(int) (jTable_DB_.getModel().getValueAt(fixedRow, 0));
                    //System.out.println("Ἡ θέση Α/Α εἶναι: " + myValue);
                    //System.out.println("Τὸ id εἶναι: " + (myValue-1));
                    //System.out.println("");
                    
                    Delete_Word JDeleteWord = new Delete_Word();
                    JDeleteWord.show();
                    
                    textPane.setText("");
                    pst.setInt(1, myValue);
                    pst.execute();
 
                    model.removeRow(fixedRow);
                    //System.out.println(fixedRow);            
            
                    conn.close();
                    pst.close();
                    
                    //System.out.println("");
                    //System.out.println("Ἡ θέση Α/Α εἶναι: " + myValue);
                    //System.out.println("Ὁ δείκτης θέσης (id) εἶναι: " + (myValue-1));
                    //System.out.println("Ὁ τελευταῖος δείκτης (totalRows) εἶναι:" + totalRows);
                    //System.out.println("");
                    //System.out.println("");

        
                    if  ((myValue-1)==totalRows){
                        renumberFlag=false;
                        //System.out.println("renumberFlag: " + renumberFlag);
                        //System.out.println("Δὲν χρειάζεται νὰ γίνει ταξινόμηση καὶ ἀναρίθμιση.");
                        //System.out.println("");
                    } 
                    else {
                        renumberFlag=true;
                        //System.out.println("renumberFlag: " + renumberFlag);
                        //System.out.println("Πρέπει νὰ γίνει ταξινόμηση καὶ ἀναρίθμιση.");
                        //System.out.println("");
                    }  
        
                }
            }
            
            else {
                myValue=(int) (jTable_DB_.getModel().getValueAt(selectedRow, 0));
                    //System.out.println("Ἡ θέση Α/Α εἶναι: " + myValue);
                    //System.out.println("Τὸ id εἶναι: " + (myValue-1));
                    //System.out.println("");
                pst.setInt(1,myValue);
                pst.execute();

                model.removeRow(jTable_DB_.getSelectedRow());
                //System.out.println(jTable_DB_.getSelectedRow());            

                totalRows=jTable_DB_.getRowCount();
                //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (totalRows)); 
                jTextField_totalRecords_.setText(String.format("%,d", totalRows));

                Delete_Word JDeleteWord = new Delete_Word();
                JDeleteWord.show();

                textPane.setText("");
                conn.close();
                pst.close();
                
                    //System.out.println("");
                    //System.out.println("Ἡ θέση Α/Α εἶναι: " + myValue);
                    //System.out.println("Ὁ δείκτης θέσης (id) εἶναι: " + (myValue-1));
                    //System.out.println("Ὁ τελευταῖος δείκτης (totalRows) εἶναι:" + totalRows);
                    //System.out.println("");
                    //System.out.println("");

        
                if  ((myValue-1)==totalRows){
                    renumberFlag=false;
                    //System.out.println("renumberFlag: " + renumberFlag);
                    //System.out.println("Δὲν χρειάζεται νὰ γίνει ταξινόμηση καὶ ἀναρίθμιση.");
                    //System.out.println("");
                } 
                else {
                    renumberFlag=true;
                    //System.out.println("renumberFlag: " + renumberFlag);
                    //System.out.println("Πρέπει νὰ γίνει ταξινόμηση καὶ ἀναρίθμιση.");
                    //System.out.println("");
                }                

            }
        
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 

        try {

            deleteTheWordFromPerseus();
            deleteTheWordFromLogeion();
            deleteTheWordFromPalaiaDiathiki();
            deleteTheWordFromKainiDiathiki();
            deleteTheWordFromPolytonikaKeimena();
            deleteTheWordFromLexikoChristi();
            deleteTheWordFromOlaTaLexika();
                           
        }
        catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }//GEN-LAST:event_jButton_DeleteRecord_ActionPerformed

    private void jButton_Refresh_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Refresh_ActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTable_DB_.getModel();
        jTable_DB_.setModel(model);
        jTable_DB_.setAutoCreateRowSorter(true);
        
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable_DB_.getModel()) {
            @Override public boolean isSortable(int column) {
            return false;
            }
        };
        
        rowSorter.setRowFilter(new RowFilter<TableModel, Integer>() {
            @Override public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
                return entry.getIdentifier() % 2 == 0;
            }
        });

        //rowSorter.setSortKeys(null);
        model.fireTableDataChanged();        
        refresh_jTableDB();
        
        jTextField_searchTxt_.setText("");
        textPane.setText("");
        jTextField_Link_.setText("https://el.wiktionary.org/wiki/");
        
        jButton_AddΜultipleRecords_.setEnabled(true);
        
        nowTotalRows=jTable_DB_.getRowCount();
        //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows)); 
        jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows));
        
        jTable_DB_.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
    }//GEN-LAST:event_jButton_Refresh_ActionPerformed

    private void jButton_AddRecord_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddRecord_ActionPerformed
         
        compareFlag=true;
        manyWordsFlag=false;
        
        if (name==null){
            //System.out.println(name);
            return;
        }
        else {
            
        }
        
        name=textPane.getText();
        
        if (name==null){
            //System.out.println(name);
            return;
        }
        else {
            
        }
        
        saveMyWord();
                
        try {

            if (compareFlag==false){
                return;
            }
            else {
        
            String url="jdbc:sqlite:./database/sqlitedatabase.db";
            String sql="insert into polytlex (word, source1, source2, source3) values ('"+textPane.getText()+"',  '"+noString+"', '"+noString+"', '"+noString+"')";
          
            Connection conn=null; 
            conn=DriverManager.getConnection(url);
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.execute(); 
            
            ResultSet rs = pst.getGeneratedKeys();
 
            if (rs.next()) {
 
            insertId = rs.getInt(1);
            //Work with insertId ...
            //System.out.println(insertId);
 
        }  
            
            DefaultTableModel model = (DefaultTableModel) jTable_DB_.getModel();
            
            rowCount=model.getRowCount();
            Vector obj =new Vector();
            obj.add(insertId);
            obj.add(textPane.getText());
            obj.add(noString);
            obj.add(noString);
            obj.add(noString);
            model.addRow(obj);
            
            rowCount=model.getRowCount();
            //System.out.println("RowCount=" + rowCount + "   " + "insertId=" + insertId);
            model.setRowCount(rowCount);

            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT COUNT(*) AS recordCount FROM polytlex");
            r.next();
            int count = r.getInt("recordCount");
            r.close();
            //System.out.println("Ὁ πίνακας ἔχει " + count + " ἐγγραφές.");

            pst.close();            
            conn.close();

            }
        }   

        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
        
        totalRows=jTable_DB_.getRowCount();
        //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (totalRows)); 
        jTextField_totalRecords_.setText(String.format("%,d", totalRows));
            
         if (jTextField_searchTxt_.getCaretPosition()!=0){ 
            
            searchBackup=jTextField_searchTxt_.getText();
            //System.out.println("=== " + (searchBackup) + " ==="); 
            
            
            jButton_Refresh_.doClick();
     
            DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
            model.fireTableDataChanged();
            
            jTextField_searchTxt_.setText(searchBackup);
            
            }
         
         else {
             
         }
                
    }//GEN-LAST:event_jButton_AddRecord_ActionPerformed

    private void jButton_UpdateRecord_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateRecord_ActionPerformed
        
        compareFlag=true;
        manyWordsFlag=false;

        if (name==null){
            //System.out.println(name);
            return;
        }
        else {
            
        }

        name=textPane.getText();
                
        updateMyWord();
        
        backupWord=beforeWord;
        
        try{
            
            deleteTheWordFromPerseus();
            deleteTheWordFromLogeion();
            deleteTheWordFromPalaiaDiathiki();
            deleteTheWordFromKainiDiathiki();
            deleteTheWordFromPolytonikaKeimena();
            deleteTheWordFromLexikoChristi();
            deleteTheWordFromOlaTaLexika();   
        
        } catch (IOException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
           if (compareFlag==false){
                return;
            }
            
           else {  
            
            String url="jdbc:sqlite:./database/sqlitedatabase.db";
           
            Connection conn=null; 
            conn=DriverManager.getConnection(url);
        
            DefaultTableModel model = (DefaultTableModel) jTable_DB_.getModel();
                    
            selectedRow=jTable_DB_.getSelectedRow();
            nowTotalRows=jTable_DB_.getRowCount();
            
            if (nowTotalRows < totalRows){
            fixedRow=jTable_DB_.getSelectedRow();
            //System.out.println(fixedRow);
                if (jTable_DB_.getRowSorter()!=null) {
                    fixedRow = jTable_DB_.getRowSorter().convertRowIndexToModel(fixedRow);
                    //System.out.println("Πραγματικὴ θέση ἐγγραφῆς: " + fixedRow + ": " + model.getValueAt(fixedRow, 1).toString());

                    nowTotalRows=jTable_DB_.getRowCount();
                    //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (nowTotalRows-1)); 
                    jTextField_totalRecords_.setText(String.format("%,d", nowTotalRows-1));
                    myValue=(int) (jTable_DB_.getModel().getValueAt(fixedRow, 0));
                    String sql="update polytlex set word='"+textPane.getText()+"' where id='" + myValue+"'";
                    PreparedStatement ps_1 = conn.prepareStatement(sql);
                    //System.out.println("Τὸ id εἶναι: " + myValue);
                    //System.out.println("");
                    //System.out.println("");
                    ps_1.execute();
                    //System.out.println(fixedRow);   
                    
                    textPane.setText("");            

                    ps_1.close();
                    conn.close();
              
                }
            }
        
        
            else {
                selectedRow=jTable_DB_.getSelectedRow();
                myValue=(int) (jTable_DB_.getModel().getValueAt(selectedRow, 0));
                String sql="UPDATE polytlex SET WORD='"+textPane.getText()+"' where id='" + myValue+"'";
                PreparedStatement ps_2 = conn.prepareStatement(sql);
                //System.out.println("Τὸ id εἶναι: " + myValue);
                //System.out.println("");
                //System.out.println("");
                ps_2.execute();
          
                //System.out.println(jTable_DB_.getSelectedRow());            
                totalRows=jTable_DB_.getRowCount();
                //System.out.println("Συνολικὸς ἀριθμὸς ἐγγραφῶν: " + (totalRows)); 
                jTextField_totalRecords_.setText(String.format("%,d", totalRows));
                               
                textPane.setText("");            

                ps_2.close();
                conn.close();
                
              
                model.fireTableDataChanged();
                refresh_jTableDB();
            }
        }
               
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
        
        if (jTextField_searchTxt_.getCaretPosition()!=0){ 
            
            searchBackup=jTextField_searchTxt_.getText();
            //System.out.println("=== " + (searchBackup) + " ==="); 
            
            jButton_Refresh_.doClick();
     
            DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
            model.fireTableDataChanged();
           
            jTextField_searchTxt_.setText(searchBackup);
            
            }
         
         else {
             
         }
        
        
    }//GEN-LAST:event_jButton_UpdateRecord_ActionPerformed

    private void jCheckBox_Enable_Asceding_Order_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Enable_Asceding_Order_ActionPerformed
       
        if (jCheckBox_Enable_Asceding_Order_.isSelected()){
            jButton_Asceding_Order_.setEnabled(true);
        }
        else{
            jButton_Asceding_Order_.setEnabled(false);
        }
        
    }//GEN-LAST:event_jCheckBox_Enable_Asceding_Order_ActionPerformed

    private void jButton_Asceding_Order_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Asceding_Order_ActionPerformed

        jButton_Refresh_.doClick();
        
        
        refresh_jTableDB();
        
        try {
        
            String url="jdbc:sqlite:./database/sqlitedatabase.db";
            Connection conn=null; 
            conn=DriverManager.getConnection(url);

            
            //System.out.println("");
            //System.out.println("-----------------------------------------------------------------------------------");
            //System.out.println("Ἔναρξη τῆς διαδικασίας τῆς ταξινόμησης στὴ στήλη Λῆμμα (word) κατὰ αὔξουσα σειρά.");
            //System.out.println("");  
            String sql_1 = "CREATE TABLE TempSortedTable" +
                           " (id INTEGER,   " +
                           " NewOrder text, " + 
                           " source1 text,  " + 
                           " source2 text,  " + 
                           " source3 text,  " + 
                           " PRIMARY KEY ( id ))"; 
            PreparedStatement pst_1 = conn.prepareStatement(sql_1);                    
            pst_1.execute();  
            
            //System.out.println("");
            //System.out.println("Δημιουργία τοῦ προσωρινοῦ πίνακα TempSortedTable.");
            //System.out.println("");
            
    
            String sql_2="INSERT INTO TempSortedTable (NewOrder, source1, source2, source3) SELECT word, source1, source2, source3 FROM polytlex ORDER BY word ASC";
            PreparedStatement pst_2 = conn.prepareStatement(sql_2);
            pst_2.execute();  
            //System.out.println("");
            //System.out.println("Εἰσαγωγὴ τῶν ἐγγραφῶν τοῦ πίνακα polytlex στὸν πίνακα TempSortedTable χρησιμοποιῶντας τὴ στήλη word κατὰ αὔξουσα σειρά.");
            //System.out.println("");
 
            String sql_3="ALTER TABLE polytlex RENAME TO polytlex_old"; 
            PreparedStatement pst_3 = conn.prepareStatement(sql_3);
            pst_3.execute(); 
            //System.out.println("");
            //System.out.println("Μετoνομασία τοῦ πρωτότυπου πίνακα polytlex σὲ polytlex_old.");
            //System.out.println("");

            String sql_4="ALTER TABLE TempSortedTable RENAME TO polytlex"; 
            PreparedStatement pst_4 = conn.prepareStatement(sql_4);
            pst_4.execute(); 
            //System.out.println("");
            //System.out.println("Μετoνομασία τοῦ προσωρινοῦ πίνακα TempSortedTable σὲ polytlex.");
            //System.out.println("");

            
            String sql_5="ALTER TABLE polytlex RENAME COLUMN NewOrder TO word"; 
            PreparedStatement pst_5 = conn.prepareStatement(sql_5);
            pst_5.execute(); 
            //System.out.println("");
            //System.out.println("Μετoνομασία τῆς στήλης NewOrder σὲ word.");
            //System.out.println("");            
            
            
            String sql_6="DROP TABLE polytlex_old"; 
            PreparedStatement pst_6 = conn.prepareStatement(sql_6);
            pst_6.execute(); 
            //System.out.println("");
            //System.out.println("Κατάργηση τοῦ πίνακα polytlex_old.");
            //System.out.println("");  

            //System.out.println("");
            //System.out.println("Τέλος τῆς διαδικασίας τῆς ταξινόμησης στὴ στήλη Λήμμα (word) κατὰ αὔξουσα σειρά.");
            //System.out.println("-----------------------------------------------------------------------------------");
            //System.out.println("");  
            
            pst_1.close();
            pst_2.close();
            pst_3.close();
            pst_4.close();
            pst_5.close();
            pst_6.close();
            
            conn.close();
        }    

        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
       
        refresh_jTableDB();
        renumberFlag=false;        
            
    }//GEN-LAST:event_jButton_Asceding_Order_ActionPerformed

    private void jButton_AddΜultipleRecords_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddΜultipleRecords_ActionPerformed

        String path1 = "./addmywords/mywords.txt";
        String path2 = "./data/olatalexika/olatalexika_ola_ta_grammata.dat";
        
        if (renumberFlag==true){
            //addWords=-1;
            flag_addWords=false;
            Saving_Many_Words_Problem JSaving_Many_Words_Problem = new Saving_Many_Words_Problem();
            JSaving_Many_Words_Problem.show();
            return;
        }
        else {
            
        
            addWords=0;

            refresh_jTableDB();

            myTotalWords=jTable_DB_.getRowCount();
            //System.out.println("----> " + myTotalWords + " <----");

            compareFlag=true;
            manyWordsFlag=true;

                try {

                    //Φόρτωση δεδομένων ἀπὸ τὸ ἀρχεῖο mywords.txt.
                    BufferedReader myBufferedReader1 = new BufferedReader(new FileReader(path1)); //default is 8K = 8192
                    //Ἀνάγνωση ὁλόκληρης τῆς γραμμῆς ὡς συμβολοσειρά.
                    String line1 = myBufferedReader1.readLine();             

                    k=myTotalWords+1;
                    addWords=k;
                    while (line1 != null) {
                        line1 = myBufferedReader1.readLine();
                        //Φόρτωση δεδομένων ἀπὸ τὸ ἀρχεῖο olatalexika_ola_ta_grammata.dat.
                        BufferedReader myBufferedReader2 = new BufferedReader(new FileReader(path2)); //default is 8K = 8192
                        //Ἀνάγνωση ὁλόκληρης τῆς γραμμῆς ὡς συμβολοσειρά.
                        String line2 = myBufferedReader2.readLine();  

                        while (line2 != null){
                            line2 = myBufferedReader2.readLine();

                            if (Objects.equals(line2, line1)) {
                                name="NoWord";
                                //System.out.println("Ὑπάρχει ἡ λέξη στὸ λεξικὸ ἢ δὲν εἶναι μία ἑλληνικὴ λέξη, ἄρα δὲν ἀποθηκεύω τὴ λέξη: " + line1 + "   " + name );
                                break;

                            }
                            else {
                                name=line1;
                            }
                        }

                        //Κλείσιμο τοῦ ἀντικειμένου bufferreader.
                        myBufferedReader2.close();
                        //System.out.println(path1 + " ----> " + line1);
                        //System.out.println(path2 + " ----> " + line2);
                        //System.out.println("Ἡ λέξη εἶναι: " + name);
                        //System.out.println(" ");

                        saveMyManyWords();

                    if (compareFlag==false){
                    }
                    else {

                        //System.out.println(k + " " + line1 + " *****");
                        k=k+1;

                }
                    compareFlag=true;
                    }   
                    //Κλείσιμο τοῦ ἀντικειμένου bufferreader.
                    myBufferedReader1.close();
                    //System.out.println(" ");
                    //System.out.println("Ἡ μεταφορὰ ὁλοκληρώθηκε!");

                }

                catch (FileNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

                } catch (IOException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }

            refresh_jTableDB();


            myTotalWords=jTable_DB_.getRowCount();
            //System.out.println("----> " + myTotalWords + " <----");        
            addWords=myTotalWords-addWords+1;
            //System.out.println("----> " + "addWords: " + addWords + " <----");   
            //System.out.println("");

            if (addWords==0){

                flag_addWords=true;
                Saving_Many_Words_Empty JSaving_Many_Words_Empty = new Saving_Many_Words_Empty();
                JSaving_Many_Words_Empty.show();
                renumberFlag=false;

            }

            else if (addWords==1){

                flag_addWords=true;
                Saving_Word JSaving_Word = new Saving_Word();
                JSaving_Word.show();
                renumberFlag=false;
                renumberFlag=false;
            }

            else if (addWords>1) {
                flag_addWords=true;
                Saving_Many_Words JSaving_Many_Words = new Saving_Many_Words();
                JSaving_Many_Words.show();
            }

            else { 

            }

        }
        
            //System.out.println("Ὁ δείκτης θέσης (id) εἶναι: " + myValue);
            //System.out.println("Ὁ τελευταῖος δείκτης (myTotalWords) εἶναι:" + myTotalWords);
            //System.out.println("");
            //System.out.println("");

    }//GEN-LAST:event_jButton_AddΜultipleRecords_ActionPerformed

    private void jButton_TextForeColor_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TextForeColor_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        foreColor=jColorChooser_1.showDialog(null, "Ἐπιλέξτε ἕνα χρῶμα γιὰ τὸ κείμενο", textPane.getSelectedTextColor());
        //Color foreColor=jColorChooser_1.showDialog(null, "Ἐπιλέξτε ἕνα χρῶμα γιὰ τὸ κείμενο", mySelected_Color);
        
        StyleConstants.setForeground(features1, foreColor);
        textPane.setCharacterAttributes(features1, true);
        jButtonColor_TextForeDefaultColor_.setBackground(foreColor);
        
    }//GEN-LAST:event_jButton_TextForeColor_ActionPerformed

    private void jButton_Font_Bold_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Font_Bold_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
                
        int flagBold;
        
        if (jButton_Font_Bold_.isSelected()==false){
         flagBold=1;
        }
        else {
         flagBold=0;   
        }
 
        StyleConstants.setBold(features1, Font.BOLD != flagBold); 
        textPane.setCharacterAttributes(features1, true);
           
    }//GEN-LAST:event_jButton_Font_Bold_ActionPerformed

    private void tButton_Italic_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tButton_Italic_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        features1.addAttribute(StyleConstants.Italic, true);        
        int flagItalic;
        
        if (tButton_Italic_.isSelected()==false){
            flagItalic=2;
        
        }
        else {
            flagItalic=0;   
        }
        
        StyleConstants.setItalic(features1, Font.ITALIC != flagItalic);
        textPane.setCharacterAttributes(features1, true);
        
    }//GEN-LAST:event_tButton_Italic_ActionPerformed

    private void tButton_Underline_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tButton_Underline_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        features1.addAttribute(StyleConstants.Underline, true);       
        int flagUnderline;
        
        if (tButton_Underline_.isSelected()==false){
         flagUnderline=1;
        }
        else {
         flagUnderline=0;   
        }
        
        StyleConstants.setUnderline(features1, Font.CENTER_BASELINE != flagUnderline);
        textPane.setCharacterAttributes(features1, true);
        
    }//GEN-LAST:event_tButton_Underline_ActionPerformed

    private void jButton_TextBackColor_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TextBackColor_ActionPerformed

        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        backColor=jColorChooser_1.showDialog(null, "Ἐπιλέξτε ἕνα χρῶμα φόντου", textPane.getSelectedTextColor());
        //Color backColor=jColorChooser_1.showDialog(null, "Ἐπιλέξτε ἕνα χρῶμα φόντου", mySelected_Color);
        
        StyleConstants.setBackground(features1, backColor);
        textPane.setCharacterAttributes(features1, true);
        jButtonColor_TextBackDefaultColor_.setBackground(backColor);
        
    }//GEN-LAST:event_jButton_TextBackColor_ActionPerformed

    private void textPaneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPaneKeyReleased
        
        AttributeSet attributes = textPane.getCharacterAttributes();

        currentFont=StyleConstants.getFontFamily(attributes);
        //System.out.println("==========================================================");
        //System.out.println("Ὑπάρχουν " + jComboBox SystemFonts .getItemCount() γραμματοσειρές!");
        //System.out.println("Ἡ γραμματοσειρὰ εἶναι ἡ " + currentFont);

        for (int i=0; i<jComboBox_SystemFonts_.getItemCount(); i++){
            //System.out.println("--> " + currentFont);
            whereIsThisFont=jComboBox_SystemFonts_.getItemAt(i);
            //System.out.println("--> " + whereIsThisFont);

            if (currentFont.equals(whereIsThisFont)){
                hereFont=i;
                //System.out.println("Ἡ γραμματοσειρὰ βρίσκεται στὴ θέση: " + hereFont);
                //System.out.println("");
                break;
        }
            else{}
            
        }
        jComboBox_SystemFonts_.setSelectedIndex(hereFont);
        
        
        currentFontSize=StyleConstants.getFontSize(attributes);
        //System.out.println("Ὑπάρχουν " + jComboBox FontSize .getItemCount() μεγέθη γραμματοσειρῶν!");
        //System.out.println("Τὸ μέγεθος τῆς γραμματοσειρᾶς εἶναι: " + currentFontSize);
        
        for (int j=0; j<jComboBox_FontSize_.getItemCount(); j++){
            //System.out.println("--> " + currentFontSize);
            whereIsThisFontSize=jComboBox_FontSize_.getItemAt(j);
            //System.out.println("--> " + whereIsThisFontSize);

            if (Integer.toString(currentFontSize).equals(whereIsThisFontSize)){
                hereFontSize=j;
               // //System.out.println("Τὸ μέγεθος τῆς γραμματοσειρᾶς βρίσκεται στὴ θέση: " + hereFontSize);
               // //System.out.println("==========================================================");
               // //System.out.println("");
                break;
        }
            else{}
            
        }
        jComboBox_FontSize_.setSelectedIndex(hereFontSize);
        
        int pos=textPane.getCaretPosition();

        jButton_Font_Bold_.setSelected(StyleConstants.isBold(attributes));
        tButton_Italic_.setSelected(StyleConstants.isItalic(attributes));
        tButton_Underline_.setSelected(StyleConstants.isUnderline(attributes));
        
    }//GEN-LAST:event_textPaneKeyReleased

    private void textPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPaneMouseClicked
        
        beforeWord=textPane.getText();
        
        AttributeSet attributes = textPane.getCharacterAttributes();

        currentFont=StyleConstants.getFontFamily(attributes);
        //System.out.println("==========================================================");
        //System.out.println("Ὑπάρχουν " + jComboBox SystemFonts .getItemCount() γραμματοσειρές!");
        //System.out.println("Ἡ γραμματοσειρὰ εἶναι ἡ " + currentFont);

        for (int i=0; i<jComboBox_SystemFonts_.getItemCount(); i++){
            //System.out.println("--> " + currentFont);
            whereIsThisFont=jComboBox_SystemFonts_.getItemAt(i);
            //System.out.println("--> " + whereIsThisFont);

            if (currentFont.equals(whereIsThisFont)){
                hereFont=i;
                //System.out.println("Ἡ γραμματοσειρὰ βρίσκεται στὴ θέση: " + hereFont);
                //System.out.println("");
                break;
        }
            else{}
            
        }

        jComboBox_SystemFonts_.setSelectedIndex(hereFont);
        currentFontSize=StyleConstants.getFontSize(attributes);
        //System.out.println("Ὑπάρχουν " + jComboBox FontSize .getItemCount() μεγέθη γραμματοσειρῶν!");
        //System.out.println("Τὸ μέγεθος τῆς γραμματοσειρᾶς εἶναι: " + currentFontSize);
        
        for (int j=0; j<jComboBox_FontSize_.getItemCount(); j++){
            //System.out.println("--> " + currentFontSize);
            whereIsThisFontSize=jComboBox_FontSize_.getItemAt(j);
            //System.out.println("--> " + whereIsThisFontSize);

            if (Integer.toString(currentFontSize).equals(whereIsThisFontSize)){
                hereFontSize=j;
               // //System.out.println("Τὸ μέγεθος τῆς γραμματοσειρᾶς βρίσκεται στὴ θέση: " + hereFontSize);
               // //System.out.println("==========================================================");
               // //System.out.println("");
                break;
        }
            else{}
            
        }
        jComboBox_FontSize_.setSelectedIndex(hereFontSize);
        
        int pos=textPane.getCaretPosition();

        jButton_Font_Bold_.setSelected(StyleConstants.isBold(attributes));
        tButton_Italic_.setSelected(StyleConstants.isItalic(attributes));
        tButton_Underline_.setSelected(StyleConstants.isUnderline(attributes));
        
    }//GEN-LAST:event_textPaneMouseClicked

    private void jButton_LeftAlignment_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LeftAlignment_ActionPerformed
        
        SimpleAttributeSet sas = new SimpleAttributeSet (textPane.getCharacterAttributes());
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_LEFT);
        textPane.setParagraphAttributes(sas, true);
        
    }//GEN-LAST:event_jButton_LeftAlignment_ActionPerformed

    private void jButton_CenterAlignment_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CenterAlignment_ActionPerformed
 
        SimpleAttributeSet sas = new SimpleAttributeSet (textPane.getCharacterAttributes());
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
        textPane.setParagraphAttributes(sas, true);
               
    }//GEN-LAST:event_jButton_CenterAlignment_ActionPerformed

    private void jButton_RightAlignment_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RightAlignment_ActionPerformed
        
        SimpleAttributeSet sas = new SimpleAttributeSet (textPane.getCharacterAttributes());
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_RIGHT);
        textPane.setParagraphAttributes(sas, true);
        
    }//GEN-LAST:event_jButton_RightAlignment_ActionPerformed

    private void jButton_SelectLexiconSave_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SelectLexiconSave_ActionPerformed
        
        Select_Lexicon_Save JFramemSelect_Lexicon_Save = new Select_Lexicon_Save();
        JFramemSelect_Lexicon_Save.show();   
        
    }//GEN-LAST:event_jButton_SelectLexiconSave_ActionPerformed

    private void jButtonColor_TextBackDefaultColor_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColor_TextBackDefaultColor_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        backColor=jButtonColor_TextBackDefaultColor_.getBackground();
        
        StyleConstants.setBackground(features1, backColor);
        textPane.setCharacterAttributes(features1, true);
        jButtonColor_TextBackDefaultColor_.setBackground(backColor);
        
    }//GEN-LAST:event_jButtonColor_TextBackDefaultColor_ActionPerformed

    private void jButtonColor_TextForeDefaultColor_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColor_TextForeDefaultColor_ActionPerformed
        
        features1 = new SimpleAttributeSet(textPane.getCharacterAttributes());
        foreColor=jButtonColor_TextForeDefaultColor_.getBackground();
        
        StyleConstants.setForeground(features1, foreColor);
        textPane.setCharacterAttributes(features1, true);
        jButtonColor_TextForeDefaultColor_.setBackground(foreColor);
        
    }//GEN-LAST:event_jButtonColor_TextForeDefaultColor_ActionPerformed

    private void jButton_Big_Letter_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Big_Letter_ActionPerformed
        
        if (setMyTextSize<24) {
            setMyTextSize=setMyTextSize+1;

            Font myFontNow = new Font(setMyFont, Font.PLAIN, setMyTextSize);
            jTable_DB_.setFont(myFontNow);
            
            //System.out.println("\n");
            //System.out.println("Γραμματοσειρά: " + setMyFont + "\n"  + "Μέγεθος: " + setMyTextSize);

            jTextField_FontSize_.setText(Integer.toString(setMyTextSize));

        }
        else {

        }
    }//GEN-LAST:event_jButton_Big_Letter_ActionPerformed

    private void jButton_Small_Letter_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Small_Letter_ActionPerformed
        
        if (setMyTextSize>10) {
            setMyTextSize=setMyTextSize-1;

            Font myFontNow = new Font(setMyFont, Font.PLAIN, setMyTextSize);
            jTable_DB_.setFont(myFontNow);

            //System.out.println("\n");
            //System.out.println("Γραμματοσειρά: " + setMyFont + "\n"  + "Μέγεθος: " + setMyTextSize);

            jTextField_FontSize_.setText(Integer.toString(setMyTextSize));

        }
        else {

        }
                
    }//GEN-LAST:event_jButton_Small_Letter_ActionPerformed

    private void jButton_X_Clear_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_X_Clear_ActionPerformed
            
            jButton_Refresh_.doClick();
     
            DefaultTableModel model = (DefaultTableModel)jTable_DB_.getModel();
            model.fireTableDataChanged();
            //jTable_DB_.clearSelection();
            //jTable_DB_.repaint();
            
            //System.out.println("Ἐκκαθάριση ἀναζήτησης.");
            //System.out.println("");
            //System.out.println("");
            
    }//GEN-LAST:event_jButton_X_Clear_ActionPerformed

    private void jButton_JustifiedAlignment_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_JustifiedAlignment_ActionPerformed
       
        SimpleAttributeSet sas = new SimpleAttributeSet (textPane.getCharacterAttributes());
        StyleConstants.setAlignment(sas, StyleConstants.ALIGN_JUSTIFIED);
        textPane.setParagraphAttributes(sas, true);
        
    }//GEN-LAST:event_jButton_JustifiedAlignment_ActionPerformed

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
                    
                    //Nimbus, Metal, CDE/Motif, Windows, Windows Classic
                    
                    
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
            //</editor-fold>
        //</editor-fold>
        
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Database().setVisible(true));
    }
    
    private void  refresh_jTableDB(){

  
        try{
         
            String url="jdbc:sqlite:./database/sqlitedatabase.db";
            String sql="select * from polytlex";
            
            Connection conn=null; 
            conn=DriverManager.getConnection(url);
        
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            jTextField_searchTxt_.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable_DB_.getModel();
            model.setRowCount(0);
            
            while (rs.next()){
                model.addRow(new Object[] {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            
            }
        
            totalRows=jTable_DB_.getRowCount();
            //System.out.println("Συνολικὸς ἀριθμός ἐγγραφῶν: " + (totalRows-1));
            //jTextField_totalRecords_.setText(String.format("%,d", totalRows-1));
            //System.out.println("Συνολικὸς ἀριθμός ἐγγραφῶν: " + (totalRows));
            jTextField_totalRecords_.setText(String.format("%,d", totalRows));
            //System.out.println("");
        
            rs.close();
            pst.close();
            conn.close();
        
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
         
        
}

    
    private void save2file(){
        
            File myFile = new File("./database/temp.rtf");
            try {
                StyledDocument doc;
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(myFile.getAbsolutePath()));
                
                EditorKit kit;
                doc=(StyledDocument)textPane.getDocument();

                int startDoc=0;
                int endDoc=doc.getLength();
                             
                kit=new RTFEditorKit();
                
                kit.write(outputStream, doc, startDoc, endDoc);
                outputStream.close();
                //System.out.println("Τὸ ἀρχεῖο ἀποθηκεύτηκε μὲ ἐπιτυχία!");
                
        }catch (Exception e) {
                //System.out.println("Σφάλμα, δὲν εἶναι δυνατὴ ἡ ἐγγραφὴ στὸ ἀρχεῖο!");
            
        }
        
    }
    
    private void saveSource1sql(){
          
        String url="jdbc:sqlite:./database/sqlitedatabase.db";
        Connection conn=null;
            
        try {
            conn=DriverManager.getConnection(url);
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //System.out.println("Ἡ σύνδεση μὲ τὴ βάση δεδομένων sqlitedatabase.db ἔγινε!");
            //System.out.println("");
            //System.out.println("Γιὰ τὴ στήλη Source1, ἐπιλέχτηκε ἡ λέξη: " + selectedWord);
        
            String insertSql = ("UPDATE polytlex SET source1=? WHERE word=?");
    
       
            try {
                PreparedStatement stmt;
                conn=DriverManager.getConnection(url);
                stmt = conn.prepareStatement(insertSql);
                  
                File myTempFile = new File("./database/temp.rtf");
                
                try (FileInputStream fileInStr = new FileInputStream(myTempFile)) {
                    try {
                        stmt.setBinaryStream(1, fileInStr, myTempFile.length());
                        stmt.setString(2, selectedWord);
                        stmt.executeUpdate();
                        
                        conn.close();
                        stmt.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }   catch (FileNotFoundException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (IOException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    }

    
    private void saveSource2sql(){
          
        String url="jdbc:sqlite:./database/sqlitedatabase.db";
        Connection conn=null;
            
        try {
            conn=DriverManager.getConnection(url);
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //System.out.println("Ἡ σύνδεση μὲ τὴ βάση δεδομένων sqlitedatabase.db ἔγινε!");
            //System.out.println("");
            //System.out.println("Γιὰ τὴ στήλη Source2, ἐπιλέχτηκε ἡ λέξη:  " + selectedWord);
        
            String insertSql = ("UPDATE polytlex SET source2=? WHERE word=?");
    
       
            try {
                PreparedStatement stmt;
                conn=DriverManager.getConnection(url);
                stmt = conn.prepareStatement(insertSql);
                  
                File myTempFile = new File("./database/temp.rtf");
                
                try (FileInputStream fileInStr = new FileInputStream(myTempFile)) {
                    try {
                        stmt.setBinaryStream(1, fileInStr, myTempFile.length());
                        stmt.setString(2, selectedWord);
                        stmt.executeUpdate();
                        
                        conn.close();
                        stmt.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }   catch (FileNotFoundException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (IOException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }
    
    private void saveSource3sql(){
          
        String url="jdbc:sqlite:./database/sqlitedatabase.db";
        Connection conn=null;
            
        try {
            conn=DriverManager.getConnection(url);
        }
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //System.out.println("Ἡ σύνδεση μὲ τὴ βάση δεδομένων sqlitedatabase.db ἔγινε!");
            //System.out.println("");
            //System.out.println("Γιὰ τὴ στήλη Source3, ἐπιλέχτηκε ἡ λέξη: " + selectedWord);
        
            String insertSql = ("UPDATE polytlex SET source3=? WHERE word=?");
    
       
            try {
                PreparedStatement stmt;
                conn=DriverManager.getConnection(url);
                stmt = conn.prepareStatement(insertSql);
                  
                File myTempFile = new File("./database/temp.rtf");
                
                try (FileInputStream fileInStr = new FileInputStream(myTempFile)) {
                    try {
                        stmt.setBinaryStream(1, fileInStr, myTempFile.length());
                        stmt.setString(2, selectedWord);
                        stmt.executeUpdate();
                        
                        conn.close();
                        stmt.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }   catch (FileNotFoundException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (IOException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }   
    
    private void saveMyWord(){
        
        compareFlag=true;
        name=textPane.getText();
        compareMyGramma();
        
        try {

            
            if (compareFlag==false){
                return;
            }
            else {
            
            FileWriter fw1 = new FileWriter(path, true);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path);
            fw1.write(textPane.getText());
            fw1.close();
            
            ArrayList<String> words1 = readFromFile(path);
            Collections.sort(words1);
            saveToFile(path, words1);
        
            //*****************
 
            path = (pathFakelos + pathPG + "ola_ta_grammata.dat");
            FileWriter fw2 = new FileWriter(path, true);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path);
            fw2.write(textPane.getText());
            fw2.close();

            ArrayList<String> words2 = readFromFile(path);    //ArrayList<String> words2 = readFromFile(path_userLex);
            Collections.sort(words2);
            saveToFile(path, words2);    //saveToFile(path_userLex, words2);
        
            //*****************
            
            path_for_letter_allLex=("./data/olatalexika/" + "olatalexika_"+ pathGramma);
            FileWriter fw3 = new FileWriter(path_for_letter_allLex, true);
            fw3.write(textPane.getText());
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path_for_letter_allLex);
            fw3.close();
           
            ArrayList<String> words3 = readFromFile(path_for_letter_allLex);
            Collections.sort(words3);
            saveToFile(path_for_letter_allLex, words3);
    
            //*****************
                   
            FileWriter fw4 = new FileWriter(path_allLex, true);
            fw4.write(textPane.getText());
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path_allLex);
            fw4.close();
           
            ArrayList<String> words4 = readFromFile(path_allLex);
            Collections.sort(words4);
            saveToFile(path_allLex, words4);
    
            //*****************
            
            //System.out.println("");
            //System.out.println("");
            //System.out.println("");
            
        Saving_Word JSavingWord = new Saving_Word();
        JSavingWord.show();
               
        }
        }
        
        catch (IOException e)
        {
            e.printStackTrace();
        }
              
        }
    
    
    private void saveMyManyWords(){
        
        compareFlag=true;
        
        if (name==null){
        }
        else{
            compareMyGramma();
        
        try {

            String url="jdbc:sqlite:./database/sqlitedatabase.db";
            Connection conn=null;
            PreparedStatement ps_1;
            conn=DriverManager.getConnection(url);
        
            if (compareFlag==false){
                return;
            }
            else {
                ps_1 = conn.prepareStatement("INSERT INTO polytlex VALUES (?, ?, ?, ?, ?)");
                //System.out.println(k + "  " + name + " *****");
                ps_1.setInt(1, k);
                ps_1.setString(2, name);
                ps_1.setString(3, "");
                ps_1.setString(4, "");
                ps_1.setString(5, "");
          
                ps_1.executeUpdate();                
            
                ps_1.close();
                conn.close();
            
            
            FileWriter fw1 = new FileWriter(path, true);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path);
            fw1.write(name);
            fw1.close();
            
            ArrayList<String> words1 = readFromFile(path);
            Collections.sort(words1);
            saveToFile(path, words1);
        
            //*****************
            
            //pathGramma="ola_ta_grammata";
            path = (pathFakelos + pathPG + "ola_ta_grammata.dat");
            FileWriter fw2 = new FileWriter(path, true);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path);
            fw2.write(name);
            fw2.close();

            ArrayList<String> words2 = readFromFile(path);
            Collections.sort(words2);
            saveToFile(path, words2);
        
            //*****************
            
            path_for_letter_allLex=("./data/olatalexika/" + "olatalexika_"+ pathGramma);
            FileWriter fw3 = new FileWriter(path_for_letter_allLex, true);
            fw3.write(name);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path_for_letter_allLex);
            fw3.close();
           
            ArrayList<String> words3 = readFromFile(path_for_letter_allLex);
            Collections.sort(words3);
            saveToFile(path_for_letter_allLex, words3);
    
            //*****************
                   
            FileWriter fw4 = new FileWriter(path_allLex, true);
            fw4.write(name);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path_allLex);
            fw4.close();
           
            ArrayList<String> words4 = readFromFile(path_allLex);
            Collections.sort(words4);
            saveToFile(path_allLex, words4);
    
            //*****************
            
            //System.out.println("");
            //System.out.println("");
            //System.out.println("");
               
        }
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException ex) {        
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }        
        
    }
    }
        

    private void compareMyGramma() {  
    
        if (name==null){
            //System.out.println(name);
            return;
        }
        else {
        }
        
        //System.out.println("");
        stletter=(name.substring(0,1));
        //System.out.println("stletter: " + stletter);
        
        //System.out.println("Ἀρχίζει ἡ σύγκριση ἂν τὸ πρῶτο γράμμα τῆς λέξης εἶναι ἑλληνικὸ γράμμα.");
        //System.out.println("Τὸ πρῶτο γράμμα τῆς λέξης εἶναι: " + stletter);
        //System.out.println("");
        //System.out.println("");
        
                //Small letter   αἀἁάἄἅὰἂἃάᾶἆἇᾱ (14)  ᾀᾁᾴᾄᾅᾲᾂᾃᾳᾷᾆᾇᾰ (13)
                
        if        (stletter.equals("α") || stletter.equals("ἀ") || stletter.equals("ἁ") || stletter.equals("ά") || stletter.equals("ἄ") || stletter.equals("ἅ")
                || stletter.equals("ὰ") || stletter.equals("ἂ") || stletter.equals("ἃ") || stletter.equals("ά") || stletter.equals("ᾶ") || stletter.equals("ἆ")
                || stletter.equals("ἇ") || stletter.equals("ᾱ") || stletter.equals("ᾀ") || stletter.equals("ᾁ") || stletter.equals("ᾴ") || stletter.equals("ᾄ")
                || stletter.equals("ᾅ") || stletter.equals("ᾲ") || stletter.equals("ᾂ") || stletter.equals("ᾃ") || stletter.equals("ᾳ") || stletter.equals("ᾷ")
                || stletter.equals("ᾆ") || stletter.equals("ᾇ") || stletter.equals("ᾰ") 
                
                //Capital letter   ΑἈἉΆἌἍᾺἊἋΆἎἏᾹ (13)  ᾈᾉᾌᾍᾊᾋᾼᾎᾏᾸ (10)
                
                || stletter.equals("Α") || stletter.equals("Ἀ") || stletter.equals("Ἁ") || stletter.equals("Ά") || stletter.equals("Ἄ") || stletter.equals("Ἅ")
                || stletter.equals("Ὰ") || stletter.equals("Ἂ") || stletter.equals("Ἃ") || stletter.equals("Ά") || stletter.equals("Ἆ") || stletter.equals("Ἇ")
                || stletter.equals("Ᾱ") || stletter.equals("ᾈ") || stletter.equals("ᾉ") || stletter.equals("ᾌ") || stletter.equals("ᾍ") || stletter.equals("ᾊ")
                || stletter.equals("ᾋ") || stletter.equals("ᾼ") || stletter.equals("ᾎ") || stletter.equals("ᾏ") || stletter.equals("Ᾰ") )
            {
                pathGramma = ("1_gramma_alfa.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
        
        else if (stletter.equals("β") || stletter.equals("Β"))
            {
                pathGramma= ("2_gramma_vita.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
        
        else if (stletter.equals("γ") || stletter.equals("Γ"))
            {
                pathGramma= ("3_gramma_gamma.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
                
        else if (stletter.equals("δ") || stletter.equals("Δ"))
            {
                pathGramma= ("4_gramma_delta.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
        
                //Small letter   εἐἑέἔἕὲἒἓέ (10)
        
        else if   (stletter.equals("ε") || stletter.equals("ἐ") || stletter.equals("ἑ") || stletter.equals("έ") || stletter.equals("ἔ") || stletter.equals("ἕ")
                || stletter.equals("ὲ") || stletter.equals("ἒ") || stletter.equals("ἓ") || stletter.equals("έ") 
                
                //Capital letter   ΕἘἙΈἜἝῈἚἛΈ (10)
                
                || stletter.equals("Ε") || stletter.equals("Ἐ") || stletter.equals("Ἑ") || stletter.equals("Έ") || stletter.equals("Ἔ") || stletter.equals("Ἕ")
                || stletter.equals("Ὲ") || stletter.equals("Ἒ") || stletter.equals("Ἓ") || stletter.equals("Έ")  )
            {
                pathGramma = ("5_gramma_epsilon.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("ζ") || stletter.equals("Ζ"))
            {
                pathGramma= ("6_gramma_zita.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
        
                //Small letter   ηἠἡήἤἥὴἢἣήῆἦἧ (13)  ᾐᾑῄᾔᾕῂᾒᾓῃῇᾖᾗ (12)               
        
        else if   (stletter.equals("η") || stletter.equals("ἠ") || stletter.equals("ἡ") || stletter.equals("ή") || stletter.equals("ἤ") || stletter.equals("ἥ")
                || stletter.equals("ὴ") || stletter.equals("ἢ") || stletter.equals("ἣ") || stletter.equals("ή") || stletter.equals("ῆ") || stletter.equals("ἦ")
                || stletter.equals("ἧ") || stletter.equals("ᾐ") || stletter.equals("ᾑ") || stletter.equals("ῄ") || stletter.equals("ᾔ") || stletter.equals("ᾕ")
                || stletter.equals("ῂ") || stletter.equals("ᾒ") || stletter.equals("ᾓ") || stletter.equals("ῃ") || stletter.equals("ῇ") || stletter.equals("ᾖ")
                || stletter.equals("ᾗ")
                
                //Capital letter   ΗἨἩΉἬἭῊἪἫΉἮἯ (12)  ᾘᾙᾜᾝᾚᾛῌᾞᾟ (9)
                   
                || stletter.equals("Η") || stletter.equals("Ἠ") || stletter.equals("Ἡ") || stletter.equals("Ή") || stletter.equals("Ἤ") || stletter.equals("Ἥ")
                || stletter.equals("Ὴ") || stletter.equals("Ἢ") || stletter.equals("Ἣ") || stletter.equals("Ή") || stletter.equals("Ἦ") || stletter.equals("Ἧ")                
                || stletter.equals("ᾘ") || stletter.equals("ᾙ") || stletter.equals("ᾜ") || stletter.equals("ᾝ") || stletter.equals("ᾚ") || stletter.equals("ᾛ")
                || stletter.equals("ῌ") || stletter.equals("ᾞ") || stletter.equals("ᾟ") )
            {
                pathGramma= ("7_gramma_ita.dat"); 
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("θ") || stletter.equals("Θ"))
            {
                pathGramma= ("8_gramma_thita.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
        
                //Small letter   ιἰἱίἴἵὶἲἳίῖἶἷΐῒῑ (16)  ΐϊῗῐ (4)                       

        else if   (stletter.equals("ι") || stletter.equals("ἰ") || stletter.equals("ἱ") || stletter.equals("ί") || stletter.equals("ἴ") || stletter.equals("ἵ")
                || stletter.equals("ὶ") || stletter.equals("ἲ") || stletter.equals("ἳ") || stletter.equals("ί") || stletter.equals("ῖ") || stletter.equals("ἶ")
                || stletter.equals("ἷ") || stletter.equals("ΐ") || stletter.equals("ῒ") || stletter.equals("ῑ") || stletter.equals("ΐ") || stletter.equals("ϊ")
                || stletter.equals("ῗ") || stletter.equals("ῐ") 
                
                //Capital letter   ΙἸἹΊἼἽῚἺἻΊἾἿῙ (13)  ΪῘ (2)          
                
                || stletter.equals("Ι") || stletter.equals("Ἰ") || stletter.equals("Ἱ") || stletter.equals("Ί") || stletter.equals("Ἴ") || stletter.equals("Ἵ")
                || stletter.equals("Ὶ") || stletter.equals("Ἲ") || stletter.equals("Ἳ") || stletter.equals("Ί") || stletter.equals("Ἶ") || stletter.equals("Ἷ")
                || stletter.equals("Ῑ") || stletter.equals("Ϊ") || stletter.equals("Ῐ") )
            {
                pathGramma= ("9_gramma_iota.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("κ") || stletter.equals("Κ"))
            {
                pathGramma= ("10_gramma_kappa.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("λ") || stletter.equals("Λ"))
            {
                pathGramma= ("11_gramma_lamda.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("μ") || stletter.equals("Μ"))
            {
                pathGramma= ("12_gramma_mi.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("ν") || stletter.equals("Ν"))
            {
                pathGramma= ("13_gramma_ni.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

        else if (stletter.equals("ξ") || stletter.equals("Ξ"))
            {
                pathGramma= ("14_gramma_ksi.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }

                //Small letter   οὀὁόὄὅὸὂὃό (10)               
        
        else if   (stletter.equals("ο") || stletter.equals("ὀ") || stletter.equals("ὁ") || stletter.equals("ό") || stletter.equals("ὄ") || stletter.equals("ὅ")
                || stletter.equals("ὸ") || stletter.equals("ὂ") || stletter.equals("ὃ") || stletter.equals("ό") 
                
                //Capital letter   ΟὈὉΌὌὍῸὊὋΌ (10)   
                
                || stletter.equals("Ο") || stletter.equals("Ὀ") || stletter.equals("Ὁ") || stletter.equals("Ό") || stletter.equals("Ὄ") || stletter.equals("Ὅ")
                || stletter.equals("Ὸ") || stletter.equals("Ὂ") || stletter.equals("Ὃ") || stletter.equals("Ό") )
            {
                pathGramma= ("15_gramma_omikron.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    
        
        else if (stletter.equals("π") || stletter.equals("Π"))
            {
                pathGramma= ("16_gramma_pi.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    

                //Small letter   ρῤῥ (3)  ----------  //Capital letter   ΡῬ (2)       
        else if (stletter.equals("ρ") || stletter.equals("ῤ") || stletter.equals("ῥ") || stletter.equals("Ρ") || stletter.equals("Ῥ"))
            {
                pathGramma= ("17_gramma_ro.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    

        else if (stletter.equals("σ") || stletter.equals("Σ"))
            {
                pathGramma= ("18_gramma_sigma.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    

        else if (stletter.equals("τ") || stletter.equals("Τ"))
            {
                pathGramma= ("19_gramma_taf.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    

                //Small letter   υὐὑύὔὕὺὒὓύῦὖὗΰῢῡ (16)  ΰϋῧῠ (4)               
        
        else if   (stletter.equals("υ") || stletter.equals("ὐ") || stletter.equals("ὑ") || stletter.equals("ύ") || stletter.equals("ὔ") || stletter.equals("ὕ")
                || stletter.equals("ὺ") || stletter.equals("ὒ") || stletter.equals("ὓ") || stletter.equals("ύ") || stletter.equals("ῦ") || stletter.equals("ὖ")
                || stletter.equals("ὗ") || stletter.equals("ΰ") || stletter.equals("ῢ") || stletter.equals("ῡ") || stletter.equals("ΰ") || stletter.equals("ϋ")
                || stletter.equals("ῧ") || stletter.equals("ῠ")  
                
                //Capital letter   ΥὙΎὝῪὛΎὟῩ (9)  ΫῨ (2)                 
                
                || stletter.equals("Υ") || stletter.equals("Ὑ") || stletter.equals("Ύ") || stletter.equals("Ὕ") || stletter.equals("Ὺ") || stletter.equals("Ὓ")
                || stletter.equals("Ύ") || stletter.equals("Ὗ") || stletter.equals("Ῡ") || stletter.equals("Ϋ") || stletter.equals("Ῠ"))
            {
                pathGramma= ("20_gramma_ypsilon.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    
        
        else if (stletter.equals("φ") || stletter.equals("Φ"))
            {
                pathGramma= ("21_gramma_phi.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    

        else if (stletter.equals("χ") || stletter.equals("Χ"))
            {
                pathGramma= ("22_gramma_chi.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    

        else if (stletter.equals("ψ") || stletter.equals("Ψ"))
            {
                pathGramma= ("23_gramma_psi.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }    
                
                //Small letter   ωὠὡώὤὥὼὢὣώῶὦὧ (13)  ᾠᾡῴᾤᾥῲᾢᾣῳῷᾦᾧ (12)
                             
        else if (stletter.equals("ω") || stletter.equals("ὠ") || stletter.equals("ὡ") || stletter.equals("ώ") || stletter.equals("ὤ") || stletter.equals("ὥ")
                || stletter.equals("ὼ") || stletter.equals("ὢ") || stletter.equals("ὣ") || stletter.equals("ώ") || stletter.equals("ῶ") || stletter.equals("ὦ")
                || stletter.equals("ὧ") || stletter.equals("ᾠ") || stletter.equals("ᾡ") || stletter.equals("ῴ") || stletter.equals("ᾤ") || stletter.equals("ᾥ")
                || stletter.equals("ῲ") || stletter.equals("ᾢ") || stletter.equals("ᾣ") || stletter.equals("ῳ") || stletter.equals("ῷ") || stletter.equals("ᾦ")
                || stletter.equals("ᾧ")
                
                //Capital letter   ΩὨὩΏὬὭῺὪὫΏὮὯ (12)  ᾨᾩᾬᾭᾪᾫῼᾮᾯ (9)
                
                
                || stletter.equals("Ω") || stletter.equals("Ὠ") || stletter.equals("Ὡ") || stletter.equals("Ώ") || stletter.equals("Ὤ") || stletter.equals("Ὥ")
                || stletter.equals("Ὼ") || stletter.equals("Ὢ") || stletter.equals("Ὣ") || stletter.equals("Ώ") || stletter.equals("Ὦ") || stletter.equals("Ὧ")
                || stletter.equals("ᾨ") || stletter.equals("ᾩ") || stletter.equals("ᾬ") || stletter.equals("ᾭ") || stletter.equals("ᾪ") || stletter.equals("ᾫ")
                || stletter.equals("ῼ") || stletter.equals("ᾮ") || stletter.equals("ᾯ") )
            {
                pathGramma = ("24_gramma_omega.dat");
                path=(pathFakelos + pathPG + pathGramma); 
            }
        
        else {
            if (manyWordsFlag==false){
                if (deleteFlag==true){
                    compareFlag=false;
                    return;
                }
                Saving_Word_Error JSavingWordError = new Saving_Word_Error();
                JSavingWordError.show();
                compareFlag=false;
                return;
            }
            else{
                compareFlag=false;
                return;
            
            }
        }
    
    }
    
    private void deleteTheWordFromPerseus() throws FileNotFoundException, IOException {
        
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΠΕΡΣΕΑΣ  <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/perseus/per_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
        deleteFlag=false;       
    }
    
    private void deleteTheWordFromLogeion() throws FileNotFoundException, IOException { 
        
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΛΟΓΕΙΟΝ  <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/logeion/log_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");            
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
       deleteFlag=false; 
    }
    
    private void deleteTheWordFromPalaiaDiathiki() throws FileNotFoundException, IOException { 
        
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΠΑΛΑΙΑ ΔΙΑΘΗΚΗ  <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/palaiadiathiki/pd_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");                
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
        deleteFlag=false;        
        
    }
    
    private void deleteTheWordFromKainiDiathiki() throws FileNotFoundException, IOException { 
        
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΚΑΙΝΗ ΔΙΑΘΗΚΗ  <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/kainidiathiki/kd_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");              
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
        deleteFlag=false;        
        
    }
    
    private void deleteTheWordFromPolytonikaKeimena() throws FileNotFoundException, IOException { 
        
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΠΟΛΥΤΟΝΙΚΑ ΚΕΙΜΕΝΑ  <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/polytonikakeimena/pk_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");             
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
        deleteFlag=false;        
        
    }
        
    private void deleteTheWordFromLexikoChristi() throws FileNotFoundException, IOException { 
        
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΛΕΞΙΚΟ ΧΡΗΣΤΗ  <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/lexikochristi/lc_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");            
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");             
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε! ");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
        deleteFlag=false;       
        
    }        

    private void deleteTheWordFromOlaTaLexika() throws FileNotFoundException, IOException {  
    
        compareFlag=true;
        deleteFlag=true;
        name=backupWord;
        compareMyGramma();
            
        if (compareFlag==false){
            return;
        }
        else {
        
            //System.out.println("======>>>>  ΟΛΑ ΤΑ ΛΕΞΙΚΑ <<<<==========");
            //System.out.println("");
        
            backupMyFolder=("./data/olatalexika/olatalexika_");
 
            PrintWriter pw1 = new PrintWriter(backupMyFolder + "output.dat");
            //System.out.println(backupMyFolder + "output.dat");
            BufferedReader br1 = new BufferedReader(new FileReader(backupMyFolder + pathGramma));
            //System.out.println(backupMyFolder + pathGramma);
            //System.out.println("");               
            String line1 = br1.readLine();

            while(line1 != null)
                {
                    boolean flag = false;
                    if(line1.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw1.println(line1);
            
                line1 = br1.readLine();
            
                }
        
            pw1.flush();
            br1.close();
            pw1.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
     
            File fileInput1 = new File(backupMyFolder + pathGramma);
            File renameInput1 = new File(backupMyFolder + "old_" + pathGramma);
            File fileOutput1 = new File(backupMyFolder + "output.dat");
            File renameOutput1 = new File(backupMyFolder + pathGramma);

            boolean flagInput1 = fileInput1.renameTo(renameInput1);
            if (flagInput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput1 = fileOutput1.renameTo(renameOutput1);
            if (flagOutput1 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput1.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
            
        ////////////////////////////////////////////////////////////////////////
        
            PrintWriter pw2 = new PrintWriter(backupMyFolder + "output.dat");
            BufferedReader br2 = new BufferedReader(new FileReader(backupMyFolder + "ola_ta_grammata.dat"));
            String line2 = br2.readLine();

            while(line2 != null)
                {
                    boolean flag = false;
                    if(line2.equals(backupWord))
                {
                    flag = true;
                }
            
                if(!flag)
                pw2.println(line2);
            
                line2 = br2.readLine();
            
                }
        
            pw2.flush();
        
            br2.close();
            pw2.close();
                
            //System.out.println("Ἡ λέξη σβήστηκε μὲ ἐπιτυχία!");
        
        //       
            File fileInput2 = new File(backupMyFolder + "ola_ta_grammata.dat");
            File renameInput2 = new File(backupMyFolder + "old_" + "ola_ta_grammata.dat");
            File fileOutput2 = new File(backupMyFolder + "output.dat");
            File renameOutput2 = new File(backupMyFolder + "ola_ta_grammata.dat");

            boolean flagInput2 = fileInput2.renameTo(renameInput2);
            if (flagInput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
            }
        
        
            boolean flagOutput2 = fileOutput2.renameTo(renameOutput2);
            if (flagOutput2 == true) {
                //System.out.println("Τὸ ἀρχεῖο μετενομάστηκε!");
            }
            else {
            //System.out.println("Ἡ μετενομασία ἀπέτυχε!");
        }

            if (renameInput2.delete()) {
                //System.out.println("Τὸ ἀρχεῖο σβήστηκε μὲ ἐπιτυχία!");
            }
            else {
            //System.out.println("Ἀδυναμία διαγραφῆς τοῦ ἀρχείου.");
           }
           
        }
        deleteFlag=false;
    }
    
    
    private void updateMyWord(){
        
        compareFlag=true;
        name=textPane.getText();
        compareMyGramma();
        
        try {
            
            if (compareFlag==false){
                return;
            }
            else {
            
            FileWriter fw1 = new FileWriter(path, true);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path);
            fw1.write(textPane.getText());
            fw1.close();
            
            ArrayList<String> words1 = readFromFile(path);
            Collections.sort(words1);
            saveToFile(path, words1);
        
            //*****************
            
            //pathGramma="ola_ta_grammata";
            path = (pathFakelos + pathPG + "ola_ta_grammata.dat");
            FileWriter fw2 = new FileWriter(path, true);
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path);
            fw2.write(textPane.getText());
            fw2.close();

            ArrayList<String> words2 = readFromFile(path);    //ArrayList<String> words2 = readFromFile(path_userLex);
            Collections.sort(words2);
            saveToFile(path, words2);    //saveToFile(path_userLex, words2);
        
            //*****************
            
            path_for_letter_allLex=("./data/olatalexika/" + "olatalexika_"+ pathGramma);
            FileWriter fw3 = new FileWriter(path_for_letter_allLex, true);
            fw3.write(textPane.getText());
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path_for_letter_allLex);
            fw3.close();
           
            ArrayList<String> words3 = readFromFile(path_for_letter_allLex);
            Collections.sort(words3);
            saveToFile(path_for_letter_allLex, words3);
    
            //*****************
                   
            FileWriter fw4 = new FileWriter(path_allLex, true);
            fw4.write(textPane.getText());
            //System.out.println("Ἡ ἀποθήκευση θὰ γίνει στὸ ἀρχεῖο: " + path_allLex);
            fw4.close();
           
            ArrayList<String> words4 = readFromFile(path_allLex);
            Collections.sort(words4);
            saveToFile(path_allLex, words4);
    
            //*****************
            
            //System.out.println("");
            //System.out.println("");
            //System.out.println("");
            
        Updating_Word JUpdatingWord = new Updating_Word();
        JUpdatingWord.show();
               
        }
        }
        
        catch (IOException e)
        {
            e.printStackTrace();
        }
              
    }
    
    public boolean isDark(Color color) {
        brightness = (color.getRed() * 0.299 + 
                         color.getGreen() * 0.587 + 
                         color.getBlue() * 0.114);
    
        //System.out.println("Ἡ φωτεινότητα τοῦ φόντου στὸ πτυσσόμενο μενοῦ εἶναι: " + brightness);
        if (brightness <= 64){
            //System.out.println("Πολὺ σκοτεινὸ φόντο!");
            selectItemColor=BG_Color_btn.brighter().brighter();
    }
        else if ((brightness > 64) && (brightness <=128)){
            //System.out.println("Σκοτεινὸ φόντο!");
            selectItemColor=BG_Color_btn.brighter();
            
        }
        else if ((brightness > 128) && (brightness <=192)){
            //System.out.println("Φωτεινὸ φόντο!");
            selectItemColor=BG_Color_btn.darker();
        }
    
        else if ((brightness > 192) && (brightness <=255)){
            //System.out.println("Πολὺ φωτεινὸ φόντο!");
            selectItemColor=BG_Color_btn.darker().darker();
        }
    
        else {
        }
        
        //System.out.println("");
        //System.out.println("");
        
        return brightness < 128;
}
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonColor_TextBackDefaultColor_;
    private javax.swing.JButton jButtonColor_TextForeDefaultColor_;
    private javax.swing.JButton jButton_AddRecord_;
    private javax.swing.JButton jButton_AddΜultipleRecords_;
    private javax.swing.JButton jButton_Asceding_Order_;
    private javax.swing.JButton jButton_Big_Letter_;
    private javax.swing.JButton jButton_CenterAlignment_;
    private javax.swing.JButton jButton_DeleteRecord_;
    private javax.swing.JToggleButton jButton_Font_Bold_;
    private javax.swing.JButton jButton_Font_Decrease_;
    private javax.swing.JButton jButton_Font_Increase_;
    private javax.swing.JButton jButton_JustifiedAlignment_;
    private javax.swing.JButton jButton_LeftAlignment_;
    private javax.swing.JButton jButton_OpenLink_;
    private javax.swing.JButton jButton_Refresh_;
    private javax.swing.JButton jButton_RightAlignment_;
    private javax.swing.JButton jButton_Save_;
    private javax.swing.JButton jButton_SelectLexiconSave_;
    private javax.swing.JButton jButton_Small_Letter_;
    private javax.swing.JButton jButton_TextBackColor_;
    private javax.swing.JButton jButton_TextForeColor_;
    private javax.swing.JButton jButton_UpdateRecord_;
    private javax.swing.JButton jButton_X_Clear_;
    private javax.swing.JCheckBox jCheckBox_EnableEditing_;
    private javax.swing.JCheckBox jCheckBox_Enable_Asceding_Order_;
    private javax.swing.JColorChooser jColorChooser_1;
    private javax.swing.JComboBox<String> jComboBox_FontSize_;
    private javax.swing.JComboBox<String> jComboBox_SystemFonts_;
    private javax.swing.JFileChooser jFileChooser_1;
    private javax.swing.JLabel jLabel_Search_;
    private javax.swing.JLabel jLabel_Source_;
    private javax.swing.JLabel jLabel_totalRecords_;
    private javax.swing.JPanel jPanel_Database_;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_DB_;
    private javax.swing.JTextField jTextField_FontSize_;
    private javax.swing.JTextField jTextField_Link_;
    private javax.swing.JTextField jTextField_searchTxt_;
    private javax.swing.JTextField jTextField_totalRecords_;
    private javax.swing.JToggleButton tButton_Italic_;
    private javax.swing.JToggleButton tButton_Underline_;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables

}


