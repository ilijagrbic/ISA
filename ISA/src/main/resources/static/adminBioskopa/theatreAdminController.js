angular.module('app')
    .controller('theatreAdminController', function ($scope, $state, $sce, cinemaTheatreService) {
		cinemaTheatreService.getTheatres(
				function(info){//succes function
					$scope.theatres=info.data;
					
				},
				function(info){//fail function
					$scope.theatres=[];
					
				}
		);

		$scope.editPlace = function(id){
			$state.go("adminEditPlace", {cinemaTheatreId: id});
			
		}
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });