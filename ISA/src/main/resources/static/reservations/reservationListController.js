angular.module('app').controller(
		'reservationListController',
		function($rootScope, $scope, $state, reservationsService) {
			reservationsService.getReservations($rootScope.USER.id, function(res) {
				$scope.reservationList = res.data;
				console.log(JSON.stringify(res.data));
			}, function(res) {
				alert("Error - nije mogao da pronadje rezervacije");
			});

});
		