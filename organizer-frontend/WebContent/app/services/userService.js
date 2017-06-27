/**
 * Created by radu on 25.06.2017.
 */
(function() {

    var userModule = angular.module('userService', []);

    userModule.factory('UserService', ['$http', 'config',
        function ($http, config) {

            var service = {};

            service.getAll = getAll;
            service.getById = getById;
            service.getByUsername = getByUsername;
            service.insertUser = insertUser;
            service.updateUser = updateUser;
            service.deleteUser = deleteUser;

            return service;

            function getAll() {
                return $http.get(config.API_URL + '/user/all').then(handleSuccess, handleError('Error getting all users'));
            }

            function getById(id) {
                return $http.get(config.API_URL + '/user/details/' + id).then(handleSuccess, handleError('Error getting user by id'));
            }

            function getByUsername(username) {
                return $http.get(config.API_URL + '/user/details/' + username).then(handleSuccess, handleError('Error getting user by username'));
            }

            function insertUser(user) {
                return $http.post(config.API_URL + '/user/', user).then(handleSuccess, handleError('Error creating user'));
            }

            function updateUser(user, id) {
                return $http.put(config.API_URL + '/user/' + id, user).then(handleSuccess, handleError('Error updating user'));
            }

            function deleteUser(id) {
                return $http.delete(config.API_URL + '/user/' + id).then(handleSuccess, handleError('Error deleting user'));
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
    }]);


})();