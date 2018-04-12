angular.module('app')
    .controller('adminMovieEditController', function ($scope, $state, $stateParams, movieShowService, uploadService, actorService, projekcijeService, salaService) {
    	
		movieShowService.getMovieShow($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.curentMovie = info.data;
				},
				function(){
					$scope.curentMovie = null;
				}
		)
		
		projekcijeService.getProjekcijeInMovie($stateParams.cinemaId, $stateParams.movieId,
				function(info){
					$scope.listProjekcije = info.data;
				},
				function(){
					
				}
		)
		
		actorService.getActors(
				function(info){
					$scope.listGlumci = info.data
				},
				function(){
					
				}
		)
		
		salaService.getSaleByCinnema($stateParams.cinemaId,
				function(info){
					$scope.listSale = info.data;
				},
				function(){
		
				}
			)
		
		
		$scope.enterGlumacVal = null;
		$scope.enterGlumac = false;
		$scope.newActroPanelActiaved=false;
		$scope.newProjekcijaPanelActivated=false;
		$scope.dt = new Date();
		$scope.opdNewSala = true;
		
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
			var DTO = {
					"date": $scope.newProjDate,
					"sala":sala,
					"cena":$scope.newProjCena,
					"film":$stateParams.movieId
					
				}
			
			projekcijeService.postProjekcija(DTO,
					function(info){
						$scope.listProjekcije.splice($scope.listProjekcijelength, "0", info.data);
					},
					function(){
						
					}
			)
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
					function(){
						
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
			    				function(){
			    					
			    				}
			    			);
			              
		          }, 
		          function () {
		        	  console.log("upload error")
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