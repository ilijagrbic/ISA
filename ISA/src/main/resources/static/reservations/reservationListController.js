angular.module('app').controller(
		'reservationListController',
		function($rootScope, $scope, $state, reservationsService, reservationService) {
			
			if ($rootScope.USER != null){
			$scope.showOceniPanel=true;
			$scope.showOceni = function(res){
				$scope.showOceniPanel=false;
				$scope.rateingReservation = res;
			}

			$scope.date = function(date){
	    		var dat = new Date(date);
	    		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
	    	}
			$scope.commitRate = function(){

	    		reservationService.rateReservation(
	    					{
	    				        "status":"WATCHED",
	    				        "ocenaFilm":$scope.ambijent,
	    				        "ocenaAmbijent":$scope.ocena,
	    	    			},$scope.rateingReservation.id,
	    				function(info){
	    	    				for(i = 0;i<$scope.reservationList.length; i++){
	    	    					if($scope.reservationList[i].id==$scope.rateingReservation.id){
	    	    						$scope.reservationList[i].ocenaAmbijent=info.data.ocenaAmbijent;
	    	    						$scope.reservationList[i].ocenaFilm=info.data.ocenaFilm;
	    	    					}
	    	    				}
	    	    				
	    	    			},
	    				function(info){
	    	    				alert(info.data.err);
	    				}
	    		)
			}
			reservationsService.getReservations($rootScope.USER.id, function(res) {
				$scope.reservationList = res.data;
			}, function(res) {
				alert("Error - nije mogao da pronadje rezervacije");
			});
			
			
			$scope.odustani = function(idReservation, $index){
				
				$scope.reservationList = [];
				reservationsService.cancelReservation($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
				}, function(res) {
					alert("Error - nije mogao da pronadje rezervacije");
				});
				
			}
			
			$scope.cancelReservation = function(idReservation, $index){
				
				$scope.reservationList = [];
				reservationsService.getReservations($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
				}, function(res) {
					alert("Error - nije mogao da pronadje rezervacije");
				});
				
			}
			
			$scope.potvrdi = function(idReservation, $index){
				$scope.reservationList = [];
				reservationsService.acceptReservation($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
				}, function(res) {
					alert("Error - nije mogao da pronadje rezervacije");
				});
				
			}
			}
			else{
				$state.go('signin');
			}

});
		