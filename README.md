# RunBatAndFetchOutput

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ashutosh.Mishra on 05/19/2017.
 */
public class TriggerBatAndFetchOutput {

    public static void main(String[] args) throws IOException {

        Runtime rt = Runtime.getRuntime();
        String[] commands = {"Version.bat","-get t"};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));


        System.out.println("Here is the standard output of the command: the line in which command was entered\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }


        System.out.println("\n\nHere is the standard error/output of the command :\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }
}


Version.bat :
java -version


---------
public class SingleJava {

    public static void main(String[] args) throws IOException {

        SingleJava singleJava=new SingleJava();

        System.out.println(singleJava.getExeData("hello.exe"));

    }

    public String getExeData(String exeName) throws IOException {

        Runtime rt = Runtime.getRuntime();
        //String[] commands = {"Version.bat", "-get t"};
        String[] commands = {exeName, "-get t"};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));


        String capturedInput = "";
        String finalCapturedInput = "";
        while ((capturedInput = stdInput.readLine()) != null) {
            finalCapturedInput = capturedInput + finalCapturedInput;
        }

        String capturedError = "";
        String finalCapturedError = "";
        while ((capturedError = stdError.readLine()) != null) {
            finalCapturedError = capturedError + finalCapturedError;

        }

        if ("".equals(finalCapturedError)) { //If no error send the input

            return finalCapturedInput;
        }
        else {
            return finalCapturedError;
        }
    }
}
