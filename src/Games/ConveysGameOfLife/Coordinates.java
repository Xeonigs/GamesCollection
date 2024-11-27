package src.Games.ConveysGameOfLife;

import java.util.ArrayList;
import java.util.Collection;

public class Coordinates implements Comparable<Coordinates> {
    public final int x, y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }

    public Collection<Coordinates> getNeighbours() {
        Collection<Coordinates> neighbors = new ArrayList<>(8);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                neighbors.add(new Coordinates(x + i, y + j));
            }
        }
        return neighbors;
    }

    @Override
    public int compareTo(Coordinates o) {
        if (x == o.x) {
            return Integer.compare(y, o.y);
        }
        return Integer.compare(x, o.x);
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Coordinates) obj) == 0;
    }

    @Override
    public int hashCode() {
        final int prime1 = 0x9e3779b9; // Large prime (Golden ratio)
        final int prime2 = 0x85ebca6b; // Another large prime

        int h = x;
        h ^= y + prime1 + (h << 6) + (h >> 2); // Mix x and y
        h *= prime2; // Spread values with prime multiplication

        return h;
    }
}
