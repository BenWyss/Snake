import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private int snakeLength = 3;
    private int moves = 0;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeImage;

    private ImageIcon titleImage;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {

        if(moves == 0) {
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
        }

        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        titleImage = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        rightMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        for (int i = 0; i < snakeLength; i++) {
            if (i==0 && right) {
                rightMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i==0 && left) {
                leftMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/leftmouth.png");
                leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i==0 && up) {
                upMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/upmouth.png");
                upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i==0 && down) {
                downMouth = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/downmouth.png");
                downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i!=0) {
                snakeImage = new ImageIcon("/Users/benjaminwyss/Documents/GitHub/Snake/snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
        }

        g.dispose();
    }

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
