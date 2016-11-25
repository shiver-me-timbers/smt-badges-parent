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

import shiver.me.timbers.badge.font.FontMetrics;
import shiver.me.timbers.badge.options.CommonBadgeOptions;

/**
 * @author Karl Bennett
 */
public class CommonBadgeDataFactory<O extends CommonBadgeOptions, D extends CommonBadgeData> {

    private final int height;
    private final int padding;
    private final int fontSize;
    private final FontMetrics fontMetrics;
    private final InternalBadgeDataFactory<O, D> factory;

    public CommonBadgeDataFactory(
        String fontFile,
        int fontSize,
        int height,
        int padding,
        InternalBadgeDataFactory<O, D> factory
    ) {
        this(new FontMetrics(fontFile, fontSize), fontSize, height, padding, factory);
    }

    public CommonBadgeDataFactory(
        FontMetrics fontMetrics,
        int fontSize,
        int height,
        int padding,
        InternalBadgeDataFactory<O, D> factory
    ) {
        this.height = height;
        this.padding = padding;
        this.fontSize = fontSize;
        this.fontMetrics = fontMetrics;
        this.factory = factory;
    }

    public D create(O options) {
        return factory.create(height, padding, fontSize, fontMetrics, options);
    }
}
