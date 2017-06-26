/**
 * Created by radu on 25.06.2017.
 */
(function() {

    var userModule = angular.module('userService', []);

    userModule.factory('UserService', ['$http', 'config',
        function ($http, config) {

            var service = {};

            service.GetAll = GetAll;
            service.GetById = GetById;
            service.GetByUsername = GetByUsername;
            service.Create = Create;
            service.Update = Update;
            service.Delete = Delete;

            return service;

            function GetAll() {
                return $http.get(config.API_URL + '/user/all').then(handleSuccess, handleError('Error getting all users'));
            }

            function GetById(id) {
                return $http.get(config.API_URL + '/user/details/' + id).then(handleSuccess, handleError('Error getting user by id'));
            }

            function GetByUsername(username) {
                return $http.get(config.API_URL + '/user/details/' + username).then(handleSuccess, handleError('Error getting user by username'));
            }

            function Create(user) {
                return $http.post(config.API_URL + '/user/add', user).then(handleSuccess, handleError('Error creating user'));
            }

            function Update(user) {
                return $http.put(config.API_URL + '/user/edit' + user.id, user).then(handleSuccess, handleError('Error updating user'));
            }

            function Delete(id) {
                return $http.delete(config.API_URL + '/user/delete' + id).then(handleSuccess, handleError('Error deleting user'));
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