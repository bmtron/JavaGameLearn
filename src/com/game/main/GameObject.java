package com.game.main;

import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int velX, velY;
    public boolean jumping = false;

    protected boolean jumpingUp = false;
    protected boolean jumpingDown = false;
    protected int totalJumpTicks = 0;


    protected int CONST_PLAYER_JUMP_TICKS = 10;
    protected int CONST_PLAYER_JUMP_ACCEL = -1;
    protected int CONST_PLAYER_JUMP_DECEL = 1;

    protected int CONST_BASE_VEL = 10;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void jumpTick();
    public abstract void render(Graphics g);

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY(int y) {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setJumping() {
        this.jumping = !this.jumping;
    }

}
