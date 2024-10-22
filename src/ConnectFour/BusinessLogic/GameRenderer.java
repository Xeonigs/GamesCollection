package src.ConnectFour.BusinessLogic;

public class GameRenderer implements Renderer {
    private final Board board;

    public GameRenderer(final Board board) {
        this.board = board;
    }

    @Override
    public void render() {
        final var value = board.value();
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                final var player = value[i][j];
                if (player == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(player.getSymbol());
                }
            }
            System.out.println();
        }
    }
}
