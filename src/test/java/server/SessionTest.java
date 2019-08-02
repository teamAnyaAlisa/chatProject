package server;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.Socket;

public class SessionTest {
    @Test
    public void shouldSetCorrectName()throws IOException {
        Socket socket = mock(Socket.class);
        Session session = new Session(socket);

        session.setUsername("Name");
        assertThat(session.getUsername().equals("Name"));
    }
}

