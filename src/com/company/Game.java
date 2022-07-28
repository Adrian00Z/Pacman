package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Date;

public class Game extends JPanel implements Runnable{
    public static Arena arena = Arena.BasicArena;
    public static Player player = new Player(Main.image);
    public static Enemy enemy = new Enemy(Main.image2);
    private Date currentTime;
    private Date startTime;
    public static boolean play = false;


    public Game(){
        System.out.println(getHeight());
        arena = Arena.BasicArena;
        Arena.heightOfField = this.getHeight()/arena.getRows();
        Arena.widthOfField = this.getWidth()/arena.getCols();

        player = new Player(Main.image);
        enemy = new Enemy(Main.image2);

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                //player.setPositionRelatively(arena.getWidth(), arena.getHeight());
                repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        Thread thread = new Thread(this);
        thread.start();
    }

    public static void start(){
        player.start();
        enemy.start();

        play = true;
    }


    @Override
    public void run() {

        startTime = new Date();
        while (true) {
            System.out.print("");
            if(play) {
                Arena.heightOfField = this.getHeight()/arena.getRows();
                Arena.widthOfField = this.getWidth()/arena.getCols();

                currentTime = new Date();

                if ((currentTime.getTime() - startTime.getTime()) >= 10) {
                    startTime = new Date();
                    tick();
                }
            }
        }
    }

    private void tick(){
        player.go();
        enemy.go();
        repaint();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
        //int[][] fields = arena.getStates();
        Arena.heightOfField = this.getHeight()/arena.getRows();
        Arena.widthOfField = this.getWidth()/arena.getCols();

        for (int rows = 0; rows < arena.getRows(); rows++) {
            for (int cols = 0; cols < arena.getCols(); cols++) {
                if (arena.getStateOfField(rows, cols) == 0){
                    g2D.setColor(Color.BLACK);
                } else {
                    g2D.setColor(Color.MAGENTA);
                }
                g2D.fillRect(Arena.widthOfField*cols, Arena.heightOfField*rows, Arena.widthOfField, Arena.heightOfField);
            }
        }
        player.draw(g2D);
        enemy.draw(g2D);
    }
}
