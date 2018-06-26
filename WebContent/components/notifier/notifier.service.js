angular.module('pronostic.services.notifier', [])
.service('$notifier', ['$http', '$timeout', function($http, $timeout){
	
	var alertEl = $('#notifier');
	
	var show = function(){
		alertEl.removeClass("notifier-hide");
		alertEl.addClass("notifier-show");
	},
	hide = function(){
		alertEl.removeClass("notifier-show");
		alertEl.addClass("notifier-hide");
	}
	return {
		
		success: function(message){
			alertEl[0].innerHTML = message;
			alertEl.addClass("alert-info");
			show();
			$timeout(function(){
				alertEl[0].innerHTML = "";
				alertEl.removeClass("alert-info");
				hide();
			}, 2000);
			return alertEl;
		},
		error: function(message){
			alertEl[0].innerHTML = message;
			alertEl.addClass("alert-danger");
			show();
			$timeout(function(){
				alertEl[0].innerHTML = "";
				alertEl.removeClass("alert-danger");
				hide();
			}, 2000);
			return alertEl;
		}
		
	};

}]);