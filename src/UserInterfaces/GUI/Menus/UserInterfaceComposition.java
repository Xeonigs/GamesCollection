package src.UserInterfaces.GUI.Menus;

import src.Games.ConnectFour.ConnectFour;
import src.Games.ConnectFour.GameLogic.Players.HumanPlayer;
import src.Games.ConnectFour.GameLogic.Players.MiniMaxPlayer;
import src.UserInterfaces.GUI.ApplicationPanel;
import src.UserInterfaces.GUI.Button;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.util.LinkedList;

public class UserInterfaceComposition implements Runnable {
    @Override
    public void run() {
        var applicationPanel = new ApplicationPanel();

        var mainMenuGuiObjects = new LinkedList<GUIObject>();
        var mainMenu = new Menu(mainMenuGuiObjects);

        var gamesMenuGuiObjects = new LinkedList<GUIObject>();
        var gamesMenu = new Menu(gamesMenuGuiObjects);

        var settingsMenuGuiObjects = new LinkedList<GUIObject>();
        var settingsMenu = new Menu(settingsMenuGuiObjects);

        var connectFourMenuGuiObjects = new LinkedList<GUIObject>();
        var connectFourMenu = new Menu(connectFourMenuGuiObjects);

        final var buttonDimension = new Dimension(200, 50);

        mainMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 100), buttonDimension, 1, "Start", () -> {
            applicationPanel.switchGUI(gamesMenu);
        }));
        mainMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 200), buttonDimension, 1, "Options", () -> {
            applicationPanel.switchGUI(settingsMenu);
        }));
        mainMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 300), buttonDimension, 0.5f, "Exit", () -> {
            System.exit(0);
        }));

        gamesMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 100), buttonDimension, 1, "Connect Four", () -> {
            applicationPanel.switchGUI(connectFourMenu);
        }));
        gamesMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 200), buttonDimension, 1, "Game 2", () -> {
            System.out.println("Game 2 clicked");
        }));
        gamesMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 300), buttonDimension, 1, "Game 2", () -> {
            System.out.println("Game 3 clicked");
        }));
        gamesMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 400), buttonDimension, 1, "Back", () -> {
            applicationPanel.switchGUI(mainMenu);
        }));

        settingsMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 100), buttonDimension, 1, "Volume", () -> {
            System.out.println("Volume clicked");
        }));
        settingsMenuGuiObjects.add(new Button(new Point(300, 200), buttonDimension, 1, "Back", () -> {
            applicationPanel.switchGUI(mainMenu);
        }));

        connectFourMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 100), buttonDimension, 1, "Player vs Player", () -> {
            applicationPanel.switchGUI(new ConnectFour().loadGame(HumanPlayer.class, HumanPlayer.class));
        }));
        connectFourMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 200), buttonDimension, 1, "Player vs COM", () -> {
            applicationPanel.switchGUI(new ConnectFour().loadGame(HumanPlayer.class, MiniMaxPlayer.class));
        }));
        connectFourMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 300), buttonDimension, 1, "COM vs Player", () -> {
            applicationPanel.switchGUI(new ConnectFour().loadGame(MiniMaxPlayer.class, HumanPlayer.class));
        }));
        connectFourMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 400), buttonDimension, 1, "COM vs COM", () -> {
            applicationPanel.switchGUI(new ConnectFour().loadGame(MiniMaxPlayer.class, MiniMaxPlayer.class));
        }));
        connectFourMenuGuiObjects.add(new src.UserInterfaces.GUI.Button(new Point(300, 500), buttonDimension, 1, "Back", () -> {
            applicationPanel.switchGUI(gamesMenu);
        }));



        applicationPanel.switchGUI(mainMenu);
        applicationPanel.run();
    }
}
