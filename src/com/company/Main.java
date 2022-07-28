package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Main {

    //TODO
    //  Resizeable
    //  pauseOption
    //  goodOpponent
    public static Image image;
    public static Image image2;

    public static void main(String[] args) {
        importImg();

        Window window = new Window();
    }

    private static void importImg() {
        URL url = Window.class.getClassLoader().getResource("Plasmaschweißer_AdrianZeitlberger.jpg");
        URL url2 = Window.class.getClassLoader().getResource("25_Zeitlberger_Schmetterling.jpg");

        try {
            image = ImageIO.read(url);
            image2 = ImageIO.read(url2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}