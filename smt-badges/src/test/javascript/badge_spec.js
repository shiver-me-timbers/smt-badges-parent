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

describe('Badge Functions', function () {

  var originalGetElementById;

  beforeEach(function () {
    originalGetElementById = document.getElementById;
  });

  afterEach(function () {
    document.getElementById = originalGetElementById;
  });

  function Element() {
  }

  Element.prototype.setAttribute = function () {
  };
  Element.prototype.getBBox = function () {
  };

  it('Can get the width of an SVG element.', function () {

    var getElementById = mockFunction();
    var id = 'some id';
    var element = mock(Element);
    var bBox = { width: null };
    var expected = 42;

    // Given
    when(getElementById)(id).thenReturn(element);
    document.getElementById = getElementById;
    when(element).getBBox().thenReturn(bBox);
    bBox.width = expected;

    // When
    var actual = getWidth(id);

    // Then
    assertThat(actual, equalTo(expected));
  });

  it('Can create an SVG path direction string.', function () {

    // When
    var actual = pathPoints(1, 2, 3, 4, 5);

    // Then
    assertThat(actual, equalTo('M1 2h3v4H5z'));
  });

  it('Can set an attribute.', function () {

    var getElementById = mockFunction();
    var id = 'some id';
    var name = 'some name';
    var value = 'some value';
    var element = mock(Element);

    // Given
    when(getElementById)(id).thenReturn(element);
    document.getElementById = getElementById;

    // When
    setAttribute(id, name, value);

    // Then
    verify(element).setAttribute(name, value);
  });

  it('Can set an SVG elements width.', function () {

    var getElementById = mockFunction();
    var id = 'some id';
    var width = 42;
    var element = mock(Element);

    // Given
    when(getElementById)(id).thenReturn(element);
    document.getElementById = getElementById;

    // When
    setWidth(id, width);

    // Then
    verify(element).setAttribute('width', width);
  });

  it('Can set an SVG path elements directions.', function () {

    var getElementById = mockFunction();
    var id = 'some id';
    var element = mock(Element);

    // Given
    when(getElementById)(id).thenReturn(element);
    document.getElementById = getElementById;

    // When
    setPath(id, 1, 2, 3, 4, 5);

    // Then
    verify(element).setAttribute('d', 'M1 2h3v4H5z');
  });

  it('Can set an SVG elements x value.', function () {

    var getElementById = mockFunction();
    var id = 'some id';
    var x = 42;
    var element = mock(Element);

    // Given
    when(getElementById)(id).thenReturn(element);
    document.getElementById = getElementById;

    // When
    setX(id, x);

    // Then
    verify(element).setAttribute('x', x);
  });
});
