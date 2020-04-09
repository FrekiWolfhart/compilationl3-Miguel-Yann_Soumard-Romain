import c3a.*;
import sa.*;
import ts.Ts;

public class Sa2c3a extends SaDepthFirstVisitor<C3aOperand> {

    private C3a c3a;

    public Sa2c3a(SaNode root, Ts table) {
        this.c3a = new C3a();
        root.accept(this);
    }

    public C3a getC3a(){
        return this.c3a;
    }
    
    public C3aOperand visit(SaInst node){
        if (node instanceof SaAppel)
            visit((SaAppel) node);
        else if (node instanceof SaInstAffect)
            visit((SaInstAffect) node);
        else if (node instanceof SaInstBloc)
            visit((SaInstBloc) node);
        else if (node instanceof SaInstEcriture)
            visit((SaInstEcriture) node);
        else if (node instanceof SaInstRetour)
            visit((SaInstRetour) node);
        else if (node instanceof SaInstSi)
            visit((SaInstSi) node);
        else if (node instanceof SaInstTantQue)
            visit((SaInstTantQue) node);

        return null;
    }

    //TODO: Attention, très probablement faux
    @Override
    public C3aOperand visit(SaExpEqual node) {
        SaExp op1 = node.getOp1();
        SaExp op2 = node.getOp2();

        // Récupére la valeur des expressions
        C3aOperand E1 = visit(op1);
        C3aOperand E2 = visit(op2);

        // Voir les actions sémantiques du cours pour comprendre
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp E = c3a.newTemp();

        C3aInstJumpIfEqual line1 = new C3aInstJumpIfEqual(E1, E2, e1, "if E1 == E2 goto e1");
        C3aInstAffect line2 = new C3aInstAffect(E, new C3aConstant(0), "E.t = 0");
        C3aInstJump line3 = new C3aInstJump(e2, "jump e2");
        C3aInstAffect line4 = new C3aInstAffect(E, new C3aConstant(1), "E.t = 1");

        c3a.ajouteInst(line1);
        c3a.ajouteInst(line2);
        c3a.ajouteInst(line3);
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(line4);
        c3a.addLabelToNextInst(e2);
        c3a.ajouteInst(null);

        return null;
    }

    @Override
    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    @Override
    public C3aOperand visit(SaInstBloc node) {
        return visit(node.getVal());
    }

    @Override
    public C3aOperand visit(SaExpInf node) {
        SaExp op1 = node.getOp1();
        SaExp op2 = node.getOp2();

        // Récupére la valeur des expressions
        C3aOperand E1 = visit(op1);
        C3aOperand E2 = visit(op2);

        // Voir les actions sémantiques du cours pour comprendre
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp E = c3a.newTemp();

        C3aInstJumpIfLess line1 = new C3aInstJumpIfLess(E1, E2, e1, "if E1 == E2 goto e1");
        C3aInstAffect line2 = new C3aInstAffect(E, new C3aConstant(0), "E.t = 0");
        C3aInstJump line3 = new C3aInstJump(e2, "jump e2");
        C3aInstAffect line4 = new C3aInstAffect(E, new C3aConstant(1), "E.t = 1");

        c3a.ajouteInst(line1);
        c3a.ajouteInst(line2);
        c3a.ajouteInst(line3);
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(line4);
        c3a.addLabelToNextInst(e2);
        c3a.ajouteInst(null);

        return null;
    }


    @Override
    public C3aOperand visit(SaExp node) {
        return null;
    }

    @Override
    public C3aOperand visit(SaExpNot node) {
        SaExp op1 = node.getOp1();
        SaExp op2 = node.getOp2();

        // Récupére la valeur des expressions
        C3aOperand E1 = visit(op1);
        C3aOperand E2 = visit(op2);

        // Voir les actions sémantiques du cours pour comprendre
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp E = c3a.newTemp();

        C3aInstJumpIfNotEqual line1 = new C3aInstJumpIfNotEqual(E1, E2, e1, "if E1 == E2 goto e1");
        C3aInstAffect line2 = new C3aInstAffect(E, new C3aConstant(0), "E.t = 0");
        C3aInstJump line3 = new C3aInstJump(e2, "jump e2");
        C3aInstAffect line4 = new C3aInstAffect(E, new C3aConstant(1), "E.t = 1");

        c3a.ajouteInst(line1);
        c3a.ajouteInst(line2);
        c3a.ajouteInst(line3);
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(line4);
        c3a.addLabelToNextInst(e2);
        c3a.ajouteInst(null);

        return null;
    }

