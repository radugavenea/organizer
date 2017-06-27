(function() {
	var app = angular.module('angularjs-demo', [
		'ngRoute', 'configModule', 'ngCookies', 'ui.bootstrap',
        'loginController',
        'authenticateService',
        'flashService',
        'userControllers',
		'userService',
        'homeController',
		'goalController',
		'goalService',
        'eventController',
        'eventService'
    ])
})();