angular.module('app')
.service('reservationService', function ($http) {
    return {
    	getAllInProj: function(projId, onSuccess, onError){
    		var req = {
    	                method: 'GET',
    	                url: '/api/projection/'+projId+'/reservations'
    	            };
    		$http(req).then(onSuccess, onError);
    	},
		 postReservation: function (projId, data, onSuccess, onError) {
	        var req = {
	            method: 'POST',
	            url: '/api/projections/'+projId+'/reservations',
	            headers: {
	                 'Content-Type': 'application/json'
	             },
	             data: data
	        };
	        $http(req).then(onSuccess, onError);
	    },
	    getOneClick: function(cinId, onSuccess, onError){
	    	var req = {
	                method: 'GET',
	                url: '/api/cinnema/'+cinId+'/reservations/oneClick'
	            };
		$http(req).then(onSuccess, onError);
	    },
	    deleteReservation: function(resId, onSuccess, onError){
	    	var req = {
	                method: 'DELETE',
	                url: 'api/reservations/'+resId
	            };
		$http(req).then(onSuccess, onError);
	    }
    }
});