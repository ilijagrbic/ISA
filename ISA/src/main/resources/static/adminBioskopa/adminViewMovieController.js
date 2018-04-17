angular.module('app')
    .controller('adminMovieViewController', function ($scope, $state, $stateParams, movieShowService, uploadService, projekcijeService, salaService, reportService) {
    	movieShowService.getMovieShow($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.curentMovie = info.data;
				},
				function(){
					$scope.curentMovie = null;
				}
		)
		
		projekcijeService.getProjekcijeInMovie($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.listProjekcije = info.data;
				},
				function(){
					
				}
		)
		
		salaService.getSaleByMovie($stateParams.movieId,
				function(info){
					$scope.listSale = info.data;
				},
				function(){
					
				}
		)
		
		reportService.getMovieReport($stateParams.movieId,
					function(info){
						$scope.ocenaMovie=info.data.avgOcena;	
					},
					function(){
						
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