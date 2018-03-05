if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'frontend'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'frontend'.");
}
var frontend = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function main(args) {
    println('We say hello world on the frontend!!!');
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('frontend', _);
  return _;
}(typeof frontend === 'undefined' ? {} : frontend, kotlin);
