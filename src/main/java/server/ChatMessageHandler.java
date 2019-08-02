package server;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Java_1 on 01.08.2019.
 */
public class ChatMessageHandler {
    private HistoryLog logger;
    private String initialMessage;
    private String time;
    private CommandType type;
    private String userName = "";
    private int userId;

    String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    ChatMessageHandler(String message, HistoryLog logger) {
        this.logger = logger;
        this.time = getTime();
        String[] clientCommand = message.split("\\s+", 2);
        switch (clientCommand[0]) {
            case "/snd":
                type = CommandType.SND;
                initialMessage = message.substring(5);
                break;
            case "/hist":
                type = CommandType.HIST;
                initialMessage = "";
                break;
            case "/chid":
                type = CommandType.CHID;
                userName = message.substring(6);
                break;
            case "/reader":
                type = CommandType.READER;
                userId = Integer.parseInt(message.substring(8));
                break;
            case "/writer":
                type = CommandType.WRITER;
                userId = Integer.parseInt(message.substring(8));
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
            case CHID:
                return "Set name: " + userName;
            default:
                return getInfoMessage();
        }
    }

    String getName() {
        return userName;
    }

    void setName(String name) {
        userName = name;
    }

    String getInfoMessage() {
        return ">>> "+ userName + " : " + time + " : " + initialMessage;
    }

    CommandType getType() {
        return type;
    }

    int getUserId() {
        return userId;
    }
}
