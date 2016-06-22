bookishApp.factory('userService', ['$http', '$q', function($http, $q) {
    return {
        create: function(user) {
            var defered = $q.defer();

            $http.post('/users', user).success(function(data) {
                defered.resolve(data);
            }).error(function(data) {
                defered.reject(data);
            });

            return defered.promise;
        }
    }
}]);