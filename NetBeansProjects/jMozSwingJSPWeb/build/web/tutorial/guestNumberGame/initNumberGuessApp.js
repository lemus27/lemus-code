init = function() {
    var app = new NumberGuessApp();
    var client = new EchoFreeClient(app, 
            document.getElementById("rootArea"));
    client.init();
};