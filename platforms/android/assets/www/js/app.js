// ionic-http-auth was made from the ionic-starter-app sideMenu
// to create a new app, at a command prompt type this: ionic start appname sideMenu

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'ionic-http-auth' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'ionic-http-auth.controllers' is found in controllers.js
// 'ionic-http-auth.services is' found in services.js
angular.module('ionic-http-auth', [
  'ionic',
  'ngMockE2E',
  'LocalStorageModule',
  'ionic-http-auth.services',
  'ionic-http-auth.controllers'])

  .run(function($rootScope, $ionicPlatform, $httpBackend, localStorageService) {

	$ionicPlatform.ready(function() {
    if(window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
  
  // Mocking code used for simulation purposes (using ngMockE2E module)
  var turmas = [
  {
    "idTurma": "1",
    "codTurma": "A",
    "codDisciplina": "MO409",
    "nomeDisciplina": "Engenharia de Software I"
  },
  {
    "idTurma": "2",
    "codTurma": "B",
    "codDisciplina": "MC409",
    "nomeDisciplina": "Engenharia de Software II"
  }];

  var usuario = 
  {
    "id": "1",
    "nome": "Lucas Carvalho",
    "email": "ra1612655@dac.unicamp.br",
    "ra": "161255",
    "papel": "ROLE_PROFESSOR"
  }
;

  var chamadaTick = 
  {
    "idTick": "1",
    "dataHoraTick": "20/05/2015 10:00",
    "chamada": { 
      "dataChamada": "06-15-2015",
      "idChamada": 1,
      "horaInicio": "09:35"
    },
    "turma": {
      "codDisciplina": "MO409",
      "nomeDisciplina": "Engenharia de Software I",
      "nomeProfessor": "Eliana Martins",
      "idTurma": 1,
      "codTurma": "A"
    }    
  };

  var alunoChamada = 
  {
    "dataChamada": "06-15-2015",
    "idChamada": 1,
    "turma": {
      "codDisciplina": "MO409",
      "nomeDisciplina": "Engenharia de Software I",
      "nomeProfessor": "Eliana Martins",
      "idTurma": 1,
      "codTurma": "A"
    },
    "horaInicio": "09:35"
  };

  var chamadas = [
  {
    "dataChamada": "06-15-2015",
    "idChamada": 1,
    "turma": {
      "codDisciplina": "MO409",
      "nomeDisciplina": "Engenharia de Software I",
      "idTurma": 1,
      "codTurma": "A"
    },
    "horaInicio": "09:35"
  },
  {
    "dataChamada": "06-15-2015",
    "idChamada": 2,
    "turma": {
      "codDisciplina": "MC626",
      "nomeDisciplina": "Análise e Projeto de Sistema de Informação",
      "idTurma": 2,
      "codTurma": "A"
    },
    "horaInicio": "09:35"
  }
  ];

  var chamadasEncerradas = [
  {
    "dataChamada": "06-15-2015",
    "idChamada": 1,
    "turma": {
      "codDisciplina": "MO409",
      "nomeDisciplina": "Engenharia de Software I",
      "idTurma": 1,
      "codTurma": "A"
    },
    "horaInicio": "09:35",
    "horaFim": "12:35"
  },
  {
    "dataChamada": "06-15-2015",
    "idChamada": 2,
    "turma": {
      "codDisciplina": "MC626",
      "nomeDisciplina": "Análise e Projeto de Sistema de Informação",
      "idTurma": 2,
      "codTurma": "A"
    },
    "horaInicio": "09:35",
    "horaFim": "12:35"
  }
  ];

  var chamadaPresenca = [
  {
    "dataChamada": "06-15-2015",
    "idChamada": 1,
    "turma": {
      "codDisciplina": "MO409",
      "nomeDisciplina": "Engenharia de Software I",
      "idTurma": 1,
      "codTurma": "A"
    },
    "horaInicio": "09:35",
    "horaFim": "12:35",
    "frequencia": [
      {
      "raAluno": "161255",
      "nome":"Lucas Augusto Carvalho",
      "status":"Presente"
      },
      {
      "raAluno": "121551",
      "nome":"João Ninguém",
      "status":"Ausente"
      }]
  },
  {
    "dataChamada": "06-15-2015",
    "idChamada": 2,
    "turma": {
      "codDisciplina": "MC626",
      "nomeDisciplina": "Análise e Projeto de Sistema de Informação",
      "idTurma": 2,
      "codTurma": "A"
    },
    "horaInicio": "09:35",
    "horaFim": "12:35",
    "frequencia": [
      {
      "raAluno": "161255",
      "nome":"Lucas Augusto Carvalho",
      "status":"Presente"
      },
      {
      "raAluno": "121551",
      "nome":"João Ninguém",
      "status":"Ausente"
      }]
  }
  ];
  
  // returns the current list of customers or a 401 depending on authorization flag
  $httpBackend.whenGET('http://www.webulando.com.br/mo409/professor/chamada/turmas').respond(function (method, url, data, headers) {
    var authToken = localStorageService.get('authorizationToken');
	  return authToken ? [200, turmas] : [401];
  });

  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/abrir').respond(function (method, url, data, headers) {
    var authToken = localStorageService.get('authorizationToken');
    return authToken ? [200, chamadas] : [401];
  });

  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/encerrar').respond(function (method, url, data, headers) {
    var authToken = localStorageService.get('authorizationToken');
    return authToken ? [200, chamadaPresenca] : [401];
  });

  $httpBackend.whenGET('http://www.webulando.com.br/mo409/aluno/chamada').respond(function (method, url, data, headers) {
    var authToken = localStorageService.get('authorizationToken');
    return authToken ? [200, alunoChamada] : [401];
  });

  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/aluno/chamada/checkin').respond(function (method, url, data, headers) {
    var authToken = localStorageService.get('authorizationToken');
    return authToken ? [200, chamadaTick] : [401];
  });

  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/aluno/chamada/checkout').respond(function (method, url, data, headers) {
    var authToken = localStorageService.get('authorizationToken');
    return authToken ? [200, chamadaTick] : [401];
  });

  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/login').respond(function(method, url, data) {
    var authorizationToken = 'UhLP83FSzhl3SnLjqCcDH96b6I0QkdSOq4zcndTpJmQ=';
    return  [200 , { authorizationToken: authorizationToken, usuario: usuario } ];
  });

  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/logout').respond(function(method, url, data) {
    return [200];
  });

  // All other http requests will pass through
  $httpBackend.whenGET(/.*/).passThrough();
  
})

