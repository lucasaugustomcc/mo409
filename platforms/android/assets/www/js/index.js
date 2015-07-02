/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    
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

        app.onClickHome();

        /**
        * This callback will be executed every time a geolocation is recorded in the background.
        */
        var callbackFn = function(location) {
            console.log('[js] BackgroundGeoLocation callback:  ' + location.latitude + ',' + location.longitude);
            

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
            desiredAccuracy: 0,
            stationaryRadius: 50,
            distanceFilter: 50,
            notificationTitle: 'Enviando Ticks', // <-- android only, customize the title of the notification
            notificationText: 'Ativo', // <-- android only, customize the text of the notification
            activityType: 'AutomotiveNavigation',
            debug: false, // <-- enable this hear sounds for background-geolocation life-cycle.
            stopOnTerminate: false // <-- enable this to clear background location settings when the app terminates
        });
            
        bgGeo.start();
            
    },
    onClickHome: function() {
        var fgGeo = window.navigator.geolocation;

        // Your app must execute AT LEAST ONE call for the current position via standard Cordova geolocation,
        //  in order to prompt the user for Location permission.
        fgGeo.getCurrentPosition(function(location) {            
            $.ajax({url: "http://192.168.1.8/json-android.php?name=" + coords.latitude});
        });
    },    
    /**
    * Cordova foreground geolocation watch has no stop/start detection or scaled distance-filtering to conserve HTTP requests based upon speed.  
    * You can't leave Cordova's GeoLocation running in background or it'll kill your battery.  This is the purpose of BackgroundGeoLocation:  to intelligently 
    * determine start/stop of device.
    */
    onPause: function() {
        console.log('- onPause');
        //app.stopPositionWatch();
    },
    /**
    * Once in foreground, re-engage foreground geolocation watch with standard Cordova GeoLocation api
    */
    onResume: function() {
        console.log('- onResume');
        //app.watchPosition();
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        console.log('Received Event: ' + id);
    }
};

app.initialize();