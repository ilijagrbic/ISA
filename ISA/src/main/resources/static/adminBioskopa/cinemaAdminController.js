angular.module('app')
    .controller('cinemaAdminController', function ($scope, cinemaTheatreService) {
		cinemaTheatreService.getCinemas(
				function(info){//succes function
					$scope.cinemas=info.data;
					
				},
				function(info){//fail function
					$scope.cinemas=[];
					
				}
		);
    });