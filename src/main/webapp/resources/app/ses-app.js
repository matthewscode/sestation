/**
 * Created by Matthew on 11/24/2016.
 */

var sesApp = angular.module('sesApp', ['ngRoute']);
sesApp.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/dash.html',
            controller  : 'dashCtrl'
        })
        .when('/create', {
            templateUrl : 'pages/config-create.html',
            controller  : 'configCtrl'
        })

});

sesApp.controller('mainCtrl', ['$scope', '$http', function($scope, $http){
    $scope.configName = "Hello World";
    $scope.configLoaded = false;
}]);

sesApp.controller('configCtrl', ['$scope', '$http', function($scope, $http){
    $scope.newConfig = {};
    
}]);
