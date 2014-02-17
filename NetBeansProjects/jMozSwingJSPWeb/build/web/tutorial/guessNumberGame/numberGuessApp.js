//The NumberGuessApp class
  /*******************************************************************
  *
 * para importar la  libreria que se  ocupa
 * !!!tener cuidado con el orden de  inclusion!!!!!
 * !!!y con la Repeticion de  import!!!!!!
 */
importJSFinJSF("tutorial/guessNumberGame/game.js")
importJSFinJSF("tutorial/guessNumberGame/congratulator.js")

/**
 * Guess-a-number Tutorial Application.
 */
NumberGuessApp = Core.extend(Echo.Application, { /*crea  una  clase derivada por extension*/

    $construct: function() {/*definiendo el constructor*/
        Echo.Application.call(this);/*ejecutando esta misma aplicacion*/
        this.startNewGame();/*llmando al metodo interno de la clase para crear un nuevo juego*/
    },
    
    /**
     * Displays a congratulatory message to the user when s/he 
     * has guessed the correct number.
     * 
     * @param numberOfTries the number of tries it took the 
     *        user to guess the correct answer.
     */
    congratulate: function(numberOfTries) {
        this.rootComponent.removeAll();/*limpia pantalla*/
        this.rootComponent.add(new NumberGuessApp.Congratulator(
                numberOfTries));/*agrega en pantalla el objeto de la ventana congratulator, pasando al costructor el numero de intentos*/
    },
    
    /**
     * Starts a new game:
     * Sets content of Window to a new Game
     */
    startNewGame: function() {
        this.rootComponent.removeAll();
        this.rootComponent.add(new NumberGuessApp.Game());
    }
});
