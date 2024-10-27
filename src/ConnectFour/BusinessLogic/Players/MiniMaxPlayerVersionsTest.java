package src.ConnectFour.BusinessLogic.Players;

import src.ConnectFour.BusinessLogic.Board;
import src.ConnectFour.ConnectFour;

class MiniMaxPlayerVersionsTest {
    private Board board = new Board(new Player[ConnectFour.COLUMN][ConnectFour.ROW], new int[ConnectFour.COLUMN]);


    @org.junit.jupiter.api.Test
    void benchMarkTestV1() {
        final var depth = 8;
        final var testRounds = 1;
        Player testPlayer = new MiniMaxPlayerV1('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        6 depth, 10 rounds: 287ms
        6 depth, 10 rounds: 293ms
        8 depth, 1 rounds: 6794ms
         */
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV2() {
        final var depth = 6;
        final var testRounds = 10;
        Player testPlayer = new MiniMaxPlayerV2('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        6 depth, 10 rounds: 300ms
        6 depth, 10 rounds: 289ms
        6 depth, 10 rounds: 311ms
         */
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV3() {
        final var depth = 8;
        final var testRounds = 100;
        Player testPlayer = new MiniMaxPlayerV3('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV4() {
        final var depth = 6;
        final var testRounds = 100;
        Player testPlayer = new MiniMaxPlayerV4('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        8 depth, 100 rounds: 628ms
        8 depth, 100 rounds: 599ms
        8 depth, 100 rounds: 615ms
         */
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV5() {
        final var depth = 8;
        final var testRounds = 50;
        Player testPlayer = new MiniMaxPlayerV5('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        8 depth, 100 rounds: 191ms
        8 depth, 100 rounds: 204ms
        8 depth, 100 rounds: 198ms
         */
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV6() {
        final var depth = 8;
        final var testRounds = 100;
        Player testPlayer = new MiniMaxPlayerV6('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        8 depth, 100 rounds: 111ms
        8 depth, 100 rounds: 125ms
        8 depth, 100 rounds: 114ms
         */
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV7() {
        final var depth = 8;
        final var testRounds = 100;
        Player testPlayer = new MiniMaxPlayerV7('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        8 depth, 100 rounds: 90ms
        8 depth, 100 rounds: 95ms
        8 depth, 100 rounds: 94ms

        10 depth, 1 round: 1038ms
        11 depth, 1 round: 3270ms
        12 depth, 1 round: 7564ms
        13 depth, 1 round: 28551ms
        14 depth, 1 round: 76470ms
         */
    }

    @org.junit.jupiter.api.Test
    void benchMarkTestV8() {
        final var depth = 8;
        final var testRounds = 100;
        Player testPlayer = new MiniMaxPlayerV8('X', board, depth);
        testMiniMaxPlayer(testPlayer, testRounds);
        /*
        8 depth, 100 rounds: 87ms
        8 depth, 100 rounds: 91ms
        8 depth, 100 rounds: 87ms
         */
    }

    private void testMiniMaxPlayer(Player testPlayer, int testRounds) {
        // Arrange
        final var column = 7;
        final var row = 6;
        Player dummyPlayer = new HumanPlayer('O', null, null);
        testPlayer.setOpponent(dummyPlayer);
        dummyPlayer.setOpponent(testPlayer);

        // Act
        long[] durations = new long[testRounds];
        long totalDurations = 0;

        testPlayer.getMove(); // Warm up
        for (int i = 0; i < testRounds; i++) {
            long startTime = System.nanoTime();
            testPlayer.getMove();
            long endTime = System.nanoTime();
            durations[i] = (endTime - startTime);
            totalDurations += durations[i];
        }
        long averageDuration = totalDurations / testRounds;

        // Assert
        System.out.println("Average duration: " + averageDuration / 1000000 + "ms");
    }
}