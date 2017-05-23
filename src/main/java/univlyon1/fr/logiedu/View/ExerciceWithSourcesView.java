/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;
import univlyon1.fr.logiedu.Utility.CodeEditor;
import univlyon1.fr.logiedu.View.infoPane.infoPane;

/**
 *
 * @author dyavil
 */
public class ExerciceWithSourcesView extends ExerciceView {
    
    private GridPane returnPane;
    private GridPane codePane;
    private Label textContent;
    private WebView Output;
    private Button runButton;
    private CodeEditor editor;
    private String outputContent;
    private String startOutputContent;
    
    
    private infoPane compilePane;
    private infoPane execPane;
    
    public ExerciceWithSourcesView(String course, String name, String content, int parentWidth, String fileContent) {
        super(course, name, content, parentWidth);
        this.returnPane = new GridPane();
        this.codePane = new GridPane();
        this.Output = new WebView();
        
        this.compilePane = new infoPane("");
        this.execPane = new infoPane("");
        this.outputContent = "";
        this.startOutputContent = "<html style='background-color:black'; padding:5px; font-family: \"Helvetica\";><div style='text-align:center; color:lightgray;'>-- Console --</div>";
        this.Output.getEngine().loadContent(startOutputContent+outputContent+"</html>");


        this.textContent = new Label(content);
        this.textContent.setWrapText(true);
        this.textContent.getStyleClass().add("exercice-content");

        ScrollPane contentPane = new ScrollPane();
        contentPane.setContent(this.textContent);
        contentPane.getStyleClass().add("scroll-pane");
        textContent.wrapTextProperty().setValue(Boolean.TRUE);
        contentPane.setFitToWidth(true);
        contentPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.runButton = new Button();
        this.runButton.getStyleClass().add("run");
        ColumnConstraints midCol = new ColumnConstraints((parentWidth-80)/2);
        ColumnConstraints midCol3 = new ColumnConstraints(60);
        midCol3.setHalignment(HPos.CENTER);
        //System.out.println((parentWidth-80)/2 + ", " + parentWidth);
        this.codePane.getColumnConstraints().add(midCol);
        this.codePane.getColumnConstraints().add(midCol3);
        this.codePane.getColumnConstraints().add(midCol);
        ColumnConstraints amidCol = new ColumnConstraints((parentWidth-110)/2);
        amidCol.setHalignment(HPos.CENTER);
        this.returnPane.getColumnConstraints().add(amidCol);
        ColumnConstraints amidCol2 = new ColumnConstraints(60);
        amidCol2.setHalignment(HPos.CENTER);
        this.returnPane.getColumnConstraints().add(amidCol2);
        this.returnPane.getColumnConstraints().add(amidCol);
        RowConstraints rowc1 = new RowConstraints(90);
        rowc1.setValignment(VPos.TOP);
        this.returnPane.getRowConstraints().add(rowc1);
        this.getMiddlePane().getRowConstraints().clear();
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(80));
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(10));
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(400));
        
        
        GridPane testBack = new GridPane();
        GridPane testBack2 = new GridPane();
        ColumnConstraints midColtest = new ColumnConstraints((parentWidth-80)/2-30);
        midColtest.setHalignment(HPos.CENTER);
        testBack.getColumnConstraints().add(midColtest);
        testBack2.getColumnConstraints().add(midColtest);
        testBack.getStyleClass().add("pcback");
        testBack2.getStyleClass().add("pcback");
        //testBack.getRowConstraints().add(new RowConstraints(5));
        
        
        editor = new CodeEditor(fileContent, parentWidth-20);
        testBack.add(editor, 0, 0);
        testBack2.add(Output, 0, 0);
        Output.getStyleClass().add("pcback2");
        this.codePane.add(testBack, 0, 0);
        this.codePane.add(testBack2, 2, 0);
        
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(30));
        this.returnPane.add(this.execPane, 0, 0);
        this.returnPane.add(this.compilePane, 2, 0);
        this.codePane.add(this.runButton, 1, 0);
        
        this.getMiddlePane().getChildren().clear();
        this.getMiddlePane().add(contentPane, 0, 0);
        this.getMiddlePane().add(codePane, 0, 2);
        this.getMiddlePane().add(returnPane, 0, 3);
    }
    
    
    public void setOutput(String htmlContent){
        this.Output.getEngine().loadContent(this.startOutputContent+htmlContent+"</html>");
        outputContent = htmlContent;
    }
    public void addOutput(String htmlContent){
        outputContent += htmlContent;
        this.Output.getEngine().loadContent(this.startOutputContent+outputContent+"</html>");
    }


    /**
     * @return the textContent
     */
    public Label getTextContent() {
        return textContent;
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

    /**
     * @return the codePane
     */
    public GridPane getCodePane() {
        return codePane;
    }

    /**
     * @param codePane the codePane to set
     */
    public void setCodePane(GridPane codePane) {
        this.codePane = codePane;
    }

    /**
     * @return the compilePane
     */
    public infoPane getCompilePane() {
        return compilePane;
    }

    /**
     * @return the execPane
     */
    public infoPane getExecPane() {
        return execPane;
    }




    
}
