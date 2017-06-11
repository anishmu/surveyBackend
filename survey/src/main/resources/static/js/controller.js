var myApp = angular.module('myapp',[]);

myApp.controller('MyController', function MyController($scope, $http) {
	
	$scope.surveys = [];
    $scope.form = {
                    id : -1,
                    SurveyName : "",
                    SurveyDescription : ""
                };	
	_refreshPageData();
	
	$scope.submitSurvey = function() {
	
		var method = "";
		var url = "";
		if ($scope.form.id == -1) {
			//Id is absent so add employee - POST operation
			method = "POST";
			url = '/survey/addSurvey';
		} //else {
			//If Id is present, it's edit operation - PUT operation
			//method = "PUT";
			//url = '/survey/addSurvey/' + $scope.form.id;
		//}
		var datatosend = {
				id: -1,
				surveyName: $scope.form.SurveyName,
				surveyDescription: $scope.form.SurveyDescription
		};
		datatosend = JSON.stringify(datatosend);
        $http({
                method : method,
                url : url,
                data : datatosend,
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then( _success, _error );
        };
		
        //HTTP DELETE- delete Survey by Id
        $scope.removeSurvey = function(item) {
            $http({
                method : 'DELETE',
                url : '/survey/deleteSurvey/' + item.surveyIndex
            }).then(_success, _error);
        };		

		function _refreshPageData() {
		$http({
			method : 'GET',
			url : '/survey/retrieveAllSurvey'
		}).then(function successCallback(response) {
			$scope.surveys = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
		};		
		
        function _success(response) {
                    _refreshPageData();
					_clearForm();
           };
           function _error(response) {
                    console.log(response.statusText);
           };
        //Clear the form
        function _clearForm() {
            $scope.form.SurveyName = "";
            $scope.form.SurveyDescription = "";
            $scope.form.id = -1;
			$scope.form.$setUntouched();
			$scope.form.$setPristine();
        };		   
	
});