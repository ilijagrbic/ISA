angular.module('app')
    .controller('theatreAdminController', function ($scope, $state, $sce, $rootScope, cinemaTheatreService) {
		cinemaTheatreService.getAdminTheatres($rootScope.USER.id,
				function(info){//succes function
					$scope.cinemas=info.data;
					
				},
				function(info){//fail function
					$scope.cinemas=[];
					if(info.data.err!=undefined){
						alert(info.data.err);
					}else{
						alert("Doslo je do greske.");
					}
				}
		);

		$scope.editPlace = function(id){
			$state.go("adminEditPlace", {cinemaTheatreId: id});
			
		}
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });