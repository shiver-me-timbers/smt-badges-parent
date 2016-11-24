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

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static shiver.me.timbers.badge.Badge.FLAT_PLASTIC_TEMPLATE;
import static shiver.me.timbers.badge.Badge.FLAT_SQUARE_TEMPLATE;
import static shiver.me.timbers.badge.TestUtils.The_mustache_template_works_correctly;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BadgeTemplateParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BadgeTemplateFactory templateFactory;
    private MustacheFactory mustacheFactory;
    private Flusher flusher;
    private BadgeTemplateParser parser;

    @Before
    public void setUp() {
        templateFactory = mock(BadgeTemplateFactory.class);
        mustacheFactory = mock(MustacheFactory.class);
        flusher = mock(Flusher.class);
        parser = new BadgeTemplateParser(templateFactory, mustacheFactory, flusher);
    }

    @Test
    public void Can_generate_a_badge_from_a_flat_plastic_template() throws IOException {

        final String template = someString();
        final BadgeData data = mock(BadgeData.class);

        final Mustache mustache = mock(Mustache.class);
        final Writer[] writer = new Writer[1];
        final ArgumentCaptor<Writer> writerCaptor = ArgumentCaptor.forClass(Writer.class);

        final String expected = someString();

        // Given
        given(templateFactory.choose(data)).willReturn(template);
        given(mustacheFactory.compile(template)).willReturn(mustache);
        willAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                writer[0] = invocation.getArgumentAt(0, Writer.class);
                IOUtils.write(expected, writer[0]);
                writer[0].flush();
                return null;
            }
        }).given(mustache).execute(any(Writer.class), eq(singletonMap("badge", data)));

        // When
        final String actual = parser.parse(data);

        // Then
        verify(flusher).flush(writerCaptor.capture());
        assertThat(writer[0], equalTo(writerCaptor.getValue()));
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void Can_fail_to_generate_a_badge_from_a_template() throws IOException {

        final String template = someString();
        final BadgeData data = mock(BadgeData.class);

        final Mustache mustache = mock(Mustache.class);

        final IOException exception = new IOException();

        // Given
        given(templateFactory.choose(data)).willReturn(template);
        given(mustacheFactory.compile(template)).willReturn(mustache);
        willThrow(exception).given(flusher).flush(any(Flushable.class));
        expectedException.expect(BadgeTemplateException.class);
        expectedException.expectMessage(equalTo(format("Could not load the template (%s).", template)));
        expectedException.expectCause(is(exception));

        // When
        parser.parse(data);
    }

    @Test
    public void The_flat_plastic_mustache_template_works_correctly() throws IOException {

        The_mustache_template_works_correctly(FLAT_PLASTIC_TEMPLATE);
    }

    @Test
    public void The_flat_square_mustache_template_works_correctly() throws IOException {

        The_mustache_template_works_correctly(FLAT_SQUARE_TEMPLATE);
    }
}