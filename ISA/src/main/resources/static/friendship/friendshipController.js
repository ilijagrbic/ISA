angular.module('app').controller(
		'friendshipController',
		function($rootScope, $scope, $state, friendshipService) {
			alert($rootScope.USER.name);
			friendshipService.friends($rootScope.USER.id, function(res) {
				$scope.friends = res.data;
			}, function(res) {
				alert("Error - nije mogao da pronadje prijatelje");
			});
			
			friendshipService.requests($rootScope.USER.id, function(res) {
				$scope.requests = res.data;
			}, function(res) {
				alert("Error - nije mogao da nadje zahteve za prijateljstvo");
			});
			
			friendshipService.nonFriends($rootScope.USER.id, function(res) {
				$scope.nonFriends = res.data;
			}, function(res) {
				alert("Error - nije mogao da pronadje osobe kojima moze da posalje zahtevprijatelje");
			});
			
			$scope.deleteFriend = function (user, index, indexOfRow) {
		            alert("Zelimo da obrisemo " + index + " trentuni user je " + $rootScope.USER.name);
		            friendshipService.deleteFriend($rootScope.USER.id, index, function(res) {
		            	// Vraca prijatelje
		            	alert("Index " + indexOfRow);
		            	$scope.friends.splice(indexOfRow, 1);
		            	$scope.nonFriends.push(user);
					}, function(res) {
						alert("Neki error kod brisanja prijatelja");
					});
		     };
		     
		 	$scope.sendRequest = function (user, index, indexOfRow) {
	            alert("Zelimo da obrisemo " + index + " trentuni user je " + $rootScope.USER.name);
	            friendshipService.send($rootScope.USER.id, index, function(res) {
	            	// Vraca prijatelje
	            	alert("Index " + indexOfRow);
	            	$scope.nonFriends.splice(indexOfRow, 1);
				}, function(res) {
					alert("Neki error kod brisanja prijatelja");
				});
		 	};
		     
		     $scope.acceptRequest = function (index,user,indexOfRow) {
		            alert("Zelimo da prihvatimo zahtev od " + index + " trentuni user je " + $rootScope.USER.name);
		            friendshipService.accept($rootScope.USER.id, index, function(res) {
		            	alert("Prihvatio")
						$scope.friends.push(user);
		            	$scope.requests.splice(indexOfRow, 1);
					}, function(res) {
						alert("Neki error kod prihvatanja prijatelja");
					});
		     };
});