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
