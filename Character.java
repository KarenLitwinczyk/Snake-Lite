
import java.awt.Graphics;
import javax.swing.JComponent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karen Litwinczyk
 */
//Sets the inital fields for the necessary variables for all the characters.
public class Character extends JComponent {
    int x, y;
    int h, w;
    int row, col;
    String type;
    
    public Character(int h, int w, int row, int col, String type){
        //need to off set this value since the x,y coordinates are in
        // the top left of the circle' not sure by how much
        this.x = col * 24;
        this.y = row * 24;
        this.h = h;
        this.w = w;
        this.row = row;
        this.col = col;
        this.type = type;
        
    }
    public Character(){
        this.x = col * 24;
        this.y = col * 24;
        this.h = h;
        this.w = w;
        this.row = row;
        this.col = col;
        this.type = type;
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    
}
