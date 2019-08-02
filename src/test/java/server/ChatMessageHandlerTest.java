package server;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

@Ignore
public class ChatMessageHandlerTest {

    @Test
    public void toStringSndTest() {
        //Given
        HistoryLog mockHistoryLog = mock(HistoryLog.class);
        ChatMessageHandler chatMessageHandler = new ChatMessageHandler("/snd 1234", mockHistoryLog);
        String nowTime = chatMessageHandler.getTime();

        //When
        String message = chatMessageHandler.toString();
        String expectedMessage = ">>> "+ "" + " : " + nowTime + " : " + "1234";

        //Then
        Assert.assertEquals("Something got wrong",expectedMessage, message);
    }

    @Test @Ignore
    public void toStringHistTest() {
        //Given
        HistoryLog mockHistoryLog = mock(HistoryLog.class);
        ChatMessageHandler chatMessageHandler = new ChatMessageHandler("/hist",mockHistoryLog);

        //When
        chatMessageHandler.toString();

        //Then
        verify(mockHistoryLog, times(1)).getHistory();
    }

    @Test
    public void toStringChidTest() {
        //Given
        HistoryLog mockHistoryLog = mock(HistoryLog.class);
        ChatMessageHandler chatMessageHandler = new ChatMessageHandler("/chid team02",mockHistoryLog);

        //When
        String expectedMessage = chatMessageHandler.toString();
        String actualMessage = "Set name: " + "team02";

        //Then
        Assert.assertEquals("",expectedMessage, actualMessage);

    }

}