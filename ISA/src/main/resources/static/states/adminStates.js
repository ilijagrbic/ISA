angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
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
    });