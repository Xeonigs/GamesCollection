package src.ConnectFour;

import src.ConnectFour.BusinessLogic.GameLoop;
import src.ConnectFour.BusinessLogic.Loop;

public class ConnectFour {
    public static final int COLUMN = 7;
    public static final int ROW = 6;
    public void start() {
        Loop gameLoop = new GameLoop(null, null, null, null);
        gameLoop.startLoop();
    }
}
