package src.GameInterfaces.UI;

import src.GameInterfaces.GameLogic.Player;

import java.util.Optional;

public interface InputHandler {
    Optional<Integer> getMove(Player player);
}
