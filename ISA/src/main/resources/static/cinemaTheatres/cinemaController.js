angular.module('app')
    .controller('cinemaController', function ($scope,$rootScope,  $sce, cinemaTheatreService, reservationService) {
    	if($rootScope.USER != null){
    		$scope.korisnik=true;
    	}
    	
    	$scope.reserveOneClick = function(rez){
    		reservationService.putReservation(
    					{
    				        "userId":$rootScope.USER.id,
    				        "status":"ACCEPTED",
    				        "ocenaFilm":0,
    				        "ocenaAmbijent":0,
    				        "isHost":true,
    				        "hostId":$rootScope.USER.id
    	    			},rez.id,
    				function(info){
    					for(i=0;i<$scope.oneClick.length;i++){
    						if($scope.oneClick[i].id==info.data.id){
								$scope.oneClick.splice(i,1);
							}
    					}
    				},
    				function(){
    					
    				}
    		)
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