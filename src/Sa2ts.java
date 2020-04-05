import ts.*;
import sa.*;

public class Sa2ts extends SaDepthFirstVisitor<Void>{

    Ts globalTable;
    Ts localTable;

    public Sa2ts(SaNode node){
        System.out.println("Constructeur");
        this.globalTable = new Ts();
        this.localTable = this.globalTable;
        node.accept(this);
    }

    @Override
    public Void visit(SaDecVar node){
        System.out.println("Déclaration variable");

        String name = node.getNom();
        if(this.globalTable.getVar(name) != null){
            System.err.println(name + "is already used by an other global variable.");
            System.exit(1);
        }

        this.globalTable.addVar(name, 1);

        return null;
    }

    @Override
    public Void visit(SaDecTab node){
        System.out.println("Déclaration tableau");


        String name = node.getNom();
        if(this.globalTable.getVar(name) != null){
            System.err.println(name + " is already used for an other global variable.");
            System.exit(2);
        }

        int size = node.getTaille();
        this.globalTable.addVar(name, size);

        return null;
    }

    @Override
    public Void visit(SaDecFonc node){
        System.out.println("Déclaration fonction");

        String name = node.getNom();
        if(this.globalTable.getFct(name) != null){
            System.err.println(name + "is already used for an other function.");
            System.exit(3);
        }

        SaLDec parameters = node.getParametres();

        int parametersNumber = parameters.length();

        this.localTable = new Ts();

        if(parametersNumber > 0){
            for(int i = 0; i < parametersNumber; i++){
                SaDec parameter = parameters.getTete();
                String parameterName = parameter.getNom();
                if(this.localTable.getVar(parameterName) != null){
                    System.err.println(parameterName + "is already used for an other parameter.");
                    System.exit(4);
                }
                TsItemVar param = this.localTable.addParam(parameterName);
                parameters = parameters.getQueue();
            }
        }

        SaLDec variables = node.getVariable();
        int variablesNumber = variables.length();

        if(variablesNumber > 0){
            for(int i = 0; i < variablesNumber; i++){
                SaDec variable = variables.getTete();
                String variableName = variable.getNom();
                if(this.localTable.getVar(variableName) != null){
                    System.err.println(variableName + "is already used by a parameter or an other variable.");
                    System.exit(5);
                }
                TsItemVar var = this.localTable.addVar(variableName, 1);
                variables = variables.getQueue();
            }
        }

        TsItemFct function = this.globalTable.addFct(name, parametersNumber, this.localTable, node);

        //this.localTable = this.globalTable;
        System.out.println("Sortie de la déclaration de variable");
        return null;
    }

    @Override
    public Void visit(SaVarSimple node){
        // On ne doit voir une utilisation de variable dans une fonction qu'après
        // avoir parcouru un SaDecFonc et donc qu'après avoir récupéré la table locale
        // de ce la fonction associée.
        boolean varExists = false;
        System.out.println("Visite d'une variable simple");

        if(isLocalVar(node.getNom()) ^ node.tsItem.isParam)
            varExists = true;
        if(isGlobalVar(node.getNom()))
            varExists = true;
        if(varExists && node.tsItem.getTaille() > 1) {
            System.err.println(node.getNom().concat(" is an integer, cannot be indiced"));
            System.exit(13);
        }

        if (!varExists) {
            System.err.println(node.getNom().concat(" has not yet been declared"));
            System.exit(10);
        }

        return null;
    }

    private boolean isLocalVar(String identif) {
        return localTable.getVar(identif) != null;
    }

    private boolean isGlobalVar(String identif) {
        return globalTable.getVar(identif) != null;
    }

    @Override
    public Void visit(SaVarIndicee node){
        System.out.println("Visite tableau");


        if (node.getIndice() == null) {
            System.err.println(node.getNom().concat(" Indice must be precised"));
            System.exit(11);
        }

        if(!isGlobalVar(node.getNom())) {
            System.err.println(node.getNom().concat(" Arrays must be global"));
            System.exit(12);
        }

        return null;
    }

    @Override
    public Void visit(SaAppel node){
        System.out.println("Visite appel");

        if(globalTable.getFct("main") == null|hasParam("main"))
            printErrorAndExit(14, "main function uncorrectly defined");

        if(!(globalTable.getFct(node.getNom()) == null))
            printErrorAndExit(15, node.getNom() + " not defined");

        TsItemFct func = globalTable.getFct(node.getNom());
        if(func.getNbArgs() != node.getArguments().length())
            printErrorAndExit(16, node.getNom() + " not defined");

        return null;
    }

    private boolean hasParam(String identif) {
        return globalTable.getFct(identif).getNbArgs() > 0;
    }

    private void printErrorAndExit(int code, String message) {
        System.err.println(message);
        System.exit(code);
    }

    public Ts getTableGlobale() {
        return globalTable;
    }
}
