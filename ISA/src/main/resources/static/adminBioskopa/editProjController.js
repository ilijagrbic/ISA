angular.module('app')
    .controller('editProjController', function ($scope, $state, $stateParams, movieShowService, uploadService, actorService, projekcijeService, salaService) {
    	
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
    		$scope.handlingSeat = id;
    		$scope.shotSeatEdit = true;
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
    	
    })