
angular.module('app')
.controller('adminLoginController', function ($rootScope, $scope, $state, $stateParams, cinemaTheatreService, movieShowService, uploadService, reservationService, projekcijeService, reportService, userService) {
		/*			if ($rootScope.USER == null || $rootScope.USER.role != 'REG_USER') {
		console.log("Null u userControlleru");
		$state.go("signin");
	} else { */
		userService.findById($rootScope.USER.id, function(res) {
			$scope.user = res.data;
			$scope.notChanged = true;
			$scope.changed = false;
			$scope.editPassword = false;
			$scope.editedPassword = true;
			$scope.passwords={};
		}, function(res) {
			$scope.user = {};
			toastr.error(res.data.error);
		});
//	}

	$scope.editUserDetails = function() {
		$scope.notChanged = false;
		$scope.changed = true;
		
	};

	$scope.saveUserDetails = function() {
		$scope.notChanged = true;
		$scope.changed = false;

		console.log(JSON.stringify($scope.user))

		userService.update($scope.user.id, $scope.user, function(res) {
			$scope.user = res.data;

		}, function(res) {

			// console.log(res.data.error);
		});
	};

	$scope.editPass = function() {
		$scope.editPassword = true;
		$scope.editedPassword = false;
		$scope.passwords={};
	};

	$scope.savePass = function() {
		$scope.editedPassword = true;
		$scope.editPassword = false;
		console.log($rootScope.USER.id);
		userService.updatePassword($scope.passwords,
				$rootScope.USER.id, function(res) {
					console.log(JSON.stringify(res.data));
					/*if ($rootScope.USER == null) {
						console.log("Root je null");*/
						$rootScope.USER = {
							id : res.data.id,
							role : res.data.role,
							firstName : res.data.firstName,
							lastName : res.data.lastName,
							email : res.data.email,
							city : res.data.city,
							phone : res.data.phone,
							password : res.data.password,
							firstTime : res.data.firstTime
					//	};

					}
					$scope.user = res.data;
					$scope.passwords={};
				}, function(res) {
					console.log("Error neki");
				});

	};

})
