bookishApp.component('bookList', {
  templateUrl: 'js/templates/book-list-template.html',
  controller: ['$routeParams', 'bookService',
    function BookListController($routeParams, bookService) {
      var self = this;
      bookService.fetchAll().then(function(books) {
        self.books = books.content;
      });
    }
  ]
});