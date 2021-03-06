/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.compiladores;

import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java_cup.runtime.Symbol;
import java_cup.runtime.XMLElement;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author elco45
 */
public class MainGui extends javax.swing.JFrame {

    /**
     * Creates new form MainGui
     */
    public MainGui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_choose = new javax.swing.JButton();
        tf_direction = new javax.swing.JTextField();
        btn_compile = new javax.swing.JButton();
        btn_tree = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_choose.setText("Choose File");
        btn_choose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_chooseMouseClicked(evt);
            }
        });

        tf_direction.setEditable(false);

        btn_compile.setText("Compile");
        btn_compile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_compileMouseClicked(evt);
            }
        });

        btn_tree.setText("View Tree");
        btn_tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_treeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(tf_direction, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(btn_choose))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_tree, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_compile, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(tf_direction, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btn_compile, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_tree, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_chooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_chooseMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        String userDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(userDir + "/src/main/resources"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pascal", "pas");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            tf_direction.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_btn_chooseMouseClicked

    private void btn_compileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_compileMouseClicked
        SemanticParser.ts.clear();
        SemanticParser.offset=0;
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        PascalFlexer scanner;
        Parser parser = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(tf_direction.getText()));
            scanner = new PascalFlexer(br);
            parser = new Parser(scanner);
            Symbol Sym = parser.parse();
            if(Sym.sym == 0)
                JOptionPane.showMessageDialog(this, "Se Compilo correctamente el archivo!");
            else 
                JOptionPane.showMessageDialog(this, "Se encontraron erroes en el archivo!");
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Se encontraron erroes en el archivo!"); 
        }
    }//GEN-LAST:event_btn_compileMouseClicked

    private void btn_treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_treeMouseClicked
        JFrame frame = new JFrame("XMLTreeView: [ AST.xml ]");
        frame.setSize(400, 400);

        frame.addWindowListener(new WindowAdapter() {
        });
        new XMLTreeView(frame, "AST.xml");
    }//GEN-LAST:event_btn_treeMouseClicked

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
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_choose;
    private javax.swing.JButton btn_compile;
    private javax.swing.JButton btn_tree;
    private javax.swing.JTextField tf_direction;
    // End of variables declaration//GEN-END:variables
    public TablaSimbolos ts = new TablaSimbolos();
}
