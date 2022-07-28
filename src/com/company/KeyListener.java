package com.company;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP -> {
                Game.player.setNewDirection(Figure.Direction.UP);
            }
            case KeyEvent.VK_DOWN -> {
                Game.player.setNewDirection(Figure.Direction.DOWN);
            }
            case KeyEvent.VK_LEFT -> {
                Game.player.setNewDirection(Figure.Direction.LEFT);
            }
            case KeyEvent.VK_RIGHT -> {
                Game.player.setNewDirection(Figure.Direction.RIGHT);
            }

            case KeyEvent.VK_S -> {
                Game.start();
            }
            default -> {

            }
        }
    }
}
