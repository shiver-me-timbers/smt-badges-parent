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

package shiver.me.timbers.badge.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import shiver.me.timbers.badge.data.CommonBadgeData;
import shiver.me.timbers.badge.io.Flusher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static java.util.Collections.singletonMap;

/**
 * This class uses the {@link com.github.mustachejava.MustacheFactory} to generate the badges SVG XML.
 *
 * @author Karl Bennett
 */
public class BadgeTemplateParser<D extends CommonBadgeData> {

    private final String template;
    private final MustacheFactory mustacheFactory;
    private final Flusher flusher;

    public BadgeTemplateParser(String template) {
        this(template, new DefaultMustacheFactory(), new Flusher());
    }

    public BadgeTemplateParser(String template, MustacheFactory mustacheFactory, Flusher flusher) {
        this.template = template;
        this.mustacheFactory = mustacheFactory;
        this.flusher = flusher;
    }

    public String parse(D data) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out);
        mustacheFactory.compile(template).execute(writer, singletonMap("badge", data));
        try {
            flusher.flush(writer);
        } catch (IOException e) {
            throw new BadgeTemplateException(template, e);
        }
        return out.toString();
    }
}
