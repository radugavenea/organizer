/**
 * Created by radu on 29.06.2017.
 */
(function () {
    var homeServiceModule = angular.module('homeService', []);

    homeServiceModule.factory('HomeService', ['$http', 'config',
        function ($http, config) {

            var service = {};

            service.generatePdf = generatePdf;

            return service;

            function generatePdf() {
                return $http.post(config.API_URL + '/reports').then(handleSuccess, handleError('Error'));
            }


            function handleSuccess(res) {
                return res.data;
            }

            function handleError(error) {
                return function () {
                    return { success: false, message: error };
                };
            }
        }
    ]);
})();