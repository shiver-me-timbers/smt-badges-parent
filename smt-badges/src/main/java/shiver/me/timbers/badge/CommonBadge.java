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

import shiver.me.timbers.badge.data.CommonBadgeData;
import shiver.me.timbers.badge.data.CommonBadgeDataFactory;
import shiver.me.timbers.badge.data.InternalBadgeDataFactory;
import shiver.me.timbers.badge.options.CommonBadgeOptions;
import shiver.me.timbers.badge.template.BadgeTemplateParser;

/**
 * This class contains the common logic that can be used when generating any kind of badge.
 *
 * @author Karl Bennett
 */
public class CommonBadge<O extends CommonBadgeOptions, D extends CommonBadgeData> {

    private static final String FONT_FILE = "DejaVuSans-webfont.ttf";
    static final int FONT_SIZE = 11;
    static final int HEIGHT = 20;
    static final int PADDING = 5;

    private final String svg;

    public CommonBadge(O badgeOptions, InternalBadgeDataFactory<O, D> dataFactory, String template) {
        this(
            badgeOptions,
            new CommonBadgeDataFactory<>(FONT_FILE, FONT_SIZE, HEIGHT, PADDING, dataFactory),
            new BadgeTemplateParser<D>(template)
        );
    }

    public CommonBadge(O badgeOptions, CommonBadgeDataFactory<O, D> dataFactory, BadgeTemplateParser<D> templateParser) {
        this.svg = templateParser.parse(dataFactory.create(badgeOptions));
    }

    @Override
    public String toString() {
        return svg;
    }
}
