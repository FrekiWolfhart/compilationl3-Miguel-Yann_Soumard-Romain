import sc.analysis.DepthFirstAdapter;
import sc.node.*;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

	public SaNode getRoot(){
            return this.returnValue;
        }

        @Override
	public void caseAAff2Affectation(AAff2Affectation node) {
		String name = "";
		SaExp exp = null;

		SaVarIndicee lhs = null;
		SaExp rhs = null;

		// Builds a SaVarIndicee node
		name = node.getId().getText();
		node.getTag1().apply(this);
		exp = (SaExp) this.returnValue;
		lhs = new SaVarIndicee(name, exp);

		node.getTag2().apply(this);
		rhs = (SaExp) this.returnValue;

		this.returnValue = new SaInstAffect(lhs, rhs);
	}

	@Override
	public void caseAAffAffectation(AAffAffectation node) {
		SaVar lhs = null;
		SaExp rhs = null;

		node.getId().apply(this);
		lhs = (SaVar) this.returnValue;

		node.getExpr().apply(this);
		rhs = (SaExp) this.returnValue;

		this.returnValue = new SaInstAffect(lhs, rhs);
	}

	@Override
	public void caseAAffectationInst(AAffectationInst node) {
		node.getAffectation().apply(this);
	}

	@Override
	public void caseABlockBlock(ABlockBlock node) {
		SaLInst suiteInst = null;
		node.getSuiteinst().apply(this);
		suiteInst = (SaLInst) this.returnValue;
		this.returnValue = new SaInstBloc(suiteInst);
	}

	@Override
	public void caseACall1Call(ACall1Call node) {
		String name = node.getId().getText();
		SaLExp lexp = null;
		node.getParamfinal().apply(this);
		lexp = (SaLExp) this.returnValue;
		this.returnValue = new SaAppel(name, lexp);
	}

	@Override
	public void caseACallInst(ACallInst node) {
		node.getCall().apply(this);
	}

	@Override
	public void caseAContinstSuiteinst(AContinstSuiteinst node) {
		node.getContinst().apply(this);
		node.getSuiteinst().apply(this);
	}

	@Override
	public void caseAEmptyInst(AEmptyInst node) {
		this.returnValue = null;
	}

	@Override
	public void caseAEmptyListf(AEmptyListf node) {
		this.returnValue = null;
	}

	@Override
	public void caseAEndSuiteinst(AEndSuiteinst node) {
		node.getInst().apply(this);
	}

	@Override
	public void caseAExpr01Expr(AExpr01Expr node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr1().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1, op2);
	}

	@Override
	public void caseAExpr02Expr(AExpr02Expr node) {
        node.getExpr1().apply(this);
	}

	@Override
	public void caseAExpr11Expr1(AExpr11Expr1 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr1().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr2().apply(this);
		op2 = (SaExp) this.returnValue;
		this.returnValue = new SaExpAnd(op1, op2);
	}

	@Override
	public void caseAExpr12Expr1(AExpr12Expr1 node) {
      node.getExpr2().apply(this);
	}

	@Override
	public void caseAExpr21Expr2(AExpr21Expr2 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1, op2);
	}

	@Override
	public void caseAExpr22Expr2(AExpr22Expr2 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1, op2);
	}

	@Override
	public void caseAExpr23Expr2(AExpr23Expr2 node) {
        node.getExpr3().apply(this);
	}

	@Override
	public void caseAExpr31Expr3(AExpr31Expr3 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1, op2);
	}

	@Override
	public void caseAExpr32Expr3(AExpr32Expr3 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1, op2);
	}

	@Override
	public void caseAExpr33Expr3(AExpr33Expr3 node) {
        node.getExpr4().apply(this);
	}

	@Override
	public void caseAExpr41Expr4(AExpr41Expr4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2);
	}

	@Override
	public void caseAExpr42Expr4(AExpr42Expr4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1, op2);
	}

	@Override
	public void caseAExpr43Expr4(AExpr43Expr4 node) {
        node.getExpr5().apply(this);
	}

	@Override
	public void caseAExpr51Expr5(AExpr51Expr5 node) {
       SaExp op = null;
       node.getExpr().apply(this);
       op = (SaExp) this.returnValue;
       this.returnValue = new SaExpNot(op);
	}

	@Override
	public void caseAExpr52Expr5(AExpr52Expr5 node) {
        node.getExpr6().apply(this);
	}

	@Override
	public void caseAExpr61Expr6(AExpr61Expr6 node) {
		node.getExpr().apply(this);
	}

	@Override
	public void caseAExpr62Expr6(AExpr62Expr6 node) {
        node.getExpr7().apply(this);
	}

	@Override
	public void caseAExpr63Expr6(AExpr63Expr6 node) {
        node.getExpr8().apply(this);
	}

	@Override
	public void caseAExpr64Expr6(AExpr64Expr6 node) {
		node.getCall().apply(this);
	}

	@Override
	public void caseAExpr6(AExpr6 node) {
        this.returnValue = new SaExpLire();
	}

	@Override
	public void caseAExpr71Expr7(AExpr71Expr7 node) {
            String name = node.getId().getText();
            this.returnValue = new SaExpVar(new SaVarSimple(name));
	}

	@Override
	public void caseAExpr72Expr7(AExpr72Expr7 node) {
		String id = node.getId().getText();
		SaLExp args = null;
		node.getExpr().apply(this);
		args = (SaLExp) this.returnValue;
		this.returnValue = new SaAppel(id, args);
	}

	@Override
	public void caseAExpr81Expr8(AExpr81Expr8 node) {
            int val = Integer.parseInt(node.getNumbers().getText());
            this.returnValue = new SaExpInt(val);
	}

	@Override
	public void caseAFunc2Function(AFunc2Function node) {
		String id = node.getId().getText();
		SaLDec parametres = null;
		SaLDec variables = null;
		SaInst corps = null;

		node.getParam().apply(this);
		parametres = (SaLDec) this.returnValue;

		node.getBlock().apply(this);
		corps = (SaInst) this.returnValue;

		this.returnValue = new SaDecFonc(id, parametres, variables, corps);
	}

	@Override
	public void caseAFuncFunction(AFuncFunction node) {
		String id = node.getId().getText();
		SaLDec parametres = null;
		SaLDec variables = null;
		SaInst corps = null;

		node.getParam().apply(this);
		parametres = (SaLDec) this.returnValue;

		node.getLvar().apply(this);
		variables = (SaLDec) this.returnValue;

		node.getBlock().apply(this);
		corps = (SaInst) this.returnValue;

		this.returnValue = new SaDecFonc(id, parametres, variables, corps);
	}

	@Override
	public void caseAIfContinst(AIfContinst node) {
        node.getIf().apply(this);
	}

	@Override
	public void caseALexpr1Lexpr(ALexpr1Lexpr node) {
        SaExp head = null;
        SaLExp tail = null;

        node.getExpr().apply(this);
        head = (SaExp) this.returnValue;

        node.getLexpr().apply(this);
        tail = (SaLExp) this.returnValue;

        this.returnValue = new SaLExp(head, tail);
	}

	@Override
	public void caseALexpr2Lexpr(ALexpr2Lexpr node) {
      SaExp head = null;

      node.getExpr().apply(this);
      head = (SaExp) this.returnValue;

      this.returnValue = new SaLExp(head, null);
	}

	@Override
	public void caseALinstSuiteinst(ALinstSuiteinst node) {
      SaInst head = null;
      SaLInst tail = null;

      node.getInst().apply(this);
      head = (SaInst) this.returnValue;

      node.getSuiteinst().apply(this);
      tail = (SaLInst) this.returnValue;

      this.returnValue = new SaLInst(head, tail);
	}

	@Override
	public void caseAListfProgram(AListfProgram node) {
            SaLDec functions = null;
            node.getListf().apply(this);
            functions = (SaLDec) this.returnValue;
            this.returnValue = new SaProg(null, functions);
	}

	@Override
	public void caseAListListf(AListListf node) {
      SaDec head = null;
      SaLDec tail = null;

      node.getFunction().apply(this);
      head = (SaDec) this.returnValue;

      node.getListf().apply(this);
      tail = (SaLDec) this.returnValue;

      this.returnValue = new SaLDec(head, tail);
	}

	@Override
	public void caseALvar1Lvar(ALvar1Lvar node) {
      //Je considère que ce truc correspond à {lvar1} var comma lvar

      SaDec head = null;
      SaLDec tail = null;

      node.getVar().apply(this);
      head = (SaDec) this.returnValue;

      node.getLvar().apply(this);
      tail = (SaLDec) this.returnValue;

      this.returnValue = new SaLDec(head, tail);
	}

	@Override
	public void caseALvar2Lvar(ALvar2Lvar node) {
      //Je considère que ce truc correspond à {lvar2} var

      SaDec head = null;

      node.getVar().apply(this);
      head = (SaDec) this.returnValue;

      this.returnValue = new SaLDec(head, null);
	}

	@Override
	public void caseAOptvarfProgram(AOptvarfProgram node) {
		
      SaLDec variables = null;
      SaLDec functions = null;

      node.getLvar().apply(this);
      variables = (SaLDec) this.returnValue;

      node.getListf().apply(this);
      functions = (SaLDec) this.returnValue;

	  this.returnValue = new SaProg(variables, functions);
	  
	}

	@Override
	public void caseAParam1Param(AParam1Param node) {
      SaLDec parameters = null;

      node.getLvar().apply(this);
      parameters = (SaLDec) this.returnValue;

      this.returnValue = new SaDecFonc(null, parameters, null, null);
	}

	@Override
	public void caseAParam2Param(AParam2Param node) {
      this.returnValue = null;
	}

	@Override
	public void caseAParamfinal1Paramfinal(AParamfinal1Paramfinal node) {
      this.returnValue = null;
    }

	@Override
	public void caseAParamfinal2Paramfinal(AParamfinal2Paramfinal node) {
      SaExp head = null;

      node.getLexpr().apply(this);
      head = (SaExp) this.returnValue;

      this.returnValue = new SaLExp(head, null);
	}

	@Override
	public void caseAReturnInst(AReturnInst node) {
      SaExp val = null;

      node.getRetour().apply(this);
      val = (SaExp) this.returnValue;

      this.returnValue = new SaInstRetour(val);
	}

	@Override
	public void caseASi2If(ASi2If node) {
      SaExp expr = null;
	  SaInst block = null;
	  SaInst sinonblock = null;

      node.getExpr().apply(this);
      expr = (SaExp) this.returnValue;

      node.getBlock().apply(this);
	  block = (SaInst) this.returnValue;

	  node.getSinonblock().apply(this);
	  sinonblock = (SaInst) this.returnValue;

      this.returnValue = new SaInstSi(expr, block, sinonblock);
	}

	@Override
	public void caseASiIf(ASiIf node) {
        SaExp expr = null;
        SaInst block = null;

        node.getExpr().apply(this);
        expr = (SaExp) this.returnValue;

        node.getBlock().apply(this);
        block = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(expr, block, null);
	}

	@Override
	public void caseASinonSinonblock(ASinonSinonblock node) {
        SaInst block = null;

        node.getBlock().apply(this);
        block = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(null, null, block);
	}

	@Override
	public void caseATantqueWhile(ATantqueWhile node) {
        SaExp test = null;
        SaInst faire = null;

        node.getExpr().apply(this);
        test = (SaExp) this.returnValue;

        node.getFaire().apply(this);
        faire = (SaInst) this.returnValue;

        this.returnValue = new SaInstTantQue(test, faire);
	}

	@Override
	public void caseAVar1Var(AVar1Var node) {
        this.returnValue = new SaDecVar(node.getId().getText());
	}

	@Override
	public void caseAVar2Var(AVar2Var node) {
        SaExp indice = null;
        node.getNumbers().apply(this);
        indice = (SaExp) this.returnValue;

        this.returnValue = new SaVarIndicee(node.getId().getText(), indice);
	}

	@Override
	public void caseAWhileContinst(AWhileContinst node) {
        node.getWhile().apply(this);
	}

	@Override
	public void caseAWriteInst(AWriteInst node) {
        SaExp arg = null;
        node.getExpr().apply(this);

        arg = (SaExp) this.returnValue;

        this.returnValue = new SaInstEcriture(arg);
	}
}
