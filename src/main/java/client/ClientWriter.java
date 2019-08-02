package client;

import java.io.*;
import java.net.Socket;

public class ClientWriter {
    public static void main(String[] args) {
        try (final Socket server = new Socket("localhost", 666);
             final BufferedReader in =
                     new BufferedReader(
                             new InputStreamReader(
                                     new BufferedInputStream(
                                             server.getInputStream(), 100)));
             BufferedWriter consoleOut =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             System.out)));
             final BufferedWriter out =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     new BufferedOutputStream(
                                             server.getOutputStream(), 100)));
             BufferedReader console = new BufferedReader(
                     new InputStreamReader(
                             new BufferedInputStream(
                                     System.in)))) {
            out.write("/writer");
            out.newLine();
            out.flush();
            while (true) {
                String line;
                while ((line = in.readLine()) != null) {
                    consoleOut.write(line);
                    consoleOut.newLine();
                    consoleOut.flush();
                    if ("/close".equals(line)) { break; }
                }
            }
        } catch (IOException e) {
            System.out.println("Connection was closed");
        }
    }
}
