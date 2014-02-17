//The NumberGuessApp.Congratulator class:

/**
 * A Column which presents a congratulatory message to the
 * player when the correct number has been guessed.
 */
NumberGuessApp.Congratulator = Core.extend(Echo.Column, {

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
                    text: "You got the correct answer in " 
                            + numberOfTries + (numberOfTries == 1
                            ? "try." : " tries.")
                }),
                new Echo.Button({
                    text: "Play Again",
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
