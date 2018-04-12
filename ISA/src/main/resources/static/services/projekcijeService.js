angular.module('app')
.service('projekcijeService', function ($http) {
    return {
    	getProjekcijeInMovie: function (cinId, movId, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: '/api/cinnemas/'+cinId+'/movies/'+movId+'/projections'
            };
            $http(req).then(onSuccess, onError);
        },
        deleteProjekcija: function (projId, onSuccess, onError) {
            var req = {
                method: 'DELETE',
                url: 'api/projections/'+projId
            };
            $http(req).then(onSuccess, onError);
        }
    }
});