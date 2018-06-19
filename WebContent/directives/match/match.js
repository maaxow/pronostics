
angular.module('pronostic.directives.match', [])
	.directive("pronosticMatch",[function(){
			return {
				templateUrl : 'directives/match/match.html',
				scope: {
					game: '=',
					isProno: '=?'
				},
				controller : ['$scope', function($scope){
					$scope.isProno = $scope.isProno || false;
						console.log($scope.game);
						console.log($scope.isProno);
					}]
			}
}]);
