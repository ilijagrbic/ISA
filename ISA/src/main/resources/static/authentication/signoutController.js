angular.module('app')
    .controller('signoutController', function ($rootScope, $scope, $state, authenticationService) {
    	authenticationService.signout(
    				
    	            function () {
    	            	localStorage.clear();
    	                $rootScope.USER = null;
    	                $state.go("signin");
    	            },
    	            function () {
    	            	localStorage.clear();
    	                $rootScope.USER = null;
    	                $state.go("signin");
    	            });
    	    });