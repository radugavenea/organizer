(function() {

	var homeModule = angular.module('navControllers', [ 'ngRoute' ]);

	homeModule.config(function($routeProvider) {
		$routeProvider.when('/home', {
			templateUrl : 'app/views/home/homeview.html',
			controller : 'HomeController2',
			controllerAs : "homeCtrl2"
		})
	});

	homeModule.controller('HomeController2', function($scope) {
		$scope.message = "Message from the controller...";
	});

})();