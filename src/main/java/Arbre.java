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

    public void supprimer(String extension) throws UnableToDeleteFileException {
        this.racine.supprimer(extension);
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

        public void supprimer(String extension) throws UnableToDeleteFileException {
            int l = getNom().split("[.]").length;
            String fileExtension = getNom().split("[.]")[l-1];
            if (!getRepertoire()) {
                File file = new File(this.nom);
                deleteFile(file, extension, fileExtension);
            }
            else {
                for (Noeud noeud : fils) {
                    noeud.supprimer(extension);
                }
            }
        }

        public void deleteFile(File file, String extension, String fileExtension) throws UnableToDeleteFileException {
            if (file.getParentFile() != null) {
                if (!file.getParentFile().canWrite()) {
                    throw new UnableToDeleteFileException("File"+file.getName()+" can't be deleted");
                } else {
                    if (fileExtension.equals(extension)) {
                        fakeDelete(this);
                    }
                }
            } else {
                if (fileExtension.equals(extension)) {
                    fakeDelete(this);
                }
            }
        }

        public void fakeDelete(Noeud n) {
            n.fils = null;
            n.nom = "[DELETED FILE]";
            n.repertoire = false;
            n.taille = 0;
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
            if (this.fils == null || this.fils.isEmpty()) {
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