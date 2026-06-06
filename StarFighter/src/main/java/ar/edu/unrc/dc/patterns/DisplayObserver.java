package ar.edu.unrc.dc.patterns;

import ar.edu.unrc.dc.models.*;

public interface DisplayObserver {
    public void update(String command, Game game);
    public void update(Board board);
}
