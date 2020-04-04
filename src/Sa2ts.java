import ts.*;
import sa.*;

public class Sa2ts extends SaDepthFirstVisitor<Void>{

    Ts globalTable;
    Ts localTable;

    public Sa2ts(SaNode node){
        this.globalTable = new Ts();
        this.localTable = this.globalTable;
        node.accept(this);
    }
    
    @Override
    public Void visit(SaDecVar node){
        String name = node.getNom();
        if(this.globalTable.getVar(name) != null){
            System.err.println("Two different variables cannot have the same name.");
            System.exit(1);
        }

        this.globalTable.addVar(name, 1);
    }

    @Override
    public Void visit(SaDecTab node){
        String name = node.getNom();
        if(this.globalTable.getVar(name) != null){
            System.err.println("Two different variables cannot have the same name.");
            System.exit(2);
        }

        int size = node.getTaille();
        this.globalTable.addVar(name, size);
    }

    @Override
    public Void visit(SaDecFonc node){

        String name = node.getNom();
        if(this.globalTable.getFct(name) != null){
            System.err.println("Two different functions cannot have the same name.");
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
                    System.err.println("Two different local variables cannot have the same name.");
                    System.exit(4);
                }
                TsItemVar param = this.localTable.addParam(parameterName);
                parameters = parameters.getQueue();
            }
        }

        SalDec variables = node.getVariables();
        int variablesNumber = variables.length();

        if(variablesNumber > 0){
            for(int i = 0; i < variablesNumber; i++){
                SaDec variable = variables.getTete();
                String variableName = variable.getNom();
                if(this.localTable.getVar(variableName) != null){
                    System.err.println("Two different local variables cannot have the same name.");
                    System.exit(5);
                }
                TsItemVar var = this.localTable.addVar(variableName, 1);
                variables = variables.getQueue();
            }
        }

        TsItemFct function = this.globalTable.addFct(name, parametersNumber, this.localTable, node);

        this.localTable = this.globalTable;

        return null;
    }

    @Override
    public Void visit(SaVarSimple node){
    }

    @Override
    public Void visit(SaVarIndicee node){
    }

    @Override
    public Void visit(SaAppel node){
    }
}
