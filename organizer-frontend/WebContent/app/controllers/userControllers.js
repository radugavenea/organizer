(function() {

	var usersModule = angular.module('userControllers', [ 'ngRoute' ]);

	usersModule.config(function($routeProvider) {
		$routeProvider.when('/users', {
			templateUrl : 'app/views/user/user-list.html',
			controller : 'AllUsersController',
			controllerAs : "allUsersCtrl"
		}).when('/user/:id', {
			templateUrl : 'app/views/user/user-details.html',
			controller : 'UserController',
			controllerAs : "userCtrl"
		})

	});

	usersModule.controller('AllUsersController', [ '$scope', 'UserService',
			function($scope, UserService) {
				$scope.users = [];
				var promise = UserService.GetAll();
				promise.then(
					function (data) {
						$scope.users = data;
                    },
					function (data, status, header, config) {
						alert(status);
                    }
				)

			} ]);

	usersModule.controller('UserController', [ '$scope', '$routeParams', 'UserService',
		function($scope, $routeParams, UserService) {

            var self = this;
            self.user = {id:null,email:'',password:'',name:'',role:''};
            self.users = [];

            self.submit = submit;
            self.edit = edit;
            self.remove = remove;
            self.reset = reset;


            fetchAllUsers();

            function fetchAllUsers(){
                UserService.GetAll()
                    .then(
                        function(d) {
                            self.users = d;
                        },
                        function(errResponse){
                            console.error('Error while fetching Users');
                        }
                    );
            }

            function createUser(user){
                UserService.Create(user)
                    .then(
                        fetchAllUsers,
                        function(errResponse){
                            console.error('Error while creating User');
                        }
                    );
            }

            function updateUser(user, id){
                UserService.Update(user, id)
                    .then(
                        fetchAllUsers,
                        function(errResponse){
                            console.error('Error while updating User');
                        }
                    );
            }

            function deleteUser(id){
                UserService.Delete(id)
                    .then(
                        fetchAllUsers,
                        function(errResponse){
                            console.error('Error while deleting User');
                        }
                    );
            }

            function submit() {
                if(self.user.id===null){
                    console.log('Saving New User', self.user);
                    createUser(self.user);
                }else{
                    updateUser(self.user, self.user.id);
                    console.log('User updated with id ', self.user.id);
                }
                reset();
            }

			function edit(id){
				console.log('id to be edited', id);
				for(var i = 0; i < self.users.length; i++){
					if(self.users[i].id === id) {
						self.user = angular.copy(self.users[i]);
						break;
					}
				}
			}

			function remove(id){
				console.log('id to be deleted', id);
				if(self.user.id === id) {//clean form if the user to be deleted is shown there.
					reset();
				}
				deleteUser(id);
			}

			function reset(){
                self.user = {id:null,email:'',password:'',name:'',role:''};
                $scope.myForm.$setPristine(); //reset Form
            }

		} ]);




})();
