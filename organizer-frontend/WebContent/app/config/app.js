(function() {
	var app = angular.module('angularjs-demo', [
		'ngRoute', 'configModule', 'ngCookies', 'ui.bootstrap',
        // 'navControllers',
        'loginController',
        'authenticateService',
        'flashService',
        'userControllers',
		'userService',
        'homeController',
		'goalController',
		'goalService'
    ])
})();