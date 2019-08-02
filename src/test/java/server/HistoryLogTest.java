package server;


import org.junit.Test;


import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Created by Java_1 on 01.08.2019.
 */
public class HistoryLogTest {

    @Test
    public void shouldContainLoggedStringsInHistory()throws IOException {
            HistoryLog logger = new HistoryLog();

            logger.log("string 1");

            String message = logger.getHistory();
            assertThat(message.contains("string 1"));
    }
}