(function() {
	var app = angular.module('angularjs-demo', [
		'ngRoute', 'configModule', 'ngCookies', 'ui.bootstrap',
        // 'navControllers',
		'userControllers',
		// 'userServices',
		'loginController',
		'userService',
		'authenticateService',
		'flashService',
        'homeController'
    ])
})();