.config(function($stateProvider, $urlRouterProvider) {

  $stateProvider
  
    .state('app', {
      url: "/app",
      abstract: true,
      templateUrl: "templates/menu.html",
      controller: 'AppCtrl'
    })
    .state('professor', {
      url: "/professor",
      abstract: true,
      templateUrl: "templates/professor/menu.html",
      controller: 'AppCtrl'
    })
    .state('aluno', {
      url: "/aluno",
      abstract: true,
      templateUrl: "templates/aluno/menu.html",
      controller: 'AppCtrl'
    })
    .state('app.home', {
      url: "/home",
	    views: {
	      'menuContent' :{
	          controller:  "HomeCtrl",
	          templateUrl: "templates/home.html"            	
	      }
	  }      	  
    })
    .state('aluno.home', {
      url: "/home",
      views: {
        'menuContent' :{
            controller:  "HomeCtrl",
            templateUrl: "templates/aluno/home.html"              
        }
    }         
    })
    .state('aluno.chamada', {
      url: "/chamada",
      views: {
        'menuContent' :{
            controller:  "AlunoChamadaCtrl",
            templateUrl: "templates/aluno/chamada_aluno.html"              
        }
    }         
    })    
    .state('aluno.checkin', {
      url: "/chamada",
      views: {
        'menuContent' :{
            controller:  "AlunoCheckInCtrl",
            templateUrl: "templates/aluno/encerrar_chamada_aluno.html"              
        }
    }         
    })
    .state('aluno.checkout', {
      url: "/chamada",
      views: {
        'menuContent' :{
            controller:  "AlunoCheckOutCtrl",
            templateUrl: "templates/aluno/chamada_encerrada_sucesso_aluno.html"              
        }
    }         
    })
    .state('professor.home', {
      url: "/home",
      views: {
        'menuContent' :{
            controller:  "HomeCtrl",
            templateUrl: "templates/professor/home.html"              
        }
    }         
    })
    .state('professor.chamada-turmas', {
      url: "/chamada/turmas",
      cache: false,
	    views: {
	      'menuContent' :{
	          controller:  "ChamadaTurmasCtrl",
	          templateUrl: "templates/professor/chamada_professor.html"            	
	      }
	  }      	  
    })
    .state('professor.chamada-aberta', {
      url: "/chamada/aberta",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "ChamadaAbertaCtrl",
            templateUrl: "templates/professor/encerrar_chamada_professor.html"             
        }
    }         
    })
    .state('professor.chamada-presenca', {
      url: "/chamada/presenca",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "ChamadaPresencaCtrl",
            templateUrl: "templates/professor/lista_presenca_aula_professor.html"             
        }
    }         
    })
    .state('app.logout', {
      url: "/logout",
      views: {
    	   'menuContent' :{
    		   controller: "LogoutCtrl",
           templateUrl: "templates/home.html"
         }
      } 
    });
  $urlRouterProvider.otherwise("/app/home");
});
