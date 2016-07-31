'use strict';


angular.module('inventoryApp')
  .controller('CostingCtrl', function ($scope, $timeout , $http,$filter) {
  $scope.recordsPerPage = 10;
  $scope.maxSize = 30;
  $scope.pageNo = 1;
  
  $scope.fetchAllProcess = function() {
    $http.get("getAllStores").then(function(response){
      $scope.stores = response.data;
      // $scope.gridOptions.data = $scope.stores;
      
       $scope.dispalyData($scope.recordsPerPage);
      /* console.log("============== ");
       console.log($scope.stores);
       console.log($scope.recordsPerPage);
       console.log( $scope.dispalyData);*/

    });
  };
  
  $scope.fetchAllProcess();
    $scope.idSelectedCosting = null;
	 $scope.setSelectedCosting = function(idSelectedCosting) {
	       $scope.idSelectedCosting = idSelectedCosting;
	       //console.log(idSelectedCosting);
	    }

    $scope.dispalyData = function(size){
    
      if ($scope.stores.length > 0) {

      $scope.searchResult = $scope.stores;
      $scope.pagedSearchResult = $scope.searchResult.slice(0, $scope.recordsPerPage*5);
      //console.log($scope.pagedSearchResult);
      $scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
		$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
		console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
    } else {
      $scope.searchResult = [];
      $scope.pagedSearchResult = [];
    }
    };

    $scope.searchProcess=function(){
		$scope.stores = $scope.searchResult;
		$scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
		$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
		console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
		console.log("hi:"+$scope.searchItem);
		if($scope.searchItem != ''){
			console.log("hi11:"+$scope.searchItem);
			$scope.pagedSearchResult1 = $filter('filter')($scope.searchResult,$scope.searchItem);
			
			if ($scope.pagedSearchResult1.length > 0) {
				console.log("hi000:"+$scope.searchItem);
				//$scope.searchResult = $scope.stores;
				$scope.pagedSearchResult = $scope.pagedSearchResult1
						.slice(0, $scope.recordsPerPage*5);
				$scope.stores = $scope.pagedSearchResult1;
				console.log("hi999:"+$scope.searchItem);
			} else {
				console.log("hi:88"+$scope.searchItem);
				$scope.searchResult = [];
				$scope.pagedSearchResult = [];
			}
			
			
			console.log("hi22:"+$scope.pagedSearchResult);
		}else{
			console.log("hi333:"+$scope.searchItem);
			$scope.fetchAllProcess();
		}
		
	}
   /* $scope.gridOptions = {
      headerTemplate: 'views/templates/process-header.html',
      paginationPageSizes: [10,25, 50, 75],
      paginationPageSize: 10,
      columnDefs: [
        {name: 'storeNo', title: 'Stores List'},
        {name: 'isChecked', title: 'Checkbox', cellTemplate: 'views/templates/process-checkbox.html'}
      ]

    };*/

    $scope.searchPageChange = function(pagenum) {
    	console.log("coming to search page changes");
    	console.log(pagenum);
    var begin = ((pagenum - 1) * $scope.recordsPerPage*5), end = begin
        + $scope.recordsPerPage*5;
    console.log("begin "+begin +" end >>> "+end);
    $scope.pagedSearchResult = $scope.stores.slice(begin,
        end);
    $scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
	$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
	console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
  };

    $scope.datepickerOptions = {
      formatYear: 'yy',
      maxDate: new Date(2020, 5, 22),
      minDate: new Date(),
      startingDay: 1
    };

    
    $scope.exportData = function () {
        alasql('SELECT * INTO XLSX("Costing.xlsx",{headers:true}) FROM ?',[$scope.stores]);
    };
    

    $scope.saveStores = function (csv) {
      var stores = csv.split(',');

     /* stores1= stores.map(function (s) {
        return parseInt(s);
      });*/

    //  $timeout(function () {
        $scope.stores.forEach(function (s) {
          if (stores.indexOf(s.storeNo) != -1) {
            s.isChecked = true;
          }
        });
    //  });
    /*$scope.gridOptions.data = [];
      $scope.gridOptions.data = $scope.stores;
       $scope.refresh = true;
        $timeout(function() {
        $scope.refresh = false;
       }, 0);*/
     // $scope.gridOptions.api.refreshView();

      $scope.csvStoreList = '';
    };

    $scope.selectedData = [];
    $scope.selectAll = function(check){
      if(check){
        $scope.stores.forEach(function (s) {
               s.isChecked = true;
               $scope.selectedData.push(s.storeNo);
               $scope.removeCheck1 = true;
               $scope.removeCheck2 = false;
         });

      /*   $scope.gridOptions.data = [];
      $scope.gridOptions.data = $scope.stores;
       $scope.refresh = true;
        $timeout(function() {
        $scope.refresh = false;
       }, 0);*/
       // $scope.csvStoreList = $scope.selectedData.toString();
      }else{
         $scope.stores.forEach(function (s) {
               s.isChecked = false;
               $scope.csvStoreList = '';
               $scope.selectedData = [];
         });
      }

    };

    $scope.removeAll = function(check){
      if(check){
       $scope.stores.forEach(function (s) {
               s.isChecked = false;
               $scope.csvStoreList = '';
               $scope.removeCheck2 = true;
               $scope.removeCheck1 = false;	
         });
     }
     };

  });
