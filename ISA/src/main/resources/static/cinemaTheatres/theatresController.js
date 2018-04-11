angular.module('app')
    .controller('theatresController', function ($scope, $sce, cinemaTheatreService) {
    	cinemaTheatreService.getTheatres(
				function(info){//succes function
					$scope.theatres=info.data;
					
				},
				function(info){//fail function
					$scope.theatres=[];
					
				}
		);
    	
    	$scope.trustSrc = function(url){
    		return $sce.trustAsResourceUrl(url);
		}
    });