/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import org.apache.commons.io.IOUtils;
import univlyon1.fr.logiedu.View.ExerciceWithSourcesView;

/**
 * A syntax highlighting code editor for JavaFX created by wrapping a
 * CodeMirror code editor in a WebView.
 *
 * See http://codemirror.net for more information on using the codemirror editor.
 */
public class CodeEditor extends StackPane {
  /** a webview used to encapsulate the CodeMirror JavaScript. */
  final WebView webview = new WebView();

  /** a snapshot of the code to be edited kept for easy initilization and reversion of editable code. */
  private String editingCode;

  /**
   * a template for editing code - this can be changed to any template derived from the
   * supported modes at http://codemirror.net to allow syntax highlighted editing of
   * a wide variety of languages.
   */
  private final String editingTemplate =
    "<!doctype html>" +
    "<html>" +
    "<head>" +
    "  <link rel=\"stylesheet\" href=\"/lib/codemirror.css\">" +
          "<style type=\"text/css\">"+getCss()+"</style>"+
    "  <script >"+getJS()+"</script>" +
   // "  <script src=\"http://codemirror.net/mode/clike/clike.js\"></script>" +
    "</head>" +
    "<body>" +
    "<form><textarea id=\"code\" name=\"code\">\n" +
    "${code}" +
    "</textarea></form>" +
    "<script>" +
    "  var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), {" +
    "    lineNumbers: true," +
    "    matchBrackets: true," +
    "   styleActiveLine: true,"+
    "   theme: \"eclipse\","+
    "   smartIndent: false,"+
    "    mode: \"text/x-java\"" +
    "  });" +
    "</script>" +
    "</body>" +
    "</html>";

  /** applies the editing template to the editing code to create the html+javascript source for a code editor. */
  private String applyEditingTemplate() {
    return editingTemplate.replace("${code}", editingCode);
  }

  /** sets the current code in the editor and creates an editing snapshot of the code which can be reverted to. */
  public void setCode(String newCode) {
    this.editingCode = newCode;
    webview.getEngine().loadContent(applyEditingTemplate());
  }

  /** returns the current code in the editor and updates an editing snapshot of the code which can be reverted to. */
  public String getCodeAndSnapshot() {
    this.editingCode = (String ) webview.getEngine().executeScript("editor.getValue();");
    return editingCode;
  }

  /** revert edits of the code to the last edit snapshot taken. */
  public void revertEdits() {
    setCode(editingCode);
  }
  
  public String getJS(){
        try {
            InputStream f = getClass().getResourceAsStream("/lib/codemirror.js");
            String content0 = IOUtils.toString(f, "UTF-8");
            InputStream f2 = getClass().getResourceAsStream("/lib/clike.js");
            String content1 = IOUtils.toString(f2, "UTF-8");
            InputStream f3 = getClass().getResourceAsStream("/lib/active-line.js");
            String content2 = IOUtils.toString(f3, "UTF-8");
            return (content0+content1+content2);
        } catch (IOException ex) {
            Logger.getLogger(ExerciceWithSourcesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  public String getCss(){
        try {
            InputStream f = getClass().getResourceAsStream("/lib/codemirror.css");
            String content0 = IOUtils.toString(f, "UTF-8");
            return (content0);
        } catch (IOException ex) {
            Logger.getLogger(ExerciceWithSourcesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

  /**
   * Create a new code editor.
   * @param editingCode the initial code to be edited in the code editor.
   */
  public CodeEditor(String editingCode, int parentWidth) {
    this.editingCode = editingCode;
    /*this.setPrefSize((parentWidth-80)/2, 300);
    webview.setPrefSize((parentWidth-90)/2, 260);
    webview.setMinSize((parentWidth-90)/2, 260);
    this.setMinSize((parentWidth-80)/2, 300);*/
    webview.getEngine().loadContent(applyEditingTemplate());

    this.getChildren().add(webview);
  }
}
