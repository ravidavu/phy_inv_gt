'use strict';


angular.module('inventoryApp')
  .service('ReportService', function ($http, $q) {
console.log("Report service >> ");
    return {
    	
    	fetchAllCosting: function () {
    		console.log("fetchProcess >> ");
        return $http({
          method: 'GET',
          url: 'http://localhost:8081/PhysicalInventory/getAllStores'
         
        }).then(function successCallback(response) {
        	console.log(response);
          return response.data;
        }, function errorCallback(response) {
        	console.log("error sercice >>");
        	console.log(response);
          return $q.reject(response);
        });
      }

     };

  });
