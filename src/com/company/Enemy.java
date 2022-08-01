package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Figure {
    private Skill skill;

    public Enemy(Image image) {
        super(image);
        skill = Skill.FollowPlayer;
    }

    @Override
    public void go() {
        if (isOnField()) {
            maybeChangeDirection();
        }
        super.go();
    }

    public void position() {
        x = Game.arena.getWidthOfField();
        y = Game.arena.getHeightOfField();
    }

    protected void maybeChangeDirection() {

        ArrayList<Direction> possibleDirections = new ArrayList<>();
        for (Direction newDirection : Direction.values()) {
            if (canChangeDirection(newDirection)) {
                possibleDirections.add(newDirection);
            }
        }

        chooseDirection(possibleDirections);
    }

    public void chooseDirection(ArrayList<Direction> directions) {
        switch (skill) {
            case RANDOM -> {
                directions.remove(direction.getOppositeDirection());
                int randomNumber = (int) (Math.random() * (directions.size()));
                direction = directions.get(randomNumber);
            }

            case FollowPlayer -> {
                directions.remove(direction.getOppositeDirection());
                ArrayList<Direction> goodDirections = (ArrayList<Direction>) directions.clone();
                int differenceX = Game.player.x - x;
                int differenceY = Game.player.y - y;
                for (Direction direction : goodDirections.toArray(new Direction[0])) {
                    int difference = direction.isHorizontal() ? differenceX : differenceY;
                    if (difference < 0) {
                        if (direction.isGrowingPositively()) {
                            goodDirections.remove(direction);
                        }
                    } else if (difference > 0) {
                        if (!direction.isGrowingPositively()) {
                            goodDirections.remove(direction);
                        }
                    } else {
                        goodDirections.remove(direction);
                    }
                }

                if (goodDirections.size() == 2) {
                    for (Direction newDirection : goodDirections.toArray(new Direction[0])) {
                        if (!(newDirection.isHorizontal() ^ Math.abs(differenceX) > Math.abs(differenceY))) {
                            direction = newDirection;
                        }
                    }
                } else if (goodDirections.size() == 1) {
                    direction = goodDirections.get(0);
                } else {
                    direction = directions.get(0);
                }

            }
        }
    }

    public enum Skill {
        RANDOM(),
        FollowPlayer();


    }
}
