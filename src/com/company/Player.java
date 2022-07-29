package com.company;

import java.awt.*;

public class Player extends Figure {
    private Direction newDirection;

    public Player(Image image){
        super(image);
        direction = Direction.DOWN;
        newDirection = Direction.DOWN;
    }

    public void setNewDirection(Direction newDirection) {
        this.newDirection = newDirection;
    }

    @Override
    public void go(){
        if (!newDirection.equals(direction) && isOnField()) {
            if (canChangeDirection(newDirection)) {
                direction = newDirection.copy();
            }
        }
        super.go();
    }

    public void position(){
        x = Game.arena.getWidthOfField() * Game.arena.getStartCol();
        y = Game.arena.getHeightOfField() * Game.arena.getStartRow();
    }
}
