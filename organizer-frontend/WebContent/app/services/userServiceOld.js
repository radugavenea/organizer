(function() {
	var userServices = angular.module('userServices', []);

	userServices.factory('UserFactory', [ '$http', 'config',
			function($http, config) {

				var privateUserDetails = function(id) {
					return $http.get(config.API_URL + '/user/details/' + id);
				};

				var privateUserList = function() {
					return $http.get(config.API_URL + '/user/all');
				};

				return {
					findById : function(id) {
						return privateUserDetails(id);
					},

					 findAll : function() {
						return privateUserList();
					}
				};
			} ]);

})();