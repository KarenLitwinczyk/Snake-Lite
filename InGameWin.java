
import com.google.gson.Gson;
import java.applet.Applet;
import java.applet.AudioClip;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static java.lang.Thread.sleep;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//learning about singletons/ reference
//http://www.tutorialspoint.com/java/java_using_singleton.htm

//learning to play sounds 
//http://www.deitel.com/articles/java_tutorials/20060422/LoadingPlayingAudioClips/index.html

//keyboard functionality
//https://www.youtube.com/watch?v=p9Y-NBg8eto

/**
 *
 * @author Karen Litwinczyk
 */
//my in game window that the player sees when playing the game.
public class InGameWin extends javax.swing.JFrame implements FocusListener{
    //these variables hold the url and audio for the button sound
    AudioClip menuClick;
    URL url;
    //these variables hold the url and audio for the background music
    URL BGUrl;
    AudioClip bgMusic;
    //these variables hold the url and audio for the powerUp sound
    URL powerUpURL;
    AudioClip powerUPSound;
    //these variables hold the url and audio for the gameover sound
    URL gameOver;
    AudioClip gameOverSound;
    //This is the only instance of this class.
    private static InGameWin instance = new InGameWin();
    private SnakeMainWin mw;
    //the instance of the front of the snake, always will be made.
    SnakeSeg head;
    //instance of the thread class ScreenUpdater.
    ScreenUpdater su;
    //the score the player currently has
    int s;
    //the state of the game (started vs not started/stopped)
    boolean State = false;
    //the array to hold the top 5 high scores
    int [] highScore;
    //the array to hold the corresponding top 5 high scores player's username
    String[] userNames;
    //this variabe
    String userName;
    String JsonHighScore;
    String JsonUserName;
    Gson gson;
    /**
     * Creates new form InGameWin
     */
    //This class calls the nesscessary functions to update the screen
    //should I just make this a separate class file because it's so long and
    //gross in here
    public class ScreenUpdater extends Thread{
        //reference variable for the instance of InGameWin
        InGameWin inGameWin;

//        @Override
        public void run(){
            inGameWin = InGameWin.getInstance();
            //while game is running
            while(State){
                try{
                    sleep(800);
                        inGameWin.moveSnake();
                            //checks if the head is at the same spot as a powerUp
                            if(head.col == characterPen.powerUp.col && head.row == characterPen.powerUp.row){
                                powerUPSound.play();
                                s = s + 50;
                                characterPen.madeEnemy = false;
                                score.setText(Integer.toString(s));
                                characterPen.makeSnakeSegment();
                                characterPen.powerUp.relocatePowerUp();
                            }
                            //if score is mod 100 make a new enemy
                            else if(s % 100 == 0){
                                if(s <= 0){
                                    //do nothing
                                }
                                else if(characterPen.madeEnemy == false){
                                    characterPen.addToPen("e");
                                }
                            }
                            //makes enemies faster 
                            else if( s > 200){
                                if(characterPen.madeEnemy == false){
                                    characterPen.addToPen("e");
                                }
                            }
                            //makes enemies even faster
                            else if(s > 400){
                                characterPen.addToPen("e");
                            }
                            //checks to see if a player is at the same spot as an enemy
                            for(Character c : characterPen.characterList){
                                if("e".equals(c.type)){
                                    if(c.row == head.row && c.col == head.col ){
                                        State = false;
                                        break;
                                    }
                                    else{
                                        //checks if the head is touching its body
                                        for(SnakeSeg s : characterPen.snakeBody){
                                            if(s.col == c.col && s.row == c.row){
                                                State = false;
                                                System.out.println("hit snake");
                                                break;
                                            }
                                            
                                        }
                                    }
                                }
                            }
                            //if game is over exit loop
                            if(State == false){
                                break;
                            }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            //if the game is over save any new highscores and display the score to the player
            if(State == false){
                bgMusic.stop();
                inGameWin.gameOverSound.play();
                JOptionPane.showMessageDialog(inGameWin, "GAME OVER" + "\n" +
                    "Your score was: " + s);
                inGameWin.save();
                System.out.println("SAVED");
            }
        }             
    }
    
    
    private InGameWin() {
        initComponents();
        //score
        s = 0;
        //The "head" of the snake, always the lead; index 0 of 
        head = new SnakeSeg(24, 24, 7, 2, "s");
        head.dx = 1;
        head.dy = 0;
        characterPen.addToSnake(head);
        su = new ScreenUpdater();      
        setFocusable(true);
        addFocusListener(this);
        //makes tab act like a reg key.
        setFocusTraversalKeysEnabled(false);
        highScore = new int[5];
        userNames = new String[5];
        gson = new Gson();
        url = InGameWin.class.getResource("pickUp2.wav");
        menuClick = Applet.newAudioClip(url);
        BGUrl = InGameWin.class.getResource("backgroundMusic.wav");
        bgMusic = Applet.newAudioClip(BGUrl);
        powerUpURL = InGameWin.class.getResource("powerUp.wav");
        powerUPSound = Applet.newAudioClip(powerUpURL);
        gameOver = InGameWin.class.getResource("gameOver.wav");
        gameOverSound = Applet.newAudioClip(gameOver);
    }   
    
//    This function returns the only instance of InGameWin to any class/object
//    that wants to reference it/its methods.
    public static InGameWin getInstance(){
            return instance;   
    }
    @Override
    public void focusGained(FocusEvent e){
    }
    //sets the focus of the window back when the focus is lost
     @Override
    public void focusLost(FocusEvent e){
        this.setFocus();
    }
    //set the focus of the window to this
    public void setFocus(){
        this.requestFocus();
    }
    //This function sets the instance of SnakeMainWin in THIS class to the 
    //only instance of SnakeMainWin (SMW's THIS).
    public void setMainWindow(SnakeMainWin m){
        this.mw = m;
    }
    
    
    //This function will move the snake(all the segments) and change
    //direction according to the user's input. It will also check to see
    //if the head of the snake is at the outer bounds of the board.
    public void moveSnake(){
        int index;
        SnakeSeg temp = new SnakeSeg();
        SnakeSeg temp2 = new SnakeSeg();
        if(head.x <= 0 || head.x >= characterPen.getWidth()){
            head.dx = 0;
            head.dy = 0;
            State = false;
        }
        else if(head.y <= 0 || head.y >= characterPen.getHeight()){
            head.dx = 0;
            head.dy = 0;
            State = false;
        }
        index = characterPen.snakeBody.size() - 1;
        
        for(int i = index; i >= 1; i--){
            temp = characterPen.snakeBody.get(i);
            temp2 = characterPen.snakeBody.get(i - 1);
            temp.col = temp2.col;
            temp.row = temp2.row;
            temp.x = temp.col * 24;
            temp.y = temp.row * 24;
        }
        head.col += head.dx;
        head.x = head.col * 24;
        head.row += head.dy;
        head.y = head.row * 24;
        this.repaint();
        
        
    }
    //checks to see if the player's score is a new high score and saves the new/old array
    //to a json file
    public void save(){
        for(int j = 0; j < 5; j++){
            if(s > highScore[j]){
                userName = JOptionPane.showInputDialog(this, "Enter in your user name: ");
                highScore[j] = s;
                userNames[j] = userName;
                break;
            }
        }
        try {
            FileWriter fw = new FileWriter("highScoreTable.json");
            JsonHighScore = gson.toJson(highScore);
            fw.write(JsonHighScore);
            fw.close();
            FileWriter FW = new FileWriter("userNameTable.json");
            JsonUserName = gson.toJson(userNames);
            FW.write(JsonUserName);
            FW.close();
            
        } catch (IOException ex) {
            Logger.getLogger(InGameWin.class.getName()).log(Level.SEVERE, null, ex);
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

        jlScore = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        menuBtn = new javax.swing.JButton();
        startBtn = new javax.swing.JButton();
        characterPen = new CharacterPen();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(191, 130, 158));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jlScore.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jlScore.setText("Score:");

        score.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        score.setText("00");

        menuBtn.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        menuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java Pics/Menu.png"))); // NOI18N
        menuBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBtnActionPerformed(evt);
            }
        });

        startBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java Pics/Start.png"))); // NOI18N
        startBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        characterPen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        characterPen.setPreferredSize(new java.awt.Dimension(600, 312));

        javax.swing.GroupLayout characterPenLayout = new javax.swing.GroupLayout(characterPen);
        characterPen.setLayout(characterPenLayout);
        characterPenLayout.setHorizontalGroup(
            characterPenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        characterPenLayout.setVerticalGroup(
            characterPenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(characterPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(258, 258, 258)
                                .addComponent(jlScore)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(score)))
                        .addContainerGap(68, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlScore)
                    .addComponent(score))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(characterPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtnActionPerformed
        this.setVisible(false);
        mw.setVisible(true);
        State = false;
        menuClick.play();
    }//GEN-LAST:event_menuBtnActionPerformed

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed
        State = true;
        menuClick.play();
        bgMusic.loop();
        //characterPen.addToPen();
        characterPen.makeSnakeSegment();
//        characterPen.addToPen("e");
        if(characterPen.madePowerUp == false){
            characterPen.addToPen("p");
        }
       su.start();
    }//GEN-LAST:event_startBtnActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int keyCode = evt.getKeyCode();
        if(keyCode == KeyEvent.VK_UP){
            head.dx = 0;
            head.dy = -1;
        }
        else if(keyCode == KeyEvent.VK_DOWN){

            head.dx = 0;
            head.dy = 1;
        }
        else if(keyCode == KeyEvent.VK_LEFT){
            head.dx = -1; 
            head.dy = 0;
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            head.dx = 1;
            head.dy = 0;
        }
    }//GEN-LAST:event_formKeyPressed

    
    
    
    
    
    
    
    
    /**
     *
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
            java.util.logging.Logger.getLogger(InGameWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InGameWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InGameWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InGameWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//        if(instance.isVisible() == true){
//            instance.su.start();
//        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InGameWin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CharacterPen characterPen;
    private javax.swing.JLabel jlScore;
    private javax.swing.JButton menuBtn;
    private javax.swing.JLabel score;
    private javax.swing.JButton startBtn;
    // End of variables declaration//GEN-END:variables
}
