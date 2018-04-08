angular.module('app')
    .controller('signoutController', function ($rootScope, $scope, $state, authenticationService) {
    	authenticationService.signout(
    	            function () {
    	                $rootScope.USER = null;
    	                $state.go("signin");
    	            },
    	            function () {
    	                $rootScope.USER = null;
    	                $state.go("signin");
    	            });
    	    });