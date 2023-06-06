package GUI.Interfaces;

import javax.swing.*;
import java.awt.*;

public interface Frame {
    ImageIcon icon = new ImageIcon("/Users/irinaabakumova/Downloads/image.jpeg");

    Font HEADER_FONT = new Font("Dialog", Font.PLAIN, 23);

    Font LOWER_LINE_FONT = new Font("Dialog", Font.PLAIN, 15);

    String TITLE = "Лабораторная работа №2";

    void Frame();

    void createFilling();

    JPanel createHeader(String headerText);
}
