angular.module('app')
    .config(function ($stateProvider) {
        $stateProvider.state('regUser', {
            url: '/regUser',
            views: {
                'header': {
                	 templateUrl: 'homePage/header/header.html',
                     controller: 'headerController'
                },
                'content': {
                    templateUrl: 'regUser/regUser.html',
                    controller: 'regUserController'
                }
            }
        });
    });