'use strict';


angular.module('inventoryApp')
  .controller('InventoryReportCtrl', function ($scope, $timeout , $http) {
	//alert("coming to report ccccc");
	//$scope.fetchReport();
  $scope.fetchReport = function() {
	//  alert("report con");
	  console.log("inventoryReport::::::::");
    $http.get("listOfRpt").then(function(response){
    	$scope.reportlist = response.data;
        //console.log("Report Len:::"+$scope.reportlist.length);
    });
  };
  
  $scope.fetchReport();
  });