    @Override
    public C3aOperand visit(SaInstAffect node) {
        C3aOperand var = null;

        if (node.getLhs() instanceof SaVarSimple)
            var = visit((SaVarSimple) node.getLhs());
        else if (node.getLhs() instanceof SaVarIndicee)
            var = visit((SaVarIndicee) node.getLhs());

        C3aOperand exp = visit(node.getRhs());

        C3aInstAffect affect = new C3aInstAffect(var, exp, "var = expr");

        c3a.ajouteInst(affect);

        return null;
    }

    @Override
    public C3aOperand visit(SaExpVar node) {
        if (node.getVar() instanceof SaVarSimple)
            return visit((SaVarSimple) node.getVar());
        else if (node.getVar() instanceof SaVarIndicee)
            return visit((SaVarIndicee) node.getVar());

        return null;
    }

    @Override
    public C3aOperand visit(SaLInst node) {
        SaInst head = node.getTete();

        if (node.getQueue() == null)
            return null;

        if (head instanceof SaAppel)
            visit((SaAppel) head);
        else if (head instanceof SaInstAffect)
            visit((SaInstAffect) head);
        else if (head instanceof SaInstBloc)
            visit((SaInstBloc) head);
        else if (head instanceof SaInstEcriture)
            visit((SaInstEcriture) head);
        else if (head instanceof SaInstRetour)
            visit((SaInstRetour) head);
        else if (head instanceof SaInstSi)
            visit((SaInstSi) head);
        else if (head instanceof SaInstTantQue)
            visit((SaInstTantQue) head);

        visit(node.getQueue());

        return null;
    }

    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aOperand op1 = visit(node.getOp1());
        C3aOperand op2 = visit(node.getOp2());

        C3aOperand tmp = c3a.newTemp();
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();

        C3aInstJumpIfEqual line1 = new C3aInstJumpIfEqual(op1, c3a.False, e1, "if E1.t = 0 goto e1");
        C3aInstJumpIfEqual line2 = new C3aInstJumpIfEqual(op2, c3a.False, e1, "if E2.t = 0 goto e1");
        C3aInstAffect line3 = new C3aInstAffect(tmp, c3a.True, "E.T = 1");
        C3aInstJump line4 = new C3aInstJump(e2, "jump e2");
        C3aInstAffect line5 = new C3aInstAffect(tmp, c3a.False, "E.t = 0");

        c3a.ajouteInst(line1);
        c3a.ajouteInst(line2);
        c3a.ajouteInst(line3);
        c3a.ajouteInst(line4);
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(line5);
        c3a.addLabelToNextInst(e2);

