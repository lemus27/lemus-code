import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends MicroCalcBaseVisitor<Integer> {
Map<String, Integer> memory = new HashMap<String, Integer>();

  @Override
    public Integer visitOPSUMA(MicroCalcParser.OPSUMAContext ctx) {
		int left = visit(ctx.expResta(0));  
        int right = visit(ctx.expResta(1)); 
        return left+right;	
    }

  @Override
    public Integer visitOPRESTA(MicroCalcParser.OPRESTAContext ctx) {
		int left = visit(ctx.expProducto(0));  
        int right = visit(ctx.expProducto(1)); 
        return left-right;	
    }

  @Override
    public Integer visitOPPRODDUCTO(MicroCalcParser.OPPRODDUCTOContext ctx) {
		int left = visit(ctx.expCociente(0));  
        int right = visit(ctx.expCociente(1)); 
        return left*right;	
    }

  @Override
    public Integer visitOPCOCIENTE(MicroCalcParser.OPCOCIENTEContext ctx) {
		int left = visit(ctx.expBase(0));  
        int right = visit(ctx.expBase(1)); 

        return left/right;	
    }

}