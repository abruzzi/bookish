bookishApp.factory('bookService', ['$http', '$q', function($http, $q) {
    return {
        fetchAll: function() {
            var defered = $q.defer();

            $http.get('/books').success(function(data) {
                defered.resolve(data);
            }).error(function(data) {
                defered.reject(data);
            });

            return defered.promise;
        },

        fetchOne: function(id) {
            var defered = $q.defer();

            $http.get('/books/'+id).success(function(data) {
                defered.resolve(data);
            }).error(function(data) {
                defered.reject(data);
            });

            return defered.promise;
        }
    }
}]);