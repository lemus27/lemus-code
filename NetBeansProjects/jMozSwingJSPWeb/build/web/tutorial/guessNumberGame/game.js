//The NumberGuessApp.Game class:

/**
 * A Column which generates a random number and provides the
 * user opportunities to guess it.
 */
NumberGuessApp.Game = Core.extend(Echo.Column, {

    /** Randomly generated number between 1 and 100 inclusive. */
    _randomNumber: null,

    /** 
      * The current lowest sensible guess, based on previous 
      * guesses.
      */
    _lowerBound: 1,

    /**
     * The current highest sensible guess, based on previous
     * guesses. 
     */
    _upperBound: 100,

    /** The number of guesses made in the current game. */
    _numberOfTries: 0,

    /** TextField into which guesses are entered. */
    _guessEntryField: null,

    /** 
     * Label displaying the current "status".  
     * Initially blank, this label will inform the user whether
     * his/her last guess was too  high, too low, or simply 
     * invalid.
     */ 
    _statuslabel: null,

    /**
     * Label indicating the total number of guesses made so far.
     */
    _countLabel: null,

    /**
     * Label prompting the user to enter a new guess.  The text 
     * of this label will change as the user makes guesses to 
     * reflect the updated "sensible" range of possible guesses.
     */
    _promptLabel: null,

    $construct: function() {
        this._randomNumber = Math.floor(Math.random() * 100) + 1;
    
        Echo.Column.call(this, {
            insets: 30,
            cellSpacing: 10,
            children: [
                new Echo.Label({
                    icon: "TitleBanner.png"
                }),

                this._statusLabel = new Echo.Label(),
                this._countLabel = new Echo.Label(),
                this._promptLabel = new Echo.Label(),

                this._guessEntryField = new Echo.TextField({
                    background: "#ffffff",
                    foreground: "#0000ff",
                    layoutData: {
                        insets: "0px 20px"
                    },
                    events: {
                        action: Core.method(
                                this, this._processGuess)
                    }
                }),

                new Echo.Button({
                    text: "Submit Your Guess",
                    actionCommand: "submit guess",
                    foreground: "#ffffff",
                    background: "#008f00",
                    insets: "3px 10px",
                    width: 200,
                    events: {
                        action: Core.method(this, 
                                this._processGuess)
                    }
                }),

                new Echo.Button({
                    text: "Start a New Game",
                    foreground: "#ffffff",
                    background: "#8f0000",
                    insets: "3px 10px",
                    width: 200,
                    events: {
                        action: Core.method(this, 
                                this._startNewGame)
                    }
                })
            ]
        });
    },
    
    /**
     * Processes a user's guess.
     */
    _processGuess: function(e) {
        var guess = parseInt(
                this._guessEntryField.get("text"), 10);
        if (isNaN(guess)) {
            this._statusLabel.set("text", 
                    "Your guess was not valid.");
            return;
        }
        
        ++this._numberOfTries;
        
        if (guess == this._randomNumber) {
            this.application.congratulate(this._numberOfTries);
            return;
        }
        
        if (guess < 1 || guess > 100) {
            this._statusLabel.set("text", "Your guess, " +
                    guess + " was not between 1 and 100.");
        } else if (guess < this._randomNumber) {
            if (guess >= this._lowerBound) {
                this._lowerBound = guess + 1;
            }
            this._statusLabel.set("text", "Your guess, " +
                    guess + " was too low.  Try again:");
        } else if (guess > this._randomNumber) {
            this._statusLabel.set("text", "Your guess, " +
                    guess + " was too high.  Try again:");
            if (guess <= this._upperBound) {
                this._upperBound = guess - 1;
            }
        }

        // Update number of tries label.
        if (this._numberOfTries == 1) {
            this._countLabel.set("text", 
                    "You have made 1 guess.");
        } else {
            this._countLabel.set("text", "You have made " +
                    this._numberOfTries + " guesses.");
        }
        
        // Update the prompt label to reflect the new sensible 
        // range of numbers.
        this._promptLabel.set("text", 
                "Guess a number between " + this._lowerBound +
                " and " + this._upperBound + ": ");

    },
    
    _startNewGame: function(e) {
        this.application.startNewGame();
    }
});