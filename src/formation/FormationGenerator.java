package formation;

import enumerators.LightPattern;
import enumerators.Shape;
import java.awt.Color;
import java.util.ArrayList;

public class FormationGenerator {

    public static Formation generateFormation(int numOfDrones, Shape shape, Color color, LightPattern lightPattern, int durationSeconds) {
        Formation outputFormation = new Formation(new ArrayList<>(), lightPattern, color, durationSeconds);

        /* TODO: calculate coordinates based on the shape decided upon. */
        for (int i = 0; i < numOfDrones; i++) {
            switch (shape) {
                case SQUARE:
                    // For now just a simple line
                    outputFormation.coordinates.add(new Coordinate((i % 2) * 100, i * 50, i * 10));
                    break;
                case CIRCLE:
                    // For now just a simple line
                    outputFormation.coordinates.add(new Coordinate((i % 2) * 100, i * (int) Math.PI, i * 10));
                    break;
            }
        }
        return outputFormation;
    }
}
