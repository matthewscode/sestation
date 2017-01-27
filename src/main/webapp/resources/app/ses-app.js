/**
 * Created by Matthew on 11/24/2016.
 */
var startPattern = '/api/scope/start/';
var stopPattern = '/api/scope/stop/';
var updateCurrentConfigPattern = '/api/scope/current/update/';
var pageListPattern = '/api/scope/page/list/';
var segmentListPattern = '/api/scope/segment/list/';
var imageListPattern = '/api/scope/image/list/';

var createPattern = '/scope/create/';
var configListPattern ='/scope/list/';
var deleteConfigPattern = '/scope/delete/';
var createMachinePattern = '/machine/create/';
var machineListPattern = '/machine/list/all/';
var currentResultsPattern = '/scope/current/';
var createAnalysisPattern = '/analysis/create/';
var analysisListPattern = '/analysis/list/all/';
var getConfigPattern = '/scope/get/';
var sesApp = angular.module('sesApp', ['ngRoute','chart.js']);
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
        .when('/scope/update/:scopeId', {
            templateUrl : 'pages/config-update.html',
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
        .when('/scope/image/list/', {
            templateUrl : 'pages/image-list.html',
            controller  : 'imageListCtrl'
        })
        .when('/admin', {
            templateUrl : 'pages/admin.html',
            controller  : 'adminCtrl'
        })
        .when('/analysis/create/', {
            templateUrl : 'pages/analysis-create.html',
            controller  : 'analysisCtrl'
        })
        .when('/analysis/list/', {
            templateUrl : 'pages/analysis-list.html',
            controller  : 'analysisCtrl'
        });



});

sesApp.controller('mainCtrl', ['$scope', '$http', '$interval', '$location', function($scope, $http, $interval, $location){
    $scope.configLoaded = true;
    $scope.menu = {};
    $scope.menu.option = 1;
    $scope.machineList = {};
    $scope.current = {};
    $scope.current.config = {};
    $scope.current.machine = {};
    $scope.current.machine.name = 'Select';
    $scope.current.machine.location = 'https://mpscope.herokuapp.com';
    $scope.displayResults = true;
    $scope.showMachineList = false;
    var resultsInterval = null,
        waitingToStart = false;

    $scope.start = function(){
        console.log('starting...');
        $http.post($scope.current.machine.location + startPattern)
            .success(function(data) {
                // console.log(data);
                waitingToStart = true;
                getCurrentResults();
            })
    };

    $scope.stop = function(){
        $http.post($scope.current.machine.location + stopPattern)
            .success(function(data) {
                // console.log(data);
            })
    };

    var getCurrentResults = function() {
        console.log('getting current results');
        $http.get($scope.current.machine.location + '/api/scope/result/running')
            .success(function(data) {
                // console.log(data);
                $scope.currentResult = data;

                if($scope.currentResult.running) {
                    waitingToStart = false;
                }

                // Set interval if currently running and no interval already set
                if(($scope.currentResult.running || waitingToStart) && !resultsInterval) {
                    console.log('running interval');
                    resultsInterval = $interval(getCurrentResults, 5000);
                }
                // Otherwise clear interval when no longer running
                else if((!$scope.currentResult.running && !waitingToStart) && resultsInterval) {
                    console.log('cleared interval');
                    $interval.cancel(resultsInterval);
                }
            })
    };

    $scope.init = function(){
        //$location.path(currentResultsPattern);
        $http.get(machineListPattern)
            .success(function(data) {
                $scope.machineList = data;
                $scope.current.machine = $scope.machineList[0];
            });
        $http.get($scope.current.machine.location + '/api/scope/config/current/')
            .success(function(data) {
                $scope.current.config = data;
                // console.log(data);
            });

        getCurrentResults();
    };

    $scope.selectMachine = function(machine){
        $scope.current.machine = machine;
        $scope.init();
        $scope.showMachineList = false;
    }
}]);

sesApp.controller('currentCtrl', ['$scope', '$http', '$interval', function($scope, $http, $interval){
    $scope.menu.option = 1;
}]);

sesApp.controller('dashCtrl', ['$scope', '$http', function($scope, $http){
    $scope.testString = " ok ";
}]);

