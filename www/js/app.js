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

  .run(function($http, $rootScope, $ionicPlatform, $httpBackend, localStorageService) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
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

var ENV = (function() {
    
    var localStorage = window.localStorage;

    return {
        settings: {
            /**
            * state-mgmt
            */
            enabled:    localStorage.getItem('enabled')     || 'true',
            aggressive: localStorage.getItem('aggressive')  || 'false'
        },
        toggle: function(key) {
            var value       = localStorage.getItem(key)
                newValue    = ((new String(value)) == 'true') ? 'false' : 'true';

            localStorage.setItem(key, newValue);
            return newValue;
        }
    }
})()

var app = {
    /**
    * @property {google.maps.Marker} location The current location
    */
    location: undefined,
    /**
    * @property {google.map.PolyLine} path The list of background geolocations
    */
    path: undefined,
    
    /**
    * @property {Array} locations List of rendered map markers of prev locations
    */
    locations: [],
    /**
    * @private
    */
    btnEnabled: undefined,
    btnPace: undefined,
    btnHome: undefined,
    btnReset: undefined,

    // Application Constructor  
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
        document.addEventListener('pause', this.onPause, false);
        document.addEventListener('resume', this.onResume, false);
        
        this.btnHome.on('click', this.onClickHome);
        this.btnReset.on('click', this.onClickReset);
        this.btnPace.on('click', this.onClickChangePace);
        this.btnEnabled.on('click', this.onClickToggleEnabled);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {        
        app.receivedEvent('deviceready');
        app.configureBackgroundGeoLocation();
    },
    configureBackgroundGeoLocation: function() {
        var fgGeo = window.navigator.geolocation,
            bgGeo = window.plugins.backgroundGeoLocation;

       /**
        * This would be your own callback for Ajax-requests after POSTing background geolocation to your server.
        */
        var yourAjaxCallback = function(response) {
            bgGeo.finish();
        };

        /**
        * This callback will be executed every time a geolocation is recorded in the background.
        */
        var callbackFn = function(location) {
            console.log('[js] BackgroundGeoLocation callback:  ' + location.latitude + ',' + location.longitude);
            
            // Update our current-position marker.
            app.setCurrentLocation(location);

            // After you Ajax callback is complete, you MUST signal to the native code, which is running a background-thread, that you're done and it can gracefully kill that thread.
            yourAjaxCallback.call(this);
        };

        var failureFn = function(error) {
            console.log('BackgroundGeoLocation error');
        };        

        // BackgroundGeoLocation is highly configurable.
        bgGeo.configure(callbackFn, failureFn, {
            url: 'http://192.168.1.8/json-android.php?name=lucas', // <-- Android ONLY:  your server url to send locations to
            params: {
                auth_token: 'user_secret_auth_token',    //  <-- Android ONLY:  HTTP POST params sent to your server when persisting locations.
                name: 'oioo'                              //  <-- Android ONLY:  HTTP POST params sent to your server when persisting locations.
            },
            token: '1212312312312312312312',
            desiredAccuracy: 0,
            stationaryRadius: 50,
            distanceFilter: 50,
            notificationTitle: 'Enviando Ticks', // <-- android only, customize the title of the notification
            notificationText: 'Ativo', // <-- android only, customize the text of the notification
            activityType: 'AutomotiveNavigation',
            debug: false, // <-- enable this hear sounds for background-geolocation life-cycle.
            stopOnTerminate: false // <-- enable this to clear background location settings when the app terminates
        });
        
        // Turn ON the background-geolocation system.  The user will be tracked whenever they suspend the app.
        var settings = ENV.settings;

        if (settings.enabled == 'true') {
            bgGeo.start();
        
            if (settings.aggressive == 'true') {
                bgGeo.changePace(true);
            }
        }
    },
    onClickToggleEnabled: function(value) {
        var bgGeo       = window.plugins.backgroundGeoLocation,
            btnEnabled  = app.btnEnabled,
            isEnabled   = ENV.toggle('enabled');
        
        btnEnabled.removeClass('btn-danger');
        btnEnabled.removeClass('btn-success');

        if (isEnabled == 'true') {
            btnEnabled.addClass('btn-danger');
            btnEnabled[0].innerHTML = 'Stop';
            bgGeo.start();
        } else {
            btnEnabled.addClass('btn-success');
            btnEnabled[0].innerHTML = 'Start';
            bgGeo.stop();
        }
    },
    /**
    * Cordova foreground geolocation watch has no stop/start detection or scaled distance-filtering to conserve HTTP requests based upon speed.  
    * You can't leave Cordova's GeoLocation running in background or it'll kill your battery.  This is the purpose of BackgroundGeoLocation:  to intelligently 
    * determine start/stop of device.
    */
    onPause: function() {
        console.log('- onPause');
    },
    /**
    * Once in foreground, re-engage foreground geolocation watch with standard Cordova GeoLocation api
    */
    onResume: function() {
        console.log('- onResume');
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        console.log('Received Event: ' + id);
    }
};

app.initialize();