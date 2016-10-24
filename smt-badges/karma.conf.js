module.exports = function (config) {
  config.set({

    basePath: '',

    frameworks: ['jasmine', 'jsmockito-jshamcrest'],

    files: [
      'src/main/resources/badge.js',
      'src/test/javascript/**/*spec.js'
    ],

    reporters: ['progress'],

    port: 9876,

    colors: true,

    logLevel: config.LOG_INFO,

    autoWatch: false,

    browsers: ['Chrome'],

    singleRun: false
  })
};
