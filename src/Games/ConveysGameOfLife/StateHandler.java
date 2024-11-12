package src.Games.ConveysGameOfLife;

import java.util.Collection;

public interface StateHandler {
    void changeQueuedChanges();
    void queueChange(Coordinates cell);
    void queueChanges(Collection<Coordinates> cells);
}
