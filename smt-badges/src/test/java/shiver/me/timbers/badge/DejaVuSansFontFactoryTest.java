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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.io.IOException;

import static java.awt.Font.PLAIN;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.badge.DejaVuSansFontFactory.DEJA_VU_SANS_TTF;
import static shiver.me.timbers.badge.TestUtils.FONTS;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;
import static shiver.me.timbers.data.random.RandomThings.someThing;

public class DejaVuSansFontFactoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DejaVuSansFontFactory factory;

    @Before
    public void setUp() {
        factory = new DejaVuSansFontFactory();
    }

    @Test
    public void Can_create_a_DejaVuSans_font() {

        // Given
        final String font = "DejaVu Sans Book";
        final int fontSize = someIntegerBetween(1, 32);

        // When
        final Font actual = factory.create(font, fontSize);

        // Then
        assertThat(actual.getName(), equalTo(font));
        assertThat(actual.getStyle(), equalTo(PLAIN));
        assertThat(actual.getSize(), equalTo(fontSize));
    }

    @Test
    public void Can_create_a_default_font() {

        // Given
        final String font = someThing(FONTS);
        final int fontSize = someIntegerBetween(1, 32);

        // When
        final Font actual = factory.create(font, fontSize);

        // Then
        assertThat(actual.getName(), equalTo(font));
        assertThat(actual.getStyle(), equalTo(PLAIN));
        assertThat(actual.getSize(), equalTo(fontSize));
    }

    @Test
    public void Can_fail_to_create_a_font() throws IOException {

        final ResourceFactory resourceFactory = mock(ResourceFactory.class);

        final IOException exception = new IOException();

        // Given
        given(resourceFactory.find(DEJA_VU_SANS_TTF)).willThrow(exception);
        expectedException.expect(BadgeFontException.class);
        expectedException.expectMessage(equalTo(format("Failed to load font file (%s)", DEJA_VU_SANS_TTF)));
        expectedException.expectCause(is(exception));

        // When
        new DejaVuSansFontFactory(resourceFactory);
    }
}