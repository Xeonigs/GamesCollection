package src.UserInterfaces.GUI;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface GUISwitcher extends Runnable, KeyListener, MouseListener {
    void switchGUI(GUIObject gui);
}
