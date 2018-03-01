
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karen Litwinczyk
 */
public class PowerUp extends Character {

    public PowerUp(int h, int w, int row, int col, String type){
        super(h, w,row, col, type);
        
    }
    
    //moves the powerUp to a different location on the grid when called
    public void relocatePowerUp(){
        this.row = (int)(Math.random() * 10);
        this.col = (int) (Math.random() * 23);
        this.x = col * 24;
        this.y = row * 24;
    }
   @Override
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D) g;
       g2.setColor(Color.BLUE);
       g2.fillRect(x, y, h, w);
           
   }
    
}
