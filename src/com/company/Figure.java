package com.company;

import javax.swing.*;
import java.awt.*;


public class Figure extends JLabel {

    protected Direction direction;
    private int x;
    private double xPercent;
    private int y;
    private double yPercent;
    private Image image;

    public Figure(Image image) {
        direction = Direction.RIGHT;
        setImage(image);
    }

    public void start(){
        x = Arena.widthOfField;
        y = Arena.heightOfField;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public int getRow() {
        return y / Arena.heightOfField;
    }
    public int getCol() {
        return x / Arena.widthOfField;
    }
    public boolean isOnField(){
        return (x % Arena.widthOfField == 0) && (y % Arena.heightOfField == 0);
    }

    public void go() {
        boolean isOnField = this.isOnField();

        if (!isOnField || canGoNextField()) {
            switch (direction) {
                case UP -> {
                    y--;
                }
                case DOWN -> {
                    y++;
                }
                case LEFT -> {
                    x--;
                }
                case RIGHT -> {
                    x++;
                }
            }

            if (x < 0){
                x += Game.arena.getWidth();
            } else if (x > Game.arena.getWidth()){
                x -= Game.arena.getWidth();
            } else if (y < 0) {
                y += Game.arena.getHeight();
            } else if (y > Game.arena.getHeight()) {
                y -= Game.arena.getHeight();
            }
        }

        xPercent = x * 100 / (Arena.widthOfField * (Game.arena.getCols() - 1));
        yPercent = y * 100 / (Arena.heightOfField * (Game.arena.getRows() - 1));

        //System.out.println(xPercent + ", " + yPercent);
    }



    protected boolean canChangeDirection(Direction newDirection) {
        return (Game.arena.getStateOfNextField(getRow(), getCol(), newDirection) == 1);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, Arena.widthOfField, Arena.heightOfField, null);

        if (x > (Game.arena.getWidth() - Arena.widthOfField)){
            g.drawImage(image, x - Game.arena.getWidth(), y, Arena.widthOfField, Arena.heightOfField, null);
        }
        if (y > (Game.arena.getHeight() - Arena.heightOfField)){
            g.drawImage(image, x, y - Game.arena.getHeight(), Arena.widthOfField, Arena.heightOfField, null);
        }
    }

    public boolean canGoNextField() {
        return (Game.arena.getStateOfNextField(getRow(), getCol(), direction) == 1);
    }

    public void setPositionRelatively(int width, int height){
        System.out.println(xPercent + ", " + width);
        x = (int) (xPercent * width);
        y = (int) (yPercent * height);
    }

    public enum Direction {
        UP(false),
        DOWN(false),
        RIGHT(true),
        LEFT(true);

        public boolean isHorizontal;

        Direction(boolean isHorizontal){
            this.isHorizontal = isHorizontal;
        }

        protected Direction copy() {
            return Direction.valueOf(this.toString());
        }

        public Direction getOppositeDirection(){
             return switch (this){
                 case UP -> DOWN;
                 case DOWN -> UP;
                 case RIGHT -> LEFT;
                 case LEFT -> RIGHT;
             };
        }

    }
}
