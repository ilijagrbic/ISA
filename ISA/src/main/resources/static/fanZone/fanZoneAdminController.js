angular.module('app')
    .controller('fanZoneAdminController', function ($scope, fanZoneService) {
    	fanZoneService.getZvanicnaPonuda(
				function(info){//succes function
					$scope.zvanicna=info.data;
					console.log(info.data);
					
				},
				function(info){//fail function
					$scope.polovni=[];
					
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
    		fanZoneService.savePolovni(
    				
    		$scope.polovniRekvizit,
    		function(info){//succes function
    			$scope.polovni=info.data;
				
			},
			function(info){//fail function
				
				
			}
    				
    		
    		
    		);
    		
    		
    		
    	}
    });