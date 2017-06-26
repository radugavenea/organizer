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
        }).when('/home',{
            templateUrl : 'app/views/homeview.html',
            controller : 'HomeController',
            controllerAs : 'homeCtrl'
        }).otherwise({redirectTo : '/login'})
    });

    loginUserModule.controller('LoginController', ['$rootScope', '$location', 'AuthenticationService', 'FlashService',
        function ($rootScope, $location, AuthenticationService, FlashService) {
            var vm = this;

            vm.login = login;

            (function initController() {
                // reset login status
                AuthenticationService.ClearCredentials();
            })();

            function login() {
                vm.dataLoading = true;
                AuthenticationService.Login(vm.username, vm.password, function (response) {
                    if (response.name) {
                        AuthenticationService.SetCredentials(vm.username, vm.password);
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