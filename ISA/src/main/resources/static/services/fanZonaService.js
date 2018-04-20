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
              posaljiPonudu: function(data,onSuccess, onError){
                var req = {
                        method: 'POST',
                        url: 'api/rekvizit/licitacija',
                        data: data
                    };
                    $http(req).then(onSuccess, onError);
            },
            zakaziZvanicni: function(data,onSuccess, onError){
                var req = {
                        method: 'POST',
                        url: 'api/rekvizit/licitacija/zvanicni',
                        data: data
                    };
                    $http(req).then(onSuccess, onError);
            },
            getuserRekviziti: function(id,onSuccess, onError){
                var req = {
                        method: 'GET',
                        url: 'api/rekvizit/' + id,
                    };
                    $http(req).then(onSuccess, onError);
            },
            getPonude: function(id,onSuccess, onError){
                var req = {
                        method: 'GET',
                        url: 'api/rekvizit/ponude/' + id,
                    };
                    $http(req).then(onSuccess, onError);
            },
            getOglasiZaOdobravanje: function(onSuccess, onError){
                var req = {
                        method: 'GET',
                        url: 'api/rekvizit/ponude/neobradjeni',
                    };
                    $http(req).then(onSuccess, onError);
            },
            prihvatiOglas : function(id,onSuccess, onError){
                var req = {
                        method: 'POST',
                        url: 'api/rekvizit/ponude/odobrenje/' + id,
                    };
                    $http(req).then(onSuccess, onError);
            },
            odbiOglas: function(id,onSuccess, onError){
                var req = {
                        method: 'POST',
                        url: 'api/rekvizit/ponude/odbijanje/' + id,
                    };
                    $http(req).then(onSuccess, onError);
            },
            prihvatiPonudu: function(data,onSuccess, onError){
                var req = {
                        method: 'POST',
                        url: 'api/rekvizit/ponude/prihvati',
                        data : data,
                    };
                    $http(req).then(onSuccess, onError);
            },
            getmyLicitacije: function(id,onSuccess, onError){
                var req = {
                        method: 'GET',
                        url: 'api/rekvizit/licitacije/'+id,
                    };
                    $http(req).then(onSuccess, onError);
            },
            
        }
    });