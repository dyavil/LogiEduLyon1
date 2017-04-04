/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import univlyon1.fr.logiedu.Utility.CodeEditor;

/**
 *
 * @author dyavil
 */
public class ExerciceWithSourcesView extends ExerciceView {
    
    private GridPane returnPane;
    private GridPane codePane;
    private Label textContent;
    private TextArea sourceContent;
    private Label stdOutput;
    private Label errOutput;
    private Button runButton;
    private CodeEditor editor;
    
    public ExerciceWithSourcesView(String course, String name, String content, int parentWidth, String fileContent) {
        super(course, name, content, parentWidth);
        this.returnPane = new GridPane();
        this.codePane = new GridPane();
        this.stdOutput = new Label("stdout");
        this.stdOutput.setMinHeight(80);
        this.stdOutput.setMinWidth(((parentWidth-100)/2));
        this.stdOutput.getStyleClass().add("stdPane");
        this.stdOutput.setAlignment(Pos.TOP_LEFT);
        this.stdOutput.setWrapText(true);
        this.errOutput = new Label("sdterr");
        this.errOutput.getStyleClass().add("errPane");
        this.errOutput.setMinHeight(80);
        this.errOutput.setMinWidth(((parentWidth-100)/2));
        this.errOutput.setAlignment(Pos.TOP_LEFT);
        this.errOutput.setWrapText(true);
        this.textContent = new Label(content);
        this.textContent.setWrapText(true);
        this.textContent.getStyleClass().add("exercice-content");
        this.sourceContent = new TextArea(fileContent);
        this.sourceContent.setWrapText(true);


        this.runButton = new Button("Run");
        ColumnConstraints midCol = new ColumnConstraints((parentWidth-80)/2);
        this.codePane.getColumnConstraints().add(midCol);
        this.codePane.getColumnConstraints().add(midCol);
        ColumnConstraints amidCol = new ColumnConstraints((parentWidth-110)/2);
        amidCol.setHalignment(HPos.CENTER);
        this.returnPane.getColumnConstraints().add(amidCol);
        ColumnConstraints amidCol2 = new ColumnConstraints(60);
        amidCol2.setHalignment(HPos.CENTER);
        this.returnPane.getColumnConstraints().add(amidCol2);
        this.returnPane.getColumnConstraints().add(amidCol);
        this.getMiddlePane().getRowConstraints().clear();
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(350));
        
        this.codePane.add(this.textContent, 1, 0);
        editor = new CodeEditor(fileContent, parentWidth);
        this.codePane.add(editor, 0, 0);
        
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(100));
        this.returnPane.add(this.stdOutput, 0, 0);
        this.returnPane.add(this.runButton, 1, 0);
        this.returnPane.add(this.errOutput, 2, 0);
        
        this.getMiddlePane().getChildren().clear();
        this.getMiddlePane().add(codePane, 0, 0);
        this.getMiddlePane().add(returnPane, 0, 1);
    }
    
    
    


    /**
     * @return the textContent
     */
    public Label getTextContent() {
        return textContent;
    }

    /**
     * @return the sourceContent
     */
    public TextArea getSourceContent() {
        return sourceContent;
    }

    /**
     * @return the stdOutput
     */
    public Label getStdOutput() {
        return stdOutput;
    }

    /**
     * @return the errOutput
     */
    public Label getErrOutput() {
        return errOutput;
    }

    /**
     * @return the runButton
     */
    public Button getRunButton() {
        return runButton;
    }

    /**
     * @return the editor
     */
    public CodeEditor getEditor() {
        return editor;
    }



    
}
