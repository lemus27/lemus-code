#!/usr/bin/python3.2
import sys
import os
import re
import subprocess
import logging
import optparse

logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)s %(message)s')

def main():
   logging.info('Switching to master branch')
   for e in sys.argv:
       print (e)
   #output,_=call_command('java -jar antlr-4.0-complete.jar   sys.argv[0]')
   # Ejecuta el comando buscando en $PATH y reemplazando el proceso actual
   if len(sys.argv) >1:
      os.execlp('java','-cp','./','antlr-4.0-complete.jar','org.antlr.v4.Tool',sys.argv[1]) 
   else: 
      os.execlp('java -cp ./:/home/mikelemus/antlr4_lib antlr-4.0-complete.jar org.antlr.v4.Tool','hello.g4') 
   logging.info('Pulled latest changes from origin into master')
   logging.info('Ensuring master has the latest changes')
#return 0
if __name__ == "__main__":
    main()
