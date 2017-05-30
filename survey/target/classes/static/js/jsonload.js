var app = angular.module('myApp', []);
app.controller('pagecontroller', function($scope, $http) {
    $http.get('/survey/retrieveAllSurvey').success(function (data){
		$scope.database = data;
	});
});