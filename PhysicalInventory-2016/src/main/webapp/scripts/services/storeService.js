'use strict';


angular.module('inventoryApp')
  .service('storeService', function ($http, $q) {

    return {

      getStoresList: function () {
        return $http({
          method: 'GET',
          url: '../json/StoreJson.json'
        }).then(function successCallback(response) {
          return response.data;
        }, function errorCallback(response) {
          return $q.reject(response);
        });
      }

    };

  });
