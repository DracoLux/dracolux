package timeline;

import dumb.LightPattern;
import formation.Formation;

import java.awt.*;

public class Event {
    public Formation formation;
    public int lightVolume;
    public Color color;
    public int waitTimeBefore;
    public LightPattern lightPattern;

    public Event(Formation formation, int lightVolume, Color color, int waitTimeBefore, LightPattern lightPattern) {
        this.formation = formation;
        this.color = color;
        this.waitTimeBefore = waitTimeBefore;
        this.lightPattern = lightPattern;
    }

}
