# Yes, you are right. MozSwing works also with JRuby :-)
#
# To run the example, set CLASSPATH to include these directories 
# and jars in workspace-mozswing/org.mozilla.browser/
#   bin
#   build/classes
#   build/dist/mozswing.jar
#   lib/commons-logging-1.1.jar
#   lib/mozdom4java.jar
#   lib/MozillaGlue-1.9.jar
#   lib/MozillaInterfaces-1.9.jar
#   lib/gtkutils.jar
#   lib/mozswing.jar
#
# This examples was tested with the trunk version of jruby:
#
#   svn co http://svn.codehaus.org/jruby/trunk/jruby jruby
#   cd jruby/
#   ant
#   export JRUBY_HOME=`pwd`
#   export PATH=`pwd`/bin:$PATH
#
# and started with the following cammand line:
#
# jruby -J-Djava.library.path=../mozswing/workspace-mozswing/org.mozilla.browser/native/macosx src/simplemozswing.rb
#
# the -J parameter for JRuby passes what follows to Java.
#

include Java

import org.mozilla.browser.MozillaWindow

win = org.mozilla.browser.MozillaWindow.new
win.setSize(500, 600)
win.load("about:")
win.setVisible(true)
