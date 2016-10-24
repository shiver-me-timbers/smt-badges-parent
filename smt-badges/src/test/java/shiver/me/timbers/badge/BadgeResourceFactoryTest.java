/*
 * Copyright 2016 Karl Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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