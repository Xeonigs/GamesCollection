package src.UserInterfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInterface implements TextUserInterface {
    @Override
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public String read(String message) throws IOException {
        System.out.printf(message + " ");
        if (System.console() != null) {
            return System.console().readLine();
        }
        InputStreamReader iStream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(iStream);
        return reader.readLine();
    }
}
