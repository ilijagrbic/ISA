angular.module('app')
    .service('fanZoneService', function ($http) {
        return {
        	getZvanicnaPonuda: function(onSuccess, onError){
        		 var req = {
                         method: 'GET',
                         url: 'api/rekvizit/zvanicni'
                     };
                     $http(req).then(onSuccess, onError);
        	},
        	getPolovnaPonuda: function(onSuccess, onError){
       		 var req = {
                        method: 'GET',
                        url: 'api/rekvizit/polovni'
                    };
                    $http(req).then(onSuccess, onError);
        	},
        	saveZvanicni: function(data,onSuccess, onError){
          		 var req = {
                           method: 'POST',
                           url: 'api/rekvizit/zvanicni',
                           data: data
                       };
                       $http(req).then(onSuccess, onError);
           	},
           	
        	savePolovni: function(data,onSuccess, onError){
         		 var req = {
                          method: 'POST',
                          url: 'api/rekvizit/polovni',
                          data: data
                      };
                      $http(req).then(onSuccess, onError);
          	},
        }
    });