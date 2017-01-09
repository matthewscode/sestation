/**
 * Created by Matthew on 11/24/2016.
 */
var machineURL = 'https://mpscope.herokuapp.com';
var startPattern = '/api/scope/start/';
var stopPattern = '/api/scope/stop/';
var updateCurrentConfigPattern = '/api/scope/current/update/';
var pageListPattern = '/api/scope/page/list/';
var segmentListPattern = '/api/scope/segment/list/';
var createPattern = '/scope/create/';
var configListPattern ='/scope/list/';
var deleteConfigPattern = '/scope/delete/';

var sesApp = angular.module('sesApp', ['ngRoute']);
sesApp.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/dash.html',
            controller  : 'dashCtrl'
        })
        .when('/scope/create/', {
            templateUrl : 'pages/config-create.html',
            controller  : 'configCtrl'
        })
        .when('/scope/current/', {
            templateUrl : 'pages/config-current.html',
            controller  : 'currentCtrl'
        })
        .when('/scope/list/', {
            templateUrl : 'pages/config-list.html',
            controller  : 'configListCtrl'
        })
        .when('/scope/page/list/', {
            templateUrl : 'pages/page-list.html',
            controller  : 'pageListCtrl'
        })
        .when('/scope/segment/list/', {
            templateUrl : 'pages/segment-list.html',
            controller  : 'segmentListCtrl'
        })


});

sesApp.controller('mainCtrl', ['$scope', '$http', function($scope, $http){
    $scope.configName = "Hello World";
    $scope.configLoaded = true;
    $scope.menuOption = 1;
    var resultsInterval = null;
    $scope.start = function(){
        console.log('starting...');
        $http.post(machineURL + startPattern)
            .success(function(data) {
                console.log(data);
                resultsInterval = $interval(getCurrentResults, 5000);
            })
    };

    $scope.stop = function(){
        $http.post(machineURL + stopPattern)
            .success(function(data) {
                console.log(data);
            })
    };
    var getCurrentResults = function() {
        console.log('running interval');

        $http.get(machineURL + '/api/scope/result/running')
            .success(function(data) {
                console.log(data);
                $scope.currentResult = data;

                // Clear interval when no longer running
                if(!$scope.currentResult.running && resultsInterval) {
                    console.log('cleared interval');
                    $interval.cancel(resultsInterval);
                }
            })
    };

    $scope.init = function(){
        $http.get(machineURL + '/api/scope/config/current/')
            .success(function(data) {
                $scope.currentConfig = data;
                console.log(data);
            });

        getCurrentResults();
    };
}]);

sesApp.controller('currentCtrl', ['$scope', '$http', '$interval', function($scope, $http, $interval){

}]);

sesApp.controller('dashCtrl', ['$scope', '$http', function($scope, $http){
    $scope.testString = " ok ";
}]);

sesApp.controller('configListCtrl', ['$scope', '$http', function($scope, $http){
    $scope.configList = [];
    $scope.selectedConfig = {};
    $scope.init = function(){
        $http.get(configListPattern)
            .success(function(data) {
                $scope.configList = data;
                console.log(data);
            });

    };
    $scope.loadConfig  = function(config){
        console.log(config);
        $http.post(machineURL + updateCurrentConfigPattern, config)
            .success(function() {

            });
    };

    $scope.deleteConfig = function(index, id){
      $http.post(deleteConfigPattern + id)
          .success(function() {
              $scope.configList.splice(index, 1);
          });
    };
}]);

sesApp.controller('pageListCtrl', ['$scope', '$http', function($scope, $http){
    $scope.pageList = {};
    $scope.init = function(){
        $http.get(machineURL + pageListPattern)
            .success(function(data) {
                $scope.pageList = data;
            })
    }
}]);
sesApp.controller('segmentListCtrl', ['$scope', '$http', function($scope, $http){
    $scope.segmentList = {};
    $scope.init = function(){
        $http.get(machineURL + segmentListPattern)
            .success(function(data) {
                $scope.segmentList = data;
            })
    }
}]);
sesApp.controller('configCtrl', ['$scope', '$http', function($scope, $http){
    $scope.newConfig = {};
    $scope.domainList = '';
    $scope.seedList = '';
    $scope.includeList = '';
    $scope.excludeList = '';
    $scope.createScope = function(){
        $scope.newConfig.domainList = createDomainList($scope.domainList);
        $scope.newConfig.seedList = createSeedList($scope.seedList);
        $scope.newConfig.includeList = createIncludeList($scope.includeList);
        $scope.newConfig.excludeList = createExcludeList($scope.excludeList);
        console.log($scope.newConfig);
        $http.post(createPattern, $scope.newConfig)
            .success(function(data) {
                console.log(data);
            })
    };
}]);

var createDomainList = function(line){
    if(line.length <= 0){
        return [];
    }
    var splitted = line.split("\n");
    var objectList = [];
    for(var i = 0; i < splitted.length; i++){
        var obj = {};
        obj.id = '';
        obj.domain = splitted[i];
        obj.scopeConfig = '';
        objectList.push(obj);

    }
    console.log(objectList);
    return objectList;
}
var createSeedList = function(line){
    if(line.length <= 0){
        return [];
    }
    var splitted = line.split("\n");
    var objectList = [];
    for(var i = 0; i < splitted.length; i++){
        var obj = {};
        obj.id = '';
        obj.seedUrl = splitted[i];
        obj.scopeConfig = '';
        objectList.push(obj);

    }
    console.log(objectList);
    return objectList;
}

var createIncludeList = function(line){
    if(line.length <= 0){
        return [];
    }
    var splitted = line.split("\n");
    var objectList = [];
    for(var i = 0; i < splitted.length; i++){
        var obj = {};
        obj.id = '';
        obj.includePattern = splitted[i];
        obj.scopeConfig = '';
        objectList.push(obj);

    }
    console.log(objectList);
    return objectList;
}
var createExcludeList = function(line){
    if(line.length <= 0){
        return [];
    }
    var splitted = line.split("\n");
    var objectList = [];
    for(var i = 0; i < splitted.length; i++){
        var obj = {};
        obj.id = '';
        obj.excludePattern = splitted[i];
        obj.scopeConfig = '';
        objectList.push(obj);
    }
    console.log(objectList);
    return objectList;
}