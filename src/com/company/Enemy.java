package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Figure {
    private Skill skill;
    public Enemy(Image image){
        super(image);
        skill = Skill.RANDOM;
    }

    @Override
    public void go(){
        if(isOnField()) {
            maybeChangeDirection();
        }
        super.go();
    }

    protected void maybeChangeDirection() {

        ArrayList<Direction> possibleDirections = new ArrayList<>();
        for (Direction newDirection: Direction.values()) {
            if(canChangeDirection(newDirection)){
                possibleDirections.add(newDirection);
            }
        }
        possibleDirections.remove(direction.getOppositeDirection());

        chooseDirection(possibleDirections.toArray());
    }

    public void chooseDirection(Object[] directions){
        switch (skill){
            case RANDOM -> {
                int randomNumber = (int) (Math.random() * (directions.length));
                direction = (Direction) directions[randomNumber];
            }
            case FollowPoint -> {

            }
        }
    }

    public enum Skill{
        RANDOM(),
        FollowPoint();


    }
}
