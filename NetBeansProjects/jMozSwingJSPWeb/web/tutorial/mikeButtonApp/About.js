/**
 * Help/About Dialog.  Displays general application information, credits, and copyrights.
 */
mikeButtonApp.AboutDialog = Core.extend(Echo.WindowPane, {

    _msg: null,
    
    $construct: function() {
      //this._msg = mikeButtonApp.getMessages();
       this._msg = mikeButtonApp.Messages;
        Echo.WindowPane.call(this, {
            styleName: "ControlPane.Button",/*mikeButtonApp.pref.windowStyleName,*/
            width: "40em",
            height: "30em",
         title: this._msg["About.WindowTitle"],
           title: "About window",
            iconInsets: "6px 10px",
            icon: "image/icon/Icon16About.png",
            modal: true,
            closable: true
    
        });
    }
});
