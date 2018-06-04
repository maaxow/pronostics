(function(){
	'use strict';
	var directivesModule = angular.module('pronostic.directives.toolbar', [])
			.directive("pronosticToolbar",[function(){
				return {
					templateUrl : 'directives/toolbar/toolbar.html',
					controller : ['$scope',
							function($scope){
						$scope.message = "Test directive toolbar";
					}]
				}
			}]);
	return directivesModule;
});