package formation;

import enumerators.LightPattern;
import enumerators.Shape;
import network.common.Coordinate;

import java.awt.Color;
import java.util.ArrayList;

import static java.lang.Math.*;

public class FormationGenerator {

    public static Formation generateFormation(int numOfDrones, Shape shape, Color color, LightPattern lightPattern, int durationSeconds) {
        Formation outputFormation = new Formation(new ArrayList<>(), lightPattern, color, durationSeconds);

        switch (shape) {
            case NUMBER_ONE:
                buildNumberOne(outputFormation, numOfDrones);
                break;
            case LINE:
                buildLine(outputFormation, numOfDrones);
                break;
            case SQUARE:
                buildSquare(outputFormation, numOfDrones);
                break;
            case CIRCLE:
                buildCircle(outputFormation, numOfDrones);
                break;
        }
        return outputFormation;
    }

    private static void buildNumberOne(Formation outputFormation, int numOfDrones) {
        float j;
        int specialPoints = 0; // Certain spots that must be occupied.
        int bottomLine = (numOfDrones - specialPoints)/3;
        int upLine = ((numOfDrones - bottomLine)/4) * 3;
        int crook = numOfDrones - specialPoints - upLine - bottomLine;

        j = 1;
        for (int i = 0; i < bottomLine; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(-400, 0, 0), new Coordinate(400, 0, 0), bottomLine, j));
            j++;
        }

        j = 1;
        for (int i = 0; i < upLine; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(0, 0, 0), new Coordinate(0, 1200, 0), upLine, j));
            j++;
        }

        j = 1;
        for (int i = 0; i < crook; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(500, 800, 0), new Coordinate(0, 1200, 0), crook, j));
            j++;
        }

    }

    /*
    private static void buildNumberTwo(Formation outputFormation, int numOfDrones) {
        float j;
        int upLine = (numOfDrones - specialPoints);
        int midLine = ((numOfDrones - bottomLine)/4) * 3;
        int botLine = numOfDrones - specialPoints - upLine - bottomLine;

        j = 1;
        for (int i = 0; i < bottomLine; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(-400, 0, 0), new Coordinate(400, 0, 0), bottomLine, j));
            j++;
        }

        j = 1;
        for (int i = 0; i < upLine; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(0, 0, 0), new Coordinate(0, 1200, 0), upLine, j));
            j++;
        }

        j = 1;
        for (int i = 0; i < crook; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(500, 800, 0), new Coordinate(0, 1200, 0), crook, j));
            j++;
        }

    }
     */

    private static void buildLine(Formation outputFormation, int n) {
        float j = 1;
        for (int i = 0; i < n; i++) {
            outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(0, 0, -500), new Coordinate(500, 800, 1200), n, j));
            j++;
        }
    }

    private static void buildCircle(Formation outputFormation, int n) {
        float h, k, r, x, y, z;
        float theta;
        for (int i = 0; i < n; i++) {
            h = 100;
            k = 200;
            r = 1000;
            y = 500;
            theta = (float) (random() * 2 * Math.PI);
            x = (float) (h + cos(theta) * r);
            z = (float) (k + sin(theta) * r);
            outputFormation.coordinates.add(new Coordinate(x, y, z));
        }
    }

    private static void buildSquare(Formation outputFormation, int n) {
        float x, y, z, j, h;
        j = 1;
        for (int i = 0; i < n; i++) {
            y = 300;
            if (i == 0) {
                x = 0;
                z = 0;
                outputFormation.coordinates.add(new Coordinate(x, y, z));
            } else if (i == 1) {
                x = 0;
                z = 800;
                outputFormation.coordinates.add(new Coordinate(x, y, z));
            } else if (i == 2) {
                x = 800;
                z = 0;
                outputFormation.coordinates.add(new Coordinate(x, y, z));
            } else if (i == 3) {
                x = 800;
                z = 800;
                outputFormation.coordinates.add(new Coordinate(x, y, z));
            } else {
                h = n - 4;
                switch (i % 4) {
                    case 0:
                        outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(0, 300, 0), new Coordinate(0, 300, 800), h/4, j));
                        break;
                    case 1:
                        outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(800, 300, 0), new Coordinate(800, 300, 800), h/4, j));
                        break;
                    case 2:
                        outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(0, 300, 0), new Coordinate(800, 300, 0), h/4, j));
                        break;
                    case 3:
                        outputFormation.coordinates.add(sitBetweenCoordinates(new Coordinate(0, 300, 800), new Coordinate(800, 300, 800), h/4, j));
                        // Increase iterator.
                        j++;
                        break;
                }
            }
        }
    }

    // Returns float for 'iteration' to sit on a line between floats from and to where numDrones number of drones are gonna be.
    public static float sitOnLine(float from, float to, float numDrones, float iteration) {
        float x = (to - from)/(numDrones + 1);
        return from + x * (iteration);
    }

    public static Coordinate sitBetweenCoordinates(Coordinate from, Coordinate to, float numDrones, float iteration) {
        float x, y, z;

        if (from.x == to.x)
            x = from.x;
        else
            x = sitOnLine(from.x, to.x, numDrones, iteration);

        if (from.y == to.y)
            y = from.y;
        else
            y = sitOnLine(from.y, to.y, numDrones, iteration);

        if (from.z == to.z)
            z = from.z;
        else
            z = sitOnLine(from.z, to.z, numDrones, iteration);

        return new Coordinate(x, y, z);
    }
}
