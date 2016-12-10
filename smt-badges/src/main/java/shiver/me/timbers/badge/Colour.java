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
 * This enum contains the valid colours that a badge can have.
 *
 * @author Karl Bennett
 */
public enum Colour {
    brightgreen("#4c1"),
    green("#97ca00"),
    yellowgreen("#a4a61d"),
    yellow("#dfb317"),
    orange("#fe7d37"),
    red("#e05d44"),
    darkgrey("#555"),
    lightgrey("#9f9f9f"),
    blue("#007ec6");

    private final String colour;

    Colour(String colour) {
        this.colour = colour;
    }


    @Override
    public String toString() {
        return colour;
    }
}
