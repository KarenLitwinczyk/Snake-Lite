
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
 * @author Karen Litwinczyk
 */
public class Enemy extends Character {
    int dx, dy;
    public Enemy(int h, int w,int row, int col, String type){
        super(h, w,row, col, type);
        this.dx = 1;
        this.dy = 0;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(x, y, w, h);

        
    }
}
