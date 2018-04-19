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
    	
    	projekcijeService.getProjekcija($stateParams.projId,
    			function(info){
    				$scope.curentlyEditedProj = info.data;
    			},
    			function(){
    				
    			}
    	)
    	
    	salaService.getSaleByCinnema($stateParams.cinemaId,
				function(info){
					$scope.listSale = info.data;
				},
				function(){
		
				}
			)
		
		reservationService.getAllInProj($stateParams.projId,
				function(info){
					$scope.rezervacije = info.data;
				},
				function(){
					
				}
		)
			    	
    	$scope.opdNewSalaEditing=true;
    	
    	$scope.backFromSeats = function(){
    		$state.go("adminEditMovie", {movieId: $stateParams.movieId, cinemaId: $stateParams.cinemaId, cinType: $stateParams.cinType});
    	}
    	
    	$scope.dontShowOldSalaEditing = function(){
    		$scope.opdNewSalaEditing=!$scope.opdNewSalaEditing;
    	}
    	
    	$scope.saveSeats = function(){
    		var datumVreme = new Date($scope.curentlyEditedProj.date.getFullYear(), $scope.curentlyEditedProj.date.getMonth(), $scope.curentlyEditedProj.date.getDate(), $scope.fff.getHours(), $scope.fff.getMinutes(), 0, 0);
    		var DTO = {
					"date": datumVreme,//$scope.curentlyEditedProj.date,
					"sala":$scope.curentlyEditedProj.sala,
					"cena":$scope.curentlyEditedProj.cena,
					"film":$stateParams.movieId,
					"sedista":$scope.curentlyEditedProj.sedista
				}
    		projekcijeService.putProjekcija($scope.curentlyEditedProj.id, DTO,
    				function(info){
    					$state.go("adminEditMovie", {movieId: $stateParams.movieId, cinemaId: $stateParams.cinemaId, cinType: $stateParams.cinType});
    				},
    				function(){
    					
    				}
    		)
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
    				function(){
    					
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