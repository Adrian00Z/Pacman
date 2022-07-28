package com.company;

import javax.swing.*;

public class Window extends JFrame {
    public static int width = 640;
    public static int height = 640;

    Game mainPanel;
    public Window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);

        mainPanel = new Game();
        add(mainPanel);
        addKeyListener(new KeyListener());
    }


}
