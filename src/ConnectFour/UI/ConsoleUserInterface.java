package src.ConnectFour.UI;

public class ConsoleUserInterface implements UserInterface {
    public void display(String message) {
        System.out.println(message);
    }

    public String read() {
        return System.console().readLine();
    }
}
