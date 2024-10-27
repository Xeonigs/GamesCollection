package src.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Players.Player;

public record Board(Player[][] value, int[] heights) implements Cloneable {
    public Board {
        if (value.length != heights.length) {
            throw new IllegalArgumentException("Board value and heights must have the same length");
        }
    }

    public Board clone() {
        return new Board(value.clone(), heights.clone());
    }
}
