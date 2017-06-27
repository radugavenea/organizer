/**
 * Created by radu on 26.06.2017.
 */
(function () {
    
    var goalModule = angular.module('goalController', ['ngRoute']);

    goalModule.controller("GoalController", ['$scope', '$rootScope', '$location','GoalService',
        function ($scope, $rootScope, $location, GoalService) {
            var self = this;
            self.goal = {id:null,name:'',description:'',actionPlan:'', progress:'', example:'',
                userId:'',totalBudget:'', availableBudget:''};
            self.goals = [];

            self.display = display;
            self.submit = submit;
            self.edit = edit;
            self.remove = remove;
            self.reset = reset;

            fetchAllGoals();

            function fetchAllGoals(){
                // console.log($rootScope.globals);    //test
                GoalService.getAllByUserId($rootScope.globals.currentUser.id)
                    .then(
                        function(d) {
                            self.goals = d;
                        },
                        function(errResponse){
                            console.error('Error while fetching Goals');
                        }
                    );
            };

            function createGoal(goal){
                GoalService.insertGoal(goal)
                    .then(
                        fetchAllGoals,
                        function(errResponse){
                            console.error('Error while creating Goal');
                        }
                    );
            }

            function updateGoal(goal, id){
                GoalService.updateGoal(goal, id)
                    .then(
                        fetchAllGoals,
                        function(errResponse){
                            console.error('Error while updating Goal');
                        }
                    );
            }

            function deleteGoal(id){
                GoalService.deleteGoal(id)
                    .then(
                        fetchAllGoals,
                        function(errResponse){
                            console.error('Error while deleting Goal');
                        }
                    );
            }

            function submit() {
                if(self.goal.id===null){
                    console.log('Saving New Goal', self.goal);
                    createGoal(self.goal);
                }else{
                    updateGoal(self.goal, self.goal.id);
                    console.log('Goal updated with id ', self.goal.id);
                }
                reset();
            }

            function edit(id){
                console.log('id to be edited', id);
                for(var i = 0; i < self.goals.length; i++){
                    if(self.goals[i].id === id) {
                        self.goal = angular.copy(self.goals[i]);
                        break;
                    }
                }
            }

            function remove(id){
                console.log('id to be deleted', id);
                if(self.goal.id === id) {//clean form if the Goal to be deleted is shown there.
                    reset();
                }
                deleteGoal(id);
            }

            function reset(){
                self.goal = {id:null,name:'',description:'',actionPlan:'', progress:'', example:'',
                    userId:'',totalBudget:'', availableBudget:''};
                $scope.myForm.$setPristine(); //reset Form
            }

            function display(goal) {
                // console.log(goal);  // test
                $scope.goal = goal;
            }

        }
    ]);
    
})();