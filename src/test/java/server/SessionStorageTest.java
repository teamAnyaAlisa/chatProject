package server;

import org.junit.Assert;
import org.junit.Test;

public class SessionStorageTest {
    @Test
    public void shouldMethodGetOneReturnNotNullSession() {
        Assert.assertNotNull(SessionStorage.getTheOne());
    }
}