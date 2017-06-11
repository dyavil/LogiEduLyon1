/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.Model;

/**
 *
 * @author dyavil
 */
public class ThreeStepProgress extends Progress {
    public ThreeStepProgress(){
        super();
        this.getCorrespondingNames().add("Devérouillé");
        this.getCorrespondingNames().add("En cours");
        this.getCorrespondingNames().add("Terminé");
        this.getCorrespondingCSS().add("bg-blocked");
        this.getCorrespondingCSS().add("bg-ongoing");
        this.getCorrespondingCSS().add("bg-done");
    }
    public ThreeStepProgress(int st, String comment){
        super();
        this.getCorrespondingNames().add("Devérouillé");
        this.getCorrespondingNames().add("En cours");
        this.getCorrespondingNames().add("Terminé");
        this.getCorrespondingCSS().add("bg-blocked");
        this.getCorrespondingCSS().add("bg-ongoing");
        this.getCorrespondingCSS().add("bg-done");
        this.setState(st);
        this.setComment(comment);
    }
}
