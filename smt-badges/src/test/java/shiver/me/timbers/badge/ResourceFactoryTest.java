package shiver.me.timbers.badge;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.TestUtils.resource;

public class ResourceFactoryTest {

    @Test
    public void Can_read_a_resource() throws IOException {

        // Given
        final String resource = "test.txt";

        // When
        final String actual = IOUtils.toString(new ResourceFactory().find(resource), UTF_8);

        // Then
        assertThat(actual, equalTo(resource(resource)));
    }
}