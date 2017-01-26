/**
 * Created by Matthew on 11/24/2016.
 */

var getAnalysisPattern = '/analysis/get/';
var seAnApp = angular.module('seAnApp', ['chart.js']);
seAnApp.config(function() {
});

seAnApp.controller('mainCtrl', ['$scope', '$http',  '$location', function($scope, $http, $location){
    $scope.analysis = {};
    $scope.init = function(){
        $http.get(getAnalysisPattern + $location.path().split('/')[2])
            .success(function(data) {
                $scope.analysis = data;
            });

    };

}]);
