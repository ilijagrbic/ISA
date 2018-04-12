angular.module('app')
.service('salaService', function ($http) {
    return {
    	getSaleByCinnema:function (cinId, onSuccess, onError) {
            var req = {
                    method: 'GET',
                    url: '/api/cinnemas/'+cinId+'/sale'
                };
                $http(req).then(onSuccess, onError);
            },
        getSaleByMovie:function(movId, onSuccess, onError){
        	var req = {
                    method: 'GET',
                    url: '/api/movies/'+movId+'/sale'
                };
                $http(req).then(onSuccess, onError);
        }
    }
});