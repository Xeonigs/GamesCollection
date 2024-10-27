package src.ConnectFour.UI;

import src.ConnectFour.BusinessLogic.Players.Player;

import java.util.Optional;

public interface InputHandler {
    Optional<Integer> getMove(Player player);
}
