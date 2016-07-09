bookishApp.factory('favoriteService', ['$http', '$q', function($http, $q) {
    return {
        fetchAll: function (current, page) {
            var defered = $q.defer();

            $http.get('/favorites?email='+current).success(function (data) {
                defered.resolve(data);
            }).error(function (data) {
                defered.reject(data);
            });

            return defered.promise;
        },

        save: function(current, bookId) {
            var defered = $q.defer();

            $http.post('/favorites?email='+current+'&bookId='+ bookId).success(function (data) {
                defered.resolve(data);
            }).error(function (data) {
                defered.reject(data);
            });

            return defered.promise;
        }
    }
}]);