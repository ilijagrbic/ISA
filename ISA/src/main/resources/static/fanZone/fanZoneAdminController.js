angular.module('app')
    .controller('fanZoneAdminController', function ($scope, fanZoneService) {
    	fanZoneService.getZvanicnaPonuda(
				function(info){//succes function
					$scope.zvanicna=info.data;
					console.log(info.data);
					
				},
				function(info){//fail function
					$scope.zvanicna=[];
					
				}
		);
    	

		fanZoneService.getOglasiZaOdobravanje(
			function(info){//succes function
				$scope.odobrenje=info.data;
				console.log($scope.odobrenje);
				
			},
			function(info){//fail function
				$scope.oglasiZaOdobravanje=[];
				
			}

			
	);
    	
    	fanZoneService.getPolovnaPonuda(
				function(info){//succes function
					$scope.polovni=info.data;
					console.log(info.data);
					
				},
				function(info){//fail function
					$scope.polovni=[];
					
				}

				
		);


    	
    	$scope.dodajZvanicniOglas=function(){
    		fanZoneService.saveZvanicni(
    				
    		$scope.novRekvizit,
    		function(info){//succes function
    			$scope.zvanicna=info.data;
				
			},
			function(info){//fail function
				
				
			}
    				
    		
    		
    		);
    		
    		
    		
    	}
    	
    	$scope.dodajPolovniOglas=function(){
    		$scope.polovniRekvizit.postavljac = JSON.parse(localStorage.getItem("user"));
    		console.log($scope.polovniRekvizit);
    		fanZoneService.savePolovni(
    				
    		$scope.polovniRekvizit,
    		function(info){//succes function
    			$scope.polovni=info.data;
				
			},
			function(info){//fail function
				
				
			}
    				
    		
    		
    		);
    		
    		
    		
    	}
    	
    	$scope.posaljiPonudu=function(cena,rekvizit){
    		$scope.licitacija= {};
    		$scope.licitacija.rekvizit = rekvizit;
    		$scope.licitacija.ponudjac = JSON.parse(localStorage.getItem("user"));
    		$scope.licitacija.price = cena;
    		console.log($scope.licitacija);
    		fanZoneService.posaljiPonudu(
    				$scope.licitacija,
    		function(info){//succes function
    			$scope.zvanicna=info.data;
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
       	$scope.mojiRekviziti=function(){
    		fanZoneService.getuserRekviziti(
    		JSON.parse(localStorage.getItem("user")).id,
    		function(info){//succes function
    			$scope.mojiRekviziti=info.data;
    			console.log($scope.mojiRekviziti);
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
       	
       	
     	$scope.ponudeZaRekvizit=function(id){
    		fanZoneService.getPonude(
    		id,
    		function(info){//succes function
    			$scope.ponude=info.data;
    			console.log($scope.ponude);
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
    	$scope.prihvatiOglas=function(id){
    		fanZoneService.prihvatiOglas(
    		id,
    		function(info){//succes function
    			$scope.odobrenje=info.data;
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
		}
		$scope.odbiOglas=function(id){
    		fanZoneService.odbiOglas(
    		id,
    		function(info){//succes function
    			$scope.odobrenje=info.data;
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
		
		$scope.prihvatiPonudu=function(ponuda){
    		fanZoneService.saveCinemaTheatre(
    				ponuda,
    		function(info){//succes function
				
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
       	
    });