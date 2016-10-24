package shiver.me.timbers.badge;

import org.junit.Test;

import java.io.Flushable;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FlusherTest {

    @Test
    public void Can_flush_a_flushable() throws IOException {

        // Given
        final Flushable flushable = mock(Flushable.class);

        // When
        new Flusher().flush(flushable);

        // Then
        verify(flushable).flush();
    }
}