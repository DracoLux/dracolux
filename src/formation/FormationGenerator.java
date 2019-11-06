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

        /* TODO: calculate coordinates based on the shape decided upon. */

        // Auxiliary variables, defined before the loop for better memory management.
        float x, y, z, theta, h, k, r, j;
        int m, n;
        n = numOfDrones;
        j = 1;
        Coordinate coord;
        for (int i = 0; i < n; i++) {
            switch (shape) {
                case SQUARE:
                    y = 300;
                    if (i == 0) {
                        x = 0;
                        z = 0;
                    } else if (i == 1) {
                        x = 0;
                        z = 400;
                    } else if (i == 2) {
                        x = 400;
                        z = 0;
                    } else if (i == 3) {
                        x = 400;
                        z = 400;
                    } else {
                        m = i % 4;
                        h = n - 4;
                        switch (m) {
                            case 0:
                                x = 0;
                                z = 0 + (400/((h/4)+ 1) * j);
                                break;
                            case 1:
                                x = 400;
                                z = 0 + (400/((h/4)+ 1) * j);
                                break;
                            case 2:
                                x = 0 + (400/((h/4)+ 1) * j);
                                z = 0;
                                break;
                            case 3:
                                x = 0 + (400/((h/4)+ 1) * j);
                                z = 400;
                                // Increase iterator.
                                j++;
                                break;
                            default:
                                x = 0;
                                z = 0;
                        }
                    }
                    coord = new Coordinate(x, y, z);
                    outputFormation.coordinates.add(coord);
                    break;
                case CIRCLE:
                    h = 100;
                    k = 200;
                    r = 300;
                    y = 500;
                    theta = (float) (random() * 2 * Math.PI);
                    x = (float) (h + cos(theta) * r);
                    z = (float) (k + sin(theta) * r);
                    coord = new Coordinate(x, y, z);
                    outputFormation.coordinates.add(coord);
                    break;
            }
        }
        return outputFormation;
    }
}
