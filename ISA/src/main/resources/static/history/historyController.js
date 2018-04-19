angular.module('app').controller(
		'historyController',
		function($rootScope, $scope, $state, reservationsService) {		
			
				
				reservationsService.getHistory($rootScope.USER.id,
							function(res){//succes function
								$scope.cinemasAndTheathres = res.data; 
							},
							function(res){//fail function
								alert("Eror prilikom ucitavanja bioskopa i pozorista");
								
							}
					);
					
		});