function resourceErrorHandler(e)
{
  alert(e.data.message);
}

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
services.factory('ChamadaService', function($resource) {
  
  return $resource(exampleAppConfig.host+'/professor/chamada/:action', {},
    {
        turmas: {
          method: 'GET',
          params: {'action' : 'turmas'},
          isArray: true,
          interceptor : {responseError : resourceErrorHandler}
        },
        abertas: {
          method: 'GET',
          params: {'action' : 'abertas'},
          isArray: true,
          interceptor : {responseError : resourceErrorHandler}
        },
        abrir: {
          method: 'POST',
          params: {'action' : 'abrir'},
          isArray: true,
          headers : {'Content-Type': 'application/json'},
          interceptor : {responseError : resourceErrorHandler}
        },
        encerrar: {
          method: 'POST',
          params: {'action' : 'encerrar'},
          isArray: true,
          headers : {'Content-Type': 'application/json'},
          interceptor : {responseError : resourceErrorHandler}
        },
        parametros: {
          method: 'GET',
          params: {'action' : 'parametros'},
          interceptor : {responseError : resourceErrorHandler}
        },
        alterarparametros: {
          method: 'POST',
          params: {'action' : 'parametros'},
          interceptor : {responseError : resourceErrorHandler}
        },
        disciplinas: {
          method: 'GET',
          params: {'action' : 'disciplinas'},
          isArray: true,
          interceptor : {responseError : resourceErrorHandler}
        },
        alunos_matriculados: {
          method: 'POST',
          params: {'action' : 'alunos_matriculados'},
          headers : {'Content-Type': 'application/json'},
          interceptor : {responseError : resourceErrorHandler}
        },
         frequencia_aluno: {
          method: 'GET',
          params: {'action' : 'frequencia_aluno'},
         // isArray: true,
          interceptor : {responseError : resourceErrorHandler}
        },
    } 
  );
});
services.factory('AlunoService', function($resource) {
  
  return $resource(exampleAppConfig.host+'/aluno/chamada/:action', {},
    {
        chamada: {
          method: 'GET',
          params: {'action' : 'turmas'},
          interceptor : {responseError : resourceErrorHandler}
        },
        presenca: {
          method: 'GET',
          params: {'action' : 'presenca'},
          headers : {'Content-Type': 'application/json'},
          interceptor : {responseError : resourceErrorHandler}
        },
        checkin: {
          method: 'POST',
          params: {'action' : 'checkin'},
          headers : {'Content-Type': 'application/json'},
          interceptor : {responseError : resourceErrorHandler}
        },
        checkout: {
          method: 'POST',
          params: {'action' : 'checkout'},
          headers : {'Content-Type': 'application/json'},
          interceptor : {responseError : resourceErrorHandler}
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