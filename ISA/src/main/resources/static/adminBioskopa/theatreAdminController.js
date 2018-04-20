angular.module('app')
    .controller('theatreAdminController', function ($scope, $state, $sce, $rootScope, cinemaTheatreService) {
		cinemaTheatreService.getAdminTheatres($rootScope.USER.id,
				function(info){//succes function
					$scope.cinemas=info.data;
					
				},
				function(info){//fail function
					$scope.cinemas=[];
					alert(info.data.err);
				}
		);

		$scope.editPlace = function(id){
			$state.go("adminEditPlace", {cinemaTheatreId: id});
			
		}
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });