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
new FlatBadge("badge", "example", Colour.brightgreen).toString();
```
![Badge Example](https://shiver-me-timbers.github.io/smt-badges-parent/badge-example.svg)

This library supports all the shields.io badge styles.

```java
new PlasticBadge("style", "plastic", Colour.green).toString();
```
![Style Plastic](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-plastic.svg)

```java
new FlatBadge("style", "flat", Colour.green).toString();
```
![Style Flat](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-flat.svg)

```java
new FlatSquareBadge("style", "flat square", Colour.green).toString();
```
![Style Flat Square](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-flat-square.svg)

```java
new SocialBadge("style", "social", "http://subject.link", "http://status.link").toString();
```
![Style Social](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-social.svg)

It supports all the standard colours.

![Colour Bright Green](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-brightgreen.svg) ![Colour Green](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-green.svg) ![Colour Yellow Green](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-yellowgreen.svg) ![Colour Yellow](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-yellow.svg) ![Colour Orange](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-orange.svg) ![Colour Red](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-red.svg) ![Colour Light Grey](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-lightgrey.svg) ![Colour Blue](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-blue.svg)

As well as custom status colours.

```java
new FlatBadge("style", "ff69b4", "#ff69b4").toString();
```
![Colour #ff69b4](https://shiver-me-timbers.github.io/smt-badges-parent/badge-colour-ff69b4.svg)

Or lastly both colours can be customised.

```java
new PlasticBadge("style", "plastic custom colours", "#836089", "#ff2711").toString();
```
![Plastic Custom Colours](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-plastic-custom-colours.svg)

```java
new FlatBadge("style", "flat custom colours", "#843774", "#179e42").toString();
```
![Flat Custom Colours](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-flat-custom-colours.svg)

```java
new FlatBadge("style", "flat square custom colours", "#89add7", "#04e66a").toString();
```
![Flat Square Custom Colours](https://shiver-me-timbers.github.io/smt-badges-parent/badge-style-flat-square-custom-colours.svg)
