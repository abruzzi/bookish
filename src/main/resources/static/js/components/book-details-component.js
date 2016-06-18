bookishApp.component('bookDetails', {
  templateUrl: 'js/templates/book-details-template.html',
  controller: ['$routeParams', 'bookService',
    function BookListController($routeParams, bookService) {
      var self = this;
      bookService.fetchOne($routeParams.bookId).then(function(book) {
        self.book = book;
        self.book.imageLink = '/images/actuals/' + self.book.asin + '.jpg';
      });
    }
  ]
});