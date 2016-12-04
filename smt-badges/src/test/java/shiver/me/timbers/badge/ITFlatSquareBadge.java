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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static java.lang.String.valueOf;
import static shiver.me.timbers.badge.BadgeTestUtils.assertFlatSquareStyle;
import static shiver.me.timbers.badge.BadgeTestUtils.assertStatus;
import static shiver.me.timbers.badge.BadgeTestUtils.assertStatusRectangle;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSubject;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSubjectRectangle;
import static shiver.me.timbers.badge.BadgeTestUtils.assertSvg;
import static shiver.me.timbers.badge.BadgeTestUtils.assertTextContainer;
import static shiver.me.timbers.badge.BadgeTestUtils.badgeWidth;
import static shiver.me.timbers.badge.BadgeTestUtils.toDocument;
import static shiver.me.timbers.badge.FlatBadge.HEIGHT;
import static shiver.me.timbers.badge.FlatSquareBadge.FLAT_SQUARE_TEMPLATE;
import static shiver.me.timbers.badge.TestUtils.The_mustache_template_works_correctly;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;

public class ITFlatSquareBadge {

    @Test
    public void Can_create_a_flat_square_badge()
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        // Given
        final String subject = someAlphaNumericString(8);
        final String status = someAlphaNumericString(13);
        final Colour colour = someEnum(Colour.class);

        // When
        final Document actual = toDocument(new FlatSquareBadge(subject, status, colour));

        // Then
        final String badgeWidth = badgeWidth(subject, status);
        final String badgeHeight = valueOf(HEIGHT);
        assertSvg(actual, badgeWidth, badgeHeight);
        assertFlatSquareStyle(actual, subject, status);
        assertSubjectRectangle(actual, badgeWidth, badgeHeight);
        assertStatusRectangle(actual, subject, status, colour, badgeHeight);
        assertTextContainer(actual);
        assertSubject(actual, subject);
        assertStatus(actual, subject, status);
    }

    @Test
    public void The_flat_mustache_template_works_correctly() throws IOException {

        The_mustache_template_works_correctly(FLAT_SQUARE_TEMPLATE);
    }
}