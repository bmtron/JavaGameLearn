package com.game.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);

        velX = 0;
        velY = 0;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void jumpTick() {
        if (totalJumpTicks <= CONST_PLAYER_JUMP_TICKS) {
            velY = (CONST_BASE_VEL - totalJumpTicks);
            y -= velY;
            totalJumpTicks++;
        }
        else if (totalJumpTicks > CONST_PLAYER_JUMP_TICKS && totalJumpTicks <= (CONST_PLAYER_JUMP_TICKS * 2)) {
            velY = CONST_BASE_VEL - totalJumpTicks;
            y -= velY;
            totalJumpTicks++;
        }
        else {
            velY = 0;
            totalJumpTicks = 0;
            jumping = false;
        }
        System.out.println("Y: " + y);
        System.out.println("velY: " + velY);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 20, 20);
    }

}
