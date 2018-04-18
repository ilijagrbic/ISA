angular.module('app')
    .controller('cinemaController', function ($scope,$rootScope,  $sce, cinemaTheatreService, reservationService) {
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
    	$scope.showCinemas = true;
    	
    	$scope.showCinnema = function(cin){
    		if($scope.showCinemas == true){
	    		reservationService.getOneClick(
	    				cin.id,
	    				function(info){
	    					$scope.oneClick = info.data;
	    					$scope.showCinemas = false;
	    				},
	    				function(){
	    					
	    				}
	    		)
    		}else{
    			$scope.showCinemas = true;
    		}
    	}
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });