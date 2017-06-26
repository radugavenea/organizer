(function() {
	var app = angular.module('angularjs-demo', [ 'ngRoute', 'configModule', 'ngCookies',
		'navControllers', 'userControllers' ,
		// 'userServices', 'loginControllers', 'loginServices',
		'loginController',
		'userService',
		'authenticateService',
		'flashService'
	])
})();