
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KAL
 */
//makes a segment of a snake, not the whole thing, they will be "chained" 
//together so that look like a snake as a whole
//will all move at the same rate, so they don't overlap.

public class SnakeSeg extends Character{
    int dx, dy;
    public SnakeSeg(int h, int w, int row, int col, String type){
        super(h, w, row, col, "s");
    }
    public SnakeSeg(){
        
        
    }
    //snake segments are green like they are supposed to be when initially made.
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillOval(x, y, h, w);
        g2.setColor(Color.GREEN);
        
    }
    



   
    
    
}
