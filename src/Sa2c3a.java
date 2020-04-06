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

    @Override
    public C3aOperand visit(SaDepthFirstVisitor node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpEqual node){
        return null;
    }

    @Override
    public C3aOperand visit(SaVarSimple node){
        return null;
    }

    @Override
    public C3aOperand visit(SaInstBloc node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpInf node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExp node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpNot node){
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
    public C3aOperand visit(SaNode node){
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
    public C3aOperand visit(SaInst node){
        return null;
    }

    @Override
    public C3aOperand visit(SaDecVar node){
        return null;
    }

    @Override
    public C3aOperand visit(SaEnvironment node){
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
    public C3aOperand visit(SaVisitor node){
        return null;
    }

    @Override
    public C3aOperand visit(SaDec node){
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
    public C3aOperand visit(Sa2Xml node){
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
    public C3aOperand visit(SaEval node){
        return null;
    }

    @Override
    public C3aOperand visit(SaDecTab node){
        return null;
    }

    @Override
    public C3aOperand visit(SaVar node){
        return null;
    }

    @Override
    public C3aOperand visit(SaExpDiv node){
        return null;
    }

}
