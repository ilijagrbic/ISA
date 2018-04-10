angular.module('app')
    .service('friendshipService', function ($http) {
        return {
            friendship: function (user, onSuccess, onError) {
                var req = {
                    method: 'POST',
                    url: '/api/authentication/signup',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: user
                };
                $http(req).then(onSuccess, onError);
            }
        )}
    });