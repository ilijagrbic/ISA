angular.module('app')
    .controller('cinemaController', function ($scope, cinemaTheatreService) {
		cinemaTheatreService.getCinemas(
				function(info){//succes function
					$scope.cinemas=info.data;
					
				},
				function(info){//fail function
					$scope.cinemas=[];
					
				}
		);
    });