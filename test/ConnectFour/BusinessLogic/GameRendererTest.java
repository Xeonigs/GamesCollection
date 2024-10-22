package test.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Board;
import src.ConnectFour.BusinessLogic.GameRenderer;
import src.ConnectFour.BusinessLogic.Player;

import static org.junit.jupiter.api.Assertions.*;

class GameRendererTest {
    @org.junit.jupiter.api.Test
    void testRender() {
        // Arrange
        Player[][] nativeBoard = new Player[7][6];
        int[] heights = new int[7];
        Board board = new Board(nativeBoard, heights);
        GameRenderer gameRenderer = new GameRenderer(board);
        gameRenderer.render();
    }

}