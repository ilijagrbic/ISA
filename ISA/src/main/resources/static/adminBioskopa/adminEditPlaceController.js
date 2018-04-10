angular.module('app')
    .controller('adminEditPlaceController', function ($scope, $state, $stateParams, cinemaTheatreService, movieShowService, uploadService) {
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
				"cinnemaId":$stateParams.cinemaTheatreId,
				"image":$scope.imgPath
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
		var something = this;
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
		
		$scope.deleteMovie = function(movieId){
			movieShowService.deleteMovieShow(movieId,
				function(info){
					for(var i=0;i<$scope.curentCinemaTheatre.repertoire.movies.length;i++){
						if($scope.curentCinemaTheatre.repertoire.movies[i].id==info.data){
							$scope.curentCinemaTheatre.repertoire.movies.splice(i,1);
						}
					}
				},
				function(){
					
				}
			)
		}
		
		$scope.newMovie = function(){
			$scope.enterMovieState = true;
		}
		
		$scope.cancelEntery = function(){
			$scope.enterMovieState = false;
		}
		
		$scope.saveEntery = function(){
			uploadService.postImage($scope.myFile, 
				function (response) {
		            $scope.imgPath = response.data.message;
		            getMovieDTO();
		            movieShowService.postMovieShow($scope.curentCinemaTheatre.id, getMovieDTO(),
		    				function(info){
		    					$scope.curentCinemaTheatre.repertoire.movies.splice($scope.curentCinemaTheatre.repertoire.movies.length, "0", info.data);
		    				},
		    				function(){
		    					
		    				}
		    			);
		              
	          }, 
	          function () {
	        	  console.log("upload error")
	          });
		}
		
		
		
    }).directive('fileUpload', fileUpload);

    fileUpload.$inject = ['$parse'];

    function fileUpload($parse) {
      var directive = {
        restrict: 'A',
        link: function (scope, element, attrs) {
          var model = $parse(attrs.fileUpload);
          var modelSetter = model.assign;

          element.bind('change', function (event) {
            scope.$apply(function () {
              scope.myFile = event.target.files[0];
              event.preventDefault();
            });
          });

        }
      };
      return directive;

    }