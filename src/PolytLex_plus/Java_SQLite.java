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

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;


public class Java_SQLite {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {

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
        
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));        

        Java_SQLite app = new Java_SQLite();

        Database table = new Database();
        table.show();
      
    }
    
    
    public List<Constructors> SelectAll(){
        
        Constructors Polytlex;
        List<Constructors> polytlexList=new ArrayList<>();
        
        String sql="SELECT * FROM polytlex";
        
        try (Connection conn = connect();
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(sql)){
            //System.out.println("index" + '\t' + "word" + '\t' + "explain") ;            
            while (rs.next()){
                Polytlex = new Constructors(rs.getInt("id"), rs.getString("word"), rs.getString("source1"), rs.getString("source2"), rs.getString("source3"));
                polytlexList.add(Polytlex);
                //System.out.println(rs.getString("index") + '\t' + rs.getString("word") + '\t' + rs.getString("explain"));
                
            }
        } catch (SQLException e){
            //System.out.println(e.getMessage());
        }
      //System.out.println("");
      return polytlexList;
    }
    
    public Connection connect(){
               
                    String url="jdbc:sqlite:./database/sqlitedatabase.db";
                    Connection conn=null;
            
                    try{
                        conn=DriverManager.getConnection(url);
                        //System.out.println("Ἡ σύνδεση μὲ τὴ βάση δεδομένων sqlitedatabase.db ἔγινε!");
                        //System.out.println("");
                    
                }
                catch (SQLException e){
                    //System.out.println(e.getMessage());

                    
                }
                    return conn;
    }
      
    
}
