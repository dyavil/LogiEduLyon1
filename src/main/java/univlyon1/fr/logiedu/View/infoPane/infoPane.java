/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.View.infoPane;

import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class infoPane extends GridPane{
    private Label content;
    
    public infoPane(String c){
        this.content = new Label();
        RowConstraints c1 = new RowConstraints();
        c1.setPercentHeight(100);
        c1.setValignment(VPos.BOTTOM);
        this.getRowConstraints().add(c1);
        this.setContent(c, "");
        this.add(content, 0, 0);
    }
    
    public void setContent(String c, String ic){
        this.content.getStyleClass().clear();
        this.content.setText(c);
        this.content.getStyleClass().add(ic);
    }
}
