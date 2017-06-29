/**
 * Created by radu on 27.06.2017.
 */
(function () {

    var eventModule = angular.module('eventController', ['ngRoute']);

    eventModule.controller('EventController', ['$scope', '$rootScope', '$location', 'EventService',
        function ($scope, $rootScope, $location, EventService) {
            var self = this;
            self.event = {id:null,name:'',startDate:'',endDate:'', remainderDate:'', note:'',
                goalId:'', userId:''};
            self.events = [];

            self.submit = submit;
            self.edit = edit;
            self.remove = remove;
            self.reset = reset;
            self.goToGoals = goToGoals;
            self.display = display;

            fetchAllEvents();

            function fetchAllEvents(){
                // console.log($rootScope.globals);    //test
                EventService.getAllByUserId($rootScope.globals.currentUser.id)
                    .then(
                        function(d) {
                            self.events = d;
                        },
                        function(errResponse){
                            console.error('Error while fetching Goals');
                        }
                    );
            };

            function createEvent(event){
                EventService.insertEvent(event)
                    .then(
                        fetchAllEvents,
                        function(errResponse){
                            console.error('Error while creating Goal');
                        }
                    );
            }

            function updateEvent(id,event){
                EventService.updateEvent(id, event)
                    .then(
                        fetchAllEvents,
                        function(errResponse){
                            console.error('Error while updating Goal');
                        }
                    );
            }

            function deleteEvent(id){
                EventService.deleteEvent(id)
                    .then(
                        fetchAllEvents,
                        function(errResponse){
                            console.error('Error while deleting Goal');
                        }
                    );
            }

            function submit() {
                self.event.goalId = $rootScope.global.goalModel;
                if(self.event.id===null){
                    console.log('Saving New Event', self.event);
                    createEvent(self.event);
                }else{
                    updateEvent(self.event.id, self.event);
                    console.log('Event updated with id ', self.event.id);
                }
                reset();
            }

            function edit(id){
                console.log('id to be edited', id);
                for(var i = 0; i < self.events.length; i++){
                    if(self.events[i].id === id) {
                        self.event = angular.copy(self.events[i]);
                        $rootScope.global.goalModel = self.event.goalId;
                        break;
                    }
                }
            }

            function remove(id){
                // console.log('id to be deleted', id);
                if(self.event.id === id) {//clean form if the Event to be deleted is shown there.
                    reset();
                }
                deleteEvent(id);
            }

            function reset(){
                self.event = {id:null,name:'',startDate:'',endDate:'', remainderDate:'', note:'',
                    goalId:'', userId:''};
                $scope.myForm.$setPristine(); //reset Form
            }

            function goToGoals(){
                // console.log($rootScope.globals);    //test
                $location.path('/home/goals');
            }

            function display(data) {
                console.log(data);
                // console.log(data.goals);
                console.log($rootScope.global);
            }

        }
    ]);

})();