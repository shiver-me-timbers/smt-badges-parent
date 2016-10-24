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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This factory is used to read the badge template file into a {@link String} ready to be processed by the
 * {@link BadgeTemplateParser}.
 *
 * @author Karl Bennett
 */
public class BadgeResourceFactory {

    private final ResourceFactory resourceFactory;

    public BadgeResourceFactory() {
        this(new ResourceFactory());
    }

    public BadgeResourceFactory(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    public String create(String resource) {
        try {
            final InputStream inputStream = resourceFactory.find(resource);
            final ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        } catch (IOException e) {
            throw new BadgeResourceException(resource, e);
        }
    }
}
