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
			goBack();
		}
		
		$scope.commitChanges = function(){
			cinemaTheatreService.setCinemaTheatreById($stateParams.cinemaTheatreId, getCinemaDTO($scope.curentCinemaTheatre),
					function(info){
						goBack();
					},
					function(){
						
					}
			)
			
			
		}
		
		getCinemaDTO = function(param){
			return {
				"id": param.id,
				"name": param.name,
				"description": param.description,
				"address": param.address,
				"type": param.type,
				"bronzeTreshold":param.bronzeTreshold,
				"silverTreshold":param.silverTreshold,
				"goldTreshold":param.goldTreshold
			}
		}
		goBack = function(){
			if($scope.curentCinemaTheatre.type=='CINNEMA'){
				$state.go("cinemasAdmin");
			}else if($scope.curentCinemaTheatre.type=='THEATRE'){
				$state.go("theatresAdmin");
			}else{
				$state.go("home");
			}	
		}
		
    });