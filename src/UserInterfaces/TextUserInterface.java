package src.UserInterfaces;

import java.io.IOException;

public interface TextUserInterface {
    void display(String message);
    String read(String message) throws IOException;
}
