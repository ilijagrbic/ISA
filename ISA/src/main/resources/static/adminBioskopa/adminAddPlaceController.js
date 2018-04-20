angular.module('app')
    .controller('adminAddPlaceController', function ($scope, fanZoneService, cinemaTheatreService) {
    	
    	cinemaTheatreService.getAll(
				function(info){//succes function
					$scope.ustanove=info.data;
					console.log(info.data);
					
				},
				function(info){//fail function
					$scope.ustanove=[];
					
				}
		);

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