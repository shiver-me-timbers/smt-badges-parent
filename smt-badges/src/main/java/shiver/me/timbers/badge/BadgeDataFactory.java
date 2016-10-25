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
    private final String font;
    private final int fontSize;
    private final FontMetrics fontMetrics;

    public BadgeDataFactory(int height, int padding, String font, int fontSize) {
        this(height, padding, font, fontSize, new FontMetrics(font, fontSize));
    }

    public BadgeDataFactory(int height, int padding, String font, int fontSize, FontMetrics fontMetrics) {
        this.height = height;
        this.padding = padding;
        this.font = font;
        this.fontSize = fontSize;
        this.fontMetrics = fontMetrics;
    }

    public BadgeData create(String subject, String status, Colour colour) {
        final int subjectWidth = textWidth(subject);
        final int statusWidth = textWidth(status);
        final int width = width(subjectWidth, statusWidth);
        final int subjectX = subjectWidth / 2;
        final int subjectY = textY();
        final int statusX = subjectWidth + (statusWidth / 2);
        final int statusY = textY();
        return new BadgeData(
            subject,
            status,
            colour,
            width,
            height,
            font,
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            subjectY,
            statusX,
            statusY
        );
    }

    private int textWidth(String text) {
        return (int) fontMetrics.calculateWidth(text) + (padding * 2);
    }

    private int width(int subjectWidth, int statusWidth) {
        return subjectWidth + statusWidth;
    }

    private int textY() {
        return height - padding - 1;
    }
}
