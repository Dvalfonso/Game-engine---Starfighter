package ar.edu.unrc.dc.models.starfighterMovement;

import java.util.*;
import ar.edu.unrc.dc.models.cell.*;

public interface StarfighterMovement {
    public List<Position> moveSequence(Position start, int verticalMove, int horizontalMove);
}
