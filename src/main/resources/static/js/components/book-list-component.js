bookishApp.component('bookList', {
  templateUrl: 'js/templates/book-list-template.html',
  controller: ['$routeParams', 'bookService',
    function BookListController($routeParams, bookService) {
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
    }
  ]
});