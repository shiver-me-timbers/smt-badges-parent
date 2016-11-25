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

package shiver.me.timbers.badge.font;

import shiver.me.timbers.badge.io.ResourceFactory;

import java.awt.*;
import java.io.IOException;

import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

/**
 * @author Karl Bennett
 */
public class FontFactory {

    private final Font font;

    public FontFactory(String fontFile) {
        this(new ResourceFactory(), fontFile);
    }

    public FontFactory(ResourceFactory resourceFactory, String fontFile) {
        try {
            this.font = createFont(TRUETYPE_FONT, resourceFactory.find(fontFile));
        } catch (FontFormatException | IOException e) {
            throw new BadgeFontException(fontFile, e);
        }
    }

    public Font create(int fontSize) {
        return font.deriveFont(PLAIN, fontSize);
    }
}
