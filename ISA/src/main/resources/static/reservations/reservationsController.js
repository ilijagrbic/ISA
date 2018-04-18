angular.module('app').controller(
		'reservationsController',
		function($rootScope, $scope, $state, reservationsService, cinemaTheatreService, movieShowService, projekcijeService, friendshipService) {
			//console.log("Rezervacije");
			$scope.izabranaSedista=false;
			//console.log($scope.searchByName);
			// Ovaj deo treba da se proveri
			if($scope.searchByName==null){
				//console.log("Prazna pretraga");
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
					//console.log("U search " + $scope.searchByName);
					//console.log("Pretraga po " + $scope.searchByName);
					if($scope.searchByName!=""){
					reservationsService.findByName($scope.searchByName, function(res) {
								//console.log(JSON.stringify(res.data));
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
					$scope.numRez=0;
					var prijatelji=[];
					var selektovane = [];
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
					
					$scope.selektovani = function(){
						$scope.izabranaSedista=true;
						$scope.numRez=0;
						selektovane=[];
						
						$('input[type=checkbox]').each(function () {
							    if(this.checked==true){
							    	selektovane.push(this.getAttribute("value"));
							    	$scope.numRez = $scope.numRez + 1;
							    }
							});
						
						if($scope.numRez!=0){
							$scope.pozivPrijatelja=true;
						}
						else{
							$scope.greska==true;
						}
						
						friendshipService.friends($rootScope.USER.id, function(res) {
							$scope.callFriends = res.data;
						}, function(res) {
							alert("Error - nije mogao da pronadje prijatelje");
						});
						//console.log(selektovane + "broj rezervacija " +$scope.numRez);
					};
					
					
					$scope.posaljiPoziv = function (selectedFriend){
						//console.log("Pozivamo prijatelje");
						var postoji = false;
						if(prijatelji.length==0 || prijatelji==undefined ){
							$scope.numRez = $scope.numRez - 1 ;
							prijatelji.push(selectedFriend); 
							//console.log("Prazan");
						}
						else{
							
							var i=0;
							for (i = 0; i < prijatelji.length; i++) {
								//console.log(selectedFriend + " " +  prijatelji[i]);
								if(prijatelji[i]==selectedFriend){
									//console.log("Prijatelj je vec pozvan");
									postoji = true;
									break;
								}
							}
							if(postoji==false){
								$scope.numRez = $scope.numRez - 1 ;
								prijatelji.push(selectedFriend);
								//console.log(JSON.stringify(prijatelji));
								
							}
						}
					
						
					};
					
					
					
					
		
				};
				
				/// getProjekcijeInMovie
			
			
			
		});