package server;

import java.sql.Timestamp;

/**
 * Created by Java_1 on 01.08.2019.
 */
public class ChatMessageHandler {
    private String initialMessage;
    private Timestamp time;
    private CommandType type;
    private String userName = "";
    private int userId;

    public ChatMessageHandler(String message) {
        time = new Timestamp(System.currentTimeMillis());
        time = new Timestamp(System.currentTimeMillis());
        String[] clientCommand = message.split("\\s+", 2);
        switch (clientCommand[0]) {
            case "/snd":
                type = CommandType.SND;
                initialMessage = message.substring(4);
                break;
            case "/hist":
                type = CommandType.HIST;
                initialMessage = "";
                break;
            case "/child":
                type = CommandType.CHILD;
                userName = message.substring(6);
                break;
            case "/reader":
                type = CommandType.READER;
                userId = Integer.parseInt(message.substring(7));
                break;
            case "/writer":
                type = CommandType.WRITER;
                userId = Integer.parseInt(message.substring(7));
                break;
            default:
                type = CommandType.NONE;
                break;
        }
    }

    public String toString() {
        switch(type) {
            case HIST:
                return MultithreadedServer.logger.getHistory();
            case CHILD:
                return "Set name: " + userName;
            default:
                return getInfoMessage();
        }
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        userName = name;
    }

    public String getInfoMessage() {
        return ">>> "+ userName + " : " + time + " : " + initialMessage;
    }

    public CommandType getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }
}
