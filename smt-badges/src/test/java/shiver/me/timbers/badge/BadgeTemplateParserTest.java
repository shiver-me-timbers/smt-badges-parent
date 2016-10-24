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

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.containsString;
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
import static shiver.me.timbers.badge.Badge.TEMPLATE;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BadgeTemplateParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Can_generate_a_badge_from_a_template() throws IOException {

        final MustacheFactory mustacheFactory = mock(MustacheFactory.class);
        final String template = someString();
        final Flusher flusher = mock(Flusher.class);
        final BadgeData data = mock(BadgeData.class);

        final Mustache mustache = mock(Mustache.class);
        final Writer[] writer = new Writer[1];
        final ArgumentCaptor<Writer> writerCaptor = ArgumentCaptor.forClass(Writer.class);

        final String expected = someString();

        // Given
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
        final String actual = new BadgeTemplateParser(template, mustacheFactory, flusher).generate(data);

        // Then
        verify(flusher).flush(writerCaptor.capture());
        assertThat(writer[0], equalTo(writerCaptor.getValue()));
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void Can_fail_to_generate_a_badge_from_a_template() throws IOException {

        final MustacheFactory mustacheFactory = mock(MustacheFactory.class);
        final String template = someString();
        final Flusher flusher = mock(Flusher.class);
        final BadgeData data = mock(BadgeData.class);

        final Mustache mustache = mock(Mustache.class);

        final IOException exception = new IOException();

        // Given
        given(mustacheFactory.compile(template)).willReturn(mustache);
        willThrow(exception).given(flusher).flush(any(Flushable.class));
        expectedException.expect(BadgeTemplateException.class);
        expectedException.expectMessage(equalTo(format("Could not load the template (%s).", template)));
        expectedException.expectCause(is(exception));

        // When
        new BadgeTemplateParser(template, mustacheFactory, flusher).generate(data);
    }

    @Test
    public void The_mustache_template_works_correctly() throws IOException {

        // Given
        final String subject = someAlphaNumericString(8);
        final String status = someAlphaNumericString(13);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out);
        final BadgeData data = new BadgeData(subject, status, null, 0, 0, null, 0, 0, 0, 0, 0, 0, 0, null);

        // When
        new DefaultMustacheFactory().compile(TEMPLATE).execute(writer, singletonMap("badge", data));
        writer.flush();
        final String actual = out.toString(UTF_8);

        // Then
        assertThat(actual, containsString(subject));
        assertThat(actual, containsString(status));
    }
}