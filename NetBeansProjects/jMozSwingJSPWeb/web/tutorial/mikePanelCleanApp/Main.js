/**
 * Echo.Application implementation.
 * Root namespace.
 */
/*
 * los dos puntos :(object literal)  indican que es un metodo a atributo  miembro de la clase
 * el  guion bajo _ indica que  es un atributo o metodo privado
 * la herencia se maneja con  extend, call.this , atravez dela herencia por prototipo
 */
DemoApp = Core.extend(Echo.Application, {

	$static: {/*miembros staticos de la clase DemoApp*/

		/**
 		* Retrieves resource map for current (globally configured) locale from resource bundle.
 		*/
		getMessages: function() {/*miembro estatico de la clase DemoApp*/
			return DemoApp.Messages.get(DemoApp.locale);
		},
		/**
 		* Global initialization method.  Creates/starts client/application in "rootArea" element of document.
 		*/
		init: function() {/*miembro esttico de la clase DemoApp*/
			Core.Web.init();
	
			var app = new DemoApp();
			var client = new Echo.FreeClient(app, document.getElementById("rootArea"));
			
			client.init();

		},
		
	},
	/*fin de los  miembros  estaticos de la  clase  demoapp*/
	/**
 	* Localized resource map.
 	*/
	_msg: null,/*miembro privado de la clase DemoApp*/
	/**
 	* The DemoApp.Workspace being displayed.
 	* @type DemoApp.Workspace
 	*/
	workspace: null,/*miembro de la clase DemoApp*/
	/** @see Echo.Application#init */
	init: function() {/*miembro no estatico de de la clase DemoApp*/
		this._msg = DemoApp.getMessages();
		var sections = [

		];

		//this.workspace = new DemoApp.Workspace(sections);
		
		//this.rootComponent.add(this.workspace);
		//this.rootComponent.add(new DemoApp.workspace(sections));//esto nunca  funciona.
		var  wp = new DemoApp.Workspace(sections);
			this.rootComponent.add(wp);

		// Edit/Enable the following line to launch a specific screen at startup.
		// this.workspace.launchScreen(sections[2].screens[0]);
	}
});

/**
 * Workspace component: the primary user interface of the demo application.
 */
DemoApp.Workspace = Core.extend(Echo.ContentPane, {

	
	/*fin de los miembro staticos de la clase DemoApp.workSpace*/
	/**
 	* Localized resource map.
 	*/
	_msg: null,/*miembro privado de la clase DemoApp.workSpace*/

	$construct: function(sections) {/*cosntructor  de la clase DemoApp.workSpace*/
		this._sections = sections;
		this._msg = DemoApp.getMessages();

		Echo.ContentPane.call(this, {/*crando una clase derivad  si usar  core.extend*/
			children: [/*agregando  elemntos  al content panel*/

			new Echo.SplitPane({/*agregando un separador en el panel*/
				separatorWidth: 6,
				separatorPosition: "18%",
				resizable: true,
				separatorHorizontalImage: "image/workspace/MainSeparator.png",
				separatorHorizontalRolloverImage: "image/workspace/MainSeparatorRollover.png",
				children: [
				new Echo.Button({
					icon: "image/workspace/PreviousArrow.png",
					rolloverIcon: "image/workspace/PreviousArrowRollover.png",
					text: this._msg["Navigation.Previous"],
					foreground: "#ffffff",
					rolloverForeground: "#c9fdd2",
					font: { bold: true, italic: true },
					iconTextMargin: 5,
					textPosition: "right",
					rolloverEnabled: true,
					events: {
						action: Core.method(this, this._processPrevious)
					}
				}),
				/*agrenado un separador  al separador  anterior*/
				new Echo.SplitPane({/*agregando un separador en el panel*/
					separatorWidth: 0,
					separatorPosition: "18%",
					resizable: true,
					//separatorHorizontalImage: "image/workspace/MainSeparator.png",
					//separatorHorizontalRolloverImage: "image/workspace/MainSeparatorRollover.png",
					/*layoutData: {
 					minimumSize: "1em",
 					maximumSize: "50%"
 					},*/
					children: [
					new Echo.Button({
						icon: "image/workspace/NextArrow.png",
						rolloverIcon: "image/workspace/NextArrowRollover.png",
						text: this._msg["Navigation.Next"],
						foreground: "#ffffff",
						rolloverForeground: "#c9fdd2",
						font: { bold: true, italic: true },
						iconTextMargin: 5,
						textPosition: "left",
						rolloverEnabled: true,
						events: {
							action: Core.method(this, this._processNext)
						}
					})
					]

				})
				],
				events: {/*metodo miembro de la calse DemoApp.WorkSpace para manejar  eventos*/
				/*	init: Core.method(this, function() {
						this.launchScreen(this.getNextScreen());
					})*/
				}
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
	_processNext: function(e) {/*metodo privado de la  clase DemoApp.workspace */
		/*this.setTransition(Extras.TransitionPane.TYPE_CAMERA_PAN_RIGHT);
		this.launchScreen(this.getNextScreen());*/
		alert("next");
	},
	/**
 	* Process a click event on the previous screen button.
 	*
 	* @param e the event
 	*/
	_processPrevious: function(e) {/*metodo privado de la  clase DemoApp.workspace */
	/*	this.setTransition(Extras.TransitionPane.TYPE_CAMERA_PAN_LEFT);
		this.launchScreen(this.getPreviousScreen());*/
		alert("previus");
	},
	
	
	
});
/*fin de la  clse  DemoApp.Workspace*/

