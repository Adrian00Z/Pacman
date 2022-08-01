package com.company;

import javax.swing.*;
import java.awt.*;


public class Figure extends JLabel {

    protected Direction direction;
    protected int x;
    private int xPercent;
    protected int y;
    protected int yPercent;
    private Image image;

    public Figure(Image image) {
        direction = Direction.RIGHT;
        setImage(image);
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public int getRow() {
        return y / Game.arena.getHeightOfField();
    }
    public int getCol() {
        return x / Game.arena.getWidthOfField();
    }
    public boolean isOnField(){
        return (x % Game.arena.getWidthOfField() == 0) && (y % Game.arena.getHeightOfField() == 0);
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

        xPercent = x * 1_000_000 / (Game.arena.getWidthOfField() * (Game.arena.getCols() - 1));
        yPercent = y * 1_000_000 / (Game.arena.getHeightOfField() * (Game.arena.getRows() - 1));

        //System.out.println(xPercent + ", " + yPercent);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected boolean canChangeDirection(Direction newDirection) {
        return (Game.arena.getStateOfNextField(getRow(), getCol(), newDirection).canGoOnThis());
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, Game.arena.getWidthOfField(), Game.arena.getHeightOfField(), null);

        if (x > (Game.arena.getWidth() - Game.arena.getWidthOfField())){
            g.drawImage(image, x - Game.arena.getWidth(), y, Game.arena.getWidthOfField(), Game.arena.getHeightOfField(), null);
        }
        if (y > (Game.arena.getHeight() - Game.arena.getHeightOfField())){
            g.drawImage(image, x, y - Game.arena.getHeight(), Game.arena.getWidthOfField(), Game.arena.getHeightOfField(), null);
        }
    }

    public boolean canGoNextField() {
        return (Game.arena.getStateOfNextField(getRow(), getCol(), direction).canGoOnThis());
    }

    public void setPositionRelatively(){
        x = 1 + (xPercent * (Game.arena.getWidthOfField() * (Game.arena.getCols() - 1))) / 1_000_000;
        y = 1 + (yPercent * (Game.arena.getHeightOfField() * (Game.arena.getRows() - 1))) / 1_000_000;
    }

    public enum Direction {
        UP(false, false),
        DOWN(false, true),
        RIGHT(true, true),
        LEFT(true, false);

        private final boolean isHorizontal;
        private final boolean isGrowingPositively;

        Direction(boolean isHorizontal, boolean isGrowingPositively){
            this.isHorizontal = isHorizontal;
            this.isGrowingPositively = isGrowingPositively;
        }

        public boolean isHorizontal() {
            return isHorizontal;
        }

        public boolean isGrowingPositively() {
            return isGrowingPositively;
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
