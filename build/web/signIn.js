/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function onSignIn(googleUser) {
    var profile=googleUser.getBasicProfile();
    $("#email").attr('value', profile.getEmail());
    $("#submit").click();

}
//function signOut() {
//    authInstance = window.gapi && window.gapi.auth2 && window.gapi.auth2.getAuthInstance();
//    console.log(authInstance);
//    var auth2 = window.gapi.auth2.getAuthInstance();
//    auth2.signOut().then(function() {
//        document.location.href = "Controller?page=logout";
//    });
//}
//function getUrlVars() {
//    var vars = {};
//    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
//        vars[key] = value;
//    });
//    return vars;
//}
//console.log(getURLVars()[page]);
//if(getURLVars()[page] === "logout") {
//    
//}