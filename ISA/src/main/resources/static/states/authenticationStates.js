angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider
            .state('signin', {
                url: '/signin',
                views: {
                    'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                        templateUrl: 'authentication/signin.html',
                        controller: 'signinController'
                    }
                }
            })
            .state('signout', {
                url: '/signout',
                views: {
                    'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                        templateUrl: 'authentication/signout.html',
                        controller: 'signoutController'
                    }
                }
            })
            .state('signup', {
                url: '/signup',
                views: {
                    'header': {
                        templateUrl: 'homePage/header/header.html',
                        controller: 'headerController'
                    },
                    'content': {
                        templateUrl: 'authentication/signup.html',
                        controller: 'signupController'
                    }
                }
            })
    });