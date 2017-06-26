(function() {

	var homeModule = angular.module('navControllers', [ 'ngRoute' ]);

	homeModule.config(function($routeProvider) {
		$routeProvider.when('/home', {
			templateUrl : 'app/views/homeview.html',
			controller : 'HomeController',
			controllerAs : "homeCtrl"
		})
	});

	homeModule.controller('HomeController', function($scope) {
		$scope.message = "Message from the controller...";
	});

})();