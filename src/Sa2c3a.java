import c3a.*;
import sa.*;
import ts.Ts;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand>{

    private C3a c3a;
    private Ts globalTable;
    private Ts localTable;

    public Sa2c3a(SaNode root, Ts table){
        this.c3a = new C3a();
        this.globalTable = table;
        this.localTable = null;
        root.accept(this);
    }

    //TODO: Attention, très probablement faux
    @Override
    public C3aOperand visit(SaExpEqual node){
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
    public C3aOperand visit(SaVarSimple node){
        return new C3aVar(node.tsItem, null);

        return null;
    }

    @Override
    public C3aOperand visit(SaInstBloc node){
        return visit(node.getVal());
    }

    @Override
    public C3aOperand visit(SaExpInf node){
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
    public C3aOperand visit(SaExp node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpNot node){
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
    public C3aOperand visit(SaInstAffect node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpVar node){
        return null;
    }

    @Override
    public C3aOperand visit(SaLInst node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpAnd node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpAdd node){
        return null;
    }

    @Override
    public C3aOperand visit(SaProg node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpOr node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpMult node){
        return null;
    }

    @Override
    public C3aOperand visit(SaInstEcriture node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpInt node){
        return null;
    }

    @Override
    public C3aOperand visit(SaInstRetour node){
        return null;
    }

    @Override
    public C3aOperand visit(SaLExp node){
        return null;
    }

    @Override
    public C3aOperand visit(SaDecFonc node){
        return null;
    }

    @Override
    public C3aOperand visit(SaDecVar node){
        return null;
    }

    @Override
    public C3aOperand visit(SaInstTantQue node){
        return null;
    }

    @Override
    public C3aOperand visit(SaInstSi node){
        return null;
    }

    @Override
    public C3aOperand visit(SaLDec node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpAppel node){
        return null;
    }

    @Override
    public C3aOperand visit(SaVarIndicee node){
        return null;
    }

    @Override
    public C3aOperand visit(SaAppel node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpSub node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpLire node){
        return null;
    }

    @Override
    public C3aOperand visit(SaDecTab node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpDiv node){
        return null;
    }

}
