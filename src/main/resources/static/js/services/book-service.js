bookishApp.factory('bookService', ['$http', '$q', function($http, $q) {
    return {
        fetchAll: function(page) {
            var defered = $q.defer();

            $http.get('/books?page='+(page || 0)).success(function(data) {
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