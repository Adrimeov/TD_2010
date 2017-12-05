package CircularDeps;

import java.util.HashSet;

public class CodeBase {
    private SourceFile[] sourceFiles;

    public CodeBase(SourceFile[] sourceFiles)
    {
        this.sourceFiles = sourceFiles;
    }

    public boolean hasCircularDependencies()
    {
        for (SourceFile file : this.sourceFiles) {
            HashSet<SourceFile> dependentFiles = new HashSet<>();
            if (existsCircularDependencies(file, dependentFiles)) {
                return true;
            }
        }
        return false;
    }

    // DFS partant de <<file>> détectant s'il existe
    // des dépendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles)
    {

        //On ajoute le fichier a l'etude dans le set de verification
        dependentFiles.add(file);
        //On itere sur les enfants
        for(SourceFile files: file.getDependencies() ){
            //Si l'enfant n'est pas deja dans le set, on passe le meme processus a celui-ci
            if(!dependentFiles.contains(files)) {
                existsCircularDependencies(files, dependentFiles);
            }
            //Si l'enfant est dans la liste on doit remonter la pile d'execution
            if(dependentFiles.contains(files)){
                return true;
            }

        }
        // Si aucune boucle, on retire l'un apres l'autre. C'est dans le cas
        // que nous sommes descendu toute en profondeur.
        dependentFiles.remove(file);

        // À compléter

        return false;
    }
}
