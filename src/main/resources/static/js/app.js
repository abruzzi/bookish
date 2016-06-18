var bookishApp = angular
    .module('bookishApp', ['ngRoute'])
    .config(['$locationProvider', '$routeProvider', function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider
          .when('/books', {
            template: '<book-list></book-list>'
          })
          .when('/books/:bookId', {
            template: '<book-details></book-details>'
          })
          .otherwise('/books');
    }
    ]);

