package src.UserInterfaces;

import java.io.IOException;

public interface UserInterface {
    void display(String message);
    String read(String message) throws IOException;
}
