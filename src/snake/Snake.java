package snake;

import util.Direction;
import util.NodeLength;
import util.StartLocation;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Node> snake = new LinkedList<>();
    private int direction;
    private boolean stopGame = false;
    private String message = "";

    public Snake() {
        snake.add(new Node(StartLocation.STARTX + (2 * NodeLength.NODELENGTH), StartLocation.STARTY));
        snake.add(new Node(StartLocation.STARTX + NodeLength.NODELENGTH, StartLocation.STARTY));
        snake.add(new Node(StartLocation.STARTX, StartLocation.STARTY));

        direction = Direction.RIGHT;
    }

    public boolean move(Node food) {
        boolean eatFood = false;

        switch (direction) {
            case Direction.UP: {
                int x = snake.getFirst().getX();
                int y = snake.getFirst().getY() - NodeLength.NODELENGTH;
                Node node = new Node(x, y);
                if (snake.contains(node)) {
                    stopGame = true;
                    message = "die";
                }
                snake.addFirst(node);
                if (food.equals(node)) {
                    eatFood = true;
                } else {
                    snake.removeLast();
                }
            }
            break;
            case Direction.DOWN: {
                int x = snake.getFirst().getX();
                int y = snake.getFirst().getY() + NodeLength.NODELENGTH;
                Node node = new Node(x, y);

                if (snake.contains(node)) {
                    stopGame = true;
                    message = "die";
                }
                snake.addFirst(node);
                if (food.equals(node)) {
                    eatFood = true;
                } else {
                    snake.removeLast();
                }
            }
            break;

            case Direction.LEFT: {
                int x = snake.getFirst().getX() - NodeLength.NODELENGTH;
                int y = snake.getFirst().getY();
                Node node = new Node(x, y);

                if (snake.contains(node)) {
                    stopGame = true;
                    message = "die";
                }
                snake.addFirst(node);
                if (food.equals(node)) {
                    eatFood = true;
                } else {
                    snake.removeLast();
                }
            }
            break;
            case Direction.RIGHT: {
                int x = snake.getFirst().getX() + NodeLength.NODELENGTH;
                int y = snake.getFirst().getY();
                Node node = new Node(x, y);

                if (snake.contains(node)) {
                    stopGame = true;
                    message = "die";
                }
                snake.addFirst(node);
                if (food.equals(node)) {
                    eatFood = true;
                } else {
                    snake.removeLast();
                }
            }
            break;
            default:
                break;
        }
        return eatFood;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        if ((direction + this.direction) == 10) {
            this.direction = 10 - direction;
        } else {
            this.direction = direction;
        }
    }

    public LinkedList<Node> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<Node> snake) {
        this.snake = snake;
    }

    public boolean isStopGame() {
        return stopGame;
    }

    public void setStopGame(boolean stopGame) {
        this.stopGame = stopGame;
    }

    public String getMessage() {
        return message;
    }
}
