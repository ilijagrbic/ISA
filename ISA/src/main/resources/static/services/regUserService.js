angular.module('app')
    .service('regUserService', function ($http) {
        return {
            getRegUsers: function (onSuccess, onError) {
                var req = {
                    method: 'GET',
                    url: '/api/users',
                };
                $http(req).then(onSuccess, onError);
            },
	        update: function (id, user, onSuccess, onError) {
	            var req = {
	                method: 'PUT',
	                url: '/api/users/' + id,
	            };
	            $http(req).then(onSuccess, onError);
	        },
	        findFriends: function (onSuccess, onError) {
	            var req = {
	                method: 'GET',
	                url: '/api/users/friends',
	            };
	            $http(req).then(onSuccess, onError);
	        },
	        findNonFriends: function (onSuccess, onError) {
	            var req = {
	                method: 'GET',
	                url: '/api/users/nonFriends',
	            };
	            $http(req).then(onSuccess, onError);
	        },
	        findRequests: function (onSuccess, onError) {
	            var req = {
	                method: 'GET',
	                url: '/api/users/findRequests',
	            };
	            $http(req).then(onSuccess, onError);
	        }
        }
    });