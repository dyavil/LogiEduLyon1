/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Model;

/**
 *
 * @author dyavil
 */
public class ThreeStepProgress extends Progress {
    
    public ThreeStepProgress(){
        super();
        this.getCorrespondingNames().add("Bloqué");
        this.getCorrespondingNames().add("En cours");
        this.getCorrespondingNames().add("Terminé");
        this.getCorrespondingCSS().add("bg-blocked");
        this.getCorrespondingCSS().add("bg-ongoing");
        this.getCorrespondingCSS().add("bg-done");
    }
    public ThreeStepProgress(int st, String comment){
        super();
        this.getCorrespondingNames().add("Bloqué");
        this.getCorrespondingNames().add("En cours");
        this.getCorrespondingNames().add("Terminé");
        this.getCorrespondingCSS().add("bg-blocked");
        this.getCorrespondingCSS().add("bg-ongoing");
        this.getCorrespondingCSS().add("bg-done");
        this.setState(st);
        this.setComment(comment);
    }
}
