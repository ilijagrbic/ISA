angular.module('app')
    .controller('adminMovieEditController', function ($scope, $state, $stateParams, movieShowService, uploadService) {
		movieShowService.getMovieShow($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.curentMovie = info.data;
				},
				function(){
					$scope.curentMovie = null;
				}
		)
		
		$scope.commitChanges = function(){
			uploadService.postImage($scope.myFile, 
					function (response) {
			            $scope.imgPath = response.data.message;
			            getMovieDTO();
			            movieShowService.putMovieShow($stateParams.cinemaId, $scope.curentMovie.id, getMovieDTO(),
			    				function(info){
			            			goBack();
			    				},
			    				function(){
			    					
			    				}
			    			);
			              
		          }, 
		          function () {
		        	  console.log("upload error")
		          });
		}
		
		$scope.backToParentMovie = function(){
			goBack();
		}
		
		getMovieDTO = function(param){
			return {
				"id": $scope.curentMovie.id,
				"name": $scope.curentMovie.name,
				"description": $scope.curentMovie.description,
				"price": $scope.curentMovie.price,
				"genre": $scope.curentMovie.genre,
				"director":$scope.curentMovie.director,
				"duration":$scope.curentMovie.duration,
				"type":$scope.curentMovie.type,
				"cinnemaId":$stateParams.cinemaId,
				"image":$scope.imgPath
			}
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