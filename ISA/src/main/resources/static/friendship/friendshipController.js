angular.module('app').controller(
		'friendshipController',
		function($rootScope, $scope, $state, friendshipService) {
			//alert($rootScope.USER.name);
			friendshipService.friends($rootScope.USER.id, function(res) {
				$scope.friends = res.data;
			}, function(res) {
				alert("Greska prilikom pronalaska prijatelja.");
			});
			
			friendshipService.requests($rootScope.USER.id, function(res) {
				$scope.requests = res.data;
			}, function(res) {
				alert("Greska prilikom pronalaska zahteva za prijateljstvo.");
			});
			
			friendshipService.nonFriends($rootScope.USER.id, function(res) {
				$scope.nonFriends = res.data;
			}, function(res) {
				alert("Greska prilikom pronalaska osoba kojim moze da posalje zahtev.");
			});
			
			$scope.deleteFriend = function (user, index, indexOfRow) {
		          //  alert("Zelimo da obrisemo " + index + " trentuni user je " + $rootScope.USER.name);
		            friendshipService.deleteFriend($rootScope.USER.id, index, function(res) {
		            	$scope.friends = [];
		            	$scope.friends = res.data;
		            	$scope.nonFriends.push(user);
		            	//console.log(indexOfRow);
					}, function(res) {
						alert("Greska prilikom brisanja prijatelja, pokusajte ponovo.");
					});
		     };
		     
		 	$scope.sendRequest = function (user, index, indexOfRow) {
	            //alert("Zelimo da posaljemo zahtev " + index + " trentuni user je " + $rootScope.USER.name);
	           // console.log( $rootScope.USER.id + " " + index );
	            friendshipService.send($rootScope.USER.id, index, function(res) {
	            	$scope.nonFriends = [];
	            	$scope.nonFriends = res.data;
	            	//console.log(indexOfRow);
	            	//console.log(JSON.stringify(res.data));
				}, function(res) {
					alert("Greska prilikom slanja zahteva za prijateljstvo, pokusajte ponovo.");
				});
		 	};
		 	
		 	$scope.cancelRequest = function (index, user, indexOfRow) {
	            //alert("Odbijanje zahteva " + index + " trentuni user je " + $rootScope.USER.name);
	           // console.log( $rootScope.USER.id + " " + index );
	            friendshipService.cancel($rootScope.USER.id, index, function(res) {
	            	$scope.nonFriends.push(user);
	            	$scope.requests = res.data;
	            	//console.log(indexOfRow);
	            	//console.log(JSON.stringify(res.data));
				}, function(res) {
					alert("Greska prilikom odbijanja zahteva za prijateljstvo, pokusajte ponovo.");
				});
		 	};
		     
		     $scope.acceptRequest = function (index, user, indexOfRow) {
		         //   alert("Zelimo da prihvatimo zahtev od " + index + " trentuni user je " + $rootScope.USER.name);
		            friendshipService.accept($rootScope.USER.id, index, function(res) {
		            	//console.log(user.name + " " + index + " " + indexOfRow)
		            	$scope.requests = [];
		            	$scope.requests = res.data;
		            	//console.log(res.data);
		            	//console.log(JSON.stringify(res.data));
		            	$scope.friends.push(user);
		            	//console.log(indexOfRow);
					}, function(res) {
						alert("Greska prilikom prihvatanja zahteva za prijateljstvo, pokusajte ponovo.");
					});
		     };
});