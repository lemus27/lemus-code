/**
 * Echo.Application implementation.
 * Root namespace.
 */
/*
 * los dos puntos :(object literal)  indican que es un metodo a atributo  miembro de la clase
 * el  guion bajo _ indica que  es un atributo o metodo privado
 * la herencia se maneja con  extend, call.this , atravez dela herencia por prototipo
 */
mvcPanelApp = Core.extend(Echo.Application, {
	
    locale:"1337",
	$static: {/*miembros staticos de la clase mvcPanelApp*/
		 /**
         * Set of available locale modules.
         */
        LOCALE_MODULES: {/*miembro estaticosde la clase DemoApp*/
            "1337": true
        },

		/**
 		* Retrieves resource map for current (globally configured) locale from resource bundle.
 		*/
		getMessages: function() {/*miembro estatico de la clase mvcPanelApp*/
			return mvcPanelApp.Messages.get(mvcPanelApp.locale);
		},
		/**
 		* Global initialization method.  Creates/starts client/application in "rootArea" element of document.
 		*/
		init: function() {/*miembro esttico de la clase mvcPanelApp*/
			Core.Web.init();
	
			var app = new mvcPanelApp();
			var client = new Echo.FreeClient(app, document.getElementById("rootArea"));
			  app.setStyleSheet(mvcPanelApp.StyleSheet);/*indica el archivo de  esilos*/
		
			client.init();

		},
		
	},
	/*fin de los  miembros  estaticos de la  clase  mvcPanelApp*/
	/**
 	* Localized resource map.
 	*/
	_msg: null,/*miembro privado de la clase mvcPanelApp*/
	/**
 	* The mvcPanelApp.Workspace being displayed.
 	* @type mvcPanelApp.Workspace
 	*/
	workspace: null,/*miembro de la clase mvcPanelApp*/
	/** @see Echo.Application#init */
	init: function() {/*miembro no estatico de de la clase mvcPanelApp*/
		this._msg = mvcPanelApp.getMessages();
		var sections = [

		];

		//this.workspace = new mvcPanelApp.Workspace(sections);
		
		//this.rootComponent.add(this.workspace);
		//this.rootComponent.add(new mvcPanelApp.workspace(sections));//esto nunca  funciona.
		var  wp = new mvcPanelApp.Workspace(sections);
			this.rootComponent.add(wp);
	
		// Edit/Enable the following line to launch a specific screen at startup.
		// this.workspace.launchScreen(sections[2].screens[0]);
	},
	
});


