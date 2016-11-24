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

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class BadgeTemplateFactoryTest {

    @Test
    public void Can_choose_a_flat_template() {

        final String flatPlasticTemplate = someString();
        final BadgeData data = mock(BadgeData.class);

        // Given
        given(data.isFlat()).willReturn(true);
        given(data.isPlastic()).willReturn(false);
        given(data.isFlatSquare()).willReturn(false);

        // When
        final String actual = new BadgeTemplateFactory(flatPlasticTemplate, someString()).choose(data);

        // Then
        assertThat(actual, is(flatPlasticTemplate));
    }

    @Test
    public void Can_choose_a_plastic_template() {

        final String flatPlasticTemplate = someString();
        final BadgeData data = mock(BadgeData.class);

        // Given
        given(data.isFlat()).willReturn(false);
        given(data.isPlastic()).willReturn(true);
        given(data.isFlatSquare()).willReturn(false);

        // When
        final String actual = new BadgeTemplateFactory(flatPlasticTemplate, someString()).choose(data);

        // Then
        assertThat(actual, is(flatPlasticTemplate));
    }

    @Test
    public void Can_choose_a_flat_square_template() {

        final String flatSquareTemplate = someString();
        final BadgeData data = mock(BadgeData.class);

        // Given
        given(data.isFlat()).willReturn(false);
        given(data.isPlastic()).willReturn(false);
        given(data.isFlatSquare()).willReturn(true);

        // When
        final String actual = new BadgeTemplateFactory(someString(), flatSquareTemplate).choose(data);

        // Then
        assertThat(actual, is(flatSquareTemplate));
    }

}