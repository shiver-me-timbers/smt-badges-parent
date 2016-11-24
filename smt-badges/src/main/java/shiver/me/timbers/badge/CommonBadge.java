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
public class CommonBadge<O extends CommonBadgeOptions, D extends CommonBadgeData> {

    private static final String FONT_FILE = "DejaVuSans-webfont.ttf";
    static final int FONT_SIZE = 11;
    static final int HEIGHT = 20;
    static final int PADDING = 5;

    private final String svg;

    public CommonBadge(
        O badgeOptions,
        InternalBadgeDataFactory<O, D> dataFactory,
        TemplateFactory<D> templateFactory
    ) {
        this(
            badgeOptions,
            new CommonBadgeDataFactory<>(FONT_FILE, FONT_SIZE, HEIGHT, PADDING, dataFactory),
            new BadgeTemplateParser<>(templateFactory)
        );
    }

    public CommonBadge(
        O badgeOptions,
        CommonBadgeDataFactory<O, D> dataFactory,
        BadgeTemplateParser<D> templateParser
    ) {
        this.svg = templateParser.parse(dataFactory.create(badgeOptions));
    }

    @Override
    public String toString() {
        return svg;
    }
}
