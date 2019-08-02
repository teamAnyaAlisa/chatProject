package server;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

class Session extends Thread {
    private Socket client;
    private BufferedReader in;
    private SessionStorage sessionStorage;
    private String username = "Anonymous";
    private int clientId;
    private boolean isReader;

    private static HistoryLog logger;
    private static final String encoding = "windows-1251";

    static {
        try {
            logger = new HistoryLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    boolean isReader() {
        return isReader;
    }


    Session(Socket client) throws IOException {
        this.client = client;
        sessionStorage = SessionStorage.getTheOne();

        in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                client.getInputStream())));
    }

    Socket getClient() {
        return client;
    }

    private BufferedWriter getClientOutBuffer(Socket client) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                client.getOutputStream()), encoding));
    }

    private void unicast(Iterator<Session> sessionIterator, ChatMessageHandler messageHandler) {
        Session session = sessionIterator.next();
        try {
            BufferedWriter out = getClientOutBuffer(session.getClient());
            //String message = messageHandler.toString();
            //String[] lineOfMessage = message.split("\n");
            //for(String line: lineOfMessage) {
                out.write(messageHandler.toString());
                out.newLine();
                out.write("");
                out.newLine();
                out.flush();
            //}
        } catch (Exception e) {
            sessionIterator.remove();
        }
    }

    private void broadcast(ChatMessageHandler messageHandler) {
        synchronized (this) {
            Iterator<Session> sessionIterator = sessionStorage.getWriterSessions().iterator();
            while (sessionIterator.hasNext()) {
                unicast(sessionIterator, messageHandler);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                ChatMessageHandler messageHandler = new ChatMessageHandler(in.readLine(), MultithreadedServer.logger);
                LinkedList<Session> list = new LinkedList<>();
                list.add(this);
                switch (messageHandler.getType()) {
                    case SND:
                        messageHandler.setName(username);
                        logger.log(messageHandler.getInfoMessage());
                        broadcast(messageHandler);
                        break;
                    case HIST:
                        unicast(list.iterator(), messageHandler);
                        break;
                    case CHID:
                        username = messageHandler.getName();
                        unicast(list.iterator(), messageHandler);
                        break;
                    case READER:
                        isReader = true;
                        clientId = messageHandler.getUserId();
                        break;
                    case WRITER:
                        isReader = false;
                        clientId = messageHandler.getUserId();
                        break;
                    default:
                        unicast(list.iterator(), messageHandler);
                        break;
                }
            } catch (Exception e) {

            }
        }
    }
}

