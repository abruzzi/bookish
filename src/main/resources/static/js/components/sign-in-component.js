bookishApp.component('signIn', {
  templateUrl: 'js/templates/sign-in-template.html',
  controller: ['$routeParams', '$rootScope', '$location', 'userService',
    function SignInController($routeParams, $rootScope, $location, userService) {
      this.user = {
        email: '',
        password: ''
      };

      this.signIn = function () {
        userService.login(this.user).then(function(user) {
          $rootScope.authenticated = true;
          $rootScope.current = user;
          $location.path('/books');
        });
      };

    }
  ]
});