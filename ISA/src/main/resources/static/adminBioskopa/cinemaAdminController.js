angular.module('app')
    .controller('cinemaAdminController', function ($scope, $state, $sce, $rootScope, cinemaTheatreService) {
		cinemaTheatreService.getAdminCinemas($rootScope.USER.id,
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
		$scope.addPlace = function(){
			$state.go("adminAddPlace");
			
		}
		
		
		$scope.trustSrc = function(url){
			return $sce.trustAsResourceUrl(url);
		}
    });