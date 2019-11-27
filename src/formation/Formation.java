package formation;


import enumerators.LightPattern;
import network.common.Coordinate;

import java.awt.*;
import java.util.List;

public class Formation {
    public List<Coordinate> coordinates;
    public LightPattern lightPattern;
    public Color color;
    public int durationSeconds;

    public Formation(List<Coordinate> coordinates, LightPattern lightPattern, Color color, int durationSeconds) {
        this.coordinates = coordinates;
        this.lightPattern = lightPattern;
        this.color = color;
        this.durationSeconds = durationSeconds;
    }

    public String toString() {
        String output = "Formation";
        output += "\nCoordinates: " + coordinates;
        output += "\nCoordinates size: " + coordinates.size();
        output += "\nLightpattern: " + lightPattern;
        output += "\nColor: " + color;
        output += "\nDuration: " + durationSeconds;
        return output;
    }
}
