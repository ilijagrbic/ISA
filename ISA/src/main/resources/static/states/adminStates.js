angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
	        .state('adminLogin', {
	            url: '/adminLogin',
	            views: {
	            	'header': {
	                    templateUrl: 'homePage/header/header.html',
	                    controller: 'headerController'
	                },
	                'content': {
	                	templateUrl: 'adminLogin/adminLogin.html',
	                    controller: 'adminLoginController'
	                }
	            }
	        })
            .state('cinemasAdmin', {
                url: '/cinemasAdmin',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/cinemasAdmin.html',
                        controller: 'cinemaAdminController'
                    }
                }
            })
            .state('fanZoneAdmin', {
                url: '/fanZoneAdmin',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'fanZone/fanZoneAdmin.html',
                    	controller: 'fanZoneAdminController'

                    },
                }
            })
              .state('fanZoneAdmin.zvanicna', {
                url: '/zvanicna',
                views: {
                	'header': {

                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'fanZone/fanZoneAdmin.html',
                    },
                    'rekviziti': {
                    	templateUrl: 'fanZone/zvanicni.html',
                    	controller: 'fanZoneAdminController'
                    }
                }
            })
                .state('fanZoneAdmin.polovna', {
                url: '/polovna',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'fanZone/fanZoneAdmin.html',
                    }
                    ,
                    'rekviziti': {
                    	templateUrl: 'fanZone/polovni.html',
                    	controller: 'fanZoneAdminController'
                    }
                }
            })
            .state('theatresAdmin', {
                url: '/theatresAdmin',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/theatresAdmin.html',
                        controller: 'theatreAdminController'
                    }
                }
            })
            .state('adminEditPlace', {
                url: '/adminEditPlace/:cinemaTheatreId',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/adminEditPlace.html',
                        controller: 'adminEditPlaceController'
                    }
                }
            })
            
               .state('placesCrud', {
                url: '/placesCrud',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/placesCrud.html',
                        controller: 'adminAddPlaceController'
                    }
                }
            })
                .state('korisniciSistem', {
                url: '/korisniciSistem',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/korisniciCrud.html',
                        controller: 'adminKorisniciController'
                    }
                }
            })
            
            .state('adminEditMovie', {
                url: '/adminEditMovie/:movieId/cinema/:cinemaId/type/:cinType',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/adminEditMovie.html',
                        controller: 'adminMovieEditController'
                    }
                }
            })
            .state('adminViewMovie', {
                url: '/adminViewMovie/:movieId/cinema/:cinemaId/type/:cinType',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/adminViewMovie.html',
                        controller: 'adminMovieViewController'
                    }
                }
            })
            .state('editProj', {
                url: '/proj/:movieId/cinema/:cinemaId/type/:cinType/proj/:projId',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'adminBioskopa/editProj.html',
                        controller: 'editProjController'
                    }
                }
            })
    });