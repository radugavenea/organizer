/**
 * Created by radu on 26.06.2017.
 */
(function () {

    var homeModule = angular.module('homeController', ['ngRoute']);

    homeModule.config(function ($routeProvider) {
        $routeProvider.when('/home/goals', {
            templateUrl: 'app/views/home/goals.html',
            controller: 'HomeController',
            controllerAs: 'homeCtrl'
        }).when('/home/events',{
            templateUrl: 'app/views/home/events.html',
            controller: 'EventController',
            controllerAs: 'eventCtrl'
        })
    });

    homeModule.controller('HomeController', ['$rootScope', '$location', 'HomeService',
        function ($rootScope, $location, HomeService) {
            var self = this;

            self.logout = logout;
            self.generateReport = generateReport;

            function logout() {
                // new AuthenticationService.clearCredentials();   // there are cleared on login page initialization
                console.log($rootScope.globals);
                $location.path('/login');
            }

            function generateReport() {
                HomeService.generatePdf();
            }

        }]
    );

})();