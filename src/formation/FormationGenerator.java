package formation;

import enumerators.LightPattern;
import enumerators.Shape;
import java.awt.Color;

public class FormationGenerator {

    public static Formation generateFormation(int numOfDrones, Shape shape, Color color, LightPattern lightPattern, int durationSeconds) {
        Formation outputFormation = new Formation(null, lightPattern, color, durationSeconds);
        /* TODO: calculate coordinates based
        on the shape decided upon. */
        return outputFormation;
    }
}
