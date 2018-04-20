angular.module('app')
    .controller('adminKorisniciController', function ($scope, fanZoneService, authenticationService) {


     	$scope.registrujAdmina=function(){
    		console.log($scope.user);
    		authenticationService.regAdmin(
    				$scope.user,
    		function(info){//succes function
    			console.log(info.data);
    			$scope.users = info.data;
			},
			function(info){//fail function
				
			}
    				
    		
    		);
    		
    		
    	}
    	
    
    });