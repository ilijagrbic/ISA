angular.module('app')
    .controller('editProjController', function ($scope, $state, $stateParams, movieShowService, uploadService, actorService, projekcijeService, salaService, reservationService) {
    	
    	$scope.salaPanelText = function(){
    		if($scope.opdNewSalaEditing==true){
    			return "Unesi novu salu";
    		}else{
    			return "Odaberi postojecu salu";
    		}
    	}
    	
    	$scope.date = function(date){
    		var dat = new Date(date);
    		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
    	}
    	
   	
    	rearangeSeats = function (a, vis, duz)
    	{
    	    var swapped;
    	    do {
    	        swapped = false;
    	        for (var i=0; i < a.length-1; i++) {
    	            if (sortCondition(a[i], a[i+1], vis, duz)) {
    	                var temp = a[i];
    	                a[i] = a[i+1];
    	                a[i+1] = temp;
    	                swapped = true;
    	            }
    	        }
    	    } while (swapped);
    	}
    	
    	sortCondition = function(seat1, seat2, vis, duz){
    		s1val = seat1.duzKord*vis+seat1.visKord;
    		s2val = seat2.duzKord*vis+seat2.visKord;
    		return s1val<s2val;
    	}
    	
    	projekcijeService.getProjekcija($stateParams.projId,
    			function(info){
    				$scope.curentlyEditedProj = info.data;
    				rearangeSeats($scope.curentlyEditedProj.sedista, $scope.curentlyEditedProj.sala.visina, $scope.curentlyEditedProj.sala.duzina);
    			},
    			function(info){
    				if(info.data.err!=undefined){
						alert(info.data.err);
					}else{
						alert("Doslo je do greske.");
					}
    			}
    	)
    	
    	salaService.getSaleByCinnema($stateParams.cinemaId,
				function(info){
					$scope.listSale = info.data;
				},
				function(info){
					if(info.data.err!=undefined){
						alert(info.data.err);
					}else{
						alert("Doslo je do greske.");
					}				}
			)
		
		reservationService.getAllInProj($stateParams.projId,
				function(info){
					$scope.rezervacije = info.data;
				},
				function(info){
					if(info.data.err!=undefined){
						alert(info.data.err);
					}else{
						alert("Doslo je do greske.");
					}
				}
		)
			    	
    	$scope.opdNewSalaEditing=true;
    	
    	$scope.backFromSeats = function(){
    		$state.go("adminEditMovie", {movieId: $stateParams.movieId, cinemaId: $stateParams.cinemaId, cinType: $stateParams.cinType});
    	}
    	
    	$scope.dontShowOldSalaEditing = function(){
    		$scope.opdNewSalaEditing=!$scope.opdNewSalaEditing;
    	}
    	
    	getArrSedista = function(x){
			var retVal = [];
			var defSed = {
					"visKord":0,
					"duzKord":0,
					"type":"ODRINARY",
					"deltaCena":0
			}
			for(i=0;i<x.visina;i++){
				for(j=0;j<x.duzina;j++){
					var temp = {
							"visKord":i,
							"duzKord":j,
							"type":"ODRINARY",
							"deltaCena":0
					};
					retVal.splice(retVal, "0", temp);
				}
			}
			
			return retVal;
		}
    	
    	$scope.saveSeats = function(){
    		var sala = $scope.curentlyEditedProj.sala;
    		var sedista=$scope.curentlyEditedProj.sedista;
    		if($scope.opdNewSalaEditing==false){
    			sala = {
    					"nazivBroj":	$scope.novSalanazivBroj,
    					"visina":	$scope.novSaladuzina,
    					"duzina":	$scope.novSalavisina
    			}
    			sedista = getArrSedista (sala);
    			
    		}
    		if($scope.curentlyEditedProj.date!=undefined&&$scope.fff!=undefined){
	    		var datumVreme = new Date($scope.curentlyEditedProj.date.getFullYear(), $scope.curentlyEditedProj.date.getMonth(), $scope.curentlyEditedProj.date.getDate(), $scope.fff.getHours(), $scope.fff.getMinutes(), 0, 0);
	    		var DTO = {
						"date": datumVreme,//$scope.curentlyEditedProj.date,
						"sala":sala,
						"cena":$scope.curentlyEditedProj.cena,
						"film":$stateParams.movieId,
						"sedista":sedista
					}
	    		projekcijeService.putProjekcija($scope.curentlyEditedProj.id, DTO,
	    				function(info){
	    					$state.go("adminEditMovie", {movieId: $stateParams.movieId, cinemaId: $stateParams.cinemaId, cinType: $stateParams.cinType});
	    				},
	    				function(info){
	    					if(info.data.exception=="org.springframework.http.converter.HttpMessageNotReadableException"){
	    						alert("Greska u unosu!")
	    					}else{
	    						if(info.data.err!=undefined){
	    							alert(info.data.err);
	    						}else{
	    							alert("Doslo je do greske.");
	    						}
	    					}
	    				}
	    		)
    		}
    		else{
    			alert("Unesite datum i vreme.")
    		}
    	}
    	
    	$scope.stringSala = function(x){
			return x.nazivBroj+"("+x.visina+","+x.duzina+")";
		}
    	
    	$scope.handlingSeat = null;
    	$scope.shotSeatEdit = false;
    	$scope.seatTypes = [
    		"ODRINARY",
    		"VIP",
    		"PROMOTION",
    		"CLOSED"
    	]
    	
    	$scope.handleButton = function(id){
    		if($scope.existsInRez(id)){
	    		$scope.handlingSeat = id;
	    		$scope.shotSeatEdit = true;
    		}
    	}
    	
    	$scope.updateSeat = function(ss){
    		for(i=0;i<$scope.curentlyEditedProj.sedista.length;i++){
    			if(ss.id==$scope.curentlyEditedProj.sedista[i].id){
    				$scope.curentlyEditedProj.sedista[i].type=ss.type;
    				if($scope.curentlyEditedProj.sedista[i].type=='PROMOTION'){
    					$scope.curentlyEditedProj.sedista[i].deltaCena=ss.deltaCena;
    				}
    			}
    		}
    	}
    	
    	$scope.addOneClickReserve = function(s){
    		if(!$scope.existsInRez(s)){
    			alert("vec rezervisano");
    		}else{
    			var DTO ={
					"projId":$stateParams.projId,
			        "userId":null,
			        "rezSedisteId":s.id,
			        "status":"ONECLICK",
			        "ocenaFilm":0,
			        "ocenaAmbijent":0,
			        "isHost":true,
			        "hostId":null
    			}
    			reservationService.postReservation($stateParams.projId, DTO,
    				function(info){
    					$scope.rezervacije.splice($scope.rezervacije.length, "0", info.data);
    				},
    				function(info){
    					if(info.data.exception=="org.springframework.http.converter.HttpMessageNotReadableException"){
    						alert("Greska u unosu!")
    					}else{
    						if(info.data.err!=undefined){
    							alert(info.data.err);
    						}else{
    							alert("Doslo je do greske.");
    						}
    					}
    				}
    			)
    			
    		}
    	}
    	
    	$scope.handleSeatBtn = function(sed){
    		if($scope.rezervacije!=undefined){
	    		if(!$scope.existsInRez(sed)){
	    			return "R"
	    		}else{
	    			return "NR"
	    		}
    		}else{
    			return ""
    		}
    	}
    	
    	$scope.existsInRez = function(sed){
    		for(i=0;i<$scope.rezervacije.length;i++){
    			if(sed.id==$scope.rezervacije[i].rezervisanoMesto.id){
    				return false;
    			}
    		}
    		return true;

    	}
    	
    })