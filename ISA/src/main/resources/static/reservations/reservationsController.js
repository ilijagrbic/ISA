angular.module('app').controller(
		'reservationsController',
		function($rootScope, $scope, $state, reservationsService, cinemaTheatreService, movieShowService, projekcijeService, friendshipService, reservationService) {
			
			
			$scope.odustanak = function(){
				console.log("Odustanak");
				$state.go('user');
			};
			$scope.zaDalje=true;
			$scope.odabranaProjekcija = false;
			$scope.sakrijTabeluIPretragu = false;
			$scope.pozivPrijatelja = true;
			$scope.numRez=0;
			$scope.existsInRez = function(sed){
				if(sed!=undefined){
					if($scope.rezervacijeUtrenutnoSelektovanojProjekciji!=undefined){
		    		for(i=0;i<$scope.rezervacijeUtrenutnoSelektovanojProjekciji.length;i++){
		    			if(sed.id==$scope.rezervacijeUtrenutnoSelektovanojProjekciji[i].rezervisanoMesto.id){
		    				return false;
		    			}
		    		}
		    		return true;
					}
				}

	    	}
			$scope.izabranaSedista=false;
			var projection;
			$scope.rez=[];
			//console.log($scope.searchByName);
			// Ovaj deo treba da se proveri
			if($scope.searchByName==null){
				//console.log("Prazna pretraga");
				cinemaTheatreService.getAll(
						function(res){//succes function
							$scope.cinemasAndTheathres = res.data; 
						},
						function(res){//fail function
							alert("Greska prilikom ucitavanja bioskopa i pozorista.");
							
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
									alert("Greska prilikom ucitavanja bioskopa i pozorista.");
									
								}
						);
					}
				};
				
				
				$scope.details = function(cinemaTheatre, index) {
					$scope.numRez=0;
					$scope.sakrijTabeluIPretragu = true;
					var prijatelji=[];
					var friendsPush=[];
					var selektovane = [];
					cinemaTheatreService.getCinemaTheatreById(index,
							function(res){
								$scope.choosed = res.data; 
							},
							function(res){
								alert("Greska prilikom ucitavanja detalja.");
								
							}
					);
					
					movieShowService.getMoviesShow(index,
							function(res){//succes function
						$scope.movies = res.data; 
					},
					function(res){//fail function
						alert("Greska prilikom izlistavanja filmova.");
						
					}
					);
					
					
				
					
					$scope.listProjection = function(idMovie){
						$scope.list="izlistaj";
						projekcijeService.getProjekcijeInMovie(index,idMovie,
								function(res){//succes function
							$scope.projekcije = res.data; 
						},
						function(res){//fail function
							alert("Greska prilikom izlistavanja projekcija");
							
						}
					);}
					
					$scope.chooseProjection = function(projekcija, idProjekcije){
						$scope.zaDalje = false;
						$scope.pozivPrijatelja = true; // Ne znma
						$scope.odabranaProjekcija = true;
						//alert("Bira projekciju");
						projection = projekcija; // Projekcija odabrana
						projekcijeService.getProjekcija(idProjekcije,
								function(res){//succes function
									$scope.choosedProjection = res.data; 
									reservationService.getAllInProj(
											idProjekcije,
											function(info){
												$scope.rezervacijeUtrenutnoSelektovanojProjekciji = info.data;
											},
											function(){
												
											}
									);
								},
								function(res){//fail function
									alert("Greska prilikom biranja projekcije.");
									
								}
						);
						
					};
					
					$scope.selektovani = function(){
						$scope.pozivPrijatelja = false;
						$scope.izabranaSedista=true;
						
						$scope.numRez=0;
						selektovane=[]; // sedista selektovana - value mozda da promenim
						
						$('input[type=checkbox]').each(function () {
							    if(this.checked==true){
							    	selektovane.push(this.getAttribute("value"));
							    	$scope.numRez = $scope.numRez + 1;
							    }
							});
						
						if($scope.numRez!=0){
							$scope.zaDalje = false;
							//$scope.pozivPrijatelja=true; //
						}
						else{
							$scope.pozivPrijatelja=true;
							$scope.greska==true;
						}
						
						friendshipService.friends($rootScope.USER.id, function(res) {
							$scope.callFriends = res.data;
						}, function(res) {
							alert("Greska pri pronalasku prijatelja.");
						});
						//console.log(selektovane + "broj rezervacija " +$scope.numRez);
					};
					
					friendshipService.friends($rootScope.USER.id, function(res) {
						$scope.callFriends = res.data;
					}, function(res) {
						alert("Greska pri pronalasku prijatelja.");
					});
					
					
					$scope.posaljiPoziv = function (selectedFriend){
						//console.log("Pozivamo prijatelje");
						var postoji = false;
						if(prijatelji.length==0 || prijatelji==undefined ){
							$scope.numRez = $scope.numRez - 1 ;
							prijatelji.push(selectedFriend);
							friendsPush.push(selectedFriend.name);
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
								friendsPush.push(selectedFriend.name);
								//console.log(JSON.stringify(prijatelji));
								
							}
						}
					
						
					};
					
					$scope.date = function(date){
			    		var dat = new Date(date);
			    		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
			    	}
					
					$scope.rezervacija = function(){
						// Selektovane - sedista 
						
						var host = {
								"projekcija" : projection,
								"idRezervant" : $rootScope.USER.id,
								"isHost" : true,
								"rezSedisteId" : selektovane[0],
								"idHost" :  $rootScope.USER.id,
								"movie" : $rootScope.selectedMovie
								//"guests" : friendsPush,
						}
						
						reservationsService.reservate(host,
							function(res){
							
								// Rezervacija uspesna
							},
							function(res){
								alert("Greska prilikom rezervacije.");
							
							}
						);
						zauzetaOdStranePrijatelja = [];
						if(prijatelji.length!=0){
							for (i = 0; i < prijatelji.length; i++) {
								
								console.log("USLO U PRIJATELJE " + JSON.stringify(prijatelji) + " selektovane " + selektovane[i+1]);
								sediste = selektovane[i+1];
								zauzetaOdStranePrijatelja.push(sediste);
								var poziv = {
										"projekcija" : projection,
										"idRezervant" : prijatelji[i],
										"isHost" : false,
										"rezSedisteId" : selektovane[i+1],
										"idHost" :  $rootScope.USER.id,
										"movie" : $rootScope.selectedMovie
										//"guests" : ''
								}
								
								reservationsService.reservate(poziv,
									function(res){
										// Rezervacija uspesna
										console.log("Od prijatelja " + sediste);
										
									},
									function(res){
										alert("Greska prilikom rezervacije.");
									
									}
								);
							}
						}
						else{
							for (j = 1; j < selektovane.length; j++) {
									console.log("Nedefinisani");
									var host = {
											"projekcija" : projection,
											"idRezervant" : $rootScope.USER.id,
											"isHost" : true,
											"rezSedisteId" : selektovane[j],
											"idHost" :  $rootScope.USER.id,
											"movie" : $rootScope.selectedMovie
											//"guests" : ''
									}
									
									reservationsService.reservate(host,
										function(res){
										},
										function(res){
											alert("Greska prilikom rezervacije.");
										
										}
									);
									//break;
								}
							
						}
						
						console.log("Zauzete od stane prijatelja: " + JSON.stringify(zauzetaOdStranePrijatelja));
						
						if(zauzetaOdStranePrijatelja.length!=0){
						if(zauzetaOdStranePrijatelja.length!=selektovane.length-1){
							console.log("Imamo visak sedista");
							
							for (j = 1; j < selektovane.length; j++) {
								
								
									if(!zauzetaOdStranePrijatelja.includes(selektovane[j])){
										
										var host = {
												"projekcija" : projection,
												"idRezervant" : $rootScope.USER.id,
												"isHost" : true,
												"rezSedisteId" : selektovane[j],
												"idHost" :  $rootScope.USER.id,
												"movie" : $rootScope.selectedMovie
												//"guests" : ''
										}
										
										reservationsService.reservate(host,
											function(res){
											},
											function(res){
												alert("Greska prilikom rezervacije");
											
											}
										);
									}
									
								}
								
						}
							
							
							
						}
						
					$state.go('listOfReservations');
					}

				
			
			
				};
			
		});