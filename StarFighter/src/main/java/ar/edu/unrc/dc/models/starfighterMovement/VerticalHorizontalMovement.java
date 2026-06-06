package ar.edu.unrc.dc.models.starfighterMovement;

import java.util.*;
import ar.edu.unrc.dc.models.cell.*;

public class VerticalHorizontalMovement implements StarfighterMovement {

    @Override
    public List<Position> moveSequence(Position start, int verticalMove, int horizontalMove) {
        List<Position> path = new ArrayList<>();
        int step;
        int row = start.getRow();

        if(verticalMove > 0)
            step = -1; // Move down
        else
            step = 1; // Move up

        for(int i = 0; i < Math.abs(verticalMove); i++){
            row += step;
            path.add(new Position(row, start.getCol()));
        }

        int col = start.getCol();
        
        if(horizontalMove > 0)
            step = 1; // Move right
        else
            step = -1; // Move left

        for(int i = 0; i < Math.abs(horizontalMove); i++){
            col += step;
            path.add(new Position(row, col));
        }

        return path;
    }


}
