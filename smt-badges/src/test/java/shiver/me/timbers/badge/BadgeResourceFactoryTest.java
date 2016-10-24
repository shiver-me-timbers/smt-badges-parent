package shiver.me.timbers.badge;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BadgeResourceFactoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_retrieve_the_badge_java_script() throws IOException {

        final ResourceFactory resourceFactory = mock(ResourceFactory.class);
        final String resource = someString();

        final String expected = someString();

        // Given
        given(resourceFactory.find(resource)).willReturn(new ByteArrayInputStream(expected.getBytes(UTF_8)));

        // When
        final String actual = new BadgeResourceFactory(resourceFactory).create(resource);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void Can_fail_to_retrieve_the_badge_java_script() throws IOException {

        final ResourceFactory resourceFactory = mock(ResourceFactory.class);
        final String resource = someString();

        final IOException exception = new IOException();

        // Given
        given(resourceFactory.find(resource)).willThrow(exception);
        expectedException.expect(BadgeResourceException.class);
        expectedException.expectMessage(equalTo(format("Could not load the resource (%s).", resource)));
        expectedException.expectCause(is(exception));

        // When
        new BadgeResourceFactory(resourceFactory).create(resource);
    }
}