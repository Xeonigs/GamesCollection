package src.Games.ConveysGameOfLife;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface StateHandler {
    void changeQueuedChanges() throws InterruptedException, ExecutionException;
    void queueChange(Coordinates cell);
    void queueChanges(Collection<Coordinates> cells);
}
