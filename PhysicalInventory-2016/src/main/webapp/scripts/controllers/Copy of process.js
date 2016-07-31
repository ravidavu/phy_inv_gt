'use strict';

angular
		.module('inventoryApp')
		.controller(
				'ProcessCtrl',
				function($scope, $timeout, $http,$filter) {

					// /$scope.stores=storeService.getStoresList();
					// $scope.stores = data;
					$scope.recordsPerPage = 10;
					$scope.maxSize = 30;
					$scope.pageNo = 1;

					$scope.fetchAllProcess = function() {
						$http.get("listOsProcess").then(function(response) {
							//console.log(response.data);
							//console.log("after getting response");
							$scope.stores = response.data;
							// new
							// console.log($scope.stores);
							$scope.stores.forEach(function(s) {
								if (s.status) {
									s.isChecked = true;
								}
								//
								// $scope.totalData.push(s.storeNo);
							});// end
							$scope.dispalyData($scope.recordsPerPage);
						});

					}

					$scope.fetchAllProcess();

					$scope.idSelectedCosting = null;
					$scope.setSelectedCosting = function(idSelectedCosting) {
						$scope.idSelectedCosting = idSelectedCosting;
						// console.log(idSelectedCosting);
					}

					$scope.dispalyData = function(size) {
						if ($scope.stores.length > 0) {

							$scope.searchResult = $scope.stores;
							$scope.pagedSearchResult = $scope.searchResult
									.slice(0, $scope.recordsPerPage * 5);
							
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
						$scope.pageNo = 1;
						$scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
						$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
						console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
						if($scope.searchItem != ''){				
							$scope.pagedSearchResult1 = $filter('filter')($scope.searchResult,$scope.searchItem);	
							if ($scope.pagedSearchResult1.length > 0) {					
								//$scope.searchResult = $scope.stores;					
								$scope.pagedSearchResult = $scope.pagedSearchResult1.slice(0, $scope.recordsPerPage*5);							
								$scope.stores = $scope.pagedSearchResult1;		
								} else {										
									$scope.searchResult = [];					
									$scope.pagedSearchResult = [];					
									}									
							}else{												
								$scope.fetchAllProcess();		
								}						
							}
						//}
					//}
					// $scope.gridOptions = {
					// headerTemplate: 'views/templates/process-header.html',
					// paginationPageSizes: [25, 50, 75],
					// paginationPageSize: 25,
					// columnDefs: [
					// {name: 'id', title: 'Stores List'},
					// {name: 'isChecked', title: 'Checkbox', cellTemplate:
					// 'views/templates/process-checkbox.html'}
					// ],
					// data: $scope.stores
					// };

					$scope.searchPageChange = function(pagenum) {
						var begin = ((pagenum - 1) * $scope.recordsPerPage * 5), end = begin
								+ $scope.recordsPerPage * 5;
						$scope.pagedSearchResult = $scope.stores.slice(
								begin, end);
						
						$scope.startIndex = (($scope.pageNo - 1) * $scope.recordsPerPage * 5);
						$scope.endIndex  = $scope.startIndex + $scope.recordsPerPage * 5;
						console.log("hi:"+$scope.startIndex+":"+$scope.endIndex );
					};

					$scope.datepickerOptions = {
						formatYear : 'yy',
						maxDate : new Date(2020, 5, 22),
						minDate : new Date(),
						startingDay : 1
					};

					$scope.selectedData = [];
					// getting all storeNo
					$scope.totalData = [];
					$scope.uncheckList = [];

					$scope.selectAll = function(check) {
						// console.log($scope.stores);
						if (check) {
							$scope.stores.forEach(function(s) {
								s.isChecked = true;
								$scope.selectedData.push(s.storeNo);
								$scope.totalData.push(s.storeNo);
								$scope.removeCheck1 = true;
								$scope.removeCheck2 = false;
							});
							// $scope.csvStoreList =
							// $scope.selectedData.toString();
						} else {
							$scope.stores.forEach(function(s) {
								s.isChecked = false;
								$scope.csvStoreList = '';
								$scope.selectedData = [];
								$scope.totalData.push(s.storeNo);
								$scope.uncheckList.push(s.storeNo);
							});
						}

					};

					$scope.removeAll = function(check) {
						if (check) {
							$scope.stores.forEach(function(s) {
								s.isChecked = false;
								$scope.csvStoreList = '';
								$scope.removeCheck2 = true;
								$scope.removeCheck1 = false;
								$scope.uncheckList.push(s.storeNo);

							});
						}
					};

					
					 $scope.exportData = function () {
					        alasql('SELECT storeNo,status INTO XLSX("Process.xlsx",{headers:true}) FROM ?',[$scope.stores]);
					    };
					
					// new
					Array.prototype.remove = function(set) {
						return this.filter(function(e, i, a) {
							return set.indexOf(e) < 0
						})
					};
					
					$scope.approve = function(){
                        var processDate = $scope.date;
                        //selected data
                        $scope.selectedData = [];
                        $scope.stores.forEach(function(s) {
    
                            if (s.isChecked == true) {
                                $scope.selectedData.push(s.storeNo);
                            } 
                        });
                            $http({
                                url : 'approve',
                                method : 'POST',
                                data : {
                                    storeNoList : $scope.selectedData
                                }
                            }).success(function() {
                                alert("approve successully updated..");
                            });
                    };
					
                    
					$scope.saveStores = function(csv) {
						
						var processDate = $filter('date')($scope.date, 'yyyy-MM-dd');
						console.log("Date:::::::::"+processDate);
						
						$scope.isvalidDate = false;
						if($scope.date){
							
							$scope.selectedData = [];
							$scope.uncheckList = [];
							$scope.stores.forEach(function(s) {

								$scope.totalData.push(s.storeNo);
								if (s.isChecked == true) {
									$scope.selectedData.push(s.storeNo);
								} else {
									$scope.uncheckList.push(s.storeNo);
								}

							});

							if (csv != null && csv.length > 0) {
								var stores = csv.split(',');
								$scope.selectedData = [];
								$scope.uncheckList = [];
								$scope.selectedData = $scope.totalData;

								$scope.selectedData = $scope.selectedData
										.remove(stores);
								$scope.uncheckList = stores;
								// console.log($scope.selectedData );

								// insert into store table end
								// console.log("save textbox data");
								// console.log(stores);
								$scope.stores.forEach(function(s) {
									if (stores.indexOf(s.storeNo) != -1) {
										s.isChecked = false;
									}
								});
							}

							$scope.csvStoreList = '';
							// console.log($scope.selectedData);

							// new code
							$http({
								url : 'createStr',
								method : 'POST',
								// contentType: "text/html",
								// data:{storeNoList: $scope.selectedData}
								data : {
									storeNoList : $scope.selectedData,
									uncheckList : $scope.uncheckList
								}
							}).success(function() {
								$scope.fetchAllProcess();
								// do something on success
								alert("Inventory Updated Successffully!!");

							});
						}else{
							console.log("IN ELSE:::::");
							$scope.isvalidDate = true;
							$scope.msg="Please enter  date .";
						}
						

					};// end

				});
