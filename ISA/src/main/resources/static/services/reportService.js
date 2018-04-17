angular.module('app')
.service('reportService', function ($http) {
    return {
    	getAmbijentReport: function(cineId, onSuccess, onError){
    		 var req = {
                     method: 'GET',
                     url: 'api/cinnemas/'+cineId+'/avgAmbijent'
                 };
             $http(req).then(onSuccess, onError);
    	},
    	getMovieReport: function(movId, onSuccess, onError){
    		 var req = {
                     method: 'GET',
                     url: 'api/movies/'+movId+'/avgAmbijent'
                 };
             $http(req).then(onSuccess, onError);
    	},
    }
});