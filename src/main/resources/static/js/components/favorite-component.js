bookishApp.component('favorite', {
    templateUrl: 'js/templates/favorite-template.html',
    controller: ['$routeParams', '$rootScope', '$location', 'favoriteService', 'localStorageService',
        function FavoritesController($routeParams, $rootScope, $location, favoriteService, localStorageService) {
            this.books = [];
            var session = localStorageService.get('session');

            var self = this;

            function refreshFavorites() {
                favoriteService.fetchAll(session.current.email).then(function (favorites) {
                    self.books = favorites;
                });
            }

            refreshFavorites();

            self.deleteFromFavorites = function(id) {
                favoriteService.delete(session.current.email, id).then(function() {
                    toastr.options.positionClass = 'toast-top-right';
                    toastr.success("Book has been deleted from favorites");

                    refreshFavorites();
                });
            };
        }
    ]
});