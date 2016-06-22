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
        },

        login: function (user) {
            var defered = $q.defer();

            $http.post('/login', user).success(function(data) {
                defered.resolve(data);
            }).error(function(data) {
                defered.reject(data);
            });

            return defered.promise;
        },

        logout: function (user) {
            var defered = $q.defer();

            $http.post('/logout', user).success(function(data) {
                defered.resolve(data);
            }).error(function(data) {
                defered.reject(data);
            });

            return defered.promise;
        }
    }
}]);