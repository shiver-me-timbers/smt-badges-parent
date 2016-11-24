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

/**
 * This class is used to generate the XML for an SVG badge that are usually used to show metrics on source code
 * repositories. The design of this badge has been copied from http://shields.io and attempts to follow their
 * specification: https://github.com/badges/shields/blob/master/spec/SPECIFICATION.md
 *
 * @author Karl Bennett
 */
public class Badge {

    static final int HEIGHT = 20;
    static final int PADDING = 5;
    static final String FONT_FILE = "DejaVuSans-webfont.ttf";
    static final int FONT_SIZE = 11;
    static final String FLAT_PLASTIC_TEMPLATE = "flat-plastic-badge.mustache";
    static final String FLAT_SQUARE_TEMPLATE = "flat-square-badge.mustache";

    private final String svg;

    public Badge(String subject, String status, Colour colour) {
        this(subject, status, colour, flat);
    }

    public Badge(String subject, String status, Colour colour, Style style) {
        this(
            new BadgeOptions(subject, status, style, colour),
            new BadgeDataFactory(HEIGHT, PADDING, FONT_FILE, FONT_SIZE),
            new BadgeTemplateParser(FLAT_PLASTIC_TEMPLATE, FLAT_SQUARE_TEMPLATE)
        );
    }

    public Badge(
        BadgeOptions badgeOptions,
        BadgeDataFactory dataFactory,
        BadgeTemplateParser templateParser
    ) {
        this.svg = templateParser.parse(dataFactory.create(badgeOptions));
    }

    @Override
    public String toString() {
        return svg;
    }
}
