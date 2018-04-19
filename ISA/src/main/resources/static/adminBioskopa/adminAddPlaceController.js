angular.module('app')
    .controller('adminAddPlaceController', function ($scope, fanZoneService, cinemaTheatreService) {

    	$scope.dodajUstanovu=function(){
    		console.log($scope.curentCinemaTheatre);
    		cinemaTheatreService.saveCinemaTheatre(
    				$scope.curentCinemaTheatre,
    		function(info){//succes function
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
       	
    	
    
    });