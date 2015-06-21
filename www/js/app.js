angular.module('exampleApp', ['ui.router', 'ionic', 'LocalStorageModule', 'exampleApp.services', 'exampleApp.controllers'])
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
      controller: 'MenuController'
    })
    .state('professor', {
      url: "/professor",
      abstract: true,
      templateUrl: "templates/professor/menu.html",
      controller: 'MenuController'
    })
    .state('aluno', {
      url: "/aluno",
      abstract: true,
      templateUrl: "templates/aluno/menu.html",
      controller: 'MenuController'
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
           controller: "LogoutController",
           templateUrl: "templates/login.html"
         }
      } 
    });
  //$urlRouterProvider.otherwise("/app/home");
      
      $locationProvider.hashPrefix('!');
      
      /* Register error provider that shows message on failed requests or redirects to login page on
       * unauthenticated requests */
        $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
              return {
                'responseError': function(rejection) {
                  var status = rejection.status;
                  var config = rejection.config;
                  var method = config.method;
                  var url = config.url;
            
                  if (status == 401) {
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
                  if (exampleAppConfig.useAuthTokenHeader && authToken != null) {
                    config.headers['X-Auth-Token'] = authToken;
                  } else if (authToken != null) {
                    config.url = config.url + "?token=" + authToken;
                  }
                }
                return config || $q.when(config);
              }
            };
        }
      );
       
    } ]
    
  ).run(function($rootScope, $ionicPlatform, $location, localStorageService, UserService, $state) {
    
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
    if (authToken !== undefined) {
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
      });
    }
    
    $rootScope.initialized = true;
  });