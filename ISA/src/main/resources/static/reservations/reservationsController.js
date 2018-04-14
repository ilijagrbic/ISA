angular.module('app').controller(
		'reservationsController',
		function($rootScope, $scope, $state, reservationsService) {
			$scope.search = function(){
				console.log("Pretraga po " + $scope.searchByName);
				reservationsService.findByName($scope.searchByName, function(res) {
							console.log(JSON.stringify(res.data));
								$scope.cinemasAndTheaters = res.data; 
							
						}, function(res) {
							console.log("Error neki kod pretrage");
						});
			};
			
			
		});