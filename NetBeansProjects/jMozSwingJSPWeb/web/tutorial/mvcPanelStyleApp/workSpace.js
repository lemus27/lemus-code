/***
 * Workspace component: the primary user interface of the demo application.
 */
mvcPanelApp.Workspace = Core.extend(Echo.ContentPane, {

	/*fin de los miembro staticos de la clase mvcPanelApp.workSpace*/
	/**
 	* Localized resource map.
 	*/
 styleName: "GlassBlue",
	_msg: null,/*miembro privado de la clase mvcPanelApp.workSpace*/
	_wp:null,
	_ContentArea:null,
	$construct: function(sections) {/*cosntructor  de la clase mvcPanelApp.workSpace*/
		this._sections = sections;
		this._msg = mvcPanelApp.getMessages();
		this._wp=new mvcPanelApp.Contenido();
		Echo.ContentPane.call(this, {/*crando una clase derivad  si usar  core.extend*/
			 styleName: "ControlPane",
			children: [/*agregando  elemntos  al content panel*/
				this._ContentArea= new Echo.ContentPane
					({
					 styleName: "Default",
						children: [
						new Echo.Button(
						{  /*styleName: "ControlPane.Button",*/
							text: "boton en el area de contenido",
							actionCommand: "submit guess",
							
							events: {
								/*action: Core.method(this,
 								this._startNewGame)*/
							}

						}),
					new Echo.WindowPane({
                	styleName: "Photo.EarthEast",
                    width: 650,
                    height: 500,
                    positionX: 235,
                    positionY: 155,
                    maximizeEnabled: true,
                    title: "Photo Album",
                    closable: false,
                    children: [
                       // this._photoBrowser = new DemoApp.AccordionPaneScreen.PhotoBrowser()
                    ]
                })
						]

					}),
			/*los  splitPanel  no soportan mas  de  2  componentes internos*/
			new Echo.SplitPane({/*agregando un separador en el panel*/
			 styleName: "ControlPane",
				separatorWidth: 6,
				separatorPosition: "18%",
				resizable: true,
				separatorHorizontalImage: "image/workspace/MainSeparator.png",
				separatorHorizontalRolloverImage: "image/workspace/MainSeparatorRollover.png",
				children: [
				new Echo.Button({
					/*  styleName: "ControlPane.Button",*/
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
				/*los  splitPanel  no soportan mas  de  2  componentes internos*/
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
						  styleName: "ControlPane.Button",
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
					}),/*new mvcPanelApp.Contenido(),*//*si funciona*/
					this._wp/*si funciona*/,
				
					]

				})
				],
				events: {/*metodo miembro de la calse mvcPanelApp.WorkSpace para manejar  eventos*/
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
		this.setLocales("1337");/*se debe  busacar  la forma de  refrescar las pantllas para qu  tome encuenta el cambiod e locale*/
		this._msg = mvcPanelApp.getMessages();
		this.removeAll();
		content=new Echo.Button(
		{styleName: "ControlPane.Button",
			text: this._msg["DownloadWindow.Title"],
			actionCommand: "submit guess",
			foreground: "#ffffff",
			background: "#008f00",
			insets: "3px 10px",
			width: 200,
			events: {
				action: Core.method(this,
 				this._startNewGame)
			}

		});
		/*    
		 * modificando el contenido de toda la  aplicacion
		 /*
		  this.removeAll();
        */
        this.add(content);
        /*modificando el contenido del ContentArea*/
		//this._ContentArea.removeAll();
		//this._ContentArea.add(content);
		
		var previus= new mvcPanelApp.Control ();
		previus.funcion2();
	},
	
	/**
 	* Process a click event on the next screen button.
 	*
 	* @param e the event
 	*/
	_startNewGame: function(e) {/*metodo privado de la  clase mvcPanelApp.workspace */
		/*this.setTransition(Extras.TransitionPane.TYPE_CAMERA_PAN_RIGHT);
		this.launchScreen(this.getNextScreen());*/
		//alert("next");
		var  wp = new mvcPanelApp.Workspace();
			this.application.rootComponent.add(wp);
	},
	/**
     * Sets the locale of the application.
     *
     * @param locale the new locale
     */
    setLocales: function(locale) {/*metodo privado de la  clase DemoApp.workspace */
       /*este  metodo  funcionabien  como parte  la  ventana principal workspace*/
        mvcPanelApp.locale = locale;
        /*esto deb estar declarado, en este caso en las  clase pricnipal*/
        if (locale in mvcPanelApp.LOCALE_MODULES) {
            this.application.client.exec(["tutorial/mvcPanelStyleApp/Messages." + locale + ".js"], Core.method(this, function() {
                this.application.setLocale(locale);
                // FIXME. Recreate UI.
            }));
        }
    },
});
/*fin de la  clse  mvcPanelApp.Workspace*/
