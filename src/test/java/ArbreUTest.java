package src.test.java;

import java.io.FileNotFoundException;
import src.main.java.Arbre;
import src.main.java.StringTransformation;
import src.main.java.UnableToDeleteFileException;

public class ArbreUTest {
    public static void main(String[] args) throws UnableToDeleteFileException {
        String path = "C:/Users/paris/UNIVERSITE_DE_PARIS/POO/TD1/Scrabble";
        exo5Supprimmer(path);
    }

    public static void exo1(String path) {
        try {
            Arbre root = new Arbre(path);
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

    public static void exo4Noeuds(String path){

        try {
            Arbre root = new Arbre(path);
            root.afficher();
            root.map();
            root.afficher();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }   
    }

    public static void exo5Traverser(String path) {
        String ext = "java";
        try {
            Arbre root = new Arbre("");
            root.traverser(ext);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void exo5Supprimmer(String path) throws UnableToDeleteFileException {
        try {
            Arbre root = new Arbre(path);
            root.afficher();
            root.supprimer("txt");

            System.out.println("\n-----------------");
            root.afficher();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
