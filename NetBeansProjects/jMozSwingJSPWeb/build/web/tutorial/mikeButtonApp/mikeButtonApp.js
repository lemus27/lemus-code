
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * para importar la  libreria que se  ocupa
 * !!!tener cuidado con el orden de  inclusion!!!!!
 * !!!y con la Repeticion de  includes!!!!!!
 */

//importJSFinJSF("lib/echo/Sync.Label.js");
//importJSFinJSF("lib/echo/Sync.Button.js");
//The NumberGuessApp class
  /*******************************************************************
*/
/*importJSFinJSF("lib/echo/Sync.ArrayContainer.js");
  importJSFinJSF("ib/echo/Sync.TextComponent.js");
 importJSFinJSF("lib/echo/Sync.Label.js");
importJSFinJSF("lib/echo/Sync.Button.js")*/
/**********************************************************/
importJSFinJSF("tutorial/mikeButtonApp/About.js")
importJSFinJSF("tutorial/mikeButtonApp/game.js")
importJSFinJSF("tutorial/mikeButtonApp/congratulator.js")
/*
 * ++++++++++++++++++++++++++++++++++
 */

mikeButtonApp = Core.extend(Echo.Application, {

        
        /**
         * Globally configured locale.
         * @type String
         */
        locale: null /*miembro estatic de la clase DemoApp*/,
	_status:null,
	_buttonColumn:null,
 $construct: function() {
		Echo.Application.call(this);
		this._status=new Echo.Label({
            text: "Hello, world!"
        });
		  var label = new Echo.Label({
            text: "Hello, world!"
        });

	
		var button1 = new Echo.Button();
		button1.set("text", "First Button");

		var button2 = new Echo.Button();
		button2.set("text", "Second Button");
		button2.set("background", "#00ff00");
		var button3=  new Echo.Button(
		{
			text: "Submit Your Guess",
			actionCommand: "submit guess",
			foreground: "#ffffff",
			background: "#008f00",
			insets: "3px 10px",
			width: 200,
			events: {
				/*action: Core.method(this,
				this._startNewGame)*/
			}

		});

		var button4=new Echo.Button(
		{
			text: "Start a New Game",
			foreground: "#ffffff",
			background: "#8f0000",
			insets: "3px 10px",
			width: 200,
			events: {
				action: Core.method(
					this,
					
								this._button1OnClick
							
				)
			}
		});
	_buttonColumn = new Echo.Column();
	
 		var buttonColumn2 = new Echo.Column(this,{
 		insets: 30,
 		cellSpacing: 10,
 		children: [
 		new Echo.Button({
 		text: "boton en columna1"
 		}),
 		new Echo.Button({
 		text: "boton en columna2",
 		background: "#00ff00"
 		})
 		]
 		});

	
		var columna3=new mikeButtonApp.Game();
		var aboutDlg= new mikeButtonApp.AboutDialog();
		this.rootComponent.add(label);
		this.rootComponent.add(this._status);
		this.rootComponent.add(button1);
		this.rootComponent.add(button2);
		this.rootComponent.add(button3);
		this.rootComponent.add(button4);
		this.rootComponent.add(_buttonColumn);/*sin funciono pero no muestra nada */
		this.rootComponent.add(buttonColumn2);/*sin funciono pero no muestra nada */
		this.rootComponent.add(columna3);/*sin funciono y si muestra el dato(mejor opcion) */
		this.rootComponent.add(aboutDlg);/*sin funciono y si muestra el dato(mejor opcion) */
		//this.rootComponent.add(new mikeButtonApp.Game());
		//this.rootComponent.add(new mikeButtonApp.Ventana());
	},
	/*fin del  cosntructor*/
	/*las  funciones  miembros  de la clase  utilizan : para indicar pertenecncia
	y deben ser miembros privados y aceptarel parametr e
	* */
	_button1OnClick: function(e) 
	{
 		var msj="hola";
 		alert(msj);
 		this._status.set("text", "Your guess was not valid.");
 		//	this.label.set("text", "Your guess was not valid.");
                    
 	}
 	,
    /**
         * Retrieves resource map for current (globally configured) locale from resource bundle.
         */
        getMessages: function() {/*miembro estatico de la clase DemoApp*/
            return mikeButtonApp.Messages.get(mikeButtonApp.locale);
        }
});


/**
 * A Column which generates a random number and provides the
 * user opportunities to guess it.
 */
mikeButtonApp.Ventana = Core.extend(Echo.Column, {

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

