
angular.module('pronostic.directives.toolbar', ['pronostic.services.login'])
	.directive("pronosticToolbar",['$timeout', function($timeout){
			return {
				templateUrl : 'directives/toolbar/toolbar.html',
				controller : ['$scope', '$login', '$state',
					function($scope, $login, $state){
						$scope.$login = $login;
						
						$scope.user = {};
						$scope.logout = function(){
							$login.logout();
						};
						
						$scope.isLoginPage = function(){
							return $state.is('home.login');
						};
						
						$scope.isLogged = function(){
							if($login.isLogged()){
								$scope.user = $login.getUserLogged();
								return true;
							}
							return false;
						};
						
					}]
			}
}]);
