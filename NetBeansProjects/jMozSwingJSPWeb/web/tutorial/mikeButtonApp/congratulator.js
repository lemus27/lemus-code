//The NumberGuessApp.Congratulator class:

/**
 * A Column which presents a congratulatory message to the
 * player when the correct number has been guessed.
 */
mikeButtonApp.Congratulator = Core.extend(Echo.Column, {

    /**
     * A Column which presents a congratulatory message to the
     * player when the correct number has been guessed.
     */
    $construct: function(numberOfTries) {/*funcion constructor con parametro de numero de entradas*/
        Echo.Column.call(this, {
            insets: 30,
            cellSpacing: 30,
            children: [
                new Echo.Label({
                    icon: "CongratulationsBanner.png"
                }),
                new Echo.Label({
                    text: "tu presionaes el boton " 
                            + numberOfTries + (numberOfTries == 1
                            ? "vez." : " veces.")
                }),
                new Echo.Button({
                    text: "regresar",
                    foreground: "#ffffff",
                    background: "#8f0000",
                    width: 200,
                    events: {
                        action: Core.method(
                                this, this._startNewGame)
                    }
                })
            ]
        });
    },
    
    _startNewGame: function(e) {
        this.application.startNewGame();
    }
});
