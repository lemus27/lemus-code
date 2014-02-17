//esta  es como la funcion main
init = function() {
    var app = new NumberGuessApp();//crea una objeto de la clase principal
    var client = new Echo.FreeClient(
            app, /*agrega el objeto app al clinte del navegador*/
            document.getElementById("rootArea")/*agrega tod en el elemto rootArea definido en el documento index*/
        );
    client.init();//icia el cliente
};