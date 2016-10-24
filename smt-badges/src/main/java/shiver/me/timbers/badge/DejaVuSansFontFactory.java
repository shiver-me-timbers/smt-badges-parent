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

import java.awt.*;
import java.io.IOException;

import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

/**
 * @author Karl Bennett
 */
public class DejaVuSansFontFactory implements FontFactory {

    private final Font dejaVuSans;

    public DejaVuSansFontFactory() {
        this(new ResourceFactory());
    }

    public DejaVuSansFontFactory(ResourceFactory resourceFactory) {
        try {
            this.dejaVuSans = createFont(TRUETYPE_FONT, resourceFactory.find("DejaVuSans.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new BadgeFontException("DejaVuSans.ttf", e);
        }
    }

    @Override
    public Font create(String font, int fontSize) {
        if (dejaVuSans.getName().equals(font)) {
            return dejaVuSans.deriveFont(PLAIN, fontSize);
        }
        return new Font(font, PLAIN, fontSize);
    }
}
