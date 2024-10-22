package src.ConnectFour.BusinessLogic;

public record Board(Player[][] value, int[] heights) {
    public Board {
        if (value.length != heights.length) {
            throw new IllegalArgumentException("Board value and heights must have the same length");
        }
    }
}
