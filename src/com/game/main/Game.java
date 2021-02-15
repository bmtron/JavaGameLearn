package com.game.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.security.Key;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -473349850293143017L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;

    private Thread thread;

    private boolean running = false;
    private Handler handler;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean jumping;



    public Game() {
        handler = new Handler();

        new Window(WIDTH, HEIGHT, "Let's Build a Game", this);

        handler.addObject(new Player(50, 300, ID.Player));

    }

    public static void main(String args[]) {
        new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);

        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        handleInput();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                if (handler.getJumping()) {
                    handler.jumpTick();
                    jumping = handler.getJumping();
                }
                delta--;
            }
            movePlayer();
            if (running) {
                render();
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }



    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void handleInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    up = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    down = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    left = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    right = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !jumping) {
                    handler.setJumping();
                    jumping = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    up = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    down = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    left = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    right = false;
                }
            }
        });
    }

    private void movePlayer() {
        if (up) {
            handler.setYVelTick(-2);
        }
        if (down) {
            handler.setYVelTick(2);
        }
        if (left) {
            handler.setXVelTick(-2);
        }
        if (right) {
            handler.setXVelTick(2);
        }

        if (!up && !down) {
            handler.setYVelTick(0);
        }
        if (!right && !left) {
            handler.setXVelTick(0);
        }

    }

    private void handleJump() {

    }

}
