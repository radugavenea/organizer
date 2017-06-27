/**
 * Created by radu on 26.06.2017.
 */
(function () {

    var goalServiceModule = angular.module('goalService', []);

    goalServiceModule.factory('GoalService', ['$http', 'config',
        function ($http, config) {

            var service = {}

            service.getAllByUserId = getAllByUserId;
            service.insertGoal = insertGoal;
            service.updateGoal = updateGoal;
            service.deleteGoal = deleteGoal;

            return service;

            function getAllByUserId(id) {
                return $http.get(config.API_URL + '/goals/' + id).then(handleSuccess, handleError('Error getting all goals'));
            }

            function insertGoal(goal) {
                return $http.post(config.API_URL + '/goals/', goal).then(handleSuccess, handleError('Error creating goal'));
            }

            function updateGoal(goal, id) {
                return $http.put(config.API_URL + '/goals/' + id, goal).then(handleSuccess, handleError('Error updating goal'));
            }

            function deleteGoal(id) {
                return $http.delete(config.API_URL + '/goals/' + id).then(handleSuccess, handleError('Error deleting goal'));
            }

            // private functions

            function handleSuccess(res) {
                return res.data;
            }

            function handleError(error) {
                return function () {
                    return { success: false, message: error };
                };
            }
        }]
    );
})();