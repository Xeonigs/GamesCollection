import src.Games.ConnectFour.ConnectFour;
import src.Games.ConveysGameOfLife.ConveysGameOfLife;
import src.UserInterfaces.GUI.ApplicationPanel;
import src.UserInterfaces.GUI.Button;
import src.UserInterfaces.GUI.GUIObject;
import src.UserInterfaces.GUI.Menu;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        var guiObjects = new LinkedList<GUIObject>();
        var menu = new Menu(guiObjects);
        var applicationPanel = new ApplicationPanel(menu);

        guiObjects.add(new Button(300, 100, 200, 50, 1, "Start", () -> {
            applicationPanel.switchGUI(menu);
        }));
        guiObjects.add(new Button(300, 200, 200, 50, 1, "Options", () -> {
            System.out.println("Options clicked");
        }));
        guiObjects.add(new Button(300, 300, 200, 50, 1, "Exit", () -> {
            System.exit(0);
        }));

        applicationPanel.run();

    }
}
