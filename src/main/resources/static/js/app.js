var bookishApp = angular
    .module('bookishApp', ['ngRoute', 'LocalStorageModule'])
    .config(['$locationProvider', '$routeProvider', 'localStorageServiceProvider', function config($locationProvider, $routeProvider, localStorageServiceProvider) {
        localStorageServiceProvider
            .setPrefix('bookish')
            .setNotify(true, true);

        $locationProvider.hashPrefix('!');

      $routeProvider
          .when('/books', {
            template: '<book-list></book-list>'
          })
          .when('/books/:bookId', {
            template: '<book-details></book-details>'
          })
          .when('/signup', {
              template: '<sign-up></sign-up>'
          })
          .when('/signin', {
              template: '<sign-in></sign-in>'
          }).
          when('/favorites', {
            template: '<favorites></favorites>'
          })
          .otherwise('/books');
    }
    ]);

