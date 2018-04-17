angular.module('app').controller(
		'reservationsController',
		function($rootScope, $scope, $state, reservationsService, cinemaTheatreService, movieShowService, projekcijeService) {
			console.log("Rezervacije");
			console.log($scope.searchByName);
			// Ovaj deo treba da se proveri
			if($scope.searchByName==null){
				console.log("Prazna pretraga");
				cinemaTheatreService.getAll(
						function(res){//succes function
							$scope.cinemasAndTheathres = res.data; 
						},
						function(res){//fail function
							alert("Eror prilikom ucitavanja bioskopa i pozorista");
							
						}
				);
			}
			
				$scope.search = function(){
					console.log("U search " + $scope.searchByName);
					console.log("Pretraga po " + $scope.searchByName);
					if($scope.searchByName!=""){
					reservationsService.findByName($scope.searchByName, function(res) {
								console.log(JSON.stringify(res.data));
									$scope.cinemasAndTheathres = res.data; 
								
							}, function(res) {
								console.log("Error neki kod pretrage");
							});
					}
					else{
						cinemaTheatreService.getAll(
								function(res){//succes function
									$scope.cinemasAndTheathres = res.data; 
								},
								function(res){//fail function
									alert("Eror prilikom ucitavanja bioskopa i pozorista");
									
								}
						);
					}
				};
				
				
				$scope.details = function(cinemaTheatre, index) {
					cinemaTheatreService.getCinemaTheatreById(index,
							function(res){
								$scope.choosed = res.data; 
							},
							function(res){
								alert("Eror detalja o bioskopu ili pozoristu");
								
							}
					);
					
					movieShowService.getMoviesShow(index,
							function(res){//succes function
						$scope.movies = res.data; 
					},
					function(res){//fail function
						alert("Eror izlistavanje filmova");
						
					}
					);
					
					$scope.listProjection = function(idMovie){
						$scope.list="izlistaj";
						projekcijeService.getProjekcijeInMovie(index,idMovie,
								function(res){//succes function
							$scope.projekcije = res.data; 
						},
						function(res){//fail function
							alert("Eror izlistavanje projekcija");
							
						}
					);}
					
					$scope.chooseProjection = function(projekcija, idProjekcije){					
						//alert("Bira projekciju");
						projekcijeService.getProjekcija(idProjekcije,
								function(res){//succes function
									$scope.choosedProjection = res.data; 
								},
								function(res){//fail function
									alert("Eror biranja projekcije");
									
								}
						);
						
					};
			
					
					
				};
				
				/// getProjekcijeInMovie
			
			
			
		});