package demolition;

import processing.core.PImage;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class Enemy extends Character {

    public Enemy (int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.life = 1;
        this.sprite = sprite;

        this.yVel = 0;
        this.xVel = 0;

        // enemy should have an x/y velocity as the game starts
    }

    public void tick(int defaultV){
        // refreshes

        if (this.life > 0) {

            if (this.xVel == 0) {
                xVel = defaultV;
            }
            this.x = this.xVel;

            if (this.yVel == 0) {
                yVel = defaultV;
            }
            this.y += this.yVel;
        }
    }

    private void takeTurn(){};

    public String[] moveX(String[] map, int speed) {
        if (this.life > 0) {
            if (speed > 0) {
                String[] newMap = map[x].split("");
                // wall check
                if (Objects.equals(newMap[y], " W") || Objects.equals(newMap[y], "B")) {
                    takeTurn();
                }
                else {
                    newMap[y] = " ";
                    this.y ++;
                    newMap[y] = "R";
                    map[x] = String.join("", newMap);
                }

            }
            else if (speed < 0) {
                this.x --;
            }
        }
        return map;
    }

    public void moveY(int speed) {
        boolean positive;

        if (this.life > 0) {

            if (this.yVel == 0) {
                yVel = speed;
            }
            this.y += this.yVel;
        }
    }

}
