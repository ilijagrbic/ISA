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
	    	    				if(info.data.err!=undefined){
	    							alert(info.data.err);
	    						}else{
	    							alert("Doslo je do greske.");
	    						}
	    				}
	    		)
			}
			reservationsService.getReservations($rootScope.USER.id, function(res) {
				$scope.reservationList = res.data;
			}, function(res) {
				alert("Greska prilikom ucitavanja rezervacija.");
			});
			
			
			$scope.odustani = function(idReservation, $index){
				
				
				reservationsService.cancelReservation($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = [];
					$scope.reservationList = res.data;
				}, function(res) {
					alert(res.data.err);
				});
				
			}
			
			$scope.cancelReservation = function(idReservation, $index){
				
				$scope.reservationList = [];
				reservationsService.getReservations($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
				}, function(res) {
					alert("Greska prilikom ucitavanja rezervacija.");
				});
				
			}
			
			$scope.potvrdi = function(idReservation, $index){
				$scope.reservationList = [];
				reservationsService.acceptReservation($rootScope.USER.id,idReservation, function(res) {
					$scope.reservationList = res.data;
				}, function(res) {
					alert("Greska prilikom prihvatanja rezervacije.");
				});
				
			}
			}
			else{
				$state.go('signin');
			}

});
		