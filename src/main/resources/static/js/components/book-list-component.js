bookishApp.component('bookList', {
  templateUrl: 'js/templates/book-list-template.html',
  controller: ['$routeParams', 'bookService', 'localStorageService', 'favoriteService',
    function BookListController($routeParams, bookService, localStorageService, favoriteService) {
      var self = this;
      var page =  $routeParams.page;

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

      self.addToFavorites = function (id) {
        var session = localStorageService.get("session");
        favoriteService.save(session.current.email, id).then(function() {

        });
      };

      self.deleteFromFavorites = function(id) {
          console.log(id);
      };
    }
  ]
});