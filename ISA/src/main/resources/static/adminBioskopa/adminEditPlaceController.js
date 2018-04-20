angular.module('app')
    .controller('adminEditPlaceController', function ($scope, $state, $stateParams, cinemaTheatreService, movieShowService, uploadService, reservationService, projekcijeService, reportService) {
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
				"goldTreshold":param.goldTreshold,
				"bronzeSale":param.bronzeSale,
				"silverSale":param.silverSale,
				"goldSale":param.goldSale,
				"gmaps":param.gmapsUrl
			}
		}
		getMovieDTO = function(param){
			return {
				"name": $scope.enterMoviename,
				"description": $scope.enterMoviedescription,
				"price": $scope.enterMovieprice,
				"genre": $scope.enterMoviegenre,
				"glumci":[],
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
					function(info){//fail function
						$scope.curentCinemaTheatre=null;
						alert(info.data.err);
					}
			);
			reservationService.getOneClick($stateParams.cinemaTheatreId,
					function(info){
						$scope.oneClick=info.data;	
					},
					function(info){
						alert(info.data.err);
					}
			);
			reportService.getAmbijentReport($stateParams.cinemaTheatreId,
					function(info){
						$scope.ambijentOcena=info.data.avgOcena;	
					},
					function(info){
						alert(info.data.err);
					})
			
		}
		
		
		
		/*
		 * Utility functions
		 */
		loadData();
		var something = this;
		$scope.enterMovie = false;
		$scope.types = ["MOVIE", "PERFORMANCE"];
		$scope.showOneClick = false;
		$scope.izvestajiShow =false;
		$scope.reportIncome = 0;
		
		$scope.showOneClickLabel = function(){
			if(!$scope.showOneClick){
				return "Prikazi brze rezervacije"
			}else{
				return "Sakriji brze rezervacije"
			}
		}
		
		$scope.showIzvestajiButtonData = function(){
			if(!$scope.izvestajiShow){
				return "Prikazi izvestaje"
			}else{
				return "Sakriji izvestaje"
			}
		}
		
		var ctx = document.getElementById('myChart').getContext('2d');
		var chart = new Chart(ctx, {
		    // The type of chart we want to create
		    type: 'line',

		    // The data for our dataset
		    data: {
		        labels: [],
		        datasets: [{
		            label: "Posecenost bioskopa",
		            backgroundColor: 'rgb(255, 99, 132)',
		            borderColor: 'rgb(255, 99, 132)',
		            data: [0],
		        }]
		    },

		    // Configuration options go here
		    options: {}
		});
		
		$scope.showPosete = function(od, doo){
			reportService.getPosete(
					{
						"od":od,
						"doo":doo
					},
					$stateParams.cinemaTheatreId,
					function(info){
						$scope.poseteInfo=info.data;
						
						var ctx = document.getElementById('myChart').getContext('2d');
						var chart = new Chart(ctx, {
						    // The type of chart we want to create
						    type: 'line',

						    // The data for our dataset
						    data: {
						        labels: [],
						        datasets: [{
						            label: "Posecenost bioskopa",
						            backgroundColor: 'rgb(255, 99, 132)',
						            borderColor: 'rgb(255, 99, 132)',
						            data: getNumbers($scope.poseteInfo),
						        }]
						    },

						    // Configuration options go here
						    options: {}
						});
						
					},
					function(info){
						alert(info.data.err);
						
					})

		
		}
		
		getNumbers = function(niz){
			var nov =[];
			for(i=0;i<niz.length;i++){
				nov.splice(nov.length, 0, niz[i].value)
			}return nov;
		}
		
		$scope.showIncome = function(od, doo){
			reportService.getIncomeReport(
				{
					"od":od,
					"doo":doo
				},
				$stateParams.cinemaTheatreId,
				function(info){
					$scope.reportIncome=info.data.avgOcena;
				},
				function(info){
					alert(info.data.err);
					
				})
		}
		
		$scope.showReports = function(){
			$scope.izvestajiShow = !$scope.izvestajiShow;
		}
		
		$scope.deleteOneClickReservation = function(par){
			reservationService.deleteReservation(par,
					function(info){
						for(i=0;i<$scope.oneClick.length;i++){
							if($scope.oneClick[i].id==info.data.id){
								$scope.oneClick.splice(i,1);
							}
						}
					},
					function(info){
						alert(info.data.err);
					}
			)
		}
		
		$scope.showHideOneClick = function(){
			$scope.showOneClick = !$scope.showOneClick ;
		}
		
		
		$scope.backToTheatres = function(){
			goBack();
		}
		
		$scope.commitChanges = function(){
			cinemaTheatreService.setCinemaTheatreById($stateParams.cinemaTheatreId, getCinemaDTO($scope.curentCinemaTheatre),
					function(info){
						goBack();
					},
					function(info){
						if(info.data.exception=="org.springframework.http.converter.HttpMessageNotReadableException"){
    						alert("Greska u unosu!")
    					}else{
    						alert(info.data.err);
    					}
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
					alert(info.data.err);
				}
			)
		}
		
		$scope.viewMovie = function(movie){
			$state.go("adminViewMovie", {cinemaId: $scope.curentCinemaTheatre.id, movieId: movie.id, cinType: $scope.curentCinemaTheatre.type});
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
		    				function(info){
		    					if(info.data.exception=="org.springframework.http.converter.HttpMessageNotReadableException"){
		    						alert("Greska u unosu!")
		    					}else{
		    						alert(info.data.err);
		    					}
		    				}
		    			);
		              
	          }, 
	          function (response) {
	        	  $scope.imgPath = "";
		            getMovieDTO();
		            movieShowService.postMovieShow($scope.curentCinemaTheatre.id, getMovieDTO(),
		    				function(info){
		    					$scope.curentCinemaTheatre.repertoire.movies.splice($scope.curentCinemaTheatre.repertoire.movies.length, "0", info.data);
		    				},
		    				function(info){
		    					alert(info.data.err);
		    				}
		    			);
	          });
		}
		
		/*$scope.findMovie = function(id){
			projekcijeService.getParentId(id, 
					function(info){
						for(i=0;i<$scope.curentCinemaTheatre.repertoire.movies.length;i++){
							if($scope.curentCinemaTheatre.repertoire.movies[i].id==info.data){
								return $scope.curentCinemaTheatre.repertoire.movies[i].name;
							}
						}
					},
					function(){
						
					}
			)
			
		}*/
		
		
		
		/**/
		$scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function() {
    $scope.dt = null;
  };

  $scope.inlineOptions = {
    customClass: getDayClass,
    minDate: new Date(),
    showWeeks: true
  };

  $scope.dateOptions = {
    dateDisabled: disabled,
    formatYear: 'yy',
    maxDate: new Date(2020, 5, 22),
    minDate: new Date(),
    startingDay: 1
  };

  // Disable weekend selection
  function disabled(data) {
    var date = data.date,
      mode = data.mode;
    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  }

  $scope.toggleMin = function() {
    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
  };

  $scope.toggleMin();

  $scope.open1 = function() {
    $scope.popup1.opened = true;
  };

  $scope.open2 = function() {
    $scope.popup2.opened = true;
  };

  $scope.setDate = function(year, month, day) {
    $scope.dt = new Date(year, month, day);
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];
  $scope.altInputFormats = ['M!/d!/yyyy'];

  $scope.popup1 = {
    opened: false
  };

  $scope.popup2 = {
    opened: false
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date();
  afterTomorrow.setDate(tomorrow.getDate() + 1);
  $scope.events = [
    {
      date: tomorrow,
      status: 'full'
    },
    {
      date: afterTomorrow,
      status: 'partially'
    }
  ];

  function getDayClass(data) {
    var date = data.date,
      mode = data.mode;
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i = 0; i < $scope.events.length; i++) {
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  }
		 /**/
  $scope.date = function(date){
		var dat = new Date(date);
		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
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