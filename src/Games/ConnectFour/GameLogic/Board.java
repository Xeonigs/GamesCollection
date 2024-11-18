package src.Games.ConnectFour.GameLogic;

import src.GameInterfaces.GameLogic.Player;

public record Board(Player[][] value, int[] heights) implements Cloneable {
    public Board {
        if (value.length != heights.length) {
            throw new IllegalArgumentException("Board value and heights must have the same length");
        }
    }
     @Override
    public String toString() {
         StringBuilder sb = new StringBuilder();
         for (int row = 0; row < value[0].length; row++) {
             for (int column = 0; column < value.length; column++) {
                 sb.append(value[column][row] == null ? '.' : value[column][row].getSymbol());
             }
             sb.append('\n');
         }
         return sb.toString();
     }

     public String horizontallyMirroredToString() {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < value[0].length; row++) {
                for (int column = value.length - 1; column >= 0; column--) {
                    sb.append(value[column][row] == null ? '.' : value[column][row].getSymbol());
                }
                sb.append('\n');
            }
            return sb.toString();
    }

    @Override
    public Board clone() {
        Player[][] newValue = new Player[value.length][value[0].length];
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[0].length; j++) {
                newValue[i][j] = value[i][j];
            }
        }
        int[] newHeights = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            newHeights[i] = heights[i];
        }
        return new Board(newValue, newHeights);
    }
}
