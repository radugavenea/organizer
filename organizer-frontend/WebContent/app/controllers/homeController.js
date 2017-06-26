/**
 * Created by radu on 26.06.2017.
 */
(function () {

    var homeModule = angular.module('homeController', ['ngRoute']);

    homeModule.config(function ($routeProvider) {
        $routeProvider.when('/home', {
            templateUrl: 'app/views/home/homeview.html',
            controller: 'HomeController',
            controllerAs: 'homeCtrl'
        })
    });

    homeModule.controller('HomeController', ['$rootScope', '$location',
        function ($rootScope, $location) {
            var self = this;

            self.logout = logout;

            function logout() {
                // new AuthenticationService.clearCredentials();   // there are cleared on login page initialization
                console.log($rootScope.globals);
                $location.path('/login');
            }
        }]
    );

})();