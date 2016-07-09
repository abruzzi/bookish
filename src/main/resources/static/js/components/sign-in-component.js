bookishApp.component('signIn', {
  templateUrl: 'js/templates/sign-in-template.html',
  controller: ['$routeParams', '$rootScope', '$location', 'userService', 'localStorageService',
    function SignInController($routeParams, $rootScope, $location, userService, localStorageService) {
      this.user = {
        email: '',
        password: ''
      };

      this.signIn = function () {
        userService.login(this.user).then(function(user) {
          localStorageService.set('session', {
            authenticated: true,
            current: user
          });

          $location.path('/books');
        });
      };

    }
  ]
});