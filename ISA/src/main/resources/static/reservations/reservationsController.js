angular.module('app').controller(
		'reservationsController',
		function($rootScope, $scope, $state, reservationsService, cinemaTheatreService) {
			if($scope.searchByName=""){
				cinemaTheatreService.getAll(
						function(res){//succes function
							$scope.cinemasAndTheathres = res.data; 
						},
						function(res){//fail function
							alert("Eror prilikom ucitavanja bioskopa i pozorista");
							
						}
				);
			}
			else{
				$scope.search = function(){
					console.log("Pretraga po " + $scope.searchByName);
					reservationsService.findByName($scope.searchByName, function(res) {
								console.log(JSON.stringify(res.data));
									$scope.cinemasAndTheathres = res.data; 
								
							}, function(res) {
								console.log("Error neki kod pretrage");
							});
				};
			}
			
			
		});