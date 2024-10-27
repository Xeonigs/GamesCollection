package src.ConnectFour.UI;

import src.ConnectFour.BusinessLogic.Player;

import java.util.Optional;

public class ConsoleInputHandler implements InputHandler {
    UserInterface userInterface;

    public ConsoleInputHandler(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public Optional<Integer> getMove(Player player) {
        final var playerSymbol = player.getSymbol();
        final var message = "Player " + playerSymbol + " enter a column number:";
        try {
            final var input = userInterface.read(message);
            return Optional.of(Integer.parseInt(input) - 1);
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return Optional.empty();
        }
    }
}