package com.runbatch.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ashutosh on 22-05-2017.
 */
public class RunLocalBatchAndFetch {

    public static void main(String[] args) throws IOException {
        RunLocalBatchAndFetch runLocalBatchAndFetch=new RunLocalBatchAndFetch();

        System.out.println(runLocalBatchAndFetch.getExeData("Local.bat"));

    }


    public String getExeData(String exeName) throws IOException {

        Runtime rt = Runtime.getRuntime();

        String[] commands = {exeName, "-get t"};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));


        String capturedInput = "";
        String finalCapturedInput = "";
        while ((capturedInput = stdInput.readLine()) != null) {
            finalCapturedInput = finalCapturedInput+"\n"+capturedInput;
        }

        String capturedError = "";
        String finalCapturedError = "";
        while ((capturedError = stdError.readLine()) != null) {
            finalCapturedError = finalCapturedError+"\n"+capturedError;

        }

        if ("".equals(finalCapturedError)) {

            return finalCapturedInput;
        }
        else {
            return finalCapturedError;
        }
    }
}
