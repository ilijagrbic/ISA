angular.module('app')
    .controller('theatresController', function ($scope, cinemaTheatreService) {
    	cinemaTheatreService.getTheatres(
				function(info){//succes function
					$scope.theatres=info.data;
					
				},
				function(info){//fail function
					$scope.theatres=[];
					
				}
		);
    });