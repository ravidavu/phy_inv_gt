'use strict';


angular.module('inventoryApp')
  .controller('InventoryReportCtrl', function ($scope, $timeout , $http,$stateParams) {
	//alert("coming to report ccccc");
	//$scope.fetchReport();
	  var storeNo =$stateParams.storeNo;
	  console.log($stateParams);
  $scope.fetchReport = function() {
	//  alert("report con");
	  console.log("inventoryReport::::::::");
    $http.get("listOfRpt?storeNo="+storeNo).then(function(response){
    	$scope.reportlist = response.data;
        //console.log("Report Len:::"+$scope.reportlist.length);
    });
  };
  
  $scope.fetchReport();
  });