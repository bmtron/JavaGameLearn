package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void jumpTick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.jumpTick();
        }
    }

    public void setYVelTick(int addVel) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.id == ID.Player) {
                tempObject.setVelY(addVel);
            }
        }
    }

    public void setXVelTick(int addVel) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.id == ID.Player) {
                tempObject.setVelX(addVel);
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void setJumping() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.id == ID.Player) {
                tempObject.jumping = !tempObject.jumping;
            }
        }
    }

    public boolean getJumping() {
        boolean jumping = false;
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.id == ID.Player) {
                jumping = tempObject.jumping;
            }
        }

        return jumping;
    }

}
