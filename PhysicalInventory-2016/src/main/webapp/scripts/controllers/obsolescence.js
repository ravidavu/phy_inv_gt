'use strict';

angular.module('inventoryApp')
  .controller('ObsolescenceCtrl', function ($scope, $timeout, $http,$filter) {
    
  $scope.recordsPerPage = 10;
  $scope.maxSize = 30;
  $scope.pageNo = 1;

  $scope.fetchAllProcess = function() {
    $http.get("getAllObs").then(function(response){
      $scope.skus = response.data;
     //   $scope.gridOptions.data =  $scope.skus; 

       $scope.dispalyData($scope.recordsPerPage);

    });

  };
  $scope.fetchAllProcess();
     $scope.dispalyData = function(size){
      if ($scope.skus.length > 0) {
      
      $scope.searchResult = $scope.skus;
      $scope.pagedSearchResult = $scope.searchResult.slice(0, $scope.recordsPerPage*5);
      $scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
		$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
		console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
    } else {
      $scope.searchResult = [];
      $scope.pagedSearchResult = [];
    }
    };
    
    $scope.searchProcess=function(){
		$scope.skus = $scope.searchResult;
		$scope.pageNo = 1;
		$scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
		$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
		console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
		if($scope.searchItem != ''){
			$scope.pagedSearchResult1 = $filter('filter')($scope.searchResult,$scope.searchItem);
			
			if ($scope.pagedSearchResult1.length > 0) {
				//$scope.searchResult = $scope.stores;
				$scope.pagedSearchResult = $scope.pagedSearchResult1
						.slice(0, $scope.recordsPerPage*5);
				$scope.skus = $scope.pagedSearchResult1;
				
				$scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
				$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
				console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
				
			} else {
				$scope.searchResult = [];
				$scope.pagedSearchResult = [];
			}
			
		}else{
			$scope.fetchAllProcess();
		}
		
	}
    
    
    $scope.idSelectedObsolescence = null;
	 $scope.setSelectedObsolescence = function(idSelectedObsolescence) {
	       $scope.idSelectedObsolescence = idSelectedObsolescence;
	       console.log(idSelectedObsolescence);
	    }
	 $scope.searchPageChange = function(pagenum) {
	    	console.log("coming to search page changes");
	    	console.log(pagenum);
	    var begin = ((pagenum - 1) * $scope.recordsPerPage*5), end = begin
	        + $scope.recordsPerPage*5;
	    console.log("begin "+begin +" end >>> "+end);
	    $scope.pagedSearchResult = $scope.skus.slice(begin,
	        end);
	    $scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
		$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
		console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
  /*$scope.searchPageChange = function(pagenum) {
	  console.log("xxxxxxxxxxx::"+pagenum);
    var begin = ((pagenum - 1) * $scope.recordsPerPage), end = begin
        + $scope.recordsPerPage;
    $scope.pagedSearchResult = $scope.searchResult.slice(begin,
        end);*/
  };

    $scope.datepickerOptions = {
      formatYear: 'yy',
      maxDate: new Date(2020, 5, 22),
      minDate: new Date(),
      startingDay: 1
    };


    $scope.exportData = function () {
        alasql('SELECT * INTO XLSX("Obsolescence.xlsx",{headers:true}) FROM ?',[$scope.searchResult]);
    };
    
    
    $scope.tableExport = function () {
    	var doc = new jsPDF();
    	var employees = $scope.searchResult;
        employees.forEach(function(employee, i){
            doc.text(20, 10 + (i * 10), 
                "First Name: " + employee.skuNo);
        });
        console.log("aaaaaaaaaaa");
        doc.saveAs('Test.pdf');
    };
    
    $scope.validateDate = function(){
		console.log("hhhhhhhhhhhhhhhhhhhhhh");
		if($scope.date){
			console.log("hhhhhhhhhhhhhhhhhhhhhh111111111");
			$scope.isvalidDate = false;
		}else{
			console.log("hhhhhhhhhhhhhhhhhhhhhh2222222222");
			//$scope.msg="Please enter  date .";
			$scope.isvalidDate = true;
			
		}
	}
    $scope.saveSkus = function (csv) {
    	console.log("hi obs:::::"+$scope.date);
      //var skus = csv.split(',');
    	$scope.isvalidDate = false;
		if($scope.date){
	      var skus = "";
	      console.log("Date:::::::::"+$scope.date);
	      /*skus = skus.map(function (s) {
	        return parseInt(s);
	      });
	*/
	    //  $timeout(function () {
	        $scope.skus.forEach(function (s) {
	          if (skus.indexOf(s.skuNo) > -1) {
	            s.isChecked = true;
	          }
	        });
	    //  });
	      $scope.csvSkuList = '';
		}else{
			console.log("IN ELSE:::::");
			$scope.isvalidDate = true;
			$scope.msg="Please enter  date .";
		}
    };

     $scope.selectedData = [];
    $scope.selectAll = function(check){
      if(check){
        $scope.skus.forEach(function (s) {
               s.isChecked = true;
               $scope.selectedData.push(s.skuNo);
               $scope.removeCheck1 = true;
               $scope.removeCheck2 = false;
         });
       // $scope.csvSkuList = $scope.selectedData.toString();
      }else{
         $scope.skus.forEach(function (s) {
               s.isChecked = false;
               $scope.csvSkuList = '';
               $scope.selectedData = [];
         });
      }

    };

    $scope.removeAll = function(check){
      if(check){
       $scope.skus.forEach(function (s) {
               s.isChecked = false;
               $scope.csvSkuList = '';
         });
       $scope.removeCheck2 = true;
       $scope.removeCheck1 = false;	
       var ss = $scope.removeCheck1;
       console.log("ss:::::::::"+ss);
       //ss=false;
     }
      
     };

  });
