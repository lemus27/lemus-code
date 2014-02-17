/***
 * Workspace component: the primary user interface of the demo application.
 */
mvcPanelApp.Contenido = Core.extend(Echo.ContentPane, {

	
	/*fin de los miembro staticos de la clase mvcPanelApp.workSpace*/
	/**
 	* Localized resource map.
 	*/
	_msg: null,/*miembro privado de la clase mvcPanelApp.workSpace*/

	$construct: function() {/*cosntructor  de la clase mvcPanelApp.workSpace*/
		
		this._msg = mvcPanelApp.getMessages();
		
		Echo.ContentPane.call(this, {/*crando una clase derivad  si usar  core.extend*/
			children: [/*agregando  elemntos  al content panel*/
		new Echo.Column({
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
 		})
			
			]
		});

		// this._createLaunchPanel();
	},
	
 
	
	
	/**
 	* Process a click event on the next screen button.
 	*
 	* @param e the event
 	*/
	_processNext: function(e) {/*metodo privado de la  clase mvcPanelApp.workspace */
		/*this.setTransition(Extras.TransitionPane.TYPE_CAMERA_PAN_RIGHT);
		this.launchScreen(this.getNextScreen());*/
		//alert("next");
		var next= new mvcPanelApp.Control ();
		next.funcion1();
	},
	/**
 	* Process a click event on the previous screen button.
 	*
 	* @param e the event
 	*/
	_processPrevious: function(e) {/*metodo privado de la  clase mvcPanelApp.workspace */
	/*	this.setTransition(Extras.TransitionPane.TYPE_CAMERA_PAN_LEFT);
		this.launchScreen(this.getPreviousScreen());*/
		//alert("previus");
			var previus= new mvcPanelApp.Control ();
				previus.funcion2();
	},
	
	
	
});
/*fin de la  clse  mvcPanelApp.Workspace*/
