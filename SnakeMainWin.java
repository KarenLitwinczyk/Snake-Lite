import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karen Litwinczyk
 */
public class SnakeMainWin extends javax.swing.JFrame {
    //The variable that holds the instance of InGameWin
    InGameWin inGameWin;
    //The instance of my table on my screen
    DefaultTableModel lb;
    //The variables to hold the url and the audio for the button sounds
    AudioClip menuClick;
    URL url;
    //The variables to hold the url and audio for the control menu sound
    URL urlControls;
    AudioClip controlsSound;
    /**
     * Creates new form SnakeMainWin
     */
    public SnakeMainWin() {
        initComponents();
          inGameWin = InGameWin.getInstance();
          inGameWin.setVisible(false);
          lb = (DefaultTableModel)leaderBoard.getModel();
          this.loadTable();       
          url = SnakeMainWin.class.getResource("pickUp2.wav");
          menuClick = Applet.newAudioClip(url);
          urlControls = SnakeMainWin.class.getResource("hello.wav");
          controlsSound = Applet.newAudioClip(urlControls);
    }
    //This function displays a window with the credits/who made the game.
    private void showCredits(){
        JOptionPane.showMessageDialog(this, "Made By: Karen Litwinczyk");
    }
    //This function displays a window with the instructions of how to play this game.
    private void showControls(){
        JOptionPane.showMessageDialog(this, "How to Play:" + "\n" + "Use your arrow keys"
                + " to move your snake around." +"\n" 
                + "Blue squares are Power Ups, eat these to grow longer!" + "\n"
                + "Red squares are Enemies, hit them and it's Game Over!" + "\n"
                + "Be careful to avoid enemies... Have Fun!");
    }
    //This function uses Gson and Json to read in the highscore and username table from
    //their corresponding Json files, while using BufferedReader and FileReader to do so.
    public void load(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("highScoreTable.json"));
            inGameWin.highScore = inGameWin.gson.fromJson(br, int[].class);
            BufferedReader BR = new BufferedReader(new FileReader("userNameTable.json"));
            inGameWin.userNames = inGameWin.gson.fromJson(BR, String[].class);
            BR.close();
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InGameWin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InGameWin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //This function calls the load function and sorts the table on the main window from
    //lowest highscore to the highest highscore. (lowest on top, highest at the bottom).
    //Then it fills in the table with the information from the highscore and userNames tables.
    public void loadTable(){
            this.load();
            int scoreSwap;
            String nameSwap;
            boolean swapped = false;
            int i = 0;
            while( swapped == false){
            if(i == 4){
                swapped = true;
                break;
            }
            if(inGameWin.highScore[i] > inGameWin.highScore[i+1]){
                scoreSwap = inGameWin.highScore[i];
                inGameWin.highScore[i] = inGameWin.highScore[i+1];
                inGameWin.highScore[i+1] = scoreSwap;
                nameSwap = inGameWin.userNames[i];
                inGameWin.userNames[i] = inGameWin.userNames[i+1];
                inGameWin.userNames[i+1] = nameSwap;
                System.out.println("SWAPPED");
               
            }
             i++;
        }
           for(int j = 0; j < 5; j++){
               lb.addRow(new Object[] {inGameWin.highScore[j], inGameWin.userNames[j]});
           }
           
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        playBtn = new javax.swing.JButton();
        controlBtn = new javax.swing.JButton();
        creditBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        leaderBoard = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playBtn.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        playBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java Pics/Play.png"))); // NOI18N
        playBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });

        controlBtn.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        controlBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java Pics/Options.png"))); // NOI18N
        controlBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        controlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlBtnActionPerformed(evt);
            }
        });

        creditBtn.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        creditBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java Pics/Credits.png"))); // NOI18N
        creditBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        creditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditBtnActionPerformed(evt);
            }
        });

        leaderBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "High Score"
            }
        ));
        jScrollPane2.setViewportView(leaderBoard);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Snake Lite");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlBtn)
                .addGap(100, 100, 100)
                .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(creditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(creditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(controlBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditBtnActionPerformed
        //plays the button sound 
        menuClick.play();
        //calls the showCredits function
        this.showCredits();
        
    }//GEN-LAST:event_creditBtnActionPerformed

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
        //hides this main window and shows the InGameWin window
        this.setVisible(false);
        inGameWin.setVisible(true);
        inGameWin.setMainWindow(this);
        //plays the button sound
        menuClick.play();
    }//GEN-LAST:event_playBtnActionPerformed

    private void controlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlBtnActionPerformed
        //plays the button sound
        menuClick.play();
        //plays the controls sound
        controlsSound.play();
        //calls the showControls function
        this.showControls();
        
    }//GEN-LAST:event_controlBtnActionPerformed

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
            java.util.logging.Logger.getLogger(SnakeMainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SnakeMainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SnakeMainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SnakeMainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SnakeMainWin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton controlBtn;
    private javax.swing.JButton creditBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable leaderBoard;
    private javax.swing.JButton playBtn;
    // End of variables declaration//GEN-END:variables
}
