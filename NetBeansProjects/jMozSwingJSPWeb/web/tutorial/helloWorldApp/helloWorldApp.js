/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * para importar la  libreria que se  ocupa
 * !!!tener cuidado con el orden de  inclusion!!!!!
 * !!!y con la Repeticion de  includes!!!!!!
 */

 importJSFinJSF("lib/echo/Sync.Label.js");
/*
 * ++++++++++++++++++++++++++++++++++
 */

HelloWorldApp = Core.extend(Echo.Application, {

    $construct: function() {
        Echo.Application.call(this);
        var label = new Echo.Label({
            text: "Hello, world!"
        });
        this.rootComponent.add(label);
    }
});


