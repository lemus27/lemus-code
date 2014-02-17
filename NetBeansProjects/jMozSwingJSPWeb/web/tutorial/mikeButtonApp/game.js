//The NumberGuessApp.Game class:

/**
 * A Column which generates a random number and provides the
 * user opportunities to guess it.
 */
mikeButtonApp.Game = Core.extend(Echo.Column, {

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
                    text: "presiona este  boton",
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
                    text: "limpiar todo",
                    foreground: "#ffffff",
                    background: "#8f0000",
                    insets: "3px 10px",
                    width: 200,
                    events: {
                        action: Core.method(this, 
                                this._startNewGame)
                    }
                }),
                
                new Echo.Button({
                    text: "abrir otra ventana",
                    foreground: "#ffffff",
                    background: "#8f0000",
                    insets: "3px 10px",
                    width: 200,
                    events: {
                        action: Core.method(this, 
                                this. _nuevaVentana)
                    }
                })
            ]
        });
    },
    
    /**
     * Processes a user's guess.
     */
    _processGuess: function(e) {
    	  ++this._numberOfTries;
    	   // Update the prompt label to reflect the new sensible 
        // range of numbers.
        this._promptLabel.set("text", 
                "hola  que  tal ");

        var guess = parseInt(
                this._guessEntryField.get("text"), 10);
                
        if (isNaN(guess)) {}else{
            this._statusLabel.set("text", 
                    "tu texto no es valiudo.");
            return;
        }
        // Update number of tries label.
        if (this._numberOfTries == 1) {
            this._countLabel.set("text", 
                    "haz presionado el boton 1 vez");
        } else {
            this._countLabel.set("text", "haz presionado el boton " +
                    this._numberOfTries + " veces.");
        }
         alert("hola"+this._numberOfTries);
       
    },
    
    _startNewGame: function(e) {
        this.application.startNewGame();
    }
    ,
    _nuevaVentana: function(e) {
        this.application.congratulate(this._numberOfTries);
    }
   
});