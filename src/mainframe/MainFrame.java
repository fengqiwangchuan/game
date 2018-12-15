package mainframe;

import snake.Node;
import snake.Snake;
import util.Direction;
import util.NodeLength;
import util.Speed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MainFrame {

    int pause = 0;

    private final int WIDTH = 1500;
    private final int HEIGHT = 1000;

    int wNum = WIDTH / NodeLength.NODELENGTH - 1;
    int hNum = HEIGHT / NodeLength.NODELENGTH - 1;

    Node food = new Node(NodeLength.NODELENGTH * new Random().nextInt(wNum - 4) + 2, NodeLength.NODELENGTH * new Random().nextInt(hNum - 4) + 2);
    Snake snake = new Snake();

    public void print() {
        JFrame jFrame = new JFrame("贪吃蛇");
        jFrame.setLocation(200,40);
        jFrame.setSize(WIDTH, HEIGHT);
        Container container = jFrame.getContentPane();
        MyPainter myPainter = new MyPainter();
        myPainter.setBackground(Color.GRAY);
        jFrame.add(myPainter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!snake.isStopGame()) {
                        if (snake.move(food)) {
                            food = new Node(NodeLength.NODELENGTH * new Random().nextInt(wNum - 4) + 2, NodeLength.NODELENGTH * new Random().nextInt(hNum - 4) + 2);
                        }
                    }
                    if (snake.getMessage().equals("die")) {
                        snake.setStopGame(true);
                        int i = JOptionPane.showConfirmDialog(jFrame, "restart?", "message", JOptionPane.YES_NO_OPTION);
                        if (i == 0) {
                            snake = new Snake();
                            food = new Node(NodeLength.NODELENGTH * new Random().nextInt(wNum - 4) + 2, NodeLength.NODELENGTH * new Random().nextInt(hNum - 4) + 2);
                        } else {
                            System.exit(0);
                        }
                    }

                    int x = snake.getSnake().getFirst().getX();
                    int y = snake.getSnake().getFirst().getY();

                    if (x > (WIDTH - NodeLength.NODELENGTH) || x < 0 || y > (HEIGHT - NodeLength.NODELENGTH) || y < 0) {
                        snake.setStopGame(true);
                        int i = JOptionPane.showConfirmDialog(jFrame, "restart?", "message", JOptionPane.YES_NO_OPTION);
                        if (i == 0) {
                            snake = new Snake();
                            food = new Node(NodeLength.NODELENGTH * new Random().nextInt(wNum - 4) + 2, NodeLength.NODELENGTH * new Random().nextInt(hNum - 4) + 2);
                        } else {
                            System.exit(0);
                        }
                    }
                    try {
                        Thread.sleep(Speed.SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jFrame.repaint();
                }
            }
        }).start();

        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.setDirection(Direction.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.setDirection(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.setDirection(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setDirection(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (pause == 0) {
                        snake.setStopGame(true);
                        pause = 1;
                    } else if (pause == 1) {
                        snake.setStopGame(false);
                        pause = 0;
                    }
                }
            }
        });

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class MyPainter extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            for (int i = 0; i < HEIGHT; i+=NodeLength.NODELENGTH) {
                g.drawLine(0,i,WIDTH,i);
            }
            for (int i = 0; i < WIDTH; i += NodeLength.NODELENGTH) {
                g.drawLine(0, i, HEIGHT, i);
            }

            g.setColor(Color.BLACK);
            g.fillOval(food.getX(), food.getY(), NodeLength.NODELENGTH, NodeLength.NODELENGTH);
            for (Node node : snake.getSnake()) {
                g.fillOval(node.getX(), getY(), NodeLength.NODELENGTH, NodeLength.NODELENGTH);
            }

            g.setColor(Color.red);
            g.setFont(new Font("黑体", 50, 50));
            g.drawString(String.valueOf(snake.getSnake().size()), WIDTH - 50, 50);
        }
    }

    public static void main(String[] args) {
        new MainFrame().print();
    }
}
