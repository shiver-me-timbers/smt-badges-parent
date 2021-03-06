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

package shiver.me.timbers.badge.data;

import org.junit.Test;
import shiver.me.timbers.badge.font.FontMetrics;
import shiver.me.timbers.badge.options.BadgeOptions;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.badge.TestUtils.textShadowY;
import static shiver.me.timbers.badge.TestUtils.textY;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.matchers.Matchers.hasField;

public class BadgeDataFactoryTest {

    @Test
    public void Can_create_a_badge_data() {

        final int height = someInteger();
        final int padding = someInteger();
        final int fontSize = someInteger();
        final FontMetrics fontMetrics = mock(FontMetrics.class);
        final BadgeOptions badgeOptions = mock(BadgeOptions.class);

        final String fontFamily = someString();
        final double subjectWidth = someDouble();
        final double statusWidth = someDouble();
        final String subject = someString();
        final String status = someString();
        final String subjectColour = someString();
        final String statusColour = someString();

        // Given
        given(fontMetrics.fontFamily()).willReturn(fontFamily);
        given(fontMetrics.calculateWidth(subject)).willReturn(subjectWidth);
        given(fontMetrics.calculateWidth(status)).willReturn(statusWidth);
        given(badgeOptions.getSubject()).willReturn(subject);
        given(badgeOptions.getStatus()).willReturn(status);
        given(badgeOptions.getSubjectColour()).willReturn(subjectColour);
        given(badgeOptions.getStatusColour()).willReturn(statusColour);

        // When
        final BadgeData actual = new BadgeDataFactory().create(height, padding, fontSize, fontMetrics, badgeOptions);

        // Then
        final int subjectContainerWidth = width(padding, subjectWidth);
        final int statusContainerWidth = width(padding, statusWidth);
        final int subjectX = subjectContainerWidth / 2;
        final int statusX = subjectContainerWidth + (statusContainerWidth / 2);
        assertThat(actual, hasField("subject", subject));
        assertThat(actual, hasField("status", status));
        assertThat(actual, hasField("subjectColour", subjectColour));
        assertThat(actual, hasField("statusColour", statusColour));
        assertThat(actual, hasField("width", width(padding, subjectWidth, statusWidth)));
        assertThat(actual, hasField("height", height));
        assertThat(actual, hasField("subjectWidth", subjectContainerWidth));
        assertThat(actual, hasField("statusWidth", statusContainerWidth));
        assertThat(actual, hasField("fontFamily", fontFamily));
        assertThat(actual, hasField("fontSize", fontSize));
        assertThat(actual, hasField("subjectX", subjectX));
        assertThat(actual, hasField("statusX", statusX));
        assertThat(actual, hasField("textShadowY", textShadowY(height, padding)));
        assertThat(actual, hasField("textY", textY(height, padding)));
    }

    private int width(int padding, double text) {
        return (int) (text + (padding * 2));
    }

    private int width(int padding, double subjectWidth, double statusWidth) {
        return width(padding, subjectWidth) + width(padding, statusWidth);
    }
}