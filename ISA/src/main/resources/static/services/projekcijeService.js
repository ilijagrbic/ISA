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
        getProjekcija: function (projId, onSuccess, onError) {
            var req = {
                method: 'GET',
                url: '/api/projections/'+projId
            };
            $http(req).then(onSuccess, onError);
        },
        deleteProjekcija: function (projId, onSuccess, onError) {
            var req = {
                method: 'DELETE',
                url: 'api/projections/'+projId
            };
            $http(req).then(onSuccess, onError);
        },
        postProjekcija: function (data, onSuccess, onError) {
            var req = {
                method: 'POST',
                url: 'api/projections/',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: data
            };
            $http(req).then(onSuccess, onError);
        },
        putProjekcija: function (projId, data, onSuccess, onError) {
            var req = {
                method: 'PUT',
                url: 'api/projections/'+projId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: data
            };
            $http(req).then(onSuccess, onError);
        }
    }
});