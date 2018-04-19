angular.module('app')
    .controller('adminMovieViewController', function ($scope, $state, $stateParams, movieShowService, uploadService, projekcijeService, salaService, reportService) {
    	
    	$scope.date = function(date){
    		var dat = new Date(date);
    		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
    	}
    	
    	movieShowService.getMovieShow($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.curentMovie = info.data;
				},
				function(info){
					$scope.curentMovie = null;
					alert(info.data.err);
				}
		)
		
		projekcijeService.getProjekcijeInMovie($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.listProjekcije = info.data;
				},
				function(info){
					alert(info.data.err);
				}
		)
		
		salaService.getSaleByMovie($stateParams.movieId,
				function(info){
					$scope.listSale = info.data;
				},
				function(info){
					alert(info.data.err);
				}
		)
		
		reportService.getMovieReport($stateParams.movieId,
					function(info){
						$scope.ocenaMovie=info.data.avgOcena;	
					},
					function(info){
						alert(info.data.err);
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