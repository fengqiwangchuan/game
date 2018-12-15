package util;

import java.util.Random;

import static util.NodeLength.NODELENGTH;

public class StartLocation {
    public static final int STARTX = NODELENGTH * (new Random().nextInt(2) + 1);
    public static final int STARTY = NODELENGTH * (new Random().nextInt(2) + 1);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextInt(2) + 1);
        }
    }
}
