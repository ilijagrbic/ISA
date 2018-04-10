angular.module('app')
    .controller('adminMovieViewController', function ($scope, $state, $stateParams, movieShowService, uploadService) {
    	movieShowService.getMovieShow($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.curentMovie = info.data;
				},
				function(){
					$scope.curentMovie = null;
				}
		)
		
		$scope.backToParentMovie = function(){
			goBack();
		}
		
		goBack = function(){
			if($stateParams.cinType=='CINNEMA'){
				$state.go("adminEditPlace", {cinemaTheatreId: $stateParams.cinemaId});
			}else if($stateParams.cinType=='THEATRE'){
				$state.go("adminEditPlace", {cinemaTheatreId: $stateParams.cinemaId});
			}else{
				$state.go("home");
			}	
		}
    })