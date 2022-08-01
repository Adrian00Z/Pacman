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
    public static boolean isPlaying = false;

    public int timeBetweenTick = 10;


    public Game(){
        arena = Arena.BasicArena;
        arena.setHeightOfField(this.getHeight()/arena.getRows());
        arena.setWidthOfField(this.getWidth()/arena.getCols());

        player = new Player(Main.image);
        enemy = new Enemy(Main.image2);

        addComponentListener();

        Thread thread = new Thread(this);
        thread.start();
    }

    public static void start(){
        player.position();
        enemy.position();

        isPlaying = true;
    }

    @Override
    public void run() {

        startTime = new Date();
        while (true) {
            System.out.print("");
            if(isPlaying) {
                currentTime = new Date();

                if ((currentTime.getTime() - startTime.getTime()) >= timeBetweenTick) {
                    System.out.println(currentTime.getTime() - startTime.getTime());
                    startTime.setTime(startTime.getTime() + timeBetweenTick);
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

        for (int rows = 0; rows < arena.getRows(); rows++) {
            for (int cols = 0; cols < arena.getCols(); cols++) {
                if (arena.getStateOfField(rows, cols) == Arena.State.WALL){
                    g2D.setColor(Color.BLACK);
                } else {
                    g2D.setColor(Color.MAGENTA);
                }
                g2D.fillRect(arena.getWidthOfField()*cols, arena.getHeightOfField()*rows, arena.getWidthOfField(), arena.getHeightOfField());
            }
        }
        player.draw(g2D);
        enemy.draw(g2D);
    }

    public void addComponentListener(){
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Game.arena.setHeightOfField(e.getComponent().getHeight()/Game.arena.getRows());
                Game.arena.setWidthOfField(e.getComponent().getWidth()/Game.arena.getCols());
                Game.player.setPositionRelatively();
                Game.enemy.setPositionRelatively();
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
    }
}
