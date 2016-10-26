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

/**
 * @author Karl Bennett
 */
public class BadgeDataFactory {

    private final int height;
    private final int padding;
    private final int fontSize;
    private final FontMetrics fontMetrics;

    public BadgeDataFactory(int height, int padding, String fontFile, int fontSize) {
        this(height, padding, fontSize, new FontMetrics(fontFile, fontSize));
    }

    public BadgeDataFactory(int height, int padding, int fontSize, FontMetrics fontMetrics) {
        this.height = height;
        this.padding = padding;
        this.fontSize = fontSize;
        this.fontMetrics = fontMetrics;
    }

    public BadgeData create(String subject, String status, Colour colour) {
        final int subjectWidth = textWidth(subject);
        final int statusWidth = textWidth(status);
        final int width = width(subjectWidth, statusWidth);
        final int subjectX = subjectX(subjectWidth);
        final int statusX = statusX(subjectWidth, statusWidth);
        final int textShadowY = textShadowY();
        final int textY = textY();
        return new BadgeData(
            subject,
            status,
            colour,
            width,
            height,
            fontMetrics.fontFamily(),
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            statusX,
            textShadowY,
            textY
        );
    }

    private static int statusX(int subjectWidth, int statusWidth) {
        return subjectWidth + (subjectX(statusWidth));
    }

    private static int subjectX(int subjectWidth) {
        return subjectWidth / 2;
    }

    private int textWidth(String text) {
        return (int) fontMetrics.calculateWidth(text) + (padding * 2);
    }

    private int width(int subjectWidth, int statusWidth) {
        return subjectWidth + statusWidth;
    }

    private int textShadowY() {
        return height - padding;
    }

    private int textY() {
        return textShadowY() - 1;
    }
}
