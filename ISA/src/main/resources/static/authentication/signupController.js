angular.module('app')
    .controller('signupController', function ($rootScope, $scope, $state, authenticationService) {
    	 $scope.signup = function () {
    	        authenticationService.signup(
    	            $scope.user,
    	            function () {
    	                $scope.user = {}; // Uspesno smo izvrsili vracamo korisnika praznog za dalje popunjavanje
    	                $state.go("signin");
    	            }, function () {
    	                // Greska
    	            });
    	    };
    	});



