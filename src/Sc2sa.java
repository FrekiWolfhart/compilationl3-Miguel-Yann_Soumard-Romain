import sc.analysis.DepthFirstAdapter;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

	@Override
	public void caseAAff2Affecttion(AAff2Affecttion node) {
		System.out.println("caseAAff2Affecttion");

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
	public void caseAAffAffecttion(AAffAffecttion node) {
		System.out.println("caseAAffAffecttion");
		SaVar lhs = null;
		SaExp rhs = null;

		node.getId().apply(this);
		lhs = (SaVar) this.returnValue;

		node.getExpr().apply(this);
		rhs = (SaExp) this.returnValue;

		this.returnValue = new SaInstAffect(lhs, rhs);
	}

	@Override
	public void caseAAffecttionInst(AAffecttionInst node) {
		node.getAffectation().apply(this);
	}

	@Override
	public void caseABlockBlock(ABlockBlock node) {
		SaLInst suiteInst = null;
		node.getSuiteInst().apply(this):
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
		node.getContinst.apply(this):
		node.getSuiteInst.apply(this);
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
		node.getInst().apply();
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
        this.returnValue = new SaExpMult(op1, op2);
	}

	@Override
	public void caseAExpr42Expr4(AExpr42Expr4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getExpr4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2);
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
		node.getExpr7().apply(this);
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
		node.getCall().apply();
	}

	@Override
	public void caseAExpr6(AExpr6 node) {
        this.returnValue = new SaExpLire();
	}

	@Override
	public void caseAExpr71Expr7(AExpr71Expr7 node) {
        node.getId().apply(this);
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
        node.getNumbers().apply(this);
	}

	@Override
	public void caseAFunc2Function(AFunc2Function node) {
		String id = node.getId.getText();
		SaLDec parametres = null;
		SaLDec variables = null;
		SaInst corps = null;

		node.getParam().apply(this);
		parametres = (SaLDec) this.returnValue;

		node.getBlock.apply(this);
		corps = (SaInst) this.returnValue;

		this.returnValue = new SaDecFonc(id, parametres, variables, corps);
	}

	@Override
	public void caseAFuncFunction(AFuncFunction node) {
		String id = node.getId.getText();
		SaLDec parametres = null;
		SaLDec variables = null;
		SaInst corps = null;

		node.getParam().apply(this);
		parametres = (SaLDec) this.returnValue;

		node.getLvar().apply(this);
		variables = (SaLDec) this.returnValue;

		node.getBlock.apply(this);
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

      node.getSuiteInst().apply(this);
      tail = (SaLinst) this.returnValue;

      this.returnValue = new SaLInst(head, tail);
	}

	@Override
	public void caseAListfProgrm(AListfProgrm node) {
      node.getListf().apply(this);
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
	public void caseALr1Lr(ALr1Lr node) {
      //Je considère que ce truc correspond à {lvar1} var comma lvar

      SaDec head = null;
      SaLDec tail = null;

      node.getVar().apply(this);
      head = (SaDec) this.returnValue;

      node.getLvar().apply(this);
      tail = (SaDec) this.returnValue;

      this.returnValue = new SaLDec(head, tail);
	}

	@Override
	public void caseALr2Lr(ALr2Lr node) {
      //Je considère que ce truc correspond à {lvar2} var

      SaDec head = null;

      node.getVar().apply(this);
      head = (SaDec) this.returnValue;

      this.returnValue = new SaLDec(head, null);
	}

	@Override
	public void caseAOptrfProgrm(AOptrfProgrm node) {
      SaLDec variables = null;
      SaLDec functions = null;

      node.getLvar().apply(this);
      variables = (SaLDec) this.returnValue;

      node.getListf().apply(this):
      functions = (SaLDec) this.returnValue;

      this.returnValue = new SaProg(variables, functions);
	}

	@Override
	public void caseAPrm1Prm(APrm1Prm node) {
      SaLDec parameters = null;

      node.getLvar().apply(this);
      parameters = (SaLDec) this.returnValue;

      this.returnValue = new SaDecFonc(null, parameters, null, null);
	}

	@Override
	public void caseAPrm2Prm(APrm2Prm node) {
      this.returnValue = null;
	}

	@Override
	public void caseAPrmfinl1Prmfinl(APrmfinl1Prmfinl node) {
      this.returnValue = null;
    }

	@Override
	public void caseAPrmfinl2Prmfinl(APrmfinl2Prmfinl node) {
      SaLExp head = null;

      node.getLexpr().apply(this);
      head = (SaLExp) this.returnValue;

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

      node.getExpr().apply(this);
      expr = (SaExp) this.returnValue;

      node.getBlock().apply(this);
      block = (SaInst) this.returnValue;

      this.returnValue = new SaInstSi(expr, block, caseASinonSinonblock);
      //Je ne suis pas certain de si la dernière ligne va fonctionner ou pas,
      //mais cela me semble être la seule solution possible.
      //Donc, en cas d'une erreur, revoir cette ligne pourrait
      //être une bonne option.'
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
	public void caseATantqueWhile(ATntqueWhile node) {
        SaExp test = null;
        SaInst faire = null;

        node.getTest().apply(this);
        test = (SaExp) this.returnValue;

        node.getFaire().apply(this);
        faire = (SaInst) this.returnValue;

        this.returnValue = new SaInstTantQue(test, faire);
	}

	@Override
	public void caseAVar1Var(AVar1Var node) {
        this.returnValue = new SaVarSimple(node.getId().getText());
	}

	@Override
	public void caseAVar2Var(AVar2Var node) {
        SaExp indice = null;
        node.getIndice().apply(this);
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

	@Override
	public void caseEOF(EOF node) {

	}

	@Override
	public void caseInlidToken(InlidToken node) {

	}

	@Override
	public void caseNode(Node node) {

	}

	@Override
	public void casePAffecttion(PAffecttion node) {

	}

	@Override
	public void casePBlock(PBlock node) {

	}

	@Override
	public void casePCll(PCll node) {

	}

	@Override
	public void casePContinst(PContinst node) {

	}

	@Override
	public void casePExpr1(PExpr1 node) {

	}

	@Override
	public void casePExpr2(PExpr2 node) {

	}

	@Override
	public void casePExpr3(PExpr3 node) {

	}

	@Override
	public void casePExpr4(PExpr4 node) {

	}

	@Override
	public void casePExpr5(PExpr5 node) {

	}

	@Override
	public void casePExpr6(PExpr6 node) {

	}

	@Override
	public void casePExpr7(PExpr7 node) {

	}

	@Override
	public void casePExpr8(PExpr8 node) {

	}

	@Override
	public void casePExpr(PExpr node) {

	}

	@Override
	public void casePFunction(PFunction node) {

	}

	@Override
	public void casePIf(PIf node) {

	}

	@Override
	public void casePInst(PInst node) {

	}

	@Override
	public void casePLexpr(PLexpr node) {

	}

	@Override
	public void casePListf(PListf node) {

	}

	@Override
	public void casePLr(PLr node) {

	}

	@Override
	public void casePPrmfinl(PPrmfinl node) {

	}

	@Override
	public void casePPrm(PPrm node) {

	}

	@Override
	public void casePProgrm(PProgrm node) {

	}

	@Override
	public void casePSinonblock(PSinonblock node) {

	}

	@Override
	public void casePSuiteinst(PSuiteinst node) {

	}

	@Override
	public void casePVr(PVr node) {

	}

	@Override
	public void casePWhile(PWhile node) {

	}

	@Override
	public void caseStrt(Strt node) {

	}

	@Override
	public void caseSwitchble(Switchble node) {

	}

	@Override
	public void caseSwitch(Switch node) {

	}

	@Override
	public void caseTAlors(TAlors node) {

	}

	@Override
	public void caseTAnd(TAnd node) {

	}

	@Override
	public void caseTBlnk(TBlnk node) {

	}

	@Override
	public void caseTComm(TComm node) {

	}

	@Override
	public void caseTDi(TDi node) {

	}

	@Override
	public void caseTEntier(TEntier node) {

	}

	@Override
	public void caseTEqul(TEqul node) {

	}

	@Override
	public void caseTFire(TFire node) {

	}

	@Override
	public void caseTId(TId node) {

	}

	@Override
	public void caseTInf(TInf node) {

	}

	@Override
	public void caseTLAcc(TLAcc node) {

	}

	@Override
	public void caseTLBrck(TLBrck node) {

	}

	@Override
	public void caseTLPr(TLPr node) {

	}

	@Override
	public void caseTMinus(TMinus node) {

	}

	@Override
	public void caseTMul(TMul node) {

	}

	@Override
	public void caseTNot(TNot node) {

	}

	@Override
	public void caseTNumbers(TNumbers node) {

	}

	@Override
	public void caseToken(Token node) {

	}

	@Override
	public void caseTOr(TOr node) {

	}

	@Override
	public void caseTPlus(TPlus node) {

	}

	@Override
	public void caseTRAcc(TRAcc node) {

	}

	@Override
	public void caseTRBrck(TRBrck node) {

	}

	@Override
	public void caseTRed(TRed node) {

	}

	@Override
	public void caseTRetour(TRetour node) {

	}

	@Override
	public void caseTRPr(TRPr node) {

	}

	@Override
	public void caseTSemicolon(TSemicolon node) {

	}

	@Override
	public void caseTSi(TSi node) {

	}

	@Override
	public void caseTSinon(TSinon node) {

	}

	@Override
	public void caseTTntque(TTntque node) {

	}

	@Override
	public void caseTWrite(TWrite node) {

	}

}
