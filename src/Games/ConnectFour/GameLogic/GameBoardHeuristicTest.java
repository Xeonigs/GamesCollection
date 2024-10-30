package src.Games.ConnectFour.GameLogic;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardHeuristicTest {
    @org.junit.jupiter.api.Test
    void test1() {
        int x = 1;
        int expected = 1;
        int actual = valuationCalculation(x);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void test2() {
        int x = 2;
        int expected = 3;
        int actual = valuationCalculation(x);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void test3() {
        int x = 3;
        int expected = 8;
        int actual = valuationCalculation(x);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void test4() {
        int x = 4;
        int expected = 16;
        int actual = valuationCalculation(x);
        assertEquals(expected, actual);
    }

    private int valuationCalculation(int x) {
        return (int) (1.5 * pow(x, 2) - 2.5 * x + 2);
    }
}