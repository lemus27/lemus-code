/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


   
    // Function to allow one JavaScript file to be included by another.
    // Copyright (C) 2006-08 www.cryer.co.uk
  importJSFinJSF=function (jsFile)
    {//solo funcion en archivos.js
      document.write('<script type="text/javascript" src="'+ jsFile + '"></scr' + 'ipt>'); 
    }
  
importJSFinHtml=function (filename)
{//solo funciona para paginas  en las que  hay body tag
var head = document.getElementsByTagName('head').item(0);
script = document.createElement('script');
script.src = filename;
script.type = 'text/javascript';
head.appendChild(script)
}
/*
and then to include a second JavaScript file simply add the line:

    importJSF('secondJS.js');
*/
