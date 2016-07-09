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
            var url = '/favorites?email=' + current + '&bookId=' + bookId;
            var defered = $q.defer();

            $http.post(url).success(function (data) {
                defered.resolve(data);
            }).error(function (data) {
                defered.reject(data);
            });

            return defered.promise;
        }
    }
}]);