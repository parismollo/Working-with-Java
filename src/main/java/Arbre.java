package src.main.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Arbre {
    public Noeud racine;

    public Arbre(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new FileNotFoundException("File "+file+" not found");
        } else {
            this.racine = new Noeud(file);
        }
    }

    public void afficher() {
        System.out.println(this.racine.toString());
        this.racine.afficherRec(" ");
    }

    public class Noeud {
        private String nom;
        private int taille;
        private boolean repertoire;
        private ArrayList<Noeud> fils;

        Noeud(File file) throws FileNotFoundException {
            if(!file.exists()) {
                throw new FileNotFoundException("File "+file+" not found!");
            } else {
                this.nom = file.getName();
                this.taille = (int)file.length();
                this.repertoire = file.isDirectory();
                setFils(file);
            }
        }

        public void afficherRec(String space) {
            if (this.fils.isEmpty()) {
                return;
            } else {
                for (Noeud noeud : fils) {
                    System.out.print(space);
                    System.out.println(noeud); 
                    noeud.afficherRec(space+" ");
                    
                }
            }
        }

        public String toString() {
            return this.nom + " ["+this.fils.size()+"]";
        }

        private void setFils(File file) {
            this.fils = new ArrayList<Noeud>();
            boolean isDir = file.isDirectory();
            if (!isDir) {
                return;
            } else {
                File[] files = file.listFiles();
                for (File f : files) {
                    try {
                        if (f != null) {
                            this.fils.add(new Noeud(f));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace(); // In case, this file don't work, we want to skip to the next file.
                    }   
                }
            }
        }
    }
}