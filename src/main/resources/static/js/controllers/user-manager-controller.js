bookishApp.controller('userManagerController', ['$rootScope', '$location', 'userService', 'localStorageService',
    function($rootScope, $location, userService, localStorageService) {
        var session = localStorageService.get("session");
        if(session) {
            $rootScope.authenticated = session.authenticated;
            $rootScope.current = session.current;
        }

        $rootScope.$on('LocalStorageModule.notification.setitem', function (key, newVal) {
            if(newVal.key === "session") {
                var session = JSON.parse(newVal.newvalue);
                $rootScope.authenticated = session.authenticated;
                $rootScope.current = session.current;
            }
        });

        $rootScope.$on('LocalStorageModule.notification.removeitem', function (key, newVal) {
            if(newVal.key === "session") {
                $rootScope.authenticated = false;
                $rootScope.current = null;
            }
        });

        var self = this;
        self.logout = function() {
            userService.logout($rootScope.current).then(function() {
                localStorageService.remove("session");
                $location.path('/signin');
            });
        }
}]);