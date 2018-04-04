angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cinemas', {
                url: '/cinemas',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'cinemaTheatres/cinemas.html',
                        controller: 'cinemaController'
                    }
                }
            })
            .state('theatres', {
                url: '/theatres',
                views: {
                	'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                    	templateUrl: 'cinemaTheatres/theatres.html',
                        controller: 'theatresController'
                    }
                }
            })
    });