
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Karen Litwinczyk
 */

public class CharacterPen extends JPanel {
    int row, col;
    int h, w;
    boolean madePowerUp;
    PowerUp powerUp;
    InGameWin inGameWin;
    boolean madeEnemy;
    
    //this 2D array will be the "world" grid and it needs to talk to
    // the snake array and the characterList array to see where they 
    //constantly are on the "map".
    int[][] world;
    //This is the list of characters (enemies and powerups that are currently on 
    //the screen.
    public ArrayList<Character> characterList; 
    //my snake arrayList to hold each element of the snake's body/length
    public ArrayList<SnakeSeg> snakeBody;
    //instance of enemy
    Enemy enemy;
    
    public CharacterPen(){
        madePowerUp = false;
        madeEnemy = false;
        characterList = new ArrayList();
        snakeBody = new ArrayList();
        world = new int[13][25];

    }
    //makes a new instance of an enemy or a powerUp based on what is passed into it
    public void addToPen(String type){
        h = 20;
        w = 20;
        if(type == "p"){
            row = (int)(Math.random() * 10);
            col = (int) (Math.random() * 23);
            powerUp = new PowerUp(h, w, row, col, "p");
            characterList.add(powerUp);
            madePowerUp = true;
        }
        else if (type == "e"){
            row = (int)(Math.random() * 10);
            col = (int) (Math.random() * 23);
            enemy = new Enemy(h, w, row, col, "e");
            characterList.add(enemy);
            madeEnemy = true;   
        }
            
        this.repaint();
    }    
    //This function adds a new instance of SnakeSeg into the snakeBody ArrayList.
    public void addToSnake(SnakeSeg snake){
       snakeBody.add(snake); 
       this.repaint();
    }
    
        
    @Override
    //call the paint components of the instances of the different characters.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(SnakeSeg e : snakeBody){
            e.paintComponent(g);
        }
        for(Character c : characterList){
            c.paintComponent(g);
        }
        
    }
    
    //This function makes a new instance of SnakeSeg and calls the function
    //to add it into the snakeBody ArryayList.
    public void makeSnakeSegment(){
      SnakeSeg num = new SnakeSeg();
      int n = snakeBody.size() - 1;
      num = snakeBody.get(n);
      row = num.row;
      col = num.col - 1;
      w = 24;
      h = 24;
      SnakeSeg s = new SnakeSeg(h, w, row, col, "s");
      this.addToSnake(s);
      this.repaint();
    }
    
    
    
    
    
    
    
}