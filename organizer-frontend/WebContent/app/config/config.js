(function() {
	var configModule = angular.module('configModule', []);

	configModule.constant('config', {
		API_URL : 'http://localhost:8080/'
	});

	configModule.constant('auth_events', {
		NOT_AUTHENTICATED: 'auth-not-authenticated'
	})

})();