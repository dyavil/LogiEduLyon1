/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

/**
 *
 * @author dyavil
 */
public class FourStepProgress extends Progress {
    
    public FourStepProgress(){
        super();
        this.getCorrespondingNames().add("Bloqué");
        this.getCorrespondingNames().add("Devérouiller");
        this.getCorrespondingNames().add("En cours");
        this.getCorrespondingNames().add("Terminé");
        this.getCorrespondingCSS().add("bg-blocked");
        this.getCorrespondingCSS().add("bg-unlocked");
        this.getCorrespondingCSS().add("bg-ongoing");
        this.getCorrespondingCSS().add("bg-done");
    }
    public FourStepProgress(int st, String comment){
        super();
        this.getCorrespondingNames().add("Bloqué");
        this.getCorrespondingNames().add("Devérouiller");
        this.getCorrespondingNames().add("En cours");
        this.getCorrespondingNames().add("Terminé");
        this.getCorrespondingCSS().add("bg-blocked");
        this.getCorrespondingCSS().add("bg-unlocked");
        this.getCorrespondingCSS().add("bg-ongoing");
        this.getCorrespondingCSS().add("bg-done");
        this.setState(st);
        this.setComment(comment);
    }
}
