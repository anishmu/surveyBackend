var app = angular.module("myApp", []);
angular.module('myApp', []).controller('pagecontroller', function($scope) {
     $scope.data = [{
        description: 'Survey1'
    }, {
        description: 'Survey2'
	}, {
		description : 'Survey3'
	}, {
		description : 'Survey4'
    }]
});