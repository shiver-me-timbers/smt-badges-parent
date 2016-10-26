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

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.badge.Badge.FONT_SIZE;
import static shiver.me.timbers.badge.Badge.HEIGHT;
import static shiver.me.timbers.badge.Badge.PADDING;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaNumericString;

public class ITBadge {

    private static final XPath XPATH = XPathFactory.newInstance().newXPath();
    private static Font DEFAULT_FONT;

    @BeforeClass
    public static void setUp() throws IOException, FontFormatException {
        DEFAULT_FONT = createFont(
            TRUETYPE_FONT,
            Thread.currentThread().getContextClassLoader().getResourceAsStream("DejaVuSans-webfont.ttf")
        );
    }

    @Test
    public void Can_create_a_badge()
        throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        // Given
        final String subject = someAlphaNumericString(8);
        final String status = someAlphaNumericString(13);
        final Colour colour = someEnum(Colour.class);

        // When
        final Document actual = toDocument(new Badge(subject, status, colour));

        // Then
        final String badgeWidth = badgeWidth(subject, status);
        final String badgeHeight = valueOf(HEIGHT);
        assertSvg(actual, badgeWidth, badgeHeight);
        assertSubjectRectangle(actual, badgeWidth, badgeHeight);
        assertStatusRectangle(actual, subject, status, colour, badgeHeight);
        assertDividerPath(actual, subject, colour, badgeHeight);
        assertGradientRectangle(actual, badgeWidth, badgeHeight);
        assertTextContainer(actual);
        assertSubject(actual, subject);
        assertStatus(actual, subject, status);
    }

    private static Document toDocument(Badge badge) throws ParserConfigurationException, IOException, SAXException {
        final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(new ByteArrayInputStream(badge.toString().getBytes(UTF_8)));
    }

    private void assertSvg(Document actual, String badgeWidth, String badgeHeight) throws XPathExpressionException {
        final Element svg = (Element) actual.getElementsByTagName("svg").item(0);
        assertThat(svg.getAttribute("width"), equalTo(badgeWidth));
        assertThat(svg.getAttribute("height"), equalTo(badgeHeight));
    }

    private void assertSubjectRectangle(Document actual, String badgeWidth, String badgeHeight) throws XPathExpressionException {
        final Element subjectRectangle = (Element) actual.getElementsByTagName("rect").item(0);
        assertThat(subjectRectangle.getAttribute("width"), equalTo(badgeWidth));
        assertThat(subjectRectangle.getAttribute("height"), equalTo(badgeHeight));
    }

    private void assertStatusRectangle(Document actual, String subject, String status, Colour colour, String badgeHeight) throws XPathExpressionException {
        final Element statusRectangle = (Element) actual.getElementsByTagName("rect").item(1);
        assertThat(statusRectangle.getAttribute("x"), equalTo(String.valueOf(textWidth(subject))));
        assertThat(statusRectangle.getAttribute("width"), equalTo(String.valueOf(textWidth(status))));
        assertThat(statusRectangle.getAttribute("height"), equalTo(badgeHeight));
        assertThat(statusRectangle.getAttribute("fill"), equalTo(colour.toString()));
    }

    private void assertDividerPath(Document actual, String subject, Colour colour, String badgeHeight) throws XPathExpressionException {
        final Element dividerPath = (Element) actual.getElementsByTagName("path").item(0);
        assertThat(dividerPath.getAttribute("d"), equalTo(dividerPathDirections(subject, badgeHeight)));
        assertThat(dividerPath.getAttribute("fill"), equalTo(colour.toString()));
    }

    private void assertGradientRectangle(Document actual, String badgeWidth, String badgeHeight) throws XPathExpressionException {
        final Element gradientRectangle = (Element) actual.getElementsByTagName("rect").item(2);
        assertThat(gradientRectangle.getAttribute("width"), equalTo(badgeWidth));
        assertThat(gradientRectangle.getAttribute("height"), equalTo(badgeHeight));
    }

    private void assertTextContainer(Document actual) throws XPathExpressionException {
        final Element textContainer = (Element) actual.getElementsByTagName("g").item(0);
        assertThat(textContainer.getAttribute("font-family"), startsWith(DEFAULT_FONT.getFamily()));
        assertThat(textContainer.getAttribute("font-size"), equalTo(valueOf(FONT_SIZE)));
    }

    private void assertSubject(Document actual, String subject) throws XPathExpressionException {
        final Element subjectTextShadow = (Element) actual.getElementsByTagName("text").item(0);
        final Element subjectText = (Element) actual.getElementsByTagName("text").item(1);
        assertThat(subjectTextShadow.getAttribute("x"), equalTo(subjectX(subject)));
        assertThat(subjectTextShadow.getAttribute("y"), equalTo(textShadowY()));
        assertThat(subjectTextShadow.getTextContent(), equalTo(subject));
        assertThat(subjectText.getAttribute("x"), equalTo(subjectX(subject)));
        assertThat(subjectText.getAttribute("y"), equalTo(textY()));
        assertThat(subjectText.getTextContent(), equalTo(subject));
    }

    private void assertStatus(Document actual, String subject, String status) throws XPathExpressionException {
        final Element statusTextShadow = (Element) actual.getElementsByTagName("text").item(2);
        final Element statusText = (Element) actual.getElementsByTagName("text").item(3);
        assertThat(statusTextShadow.getAttribute("x"), equalTo(statusX(subject, status)));
        assertThat(statusTextShadow.getAttribute("y"), equalTo(textShadowY()));
        assertThat(statusTextShadow.getTextContent(), equalTo(status));
        assertThat(statusText.getAttribute("x"), equalTo(statusX(subject, status)));
        assertThat(statusText.getAttribute("y"), equalTo(textY()));
        assertThat(statusText.getTextContent(), equalTo(status));
    }

    private Element findElementById(Document document, String id) throws XPathExpressionException {
        return (Element) XPATH.evaluate(format("//*[@id='%s']", id), document, XPathConstants.NODE);
    }

    private static String badgeWidth(String subject, String status) {
        return valueOf(textWidth(subject) + textWidth(status));
    }

    private static String pathDirections(int x, int y, int h, int v, int H) {
        return format("M%s %sh%sv%sH%sz", x, y, h, v, H);
    }

    private static String dividerPathDirections(String subject, String badgeHeight) {
        final int subjectWidth = textWidth(subject);
        return pathDirections(subjectWidth, 0, 4, Integer.valueOf(badgeHeight), subjectWidth);
    }

    private static int textWidth(String text) {
        return TestUtils.textWidth(DEFAULT_FONT.deriveFont(PLAIN, FONT_SIZE), text) + (PADDING * 2);
    }

    private static String textShadowY() {
        return valueOf(TestUtils.textShadowY(HEIGHT, PADDING));
    }

    private static String textY() {
        return valueOf(TestUtils.textY(HEIGHT, PADDING));
    }

    private static String statusX(String subject, String status) {
        return valueOf(textWidth(subject) + (textWidth(status) / 2));
    }

    private static String subjectX(String subject) {
        return valueOf(textWidth(subject) / 2);
    }
}