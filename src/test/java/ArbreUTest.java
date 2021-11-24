package src.test.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import src.main.java.Arbre;
import src.main.java.StringTransformation;

public class ArbreUTest {
    public static void main(String[] args) {
        // exo1();
        // exo4();
        exo4Noeuds();
    }

    public static void exo1() {
        try {
            Arbre root = new Arbre("C:/Users/paris/UNIVERSITE_DE_PARIS/POO/TD1/Scrabble");
            root.afficher();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void exo4() {
        StringTransformation addblah = (s) -> s + ".blah";
        String res = addblah.transf("Hello");
        System.out.println(res);
    }

    public static void exo4Noeuds(){

        try {
            Arbre root = new Arbre("C:/Users/paris/UNIVERSITE_DE_PARIS/POO/TD1/Scrabble");
            root.afficher();
            root.map();
            root.afficher();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
