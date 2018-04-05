angular.module('app')
    .controller('adminEditPlaceController', function ($scope, $state, $stateParams, cinemaTheatreService, movieShowService) {
		cinemaTheatreService.getCinemaTheatreById($stateParams.cinemaTheatreId,
				function(info){//succes function
					$scope.curentCinemaTheatre=info.data;
					
				},
				function(info){//fail function
					$scope.curentCinemaTheatre=null;
					
				}
		);
		
		$scope.backToTheatres = function(){
			if($scope.curentCinemaTheatre.type=='CINNEMA'){
				$state.go("cinemasAdmin");
			}else if($scope.curentCinemaTheatre.type=='THEATRE'){
				$state.go("theatresAdmin");
			}else{
				$state.go("home");
			}
			
		}
		
    });