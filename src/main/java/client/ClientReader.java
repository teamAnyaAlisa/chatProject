package client;

import java.io.*;
import java.net.Socket;

public class ClientReader {
    private static final String encoding = "windows-1251";

    public static void main(String[] args) {
        try (final Socket server = new Socket("localhost", 666);
             final BufferedWriter out =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             server.getOutputStream(), 100), encoding));
             BufferedReader consoleIn =
                     new BufferedReader(
                             new InputStreamReader(
                                     new BufferedInputStream(
                                             System.in), encoding));
             BufferedReader console = new BufferedReader(
                     new InputStreamReader(
                             new BufferedInputStream(
                                     System.in), encoding))) {
            String userName = "";
            while (true) {
                System.out.println("Please input name with command '/chid': ");
                userName = console.readLine();
                String command = userName.split(" ")[0];
                if ("/chid".equals(command) && userName.length() > 5) {
                    break;
                }
            }
            out.write("/reader");
            out.newLine();
            out.flush();
            out.write(userName);
            out.newLine();
            out.flush();
            while (true) {
                String line = consoleIn.readLine();
                out.write(line);
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Connection was closed");
        }
    }
}
