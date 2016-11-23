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
public class TemplateFactory {

    private final String flatPlasticTemplate;
    private final String flatSquareTemplate;

    public TemplateFactory(String flatPlasticTemplate, String flatSquareTemplate) {
        this.flatPlasticTemplate = flatPlasticTemplate;
        this.flatSquareTemplate = flatSquareTemplate;
    }

    public String choose(BadgeData data) {
        if (data.isFlatSquare()) {
            return flatSquareTemplate;
        }
        return flatPlasticTemplate;
    }
}
