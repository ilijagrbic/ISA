angular.module('app')
.service('actorService', function ($http) {
    return {
    	getActors: function (onSuccess, onError) {
            var req = {
                method: 'GET',
                url: '/api/actors'
            };
            $http(req).then(onSuccess, onError);
        }
    }
});