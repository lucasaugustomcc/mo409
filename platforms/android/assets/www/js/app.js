angular.module('exampleApp', [
  'ui.router', 
  'ionic', 
  'LocalStorageModule', 
  'exampleApp.services', 
  'exampleApp.controllers',
  'ngMockE2E'
  ])
  .config(
[ '$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {      
      $stateProvider
    .state('app.login', {
      url: "/login",
      views: {
        'menuContent' :{
            controller:  "LoginController",
            templateUrl: "templates/login.html"              
        }
    }         
    })
    .state('app', {
      url: "/app",
      abstract: true,
      templateUrl: "templates/menu.html",
      //controller: 'MenuController'
    })    
    .state('aluno', {
      url: "/aluno",
      abstract: true,
      templateUrl: "templates/aluno/menu.html",
      //controller: 'MenuController'
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
      url: "/chamada/turma",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "AlunoChamadaCtrl",
            templateUrl: "templates/aluno/chamada_aluno.html"              
        }
    }         
    })    
    .state('aluno.checkin', {
      cache: false,
      url: "/chamada/checkin",
      views: {
        'menuContent' :{
            controller:  "AlunoCheckInCtrl",
            templateUrl: "templates/aluno/encerrar_chamada_aluno.html"              
        }
    }         
    })
    .state('aluno.checkout', {
      cache: false,
      url: "/chamada/checkout",
      views: {
        'menuContent' :{
            controller:  "AlunoCheckOutCtrl",
            templateUrl: "templates/aluno/chamada_encerrada_sucesso_aluno.html"              
        }
    }         
    })
	.state('aluno.disciplinas', {
      url: "/chamada/disciplinas_aluno",
      views: {
        'menuContent' :{
            controller:  "disciplinasAlunoCtrl", 
            templateUrl: "templates/aluno/disciplinas_aluno.html"              
        }
    }         
    })
	.state('aluno.frequencia', {
      cache: false,
      url: "chamada/frequencia",
      views: {
        'menuContent' :{
            controller:  "frequenciaAlunoCtrl", 
            templateUrl: "templates/professor/frequencia_aluno.html"              
        }
      },
      params: {
        "idTurma":null
      }          
    })
	.state('aluno.about', {
      url: "/about",
      views: {
        'menuContent' :{
            controller:  "", //colocar ontroller
            templateUrl: "templates/professor/about.html"              
        }
    }         
    })
    .state('professor', {
      url: "/professor",
      abstract: true,
      templateUrl: "templates/professor/menu.html",
      //controller: 'MenuController'
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
    .state('professor.chamadas-criadas', {
      url: "/chamada/aberta",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "ChamadasCriadasCtrl",
            templateUrl: "templates/professor/chamada_criadas_professor.html"             
        }
      }         
    })
    .state('professor.chamadas-abertas', {
      url: "/chamada/aberta",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "ChamadasAbertasCtrl",
            templateUrl: "templates/professor/encerrar_chamada_professor.html"             
        }
      }         
    })
   .state('professor.frequencia_aluno', {
      url: "/chamada/frequencia_aluno",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "frequenciaAluno_professorCtrl", //colocar controller
            templateUrl: "templates/professor/frequencia_aluno.html"     
        }
      },
      params: {
        "idTurma": null,
        "raAluno": null
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
  // para fazer a pesquisa de um aluno pelo RA ou nome
	.state('professor.alunos', {
      url: "/consulta/alunos",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "ProfessorConsultarAlunosCtrl",
            templateUrl: "templates/professor/pesquisar_alunos.html"             
        }
    }         
    })
  // para fazer a pesquisa de um aluno pelo RA ou nome
  .state('professor.resultado-consulta-alunos', {
      url: "/consulta/resultado",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "ProfessorResultadoConsultarAlunosCtrl",
            templateUrl: "templates/professor/lista_consulta_alunos_professor.html"             
        }
    }         
    })
  .state('professor.disciplinas', {
     url: "/chamada/disciplinas",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "disciplinasCtrl", //colocar controller
            templateUrl: "templates/professor/disciplinas_professor.html"             
        }
    }         
    })

  
	.state('professor.lista_alunos', {
      url: "/chamada/alunos_matriculados?idTurma",
      cache: false,
      views: {
        'menuContent' :{
            controller:  "alunosMatriculadosCtrl", //colocar controller
            templateUrl: "templates/professor/lista_alunos_professor.html"             
        }
    }         
    })

    .state('app.logout', {
      url: "/logout",
      cache: false,
      views: {
         'menuContent' :{
           controller: "LogoutController",
           templateUrl: "templates/login.html"
         }
      } 
    })
	 .state('professor.about', {
      url: "/about",
      views: {
         'menuContent' :{
           controller: "", //colocar controller
           templateUrl: "templates/professor/about.html"
         }
      } 
    })
	;
  //$urlRouterProvider.otherwise("/app/home");
      
      $locationProvider.hashPrefix('!');
      
      /* Register error provider that shows message on failed requests or redirects to login page on
       * unauthenticated requests */
        $httpProvider.interceptors.push(function ($q, $rootScope, $location, localStorageService) {
              return {
                'responseError': function(rejection) {
                  var status = rejection.status;
                  var config = rejection.config;
                  var method = config.method;
                  var url = config.url;
            
                  if (status == 401) {
                    localStorageService.remove('authToken');
                    delete $rootScope.user;
                    delete $rootScope.authToken;
                    $location.path( "/app/login" );
                    //$state.go("app.login");
                  } else {
                    $rootScope.error = method + " on " + url + " failed with status " + status;
                  }
                    
                  return $q.reject(rejection);
                }
              };
          }
        );
        
        /* Registers auth token interceptor, auth token is either passed by header or by query parameter
         * as soon as there is an authenticated user */
        $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
            return {
              'request': function(config) {
                if (angular.isDefined($rootScope.authToken)) {
                  var authToken = $rootScope.authToken;
                  if (authToken != null) {
                    config.headers['X-Auth-Token'] = authToken;
                  }
                }
                return config || $q.when(config);
              }
            };
        }
      );
       
    } ]
    
  ).run(function($rootScope, $ionicPlatform, $location, localStorageService, UserService, $httpBackend, $state) {
    
    $ionicPlatform.ready(function() {
      if(window.StatusBar) {
        // org.apache.cordova.statusbar required
        StatusBar.styleDefault();
      }
    });
    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function() {
      delete $rootScope.error;
    });

    // Mocking code used for simulation purposes (using ngMockE2E module)
    var turmas = [
    {
      "idTurma": "3",
      "codTurma": "A",
      "codDisciplina": "MO409",
      "nomeDisciplina": "Engenharia de Software I",
	  "alunos": {
		  "nome":"Luã Marcelo",
		  "raAluno":"163144"
	  }
    },
    {
      "idTurma": "5",
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
      "roles": { "ROLE_PROFESSOR": true}
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
      "horaInicio": "09:35",
      "parametros": {
          "tempo": 50,
          "porcTicks": 75
      },
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
      "parametros": {
          "tempo": 50,
          "porcTicks": 75
      },
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


    

    var parametros_get = { "duracao": 50, "porcentagem": 75 };
    var parametros_post = { "duracao": 60, "porcentagem": 80 };

    //disciplinas do professor para verificar frequência aluno
    var disciplinas = [
    { 
        "codTurma":"A",
        "codDisciplina":"MO409",
        "nomeDisciplina":"Engenharia de Software I",
        "numAlunos":35
        //"professores": [ { "nome": "Eliane Martins" } ]
     },
     {
      "codTurma":"B",
      "codDisciplina":"MO410",
      "nomeDisciplina":"Engenharia de Software II",
      "numAlunos":25
        //"professores": [ { "nome": "Eliane Martins" } ]
     }
     ];

  var chamadaAberta_professor = 
    {
        
        "idChamada": 1,
        "horaInicio": "10:00",
        "horaFim": "12:00",
        "dataChamada": "10/06/2015",
        "numPresentes": "30/50",
        "turma":
        {
          "idTurma": 1,
          "codTurma":"A",
          "codDisciplina":"MC806",
          "nomeDisciplina":"Tópicos em Engenharia de Software",
          "nomeProfessor":"Eliane Martins"
        }
      };

    var alunos_matriculados = {

      "turma" : {
          "idTurma": "1",
          "codTurma":"A",
          "codDisciplina":"MC302",
          "nomeDisciplina":"Programação Orientada a Objetos",
          "numAlunos": 35,
          "numChamadas": 15,
          "porcPresencas":75,
          "porcFaltas":25
      },
      "alunos" : [
        {
          "raAluno": "161255",
          "nome":"Lucas Augusto Carvalho",
          "numPresencas":10,
          "porcPresencas":66,
          "numFaltas":5,
          "porcFaltas":34
        },
        {
          "raAluno": "121551",
          "nome":"João Ninguém",
          "numPresencas":10,
          "porcPresencas":66,
          "numFaltas":5,
          "porcFaltas":34
   }]
};
  //professor visualiza a frequência do aluno
   var frequencia_aluno = {

      "turma" : {
          "idTurma": "1",
          "codTurma":"A",
          "codDisciplina":"MC302",
          "nomeDisciplina":"Programação Orientada a Objetos",
          "numAlunos": 38,
          "numChamadas": 15
      },
      "alunos" : 
        {
          "raAluno": "161255",
          "nome":"Lucas Augusto Carvalho",
          "numPresencas":10,
          "porcPresencas":66,
          "numFaltas":5,
          "porcFaltas":34
        },
      "presencas": [
        {
            "data": "02/05/2015",
            "presente":true
        },
        {
            "data": "04/05/2015",
            "presente":false
        }]
    };

    //disciplinas para aluno visulizar frequência
    var disciplinas_aluno = [
    { 
        "codTurma":"A",
        "codDisciplina":"MO409",
        "nomeDisciplina":"Engenharia de Software I",
        "numAlunos":35
        //"professores": [ { "nome": "Eliane Martins" } ]
     },
     {
      "codTurma":"B",
      "codDisciplina":"MO410",
      "nomeDisciplina":"Engenharia de Software II",
      "numAlunos":25
        //"professores": [ { "nome": "Eliane Martins" } ]
     }
     ];

  //aluno visualiza suas frequências
  var frequencia = {

      "turma" : {
          "idTurma": "1",
          "codTurma":"A",
          "codDisciplina":"MC302",
          "nomeDisciplina":"Programação Orientada a Objetos",
          "numChamadas": 15
      },
      "frequencia" : 
        {
          "numPresencas":10,
          "porcPresencas":66,
          "numFaltas":5,
          "porcFaltas":34
        },
      "presencas": [
        {
            "data": "02/05/2015",
            "presente":true
        },
        {
            "data": "04/05/2015",
            "presente":false
        }]
    };
    


    // returns the current list of customers or a 401 depending on authorization flag
    
    // $httpBackend.whenGET('http://www.webulando.com.br/mo409/rest/user').respond(function (method, url, data, headers) {
    //   return [200, usuario];
    // });

    // $httpBackend.whenGET('http://www.webulando.com.br/mo409/professor/chamada').respond(function (method, url, data, headers) {
    // return [200, turmas];
    // });

    // exibir a tela com os dados de uma chamada aberta
    //  $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/abrir').respond(function (method, url, data, headers) {
    //    return [200, chamadas];
    //  });

    // $httpBackend.whenGET('http://www.webulando.com.br/mo409/professor/chamada/parametros').respond(function(method, url, data) {
    //    return [200, parametros_get];
    //  });

    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/parametros').respond(function(method, url, data) {
    //    return [200, parametros_post];
    //  });
 /*
    $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/encerrar').respond(function (method, url, data, headers) {
      return [200, chamadaPresenca];
    }); */

    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/presenca').respond(function (method, url, data, headers) {
    //   return [200, chamadaPresenca];
    // });


    // $httpBackend.whenGET('http://www.webulando.com.br/mo409/aluno/chamada').respond(function (method, url, data, headers) {
    //   return [200, alunoChamada];
    // });

 // $httpBackend.whenGET('http://www.webulando.com.br/mo409/professor/chamada/disciplinas').respond(function (method, url, data) {
 //    return [200, disciplinas];
 //    });

 // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/professor/chamada/alunos_matriculados').respond(function (method, url, data) {
 //    return [200, alunos_matriculados];
 //    });

 // $httpBackend.whenGET('http://www.webulando.com.br/mo409/professor/chamada/frequencia_aluno').respond(function (method, url, data) {
 //    return [200, frequencia_aluno];
 //    });

 //    $httpBackend.whenGET('http://www.webulando.com.br/mo409/aluno/chamada/turma').respond(function (method, url, data) {
 //    return [200, alunoChamada];
 //    });


    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/aluno/chamada/checkin').respond(function (method, url, data, headers) {
    //   return [200, chamadaTick];
    // });

    // $httpBackend.whenGET('http://www.webulando.com.br/mo409/aluno/chamada/disciplinas_aluno').respond(function (method, url, data) {
    // return [200, disciplinas_aluno];
    // });

    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/aluno/chamada/frequencia').respond(function (method, url, data) {
    // return [200, frequencia];
    // });

    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/aluno/chamada/checkout').respond(function (method, url, data, headers) {
    //   return [200, chamadaTick];
    // });

    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/login').respond(function(method, url, data) {
    //   var authorizationToken = 'UhLP83FSzhl3SnLjqCcDH96b6I0QkdSOq4zcndTpJmQ=';
    //   return  [200 , { authorizationToken: authorizationToken, usuario: usuario } ];
    // });

    // $httpBackend.whenPOST('http://www.webulando.com.br/mo409/logout').respond(function(method, url, data) {
    //   return [200];
    // });

     // All other http requests will pass through
    $httpBackend.whenGET(/.*/).passThrough();
    $httpBackend.whenPOST(/.*/).passThrough();
    
    $rootScope.hasRole = function(role) {
      
      if ($rootScope.user === undefined) {
        return false;
      }
      
      if ($rootScope.user.roles[role] === undefined) {
        return false;
      }
      
      return $rootScope.user.roles[role];
    };
    
    $rootScope.logout = function() {
      delete $rootScope.user;
      delete $rootScope.authToken;
      localStorageService.remove('authToken');
      $location.path("/app/login");
    };
    
     /* Try getting valid user from cookie or go to login page */
    var originalPath = $location.path();
    $location.path("/app/login");
    $state.go("app.login");
    var authToken = localStorageService.get('authToken');
    console.log('token:' + authToken);
    if (authToken !== undefined && authToken !== null) {
      $rootScope.authToken = authToken;
      UserService.get(function(user) {
        console.log("user: " + user);
        $rootScope.user = user;
        $location.path(originalPath);
        console.dir(user);
        if (user.roles.ROLE_PROFESSOR)
        {
          $state.go("professor.home");
        }
        if (user.roles.ROLE_ALUNO)
        {
          $state.go("aluno.home");
        }
      }, 
      function err() {  
        localStorageService.remove('authToken');
        delete $rootScope.authToken;
        console.log('erro autenticacao');
      });
    }
    
    $rootScope.initialized = true;
  });