grammar MicroCalc;


expresion : expSuma                                       
		  ;

expSuma : expResta (OP_MAS^ expResta)*                     #OPSUMA
		;

expResta : expProducto (OP_MENOS^ expProducto)*            #OPRESTA
         ;

expProducto : expCociente (OP_PRODUCTO^ expCociente)*      #OPPRODDUCTO
			;

expCociente : expBase (OP_COCIENTE^ expBase)*              #OPCOCIENTE
			;

expBase : NUMERO                                            #UNNUMERO
		| PARENT_AB! expresion PARENT_CE!                   #PARENTESIS
		;


BLANCO : (' '|'\t') -> skip ; 
OP_MAS : '+';
OP_MENOS : '-';
OP_PRODUCTO : '*';
OP_COCIENTE : '/';
PARENT_AB : '(';
PARENT_CE : ')';
NUMERO : ('0'..'9')+('.' ('0'..'9')+)?;