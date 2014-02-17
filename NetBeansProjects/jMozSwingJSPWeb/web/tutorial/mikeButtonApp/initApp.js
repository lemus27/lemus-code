/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
init = function() {
    var app = new mikeButtonApp();
    var client = new Echo.FreeClient(app, document.getElementById("rootArea"));
    client.init();
};