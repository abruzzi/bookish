bookishApp.component('signUp', {
  templateUrl: 'js/templates/sign-up-template.html',
  controller: ['$routeParams', 'userService',
    function SignUpController($routeParams, userService) {
      this.user = {
        email: '',
        password: ''
      };

      this.signUp = function () {
        userService.create(this.user).then(function() {
          console.log('sign up success');
        });
      };

    }
  ]
});