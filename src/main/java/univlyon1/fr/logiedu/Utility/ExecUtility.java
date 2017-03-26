/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.Utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author dyavil
 */
public class ExecUtility {

    
    private static void printLines(String name, InputStream ins, OutputStream stdOut) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            stdOut.write(line.getBytes());
            System.out.println(name + " " + line);
        }
  }
    
    public static void runProcess(String command, OutputStream errOut, OutputStream stdOut) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream(), stdOut);
        printLines(command + " stderr:", pro.getErrorStream(), errOut);
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
  }
}
