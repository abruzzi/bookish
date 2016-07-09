bookishApp.component('bookList', {
  templateUrl: 'js/templates/book-list-template.html',
  controller: ['$routeParams', 'bookService', 'localStorageService', 'favoriteService',
    function BookListController($routeParams, bookService, localStorageService, favoriteService) {
      var self = this;
      var page =  $routeParams.page;
      var session = localStorageService.get('session');

      self.favorites = [];

      bookService.fetchAll(page).then(function(books) {
        self.books = books.content;
        self.pages = range(0, books.totalPages);
        self.current = books.number;
        self.total = books.totalPages-1;
        self.first = books.first;
        self.last = books.last;
      });

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