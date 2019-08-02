package server;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

/**
 * Created by Java_1 on 01.08.2019.
 */
class HistoryLog {
    private File path;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedWriter out;
    private BufferedReader in;
    private String encoding = "windows-1251";
    HistoryLog() throws IOException {
        path = new File("C:\\Users\\Student\\project\\chatProject\\history.txt");
        path.createNewFile();
        //fileWriter = new FileWriter("history.txt", true );
        //fileReader = new FileReader("history.txt");
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(
                                        path, true), 450), encoding) );
        in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(
                                        path)), encoding));
    }
    void log(String message){
        try {
            out.write(message);
            out.newLine();
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getHistory(){
        try {
            StringBuffer history = new StringBuffer("");
            String line = in.readLine();
            while(line != null){
               history.append(line).append("\n");
               line = in.readLine();
            }
            return history.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    void closeFile() throws IOException {
        out.close();
        in.close();

    }
}