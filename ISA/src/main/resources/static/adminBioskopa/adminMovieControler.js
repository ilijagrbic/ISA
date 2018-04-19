angular.module('app')
    .controller('adminMovieEditController', function ($scope, $state, $stateParams, movieShowService, uploadService, actorService, projekcijeService, salaService) {
    	
    	$scope.salaPanelText = function(){
    		if($scope.opdNewSala==true){
    			return "Unesi novu salu";
    		}else{
    			return "Odaberi postojecu salu";
    		}
    	}
    	
    	$scope.glumacPanelText = function(){
    		if(!$scope.newActroPanelActiaved==true){
    			return "Unesi novog glumca";
    		}else{
    			return "Odaberi postojeceg glumca";
    		}
    	}
    	
    	$scope.date = function(date){
    		var dat = new Date(date);
    		return dat.toLocaleDateString()+" "+dat.toLocaleTimeString();
    	}
    	
    	
		movieShowService.getMovieShow($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.curentMovie = info.data;
				},
				function(info){
					$scope.curentMovie = null;
					alert(info.data.err);
				}
		)
		
		projekcijeService.getProjekcijeInMovie($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.listProjekcije = info.data;
				},
				function(info){
					alert(info.data.err);
				}
		)
		
		actorService.getActors(
				function(info){
					$scope.listGlumci = info.data
				},
				function(info){
					alert(info.data.err);
				}
		)
		
		salaService.getSaleByCinnema($stateParams.cinemaId,
				function(info){
					$scope.listSale = info.data;
				},
				function(info){
					alert(info.data.err);
				}
			)
		
		
		$scope.enterGlumacVal = null;
		$scope.enterGlumac = false;
		$scope.newActroPanelActiaved=false;
		$scope.newProjekcijaPanelActivated=false;
		$scope.dt = new Date();
		$scope.opdNewSala = true;
		$scope.noShowSeats=true;
		$scope.curentlyEditedProj = null;
		$scope.opdNewSalaEditing = true;
		
		/*$scope.dontShowOldSalaEditing = function(){
			$scope.opdNewSalaEditing = !$scope.opdNewSalaEditing;
		}
		
		$scope.backFromSeats = function(){
			$scope.noShowSeats=true;
		}*/
		
		$scope.manageSetas = function(proj){
			$state.go("editProj", {movieId: $stateParams.movieId, cinemaId: $stateParams.cinemaId, cinType: $stateParams.cinType, projId: proj.id});
		}
		
		$scope.saveNewProj = function(x){
			var sala;
			if($scope.opdNewSala == false){
				sala = {
						"id":null,
						"nazivBroj":$scope.newProjSalaNaziv,
						"duzina":$scope.newProjSalaDuz,
						"visina":$scope.newProjSalaVis
					}
			}
			else{
				sala = x;
			}
			//console.log($scope.newProjDate.getFullYear()+"---"+$scope.newProjDate.getMonth()+"---"+$scope.newProjDate.getDate());
			//console.log($scope.newProjTime.getHours()+"---"+$scope.newProjTime.getMinutes());
			var datumVreme = new Date($scope.newProjDate.getFullYear(), $scope.newProjDate.getMonth(), $scope.newProjDate.getDate(), $scope.newProjTime.getHours(), $scope.newProjTime.getMinutes(), 0, 0);
			var sedista = getArrSedista(sala);
			var DTO = {
					"date": datumVreme,
					"sala":sala,
					"cena":$scope.newProjCena,
					"film":$stateParams.movieId,
					"sedista":sedista
				}
			
			projekcijeService.postProjekcija(DTO,
					function(info){
						$scope.listProjekcije.splice($scope.listProjekcijelength, "0", info.data);
					},
					function(info){
						alert(info.data.err);
					}
			)
		}
		
		getArrSedista = function(x){
			var retVal = [];
			var defSed = {
					"visKord":0,
					"duzKord":0,
					"type":"ODRINARY",
					"deltaCena":0
			}
			for(i=0;i<x.visina;i++){
				for(j=0;j<x.duzina;j++){
					var temp = {
							"visKord":i,
							"duzKord":j,
							"type":"ODRINARY",
							"deltaCena":0
					};
					retVal.splice(retVal, "0", temp);
				}
			}
			
			return retVal;
		}
		
		$scope.dontShowOldSala = function(){
			$scope.opdNewSala = !$scope.opdNewSala;
		}
		
		$scope.showNewProjekcijaPanel = function(){
			$scope.newProjekcijaPanelActivated=true;
		}
		
		$scope.dontShowNewProjectionPanel = function(){
			$scope.newProjekcijaPanelActivated=false;
		}
		
		$scope.deleteProjekcija = function(proj){
			projekcijeService.deleteProjekcija(proj.id,
					function(info){
						for(var i=0;i<$scope.listProjekcije.length;i++){
							if($scope.listProjekcije[i].id==info.data.id){
								$scope.listProjekcije.splice(i,1);
							}
						}
					},
					function(info){
						alert(info.data.err);
					}
			)
		}
		
		$scope.addGlumac = function(x, ime, prezime){
			if($scope.newActroPanelActiaved==false){
				if(x==null){
					console.log("shiiiiteet");
				}
				$scope.curentMovie.glumci.splice($scope.curentMovie.glumci.length, "0", x);
			}
			else{
				xx={
						"id":null,
						"ime":ime,
						"prezime":prezime
				}
				$scope.curentMovie.glumci.splice($scope.curentMovie.glumci.length, "0", xx);
			}
		}
		
		$scope.newActorPanel = function(){
			$scope.newActroPanelActiaved=!$scope.newActroPanelActiaved;
		}
		
		$scope.showNewGlumacPanel = function(){
			$scope.enterGlumac = true;
		}
		
		$scope.dontShowNewGlumacPanel = function(){
			$scope.enterGlumac = false;
		}
		
		$scope.deleteGlumac = function(id){
			for(var i=0;i<$scope.curentMovie.glumci.length;i++){
				if($scope.curentMovie.glumci[i].id==id){
					$scope.curentMovie.glumci.splice(i,1);
				}
			}
		}
		
		$scope.commitChanges = function(){
			uploadService.postImage($scope.myFile, 
					function (response) {
			            $scope.imgPath = response.data.message;
			            getMovieDTO();
			            movieShowService.putMovieShow($stateParams.cinemaId, $scope.curentMovie.id, getMovieDTO(),
			    				function(info){
			            			goBack();
			    				},
			    				function(info){
			    					alert(info.data.err);
			    				}
			    			);
			              
		          }, 
		          function (error) {
		        	  $scope.imgPath = "";
			            getMovieDTO();
			            movieShowService.putMovieShow($stateParams.cinemaId, $scope.curentMovie.id, getMovieDTO(),
			    				function(info){
			            			goBack();
			    				},
			    				function(info){
			    					alert(info.data.err);
			    				}
			    			);
		          });
		}
		
		$scope.backToParentMovie = function(){
			goBack();
		}
		
		getMovieDTO = function(param){
			return {
				"id": $scope.curentMovie.id,
				"name": $scope.curentMovie.name,
				"description": $scope.curentMovie.description,
				"price": $scope.curentMovie.price,
				"genre": $scope.curentMovie.genre,
				"glumci": $scope.curentMovie.glumci,
				"director":$scope.curentMovie.director,
				"duration":$scope.curentMovie.duration,
				"type":$scope.curentMovie.type,
				"cinnemaId":$stateParams.cinemaId,
				"image":$scope.imgPath
			}
		}
		
		goBack = function(){
			if($stateParams.cinType=='CINNEMA'){
				$state.go("adminEditPlace", {cinemaTheatreId: $stateParams.cinemaId});
			}else if($stateParams.cinType=='THEATRE'){
				$state.go("adminEditPlace", {cinemaTheatreId: $stateParams.cinemaId});
			}else{
				$state.go("home");
			}	
		}

		$scope.imePrezime = function(x){
			return x.ime+" "+x.prezime;
		}
		
		$scope.stringSala = function(x){
			return x.nazivBroj+"("+x.visina+","+x.duzina+")";
		}
    }).directive('fileUpload', fileUpload);

    fileUpload.$inject = ['$parse'];

    function fileUpload($parse) {
      var directive = {
        restrict: 'A',
        link: function (scope, element, attrs) {
          var model = $parse(attrs.fileUpload);
          var modelSetter = model.assign;

          element.bind('change', function (event) {
            scope.$apply(function () {
              scope.myFile = event.target.files[0];
              event.preventDefault();
            });
          });

        }
      };
      return directive;

    }