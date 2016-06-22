bookishApp.controller('userManagerController', ['$rootScope', '$location', 'userService',
    function($rootScope, $location, userService) {

        var self = this;
        self.logout = function() {
            userService.logout($rootScope.current).then(function() {
                $rootScope.authenticated = false;
                $location.path('/signin');
            });
        }
}]);