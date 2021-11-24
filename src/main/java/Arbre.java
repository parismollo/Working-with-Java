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

    public void traverser(String extension) {
        this.racine.traverser(extension);
    }

    public void afficher() {
        System.out.println(this.racine.toString());
        this.racine.afficherRec(" ");
    }

    public void map() {
        this.racine.map(this.racine.transformer);
    }

    public class Noeud {
        private String nom;
        private int taille;
        private boolean repertoire;
        private ArrayList<Noeud> fils;

        private StringTransformation transformer = (s) -> setNom(s+".blah");


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

        public void traverser(String extension) {
            int l = getNom().split("[.]").length;
            String fileExtension = getNom().split("[.]")[l-1];
            if(!getRepertoire()) {
                if (fileExtension.equals(extension)) {
                    System.out.println(this.toString());
                    return;
                }
            } else {
                for (Noeud noeud : fils) {
                    noeud.traverser(extension);
                }
            }
        }

        public String setNom(String s) { // Fix this
            this.nom = s;
            return s;
        }

        public String getNom() {
            return this.nom;
        }

        public void map(StringTransformation t) {
            if (this.repertoire) {
                for (Noeud noeud : fils) {
                    noeud.map(transformer);
                }
            } else {
                this.transformer.transf(this.nom);
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

        public boolean getRepertoire() {
            return this.repertoire;
        }

        public String toString() {
            return this.nom + " ["+this.taille+"]";
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