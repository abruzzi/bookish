bookishApp.factory('favoriteService', ['$http', '$q', function($http, $q) {
    return {
        fetchAll: function (current, page) {
            var defered = $q.defer();

            $http.get('/favorites?user='+current).success(function (data) {
                defered.resolve(data);
            }).error(function (data) {
                defered.reject(data);
            });

            return defered.promise;
        }
    }
}]);