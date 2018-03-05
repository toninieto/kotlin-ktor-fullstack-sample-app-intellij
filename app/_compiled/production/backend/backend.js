if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'backend'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'backend'.");
}
var backend = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function main(args) {
    println('We say hello world on the backend!!!');
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('backend', _);
  return _;
}(typeof backend === 'undefined' ? {} : backend, kotlin);
