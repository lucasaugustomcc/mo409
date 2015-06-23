var services = angular.module('exampleApp.services', ['ngResource']);

services.factory('UserService', function($resource) {
  
  return $resource(exampleAppConfig.host+'/rest/user/:action', {},
      {
        authenticate: {
          method: 'POST',
          params: {'action' : 'authenticate'},
          headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        },
      }
    );
});
services.factory('MenuService', function() {

  var menuItems = [
      { text: '1 Page One', iconClass: 'icon ion-map', link: 'one'},
      { text: '2 Page Two', iconClass: 'icon ion-gear-b', link: 'two'},
      { text: '3 Page Three', iconClass: 'icon ion-star', link: 'three'}
  ];

  return {
    all: function() {
      return menuItems;
    }
  }
});
services.factory('ChamadaService', function($resource) {
  
  return $resource(exampleAppConfig.host+'/professor/chamada/:action', {},
    {
        turmas: {
          method: 'GET',
          params: {'action' : 'turmas'},
          isArray: true
        },
        abrir: {
          method: 'POST',
          params: {'action' : 'abrir'},
          isArray: true,
          headers : {'Content-Type': 'application/json'}
        },
        encerrar: {
          method: 'POST',
          params: {'action' : 'encerrar'},
          isArray: true,
          headers : {'Content-Type': 'application/json'}
        },
    } 
  );
});
services.factory('myService', function() {
 var savedData = {}
 function set(data) {
   savedData = data;
 }
 function get() {
  return savedData;
 }

 return {
  set: set,
  get: get
 }
})