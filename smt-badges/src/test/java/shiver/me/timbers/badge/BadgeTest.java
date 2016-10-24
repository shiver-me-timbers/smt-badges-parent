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

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BadgeTest {

    @Test
    public void Can_create_a_badge() {
        new Badge(someString(), someString(), someEnum(Colour.class));
    }

    @Test
    public void Can_generate_the_badge_SVG_XML() {

        final String subject = someString();
        final String status = someString();
        final Colour colour = someEnum(Colour.class);
        final BadgeDataFactory dataFactory = mock(BadgeDataFactory.class);
        final BadgeTemplateParser template = mock(BadgeTemplateParser.class);

        final BadgeData data = mock(BadgeData.class);

        final String expected = someString();

        // Given
        given(dataFactory.create(subject, status, colour)).willReturn(data);
        given(template.generate(data)).willReturn(expected);

        // When
        final String actual = new Badge(subject, status, colour, dataFactory, template).toString();

        // Then
        assertThat(actual, is(expected));
    }
}