bookishApp.component('bookDetails', {
  template: 'TBD',
  controller: ['$routeParams',
    function BookListController($routeParams) {
      this.bookId = $routeParams.bookId;
    }
  ]
});