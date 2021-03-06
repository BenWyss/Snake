package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    /* Instance Variables */
    //Random and Timer
    private Random random = new Random();
    private Timer timer;

    //Integer Arrays
    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];
    private int[] foodXPos = {
            25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550,
            575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850
    }; //All possible X-Positions of the Food on the Panel
    private int[] foodYPos = {
            75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550,
            575, 600, 625
    }; //All possible Y-Positions of the Food on the Panel

    //Integers
    private int snakeLength = 3; //Starting Length of the Snake = 3
    private int xPos = random.nextInt(foodXPos.length);
    private int yPos = random.nextInt(foodYPos.length);
    private int moves = 0;
    private int score = 0;

    //Booleans
    private boolean left = false; //Left Direction
    private boolean right = false; //Right Direction
    private boolean up = false; // Up Direction
    private boolean down = false; //Down Direction
    private boolean collisionDetected = false; //Self Collision (True or False)

    //ImageIcons
    private ImageIcon rightMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/rightmouth.png");
    private ImageIcon leftMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/leftmouth.png");
    private ImageIcon upMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/upmouth.png");
    private ImageIcon downMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/downmouth.png");
    private ImageIcon snakeImage = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/snakeimage.png");
    private ImageIcon titleImage = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/snaketitle.jpg");
    private ImageIcon foodImage = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/food.png");

    /* Constructor */
    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(100, this);
        timer.start();
    }

    /* Methods/Functions */
    //Paints/Draws on the Panel
    public void paint(Graphics g) {

        if(moves == 0) {
            snakeXLength[2] = 400;
            snakeXLength[1] = 425;
            snakeXLength[0] = 450;

            snakeYLength[2] = 350;
            snakeYLength[1] = 350;
            snakeYLength[0] = 350;
        }

        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        titleImage.paintIcon(this, g, 25, 11);

        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + score, 780, 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: " + snakeLength, 780, 50);

        rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        for (int i = 0; i < snakeLength; i++) {
            if (i==0 && right) {
                rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i==0 && left) {
                leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i==0 && up) {
                upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i==0 && down) {
                downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i!=0) {
                snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
        }
        if ((foodXPos[xPos] == snakeXLength[0] && foodYPos[yPos] == snakeYLength[0])) {
            snakeLength++;
            score++;
            xPos = random.nextInt(foodXPos.length);
            yPos = random.nextInt(foodYPos.length);
        }

        foodImage.paintIcon(this, g, foodXPos[xPos], foodYPos[yPos]);

        for (int c = 1; c < snakeLength; c++) {
            if (snakeXLength[c] == snakeXLength[0] && snakeYLength[c] == snakeYLength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 310, 300);

                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Press Spacebar to Try Again", 100, 360);

                collisionDetected = true;
            }
        }

        g.dispose();
    }

    //ActionListener Method - actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int r = snakeLength - 1; r >= 0; r--) {
                snakeYLength[r+1] = snakeYLength[r];
            }
            for (int r = snakeLength; r >= 0; r--) {
                if (r==0) {
                    snakeXLength[r] = snakeXLength[r] + 25;
                }
                else {
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if (snakeXLength[r] > 850) {
                    snakeXLength[r] = 25;
                }
            }
            repaint();
        }
        if (left) {
            for (int r = snakeLength - 1; r >= 0; r--) {
                snakeYLength[r+1] = snakeYLength[r];
            }
            for (int r = snakeLength; r >= 0; r--) {
                if (r==0) {
                    snakeXLength[r] = snakeXLength[r] - 25;
                }
                else {
                    snakeXLength[r] = snakeXLength[r-1];
                }
                if (snakeXLength[r] < 25) {
                    snakeXLength[r] = 850;
                }
            }
            repaint();
        }
        if (up) {
            for (int r = snakeLength - 1; r >= 0; r--) {
                snakeXLength[r+1] = snakeXLength[r];
            }
            for (int r = snakeLength; r >= 0; r--) {
                if (r==0) {
                    snakeYLength[r] = snakeYLength[r] - 25;
                }
                else {
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if (snakeYLength[r] < 75) {
                    snakeYLength[r] = 625;
                }
            }
            repaint();
        }
        if (down) {
            for (int r = snakeLength - 1; r >= 0; r--) {
                snakeXLength[r+1] = snakeXLength[r];
            }
            for (int r = snakeLength; r >= 0; r--) {
                if (r==0) {
                    snakeYLength[r] = snakeYLength[r] + 25;
                }
                else {
                    snakeYLength[r] = snakeYLength[r-1];
                }
                if (snakeYLength[r] > 625) {
                    snakeYLength[r] = 75;
                }
            }
            repaint();
        }
    }

    //KeyListener Method - keyPressed
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            snakeLength = 3;
            collisionDetected = false;
            right = true;
            left = false;
            up = false;
            down = false;
            repaint();
        }
        if (!collisionDetected) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
                moves++;
                right = true;
                left = false;
                up = false;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT && !right) {
                moves++;
                right = false;
                left = true;
                up = false;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP && !down) {
                moves++;
                right = false;
                left = false;
                up = true;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN && !up) {
                moves++;
                right = false;
                left = false;
                up = false;
                down = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                //PAUSE
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //LEAVE EMPTY / NOT USED
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //LEAVE EMPTY / NOT USED
    }
}
