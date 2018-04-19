angular.module('app')
    .controller('theatresController', function ($scope, $rootScope, $sce, cinemaTheatreService) {
    	if($rootScope.USER != null){
    		$scope.korisnik=true;
    	}
    	
    	
    	$scope.date = function(date){
    		var dat = new Date(date);
    		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
    	}
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