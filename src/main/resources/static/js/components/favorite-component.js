bookishApp.component('favorite', {
    templateUrl: 'js/templates/favorite-template.html',
    controller: ['$routeParams', '$rootScope', '$location', 'favoriteService', 'localStorageService',
        function FavoritesController($routeParams, $rootScope, $location, favoriteService, localStorageService) {
            this.books = [];
            var session = localStorageService.get('session');

            var self = this;
            favoriteService.fetchAll(session.current.email).then(function(favorites) {
                self.books = favorites;
            });

            self.deleteFromFavorites = function(id) {
                favoriteService.delete(session.current.email, id).then(function() {
                    favoriteService.fetchAll(session.current.email).then(function(favorites) {
                        self.books = favorites;
                    });
                });
            };
        }
    ]
});