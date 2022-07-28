package com.company;

import java.awt.*;

public class Player extends Figure {
    private Direction newDirection;

    public Player(Image image){
        super(image);
        newDirection = Direction.RIGHT;
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
}
