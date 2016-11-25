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
 * This class holds all thr data that will be used by the {@link BadgeTemplateParser} to generate the SVG XML.
 *
 * @author Karl Bennett
 */
public class BadgeData extends CommonBadgeData {

    private final Colour colour;

    public BadgeData(
        String subject,
        String status,
        Colour colour,
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
    }

    public Colour getColour() {
        return colour;
    }
}
