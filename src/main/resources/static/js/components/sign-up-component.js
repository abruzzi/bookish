bookishApp.component('signUp', {
  templateUrl: 'js/templates/sign-up-template.html',
  controller: ['$routeParams', '$location', 'userService',
    function SignUpController($routeParams, $location, userService) {
      this.user = {
        email: '',
        password: ''
      };

      this.signUp = function () {
        userService.create(this.user).then(function() {
          $location.path('/signin');
        });
      };

    }
  ]
});