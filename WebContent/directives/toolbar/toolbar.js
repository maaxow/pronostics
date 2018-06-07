
angular.module('pronostic.directives.toolbar', [])
	.directive("pronosticToolbar",[function(){
			return {
				templateUrl : 'directives/toolbar/toolbar.html',
				controller : toolbarController
			}
}]);

function toolbarController($scope){
	$scope.message = "Test directive toolbar";
}
