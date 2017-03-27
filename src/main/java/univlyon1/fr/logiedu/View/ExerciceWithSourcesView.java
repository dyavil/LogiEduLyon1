/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author dyavil
 */
public class ExerciceWithSourcesView extends ExerciceView {
    
    private GridPane returnPane;
    private GridPane codePane;
    private Label textContent;
    private TextArea sourceContent;
    private WebView sourceContentWV;
    private Label stdOutput;
    private Label errOutput;
    private Button runButton;
    
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
        this.sourceContentWV = new WebView();
        this.sourceContentWV.getStylesheets().add("/lib/codemirror.css");
        
        this.sourceContentWV.getEngine().loadContent("<html><body style='max-width:200px; height:200px; margin:0px; padding:0px;' ><div style='position: absolute;\n" +
"        top: 0;\n" +
"        right: 0;\n" +
"        bottom: 0;\n" +
"        left: 0;' id='code'>"+fileContent+"</div></body></html>");
        this.sourceContentWV.applyCss();
        this.sourceContentWV.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    getSourceContentWV().getEngine().executeScript(getJS()+"\n" +
                    "ace.require(\"ace/ext/language_tools\");var editor = ace.edit(\"code\");editor.setTheme(\"ace/theme/merbivore\");editor.getSession().setMode(\"ace/mode/java\");var cod = editor.getValue();"+"$(function () {\n" +
"    $('*').click(function (event) {\n" +
"        event.preventDefault();\n" +
"        event.stopPropagation();\n" +
"        clickController.printId(this);\n" +
"    });\n" +
"});");
                        }
                    }
                });
            this.sourceContentWV.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
              @Override
              public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                  if (newState == State.SUCCEEDED) {
                      JSObject window = (JSObject) sourceContentWV.getEngine().executeScript("window");
                      window.setMember("clickController", new WebController());
                    }
                }
            }
          );
        this.runButton = new Button("Run");
        ColumnConstraints midCol = new ColumnConstraints((parentWidth-80)/2);
        this.codePane.getColumnConstraints().add(midCol);
        this.codePane.getColumnConstraints().add(midCol);
        ColumnConstraints amidCol = new ColumnConstraints((parentWidth-100)/2);
        amidCol.setHalignment(HPos.CENTER);
        this.returnPane.getColumnConstraints().add(amidCol);
        this.returnPane.getColumnConstraints().add(new ColumnConstraints(40));
        this.returnPane.getColumnConstraints().add(amidCol);
        this.getMiddlePane().getRowConstraints().clear();
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(250));
        this.codePane.add(this.sourceContentWV, 0, 0);
        this.codePane.add(this.textContent, 1, 0);
        
        this.getMiddlePane().getRowConstraints().add(new RowConstraints(100));
        this.returnPane.add(this.stdOutput, 0, 0);
        this.returnPane.add(this.runButton, 1, 0);
        this.returnPane.add(this.errOutput, 2, 0);
        
        this.getMiddlePane().getChildren().clear();
        this.getMiddlePane().add(codePane, 0, 0);
        this.getMiddlePane().add(returnPane, 0, 1);
    }
    
    public String getJS(){
        try {
            InputStream f = getClass().getResourceAsStream("/lib/jquery-3.2.0.min.js");
            String content0 = IOUtils.toString(f, "UTF-8");
            InputStream f2 = getClass().getResourceAsStream("/styles/ace.js");
            String content1 = IOUtils.toString(f2, "UTF-8");
            InputStream f3 = getClass().getResourceAsStream("/styles/ext-language_tools.js");
            String content2 = IOUtils.toString(f3, "UTF-8");
            return (content0+content1+content2);
        } catch (IOException ex) {
            Logger.getLogger(ExerciceWithSourcesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
     * @return the sourceContentWV
     */
    public WebView getSourceContentWV() {
        return sourceContentWV;
    }
    
    public class WebController {
    public void printId(Object object) {
        if (org.w3c.dom.html.HTMLElement.class.isAssignableFrom(object.getClass())) {
            org.w3c.dom.html.HTMLElement it = (org.w3c.dom.html.HTMLElement) object;
            System.out.println("Id is " + it.getId() + it.getTextContent().replaceAll("(    )+", "\n"));
            sourceContent.setText(it.getTextContent().replaceAll("(    )+", "\n    "));
        }
    }
}
    
}
