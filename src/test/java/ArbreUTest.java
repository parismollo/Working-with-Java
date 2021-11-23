package src.test.java;

import java.io.FileNotFoundException;

import src.main.java.Arbre;

public class ArbreUTest {
    public static void main(String[] args) {
        exo1();
    }

    public static void exo1() {
        try {
            Arbre root = new Arbre("C:/Users/paris/UNIVERSITE_DE_PARIS/POO/TD1/Scrabble");
            root.afficher();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
