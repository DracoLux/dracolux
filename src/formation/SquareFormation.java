package formation;

import dumb.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class SquareFormation implements Formation {

    @Override
    public List<Coordinate> createFormation(int numberOfDrones) {
        /*TODO: Make dynamic depending on numberOfDrones*/
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0,0,0));
        coordinates.add(new Coordinate(5,0,0));
        coordinates.add(new Coordinate(0,0,5));
        coordinates.add(new Coordinate(5,0,5));

        return coordinates;
    }

}
