/**
 * Created by radu on 27.06.2017.
 */
(function () {

    var eventServiceModule = angular.module('eventService', []);

    eventServiceModule.factory('EventService', ['$http', 'config',
        function ($http, config) {

            var service = {}

            service.getAllByUserId = getAllByUserId;
            service.getAllByGoalId = getAllByGoalId;
            service.getById = getById;
            service.insertEvent = insertEvent;
            service.updateEvent = updateEvent;
            service.deleteEvent = deleteEvent;

            return service;

            function getAllByUserId(id) {
                return $http.get(config.API_URL + '/events/byUser/' + id).then(handleSuccess, handleError("Error fetching events by user_id"));
            }
            function getAllByGoalId(id) {
                return $http.get(config.API_URL + '/events/byGoal/' + id).then(handleSuccess,handleError("Error fetching events by goal_id"));
            }

            function getById(id) {
                return $http.get(config.API_URL + '/events/' + id).then(handleSuccess, handleError("Error fetching events"));
            }

            function insertEvent(event) {
                return $http.post(config.API_URL + '/events/', event).then(handleSuccess, handleError("Error inserting new event"));
            }

            function updateEvent(id, event) {
                return $http.put(config.API_URL + '/events/' + id, event).then(handleSuccess, handleError("Error updating event"));
            }

            function deleteEvent(id) {
                return $http.delete(config.API_URL + '/events/' + id).then(handleSuccess, handleError("Error deleting event"));
            }


            function handleSuccess(result) {
                return result.data;
            }

            function handleError(error) {
                return function () {
                    return {success: false, message : error };
                }
            }
        }
    ]);

})();