        return null;
    }

    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand op1 = visit(node.getOp1());
        C3aOperand op2 = visit(node.getOp2());

        C3aTemp result = c3a.newTemp();

        C3aInstAdd addition = new C3aInstAdd(op1, op2, result, "E.t = E1.T + E2.t");
        c3a.ajouteInst(addition);

        return null;
    }

    @Override
    public C3aOperand visit(SaProg node) {
        visit(node.getFonctions());
        visit(node.getVariables());

        return null;
    }


    @Override
    public C3aOperand visit(SaExpOr node) {
        C3aOperand op1 = visit(node.getOp1());
        C3aOperand op2 = visit(node.getOp2());

        C3aOperand tmp = c3a.newTemp();
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();

        C3aInstJumpIfEqual line1 = new C3aInstJumpIfEqual(op1, c3a.True, e1, "if E1.t = 1 goto e1");
        C3aInstJumpIfEqual line2 = new C3aInstJumpIfEqual(op2, c3a.True, e1, "if E2.t = 1 goto e1");
        C3aInstAffect line3 = new C3aInstAffect(tmp, c3a.False, "E.T = 0");
        C3aInstJump line4 = new C3aInstJump(e2, "jump e2");
        C3aInstAffect line5 = new C3aInstAffect(tmp, c3a.True, "E.t = 1");

        c3a.ajouteInst(line1);
        c3a.ajouteInst(line2);
        c3a.ajouteInst(line3);
        c3a.ajouteInst(line4);
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(line5);
        c3a.addLabelToNextInst(e2);

        return null;
    }

    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aOperand op1 = visit(node.getOp1());
        C3aOperand op2 = visit(node.getOp2());

        C3aTemp result = c3a.newTemp();

        C3aInstMult mult = new C3aInstMult(op1, op2, result, "E.t = E1.T * E2.t");
        c3a.ajouteInst(mult);

        return null;
    }

    @Override
    public C3aOperand visit(SaInstEcriture node) {
        C3aOperand exp = visit(node.getArg());
        C3aTemp tmp = c3a.newTemp();

        C3aInstWrite lecture = new C3aInstWrite(exp, "E.T = read(expr)");
        lecture.result = tmp;

        c3a.ajouteInst(lecture);

        return null;
    }

    @Override
    public C3aOperand visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    @Override
    public C3aOperand visit(SaInstRetour node) {
        C3aOperand value = visit(node.getVal());
        C3aInstReturn returnVal = new C3aInstReturn(value, "return value");

        c3a.ajouteInst(returnVal);

        return null;
    }

    @Override
    public C3aOperand visit(SaLExp node) {
        SaExp head = node.getTete();

        if (head instanceof SaExpAdd)
            visit((SaExpAdd) head);
        else if (head instanceof SaExpSub)
            visit((SaExpSub) head);
        else if (head instanceof SaExpMult)
            visit((SaExpMult) head);
        else if (head instanceof SaExpDiv)
            visit((SaExpDiv) head);
        else if (head instanceof SaExpAnd)
            visit((SaExpAnd) head);
        else if (head instanceof SaExpOr)
            visit((SaExpOr) head);
        else if (head instanceof SaExpEqual)
            visit((SaExpEqual) head);
        else if (head instanceof SaExpInf)
            visit((SaExpInf) head);
        else if (head instanceof SaExpInt)
            visit((SaExpInt) head);
        else if (head instanceof SaExpLire)
            visit((SaExpLire) head);
        else if (head instanceof SaExpVar)
            visit((SaExpVar) head);
        else if (head instanceof SaExpNot)
            visit((SaExpNot) head);
        else if (head instanceof SaAppel)
            visit((SaAppel) head);
        else if (head instanceof SaExpAppel)
            visit((SaExpAppel) head);

        if (node.getQueue() != null)
            visit(node.getQueue());

        return null;
    }

    @Override
    public C3aOperand visit(SaDecFonc node) {
        return new C3aFunction(node.tsItem);
    }

    @Override
    public C3aOperand visit(SaDecVar node) {
        return new C3aVar(node.tsItem, null);
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) {
        // Création des labels
        C3aLabel test = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();

        // Création des opérandes
        C3aOperand codeTest = visit(node.getTest());

        // Création des instructions
        C3aInstJumpIfEqual whileTest = new C3aInstJumpIfEqual(codeTest, c3a.False, suite, "if E.t == 0, goto suite");
        C3aInstJump redo = new C3aInstJump(test, "goto test");

        // Génération du code c3a
        c3a.addLabelToNextInst(test);
        c3a.ajouteInst(whileTest);
        visit(node.getFaire());        
        c3a.ajouteInst(redo);
        c3a.addLabelToNextInst(suite);

        return null;
    }

    @Override
    public C3aOperand visit(SaInstSi node) {
        // Création des labels
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();

        // Création des opérandes
        C3aOperand codeTest = visit(node.getTest());

        // Création des instructions
        C3aInstJumpIfEqual ifTest = new C3aInstJumpIfEqual(codeTest, c3a.False, faux, "if E.t == 0, goto faux");
        C3aInstJump cont = new C3aInstJump(suite, "goto suite");

        // Génération du code c3a
        c3a.ajouteInst(ifTest);
        visit(node.getAlors());
        c3a.ajouteInst(cont);
        c3a.addLabelToNextInst(faux);
        visit(node.getSinon());
        c3a.addLabelToNextInst(suite);

        return null;
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        SaDec head = node.getTete();

        if (head instanceof SaDecFonc)
            visit((SaDecFonc) head);
        else if (head instanceof SaDecTab)
            visit((SaDecTab) head);
        else if (head instanceof SaDecVar)
            visit((SaDecVar) head);

        if (node.getQueue() != null)
            visit(node.getQueue());

        return null;
    }

    @Override
    public C3aOperand visit(SaExpAppel node) {
        return visit(node.getVal());
    }

    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return new C3aVar(node.tsItem, null);
    }

    @Override
    public C3aOperand visit(SaAppel node) {
        return new C3aFunction(node.tsItem);
    }

    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aOperand op1 = visit(node.getOp1());
        C3aOperand op2 = visit(node.getOp2());

        C3aTemp result = c3a.newTemp();

        C3aInstSub sub = new C3aInstSub(op1, op2, result, "E.t = E1.T - E2.t");
        c3a.ajouteInst(sub);

        return null;
    }

    @Override
    public C3aOperand visit(SaExpLire node) {
        c3a.ajouteInst(new C3aInstRead(c3a.newTemp(), "lire()"));
        return null;
    }

    @Override
    public C3aOperand visit(SaDecTab node) {
        return new C3aVar(node.tsItem, null);
    }

    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand op1 = visit(node.getOp1());
        C3aOperand op2 = visit(node.getOp2());

        C3aTemp result = c3a.newTemp();

        C3aInstDiv div = new C3aInstDiv(op1, op2, result, "E.t = E1.T / E2.t");
        c3a.ajouteInst(div);

        return null;
    }

}
