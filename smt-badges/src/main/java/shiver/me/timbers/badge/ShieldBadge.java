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

import shiver.me.timbers.badge.data.BadgeData;
import shiver.me.timbers.badge.data.BadgeDataFactory;
import shiver.me.timbers.badge.options.BadgeOptions;

import static shiver.me.timbers.badge.Colour.darkgrey;

/**
 * This class contains the logic that is used to generate the shield style of badge.
 *
 * @author Karl Bennett
 */
public class ShieldBadge extends CommonBadge<BadgeOptions, BadgeData> {

    public ShieldBadge(String subject, String status, Colour statusColour, String template) {
        this(subject, status, statusColour.toString(), template);
    }

    public ShieldBadge(String subject, String status, String statusColour, String template) {
        this(subject, status, darkgrey.toString(), statusColour, template);
    }

    public ShieldBadge(String subject, String status, String subjectColour, String statusColour, String template) {
        super(
            new BadgeOptions(subject, status, subjectColour, statusColour),
            new BadgeDataFactory(),
            template
        );
    }
}
