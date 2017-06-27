/**
 * Created by radu on 25.06.2017.
 */
(function () {

    var loginUserModule = angular.module('loginController', [ 'ngRoute' ]);


    loginUserModule.config(function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl : 'app/views/login/login.html',
            controller : 'LoginController',
            controllerAs : 'vm'
        }).otherwise({redirectTo : '/login'})
    });

    loginUserModule.controller('LoginController', ['$rootScope', '$location', 'AuthenticationService', 'FlashService',
        function ($rootScope, $location, AuthenticationService, FlashService) {
            var vm = this;

            vm.login = login;

            (function initController() {
                // reset login status
                AuthenticationService.clearCredentials();
            })();

            function login() {
                vm.dataLoading = true;
                AuthenticationService.login(vm.email, vm.password, function (response) {
                    if (response) {
                        AuthenticationService.setCredentials(response.id, vm.email, vm.password,response.name,response.role);
                        if(response.role === 'admin'){
                            $location.path('/users');
                        }
                        else if(response.role === 'user'){
                            $location.path('/home');
                        }
                        else {
                            $location.path('/login')
                        }
                    } else {
                        FlashService.Error("User not found!");
                        vm.dataLoading = false;
                    }
                });
            }
        }
    ]);

})();