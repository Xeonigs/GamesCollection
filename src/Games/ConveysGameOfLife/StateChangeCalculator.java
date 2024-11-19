package src.Games.ConveysGameOfLife;

import java.util.Collection;

public interface StateChangeCalculator {
    Collection<Coordinates> getChanges() throws InterruptedException;
}
