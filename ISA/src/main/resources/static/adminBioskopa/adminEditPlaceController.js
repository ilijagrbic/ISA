angular.module('app')
    .controller('adminEditPlaceController', function ($scope, $state, $stateParams, cinemaTheatreService, movieShowService) {
		/*
		 * Utility functions
		 */
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
		getMovieDTO = function(param){
			return {
				"name": $scope.enterMoviename,
				"description": $scope.enterMoviedescription,
				"price": $scope.enterMovieprice,
				"genre": $scope.enterMoviegenre,
				"director":$scope.enterMoviedirector,
				"duration":$scope.enterMovieduration,
				"type":$scope.enterMovietype,
				"cinnemaId":$stateParams.cinemaTheatreId
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
		loadData = function(){
			cinemaTheatreService.getCinemaTheatreById($stateParams.cinemaTheatreId,
					function(info){//succes function
						$scope.curentCinemaTheatre=info.data;				
					},
					function(){//fail function
						$scope.curentCinemaTheatre=null;
						
					}
			);
		}
		/*
		 * Utility functions
		 */
		loadData();
		$scope.enterMovie = false;
		
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

		$scope.editMovie = function(movie){
			$state.go("adminEditMovie", {cinemaId: $scope.curentCinemaTheatre.id, movieId: movie.id, cinType: $scope.curentCinemaTheatre.type})
		}
		
		$scope.newMovie = function(){
			$scope.enterMovieState = true;
		}
		
		$scope.cancelEntery = function(){
			$scope.enterMovieState = false;
		}
		
		$scope.saveEntery = function(){
			movieShowService.postMovieShow($scope.curentCinemaTheatre.id, getMovieDTO(),
				function(info){
					$scope.curentCinemaTheatre.repertoire.movies.splice($scope.curentCinemaTheatre.repertoire.movies.length, "0", info.data);
				},
				function(){
					
				}
			);
		}
		
		
		
    });