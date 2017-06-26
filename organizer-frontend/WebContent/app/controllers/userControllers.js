(function() {

	var usersModule = angular.module('userControllers', [ 'ngRoute' ]);

	usersModule.config(function($routeProvider) {
		$routeProvider.when('/users', {
			templateUrl : 'app/views/user/user-list.html',
			controller : 'AllUsersController',
			controllerAs : "allUsersCtrl"
		}).when('/user/:id', {
			templateUrl : 'app/views/user/user-details.html',
			controller : 'UserController',
			controllerAs : "userCtrl"
		})

	});

	usersModule.controller('AllUsersController', [ '$scope', 'UserService',
			function($scope, UserService) {
				$scope.users = [];
				var promise = UserService.GetAll();
				promise.then(
					function (data) {
						$scope.users = data;
                    },
					function (data, status, header, config) {
						alert(status);
                    }
				)

			} ]);

	usersModule.controller('UserController', [ '$scope', '$routeParams', 'UserService',
		function($scope, $routeParams, UserService) {
				var id = $routeParams.id;
				var promise = UserService.GetById(id);
				$scope.user = null;
				promise.then(
					function(data) {
						$scope.user = data;
				}, function (data, status, header, config) {
						alert(status);
                    }
				)
			} ]);


})();
