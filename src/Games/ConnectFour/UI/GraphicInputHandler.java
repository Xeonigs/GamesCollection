package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.Player;
import src.GameInterfaces.UI.InputHandler;

import java.util.Optional;

public class GraphicInputHandler implements InputHandler {
    @Override
    public Optional<Integer> getMove(Player player) {
        return Optional.empty();
    }
}
