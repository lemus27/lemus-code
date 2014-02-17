<%-- 

    Document   : index
    Created on : 26-abr-2011, 14:07:31
    Author     : mike
The page must have all the necessary Echo libraries loaded. The following base libraries must always be loaded, and in this order:

    Core.js
    Core.Web.js
    Application.js
    Render.js
    Sync.js
    Client.js
    Serial.js
    FreeClient.js

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="lib/corejs/Core.js"></script>
  <script type="text/javascript" src="lib/corejs/Core.Web.js"></script>
  <script type="text/javascript" src="lib/echo/Application.js"></script>
  <script type="text/javascript" src="lib/echo/Render.js"></script>
  <script type="text/javascript" src="lib/echo/Sync.js"></script>
  <script type="text/javascript" src="lib/echo/Serial.js"></script>
  <script type="text/javascript" src="lib/echo/Client.js"></script>
  <script type="text/javascript" src="lib/echo/FreeClient.js"></script>
   
  
  <%--*******************************************************************
  *
 * para importar la  libreria que se  ocupa
 * !!!tener cuidado con el orden de  inclusion!!!!!
 * !!!y con la Repeticion de  import!!!!!!
 *
--%>
  <script type="text/javascript" src="tutorial/helloWorldApp/importJSF.js">  </script>
  <%--*******************************************************************--%>
<script type="text/javascript" src="tutorial/helloWorldApp/helloWorldApp.js">  </script>
<script type="text/javascript" src="tutorial/helloWorldApp/initApp.js">  </script>
  
    
  <title>Echo3 Client-Side JavaScript Application Demo</title>
 </head>
 <body onload="init();" style="height:100%;width:100%;margin:0px;padding: 0px;overflow:auto;font-family:verdana, arial, helvetica, sans-serif;font-size:10pt">
  <div id="rootArea" style="position:absolute;width:100%;height:100%;"></div>
 </body>
</html>
