import ts.*;
import sa.*;

import java.util.HashMap;

public class Sa2ts extends SaDepthFirstVisitor<Void> {
    private Ts globalTable;
    private Ts localTable;
    private HashMap<String, Boolean> currentParams;


    public Sa2ts(SaNode node) {
        checkNullNode(node);
        this.globalTable = new Ts();
        this.localTable = null;
        this.currentParams = null;
        node.accept(this);
    }


    public Ts getTableGlobale() {
        return globalTable;
    }


    @Override
    public Void visit(SaDecVar node) {
        checkNullNode(node);

        if (localTable == null) {
            assert (globalTable.getVar((node.getNom())) == null);
            globalTable.addVar(node.getNom(), 1);
        } else {
            assert (localTable.getVar(node.getNom()) == null);
            if (currentParams.containsKey(node.getNom()))
                localTable.addParam(node.getNom());
            else
                localTable.addVar(node.getNom(), 1);
        }

        node.tsItem = new TsItemVar(node.getNom(), 1);

        return null;
    }


    @Override
    public Void visit(SaDecTab node) {
        checkNullNode(node);

        // Les tableaux sont toujours globaux
        assert (localTable == null);

        // On vérifie qu'il s'agit de la première déclaration
        assert (globalTable.getVar(node.getNom()) == null);

        // Si les deux conditions précédentes sont respectées,
        // on l'ajoute à la table globale
        globalTable.addVar(node.getNom(), node.getTaille());

        node.tsItem = new TsItemVar(node.getNom(), node.getTaille());

        return null;
    }


    @Override
    public Void visit(SaDecFonc node) {
        checkNullNode(node);

        String name = node.getNom();
        int nbArgs = node.getParametres() == null ? 0 : node.getParametres().length();

        // Vérifie que la fonction n'est pas déjà déclarée
        assert (globalTable.getFct(node.getNom()) != null);

        // On crée une table locale
        localTable = new Ts();
        currentParams = new HashMap<>();

        // On ajoute le TsItemFct dans SaDecFonc
        node.tsItem = new TsItemFct(name, nbArgs, localTable, node);

        // On entre la fonction dans la table globale
        globalTable.addFct(name, nbArgs, localTable, node);

        // On ajoute les paramètres à la hashmap pour pouvoir
        // savoir quels sont les paramètres.
        if (nbArgs != 0) {
            SaLDec parameters = node.getParametres();

            for (int i = 0; i < nbArgs; i++) {
                SaDec parameter = parameters.getTete();
                String parameterName = parameter.getNom();
                currentParams.put(parameterName, true);
                parameters = parameters.getQueue();
            }

            if((parameters = node.getParametres()) != null) {
                parameters.getTete().accept(this);
                if(parameters.getQueue() != null) parameters.getQueue().accept(this);
            }
        }

        SaLDec vars = node.getVariable();
        if(vars != null) {
            vars.getTete().accept(this);
            if(vars.getQueue() != null) vars.getQueue().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(SaVarSimple node) {
        boolean varExists = false;

        checkNullNode(node);
        assert (node.tsItem != null);

        if (isLocalVar(node.getNom()) ^ node.tsItem.isParam)
            varExists = true;
        if (isGlobalVar(node.getNom()))
            varExists = true;
        if (varExists && node.tsItem.getTaille() > 1)
            printErrorAndExit(13, node.getNom().concat(" is an integer, cannot be indiced"));

        if (!varExists)
            printErrorAndExit(10, node.getNom().concat(" has not yet been declared"));

        return null;
    }

    private boolean isLocalVar(String identif) {
        return localTable.getVar(identif) != null;
    }

    private boolean isGlobalVar(String identif) {
        return globalTable.getVar(identif) != null;
    }


    @Override
    public Void visit(SaVarIndicee node) {
        checkNullNode(node);
        assert (node.tsItem != null);

        if (node.getIndice() == null)
            printErrorAndExit(11, node.getNom().concat(" Indice must be precised"));

        if (!isGlobalVar(node.getNom()))
            printErrorAndExit(12, node.getNom().concat(" Arrays must be global"));

        return null;
    }


    @Override
    public Void visit(SaAppel node) {
        checkNullNode(node);

        if (globalTable.getFct("main") == null || hasParam("main"))
            printErrorAndExit(14, "main function uncorrectly defined");

        if (!(globalTable.getFct(node.getNom()) == null))
            printErrorAndExit(15, node.getNom() + " not defined");

        TsItemFct func = globalTable.getFct(node.getNom());
        if (func.getNbArgs() != node.getArguments().length())
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

    private void checkNullNode(SaNode node) {
        if (node == null)
            printErrorAndExit(100, "Node does not exists");
    }

}
