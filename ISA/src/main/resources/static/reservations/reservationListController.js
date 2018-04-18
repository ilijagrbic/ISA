angular.module('app').controller(
		'reservationListController',
		function($rootScope, $scope, $state, reservationsService) {
			reservationsService.getReservations($rootScope.USER.id, function(res) {
				$scope.reservationList = res.data;
				console.log(JSON.stringify(res.data));
			}, function(res) {
				alert("Error - nije mogao da pronadje rezervacije");
			});
			
			
			$scope.odustani = function(idReservation, $index){
				
				$scope.reservationList = [];
				reservationsService.cancelReservation($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
					console.log(JSON.stringify(res.data));
				}, function(res) {
					alert("Error - nije mogao da pronadje rezervacije");
				});
				
			}
			
			$scope.cancelReservation = function(idReservation, $index){
				
				$scope.reservationList = [];
				reservationsService.getReservations($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
					console.log(JSON.stringify(res.data));
				}, function(res) {
					alert("Error - nije mogao da pronadje rezervacije");
				});
				
			}
			
			$scope.potvrdi = function(idReservation, $index){
				$scope.reservationList = [];
				reservationsService.acceptReservation($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
					console.log(JSON.stringify(res.data));
				}, function(res) {
					alert("Error - nije mogao da pronadje rezervacije");
				});
				
			}

});
		