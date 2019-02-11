package com.loyanix.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {


    public void showMenu() throws IOException {

        boolean isRuning = true;

        while (isRuning) {

            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            switch (bufferedReader.readLine()) {

                case "1":
                    System.out.println("Show Admin menu");
                    break;
                case "2":
                    System.out.println("Show Client menu");
                    break;
                case "0":
                    isRuning = false;
                    break;
                default:
                    System.out.println("Wrong symbol");

            }
        }
    }
}
