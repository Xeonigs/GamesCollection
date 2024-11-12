package src.Games.ConveysGameOfLife;

public class Coordinates implements Comparable<Coordinates> {
    public final int x, y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
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
        return x * 31 + y;
    }
}
