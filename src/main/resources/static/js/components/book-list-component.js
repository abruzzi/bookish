bookishApp.component('bookList', {
  templateUrl: 'js/templates/book-list-template.html',
  controller: ['$scope', '$routeParams', 'bookService', 'localStorageService', 'favoriteService',
    function BookListController($scope, $routeParams, bookService, localStorageService, favoriteService) {
      var self = this;
      var page =  $routeParams.page || 0;
      var keywords = "";
      var session = localStorageService.get('session');

      self.favorites = [];

      $scope.$on('search-performed', function(e, data) {
        console.log(data.keywords);
        refreshBookList(data.keywords, page);
      });

      function refreshBookList(keywords, page) {
        bookService.fetchAll(keywords, page).then(function(books) {
          self.books = books.content;
          self.pages = range(0, books.totalPages);
          self.current = books.number;
          self.total = books.totalPages-1;
          self.first = books.first;
          self.last = books.last;
        });
      }

      refreshBookList(keywords, page);

      function range(start, end) {
        var result = [];

        for(var i = start; i < end; i++) {
          result.push(i);
        }

        return result;
      }

      function refreshFavorites() {
        favoriteService.fetchAll(session.current.email).then(function (favorites) {
          self.favorites = favorites;
        });
      }

      self.addToFavorites = function (id) {
        favoriteService.save(session.current.email, id).then(function() {
          toastr.options.positionClass = 'toast-top-right';
          toastr.success("Book has been added to favorites");
          refreshFavorites();
        });
      };

      refreshFavorites();

      self.enableAddToFavoritesLink = function(id) {
        return !_.includes(_.map(self.favorites, "id"), id);
      };
    }
  ]
});