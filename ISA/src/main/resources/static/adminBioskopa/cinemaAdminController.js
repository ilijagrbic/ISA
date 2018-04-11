angular.module('app')
    .controller('cinemaAdminController', function ($scope, $state, $sce, cinemaTheatreService) {
		cinemaTheatreService.getCinemas(
				function(info){//succes function
					$scope.cinemas=info.data;
					
				},
				function(info){//fail function
					$scope.cinemas=[];
					
				}
		);
		
		$scope.editPlace = function(id){
			$state.go("adminEditPlace", {cinemaTheatreId: id});
			
		}
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });