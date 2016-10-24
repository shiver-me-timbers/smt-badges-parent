<!---
Copyright 2016 Karl Bennett

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
smt-badges-parent
===========
[![Build Status](https://travis-ci.org/shiver-me-timbers/smt-badges-parent.svg)](https://travis-ci.org/shiver-me-timbers/smt-badges-parent) [![Coverage Status](https://coveralls.io/repos/shiver-me-timbers/smt-badges-parent/badge.svg?branch=master&service=github)](https://coveralls.io/github/shiver-me-timbers/smt-badges-parent?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-badges-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.shiver-me-timbers/smt-badges-parent/)

This is the parent project of the smt-badges library. It contains the following projects.

## [smt-badges](smt-badges)

This is a library that can be used to generate [shields.io](https://shields.io/) style badges. Examples of which can be 
seen at the top of this README.

#### Usage

```java
// This will produce the badges SVG XML as a String.
new Badge("badge", "example", Colour.yellow).toString();
```

###### Example

![Badge Example](https://shiver-me-timbers.github.io/smt-badges-parent/badge-example.svg)
