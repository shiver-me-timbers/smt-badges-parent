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

import static shiver.me.timbers.badge.Style.flat;
import static shiver.me.timbers.badge.Style.flat_square;
import static shiver.me.timbers.badge.Style.plastic;

/**
 * This class holds all thr data that will be used by the {@link BadgeTemplateParser} to generate the SVG XML.
 *
 * @author Karl Bennett
 */
public class BadgeData extends CommonBadgeData {

    private final Colour colour;
    private final Style style;

    public BadgeData(
        String subject,
        String status,
        Colour colour,
        Style style,
        int width,
        int height,
        String fontFamily,
        int fontSize,
        int subjectWidth,
        int statusWidth,
        int subjectX,
        int statusX,
        int textShadowY,
        int textY
    ) {
        super(
            subject,
            status,
            width,
            height,
            fontFamily,
            fontSize,
            subjectWidth,
            statusWidth,
            subjectX,
            statusX,
            textShadowY,
            textY
        );
        this.colour = colour;
        this.style = style;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean isFlat() {
        return style == null || style == flat;
    }

    public boolean isPlastic() {
        return plastic.equals(style);
    }

    public boolean isFlatSquare() {
        return flat_square.equals(style);
    }
}
