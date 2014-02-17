//The NumberGuessApp class

/**
 * Guess-a-number Tutorial Application.
 */
NumberGuessApp = Core.extend(Echo.Application, {

    $construct: function() {
        Echo.Application.call(this);
        this.startNewGame();
    },
    
    /**
     * Displays a congratulatory message to the user when s/he 
     * has guessed the correct number.
     * 
     * @param numberOfTries the number of tries it took the 
     *        user to guess the correct answer.
     */
    congratulate: function(numberOfTries) {
        this.rootComponent.removeAll();
        this.rootComponent.add(new NumberGuessApp.Congratulator(
                numberOfTries));
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
