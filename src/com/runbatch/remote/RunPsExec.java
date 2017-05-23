package com.runbatch.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ashutosh on 22-05-2017.
 */

//Download https://technet.microsoft.com/en-us/sysinternals/bb897553.aspx
//Help : .\RunBatchAndFetchOutput\PSTools\Pstools.chm

public class RunPsExec {

    public static void main(String[] args) throws IOException {
        RunPsExec runPsExec=new RunPsExec();
        runPsExec.getDataFromRemoteExecution("10.10.10.10","uname","pwd","c:\\program.exe");

    }


    public String getDataFromRemoteExecution(String ipaddress,String uname,String password,String batToExecute) throws IOException {

        String masterBat="TriggerRemoteBatAndFetch.bat";
        Runtime rt = Runtime.getRuntime();

        String[] commands = {masterBat,ipaddress,uname,password,batToExecute, "-get t"};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));


        String capturedInput = "";
        String finalCapturedInput = "";
        boolean flagStartCapture = false;
        while ((capturedInput = stdInput.readLine()) != null) {
            
            if (capturedInput.contains("Sysinternals")) {
                flagStartCapture = true;
                continue;

            }

            if (flagStartCapture) {
                finalCapturedInput = finalCapturedInput + "\n" + capturedInput;
            }
        }

        String capturedError = "";
        String finalCapturedError = "";
        while ((capturedError = stdError.readLine()) != null) {
            finalCapturedError = finalCapturedError+"\n"+capturedError;

        }

         if (finalCapturedError.equals(null) || "".equals(finalCapturedError) || !finalCapturedInput.equals(null)) { //If no error and input not null send the input

            return finalCapturedInput;
        }
        else {
            return finalCapturedError;
        }


    }



}