sesApp.controller('configListCtrl', ['$scope', '$http', '$location', function($scope, $http, $location){
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
        $http.post($scope.current.machine.location + updateCurrentConfigPattern, config)
            .success(function() {
                $location.path(currentResultsPattern);
                $scope.current.config = config;
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
    $scope.menu.option = 2;
    $scope.pageList = {};
    $scope.init = function(){
        $http.get($scope.current.machine.location + pageListPattern)
            .success(function(data) {
                $scope.pageList = data;
            })
    }
}]);
sesApp.controller('segmentListCtrl', ['$scope', '$http', function($scope, $http){
    $scope.menu.option = 3;
    $scope.segmentList = {};
    $scope.init = function(){
        $http.get($scope.current.machine.location + segmentListPattern)
            .success(function(data) {
                $scope.segmentList = data;
            })
    }
}]);
sesApp.controller('imageListCtrl', ['$scope', '$http', function($scope, $http){
    $scope.menu.option = 4;
    $scope.imageList = {};
    $scope.imageCount = 0;
    $scope.init = function(){
        $http.get($scope.current.machine.location + imageListPattern)
            .success(function(data) {
                $scope.imageList = data;
            })
    }
    $scope.imagePlus = function(){
        $scope.imageCount++;
    }
}]);

sesApp.controller('configCtrl', ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location){
    $scope.newConfig = {};
    $scope.domainList = '';
    $scope.seedList = '';
    $scope.includeList = '';
    $scope.excludeList = '';
    $scope.rows = 0;
    $scope.createScope = function(){
        $scope.newConfig.domainList = createDomainList($scope.domainList);
        $scope.newConfig.seedList = createSeedList($scope.seedList);
        $scope.newConfig.includeList = createIncludeList($scope.includeList);
        $scope.newConfig.excludeList = createExcludeList($scope.excludeList);
        $http.post(createPattern, $scope.newConfig)
            .success(function() {
                $location.path(configListPattern);
            })
    };
    $scope.init = function(){
        $scope.getScopeConfig($routeParams.scopeId);
    }
    $scope.getScopeConfig = function(id){
        $http.get(getConfigPattern + id + '/')
            .success(function(data) {
                $scope.newConfig = data;
                for(var i = 0; i < data.domainList.length; i++){
                    $scope.domainList = $scope.domainList + data.domainList[i].domain + '\n';
                }
                for(i = 0; i < data.seedList.length; i++){
                    $scope.seedList = $scope.seedList + data.seedList[i].seedUrl + '\n';
                }
                for(i = 0; i < data.includeList.length; i++){
                    $scope.includeList = $scope.includeList + data.includeList[i].includePattern + '\n';
                }
                for(i = 0; i < data.excludeList.length; i++){
                    $scope.excludeList = $scope.excludeList + data.excludeList[i].excludePattern + '\n';
                }
            })
    }
}]);
sesApp.controller('adminCtrl', ['$scope', '$http', function($scope, $http){
    $scope.newMachine = {};
    $scope.createMachine = function () {
        $http.post(createMachinePattern, $scope.newMachine)
            .success(function() {
                console.log('created machine');
            })
    }
}]);
sesApp.controller('analysisCtrl', ['$scope', '$http', '$location', function($scope, $http, $location){
    $scope.newAnalysis = {};
    $scope.analysisList = {};
    $scope.createAnalysis = function () {
        $http.post(createAnalysisPattern, $scope.newAnalysis)
            .success(function() {
                console.log('created machine');
                $location.path(currentResultsPattern);
            })
    }
    $scope.init = function () {
        $http.get(analysisListPattern)
            .success(function(data) {
                $scope.analysisList = data;
                console.log('got analysis list');
            })
    }
}]);
sesApp.controller("donutCtrl", function ($scope) {
    $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
     $scope.labels = [];
    // $scope.series = ['Word Count'];
    $scope.data = [];

    $scope.$watch('currentResult.directoryList', function() {
        var result = $scope.currentResult.directoryList,
            newLabels = [],
            newData = [];

        for(var i = 0, len = result.length; i < len; i++) {
            newLabels.push(result[i].directory);
            newData.push(result[i].wordCount);
        }

        $scope.labels = newLabels;
        $scope.data = newData;
    }, true);

});
var createDomainList = function(line){
    if(line.length <= 0){
        return [];
    }
    var splitted = line.split("\n");
    var objectList = [];
    for(var i = 0; i < splitted.length; i++){
        if(splitted[i].length > 0) {
            var obj = {};
            obj.domain = splitted[i];
            objectList.push(obj);
        }

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
        if(splitted[i].length > 0) {
            var obj = {};
            obj.seedUrl = splitted[i];
            objectList.push(obj);
        }
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
        if(splitted[i].length > 0) {
            var obj = {};
            obj.includePattern = splitted[i];
            objectList.push(obj);
        }
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
        if(splitted[i].length > 0) {
            var obj = {};
            obj.excludePattern = splitted[i];
            objectList.push(obj);
        }
    }
    console.log(objectList);
    return objectList;
}