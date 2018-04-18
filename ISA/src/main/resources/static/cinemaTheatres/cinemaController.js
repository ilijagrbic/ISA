angular.module('app')
    .controller('cinemaController', function ($scope,$rootScope,  $sce, cinemaTheatreService) {
    	if($rootScope.USER != null){
    		$scope.korisnik=true;
    	}
    
    	cinemaTheatreService.getCinemas(
				function(info){//succes function
					$scope.cinemas=info.data;
					
				},
				function(info){//fail function
					$scope.cinemas=[];
					
				}
		);
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });