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
 * This badge is very minimalist, it hos no gradient of rounded corners.
 * <p>
 * The design of this badge has been copied from http://shields.io and attempts to follow their
 * specification: https://github.com/badges/shields/blob/master/spec/SPECIFICATION.md
 *
 * @author Karl Bennett
 */
public class FlatSquareBadge extends CommonBadge<BadgeOptions, BadgeData> {

    static final String FLAT_SQUARE_TEMPLATE = "flat-square-badge.mustache";

    public FlatSquareBadge(String subject, String status, Colour colour) {
        super(
            new BadgeOptions(subject, status, colour),
            new BadgeDataFactory(),
            FLAT_SQUARE_TEMPLATE
        );
    }
}
