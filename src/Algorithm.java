import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    public int getNullableRuleIndex(String [] grammerRule){
        int ruleIndex = 0;
        for (int i =0; i< grammerRule.length; i++){
            if (grammerRule[i].contains("#")){
                ruleIndex = i;
            }
        }
        return  ruleIndex;
    }
    public Character getNullableNonTerminalSymbol(String str){
        return str.charAt(0);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getBeginDirectlyWith(String [] rule , int nullableRuleTerminalIndex, Character nullableNonTerminal){

        String firstRule;
        String secondRule;
        String normalRule;
        List <String> stringList = new ArrayList<>();
        for (int i =0; i<rule.length; i++){
            if (i == nullableRuleTerminalIndex){

            }else if(rule[i].charAt(2) == nullableNonTerminal){
                firstRule =rule[i].charAt(0)+" BDW "+ rule[i].charAt(2);
                stringList.add(firstRule);

                secondRule=rule[i].charAt(0)+" BDW "+  rule[i].charAt(3);
                stringList.add(secondRule);

            }else {
                normalRule = rule[i].charAt(0)+" BDW "+rule[i].charAt(2);
                stringList.add(normalRule);
            }
        }



        return stringList;

    }
    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getBeginWith(String [] rule , int nullableRuleTerminalIndex, Character nullableNonTerminal){

        String firstRule = null;
        String secondRule= null;
        String normalRule=null;
        List <String> stringList = new ArrayList<>();
        for (int i =0; i<rule.length; i++){
            if (i == nullableRuleTerminalIndex){}
            else if(rule[i].charAt(2) == nullableNonTerminal){
                firstRule =rule[i].charAt(0)+" BW "+ rule[i].charAt(2);
                stringList.add(firstRule);

                secondRule=rule[i].charAt(0)+" BW "+  rule[i].charAt(3);
                stringList.add(secondRule);

            }else {
                normalRule = rule[i].charAt(0)+" BW "+rule[i].charAt(2);
                stringList.add(normalRule);
            }
        }

        return stringList;

    }
    public List<String> getBeginWithReflexive(List<String>list) {
        List<String> BWList = new ArrayList<>();
        List<String> BWList2 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String val = list.get(i).charAt(0) + " BW " + list.get(i).charAt(0);
            BWList.add(val);

            String val2 = list.get(i).charAt(6) + " BW " + list.get(i).charAt(6);
            BWList.add(val2);
        }
        for (int i = 0; i < BWList.size(); i++) {
            if (!BWList2.contains(BWList.get(i))) {
                BWList2.add(BWList.get(i));
            }
        }
        return BWList2;
    }

    public List<String> getBeginWithTrasitive(List<String>list) {
        List<String> BWList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).charAt(6) ==list.get(j).charAt(0)){
                    String val= list.get(i).charAt(0)+" BW "+list.get(j).charAt(6);
                    BWList.add(val);
                }
            }
        }

        return BWList;

    }
    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getFirstOf(String [] rule , List<String> stringList) {
        List<String> firstOfList = new ArrayList<>();
        List<String> firstOfList2 = new ArrayList<>();

        for (int i = 0; i < rule.length; i++) {
            for (int j = 0; j < stringList.size(); j++) {
                if (rule[i].charAt(0) == stringList.get(j).charAt(0)) {
                    if (Character.isLowerCase(stringList.get(j).charAt(5))) {
                        String val = "First(" + rule[i].charAt(0) + ") = " + stringList.get(j).charAt(5);
                        firstOfList.add(val);
                    }
                }
            }
        }

        for (int i = 0; i < rule.length; i++) {
            for (int j = 0; j < stringList.size(); j++) {
                if (Character.isLowerCase(rule[i].charAt(2))) {
                    if (rule[i].charAt(2) == stringList.get(j).charAt(0)){
                        if (Character.isLowerCase(stringList.get(j).charAt(5))) {
                            String val = "First(" + rule[i].charAt(2) + ") = " + stringList.get(j).charAt(5);
                            firstOfList.add(val);
                        }

                    }
                }
            }
        }

        for (int i = 0; i < firstOfList.size(); i++) {
            if (!firstOfList2.contains(firstOfList.get(i))) {
                firstOfList2.add(firstOfList.get(i));
            }
        }
        return firstOfList2;
    }
    //--------------------------------------------------------------------------------------------------------------------------------------



    public List<String> getFirstOfRighHandSide(String [] rule , List<String> stringList,Character Nullable) {
        List<String> firstOfList = new ArrayList<>();


        for (int i = 0; i < rule.length; i++) {
            for (int j = 0; j < stringList.size(); j++) {
                if(rule[i].charAt(2)==stringList.get(j).charAt(6)){
                    String val="First("+rule[i].substring(2)+") = "+stringList.get(j).charAt(11);
                    firstOfList.add(val);
                }

            }
        }

        for (int i = 0; i < rule.length; i++) {
            for (int j = 0; j < stringList.size(); j++) {
                //System.out.println(rule[i].length());
               if(rule[i].length()>3) {
                    if (rule[i].charAt(3) == stringList.get(j).charAt(6) && rule[i].charAt(2) == Nullable) {
                        String val = "First(" + rule[i].substring(2) + ") = " + stringList.get(j).charAt(11);
                        firstOfList.add(val);
                    }
                 }
            }
        }
        return firstOfList;
    }
    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getFollowedDirectlyBy(String[] rule){
        List<String> FDB = new ArrayList<>();

        for (int i =0; i<rule.length; i++){
            for (int j = 2; j<rule[i].length(); j++){
                if(rule[i].length()>4) {
                    if (Character.isUpperCase(rule[i].charAt(j)) && rule[i].charAt(j + 1) != ' ') { // S->ABc
                        String val = rule[i].charAt(j) + " FDB " + rule[i].charAt(j + 1);
                        FDB.add(val);
                    }
                }
            }

        }

        return FDB;

    }
    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getDirectEndOf(String[] rule,Character nullableNonTerminal){
        List<String> DEO = new ArrayList<>();

        for (int i =0; i< rule.length; i++){
            if (rule[i].charAt(rule[i].length()-1) != '#'){
                String val = rule[i].charAt(rule[i].length()-1) + " DEO " + rule[i].charAt(0);
                DEO.add(val);
            }

        }

        for (int i =0; i< rule.length; i++){
            if ( rule[i].charAt(rule[i].length()-1) == nullableNonTerminal){
                String val = rule[i].charAt(rule[i].length()-2) + " DEO " + rule[i].charAt(0);
                DEO.add(val);
            }

        }


        return DEO;
    }
    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getEndOf(String[] rule,Character nullableNonTerminal){
        List<String> DEO = new ArrayList<>();

        for (int i =0; i< rule.length; i++){
            if (rule[i].charAt(rule[i].length()-1) != '#'){
                String val = rule[i].charAt(rule[i].length()-1) + " EO " + rule[i].charAt(0);
                DEO.add(val);
            }

        }

        for (int i =0; i< rule.length; i++){
            if ( rule[i].charAt(rule[i].length()-1) == nullableNonTerminal){
                String val = rule[i].charAt(rule[i].length()-2) + " EO " + rule[i].charAt(0);
                DEO.add(val);
            }

        }


        return DEO;
    }
    public List<String> getEndOfReflexsive(List<String>list) {
        List<String> EOReflexsive = new ArrayList<>();
        List<String> EOReflexsive2 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String val = list.get(i).charAt(0) + " EO " + list.get(i).charAt(0);
            EOReflexsive.add(val);

            String val2 = list.get(i).charAt(6) + " EO " + list.get(i).charAt(6);
            EOReflexsive.add(val2);
        }
        for (int i = 0; i < EOReflexsive.size(); i++) {
            if (!EOReflexsive2.contains(EOReflexsive.get(i))) {
                EOReflexsive2.add(EOReflexsive.get(i));
            }
        }
        return EOReflexsive2;
    }

    public List<String> getEndOfTrasitive(List<String>list) {
        List<String> BWList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).charAt(6) ==list.get(j).charAt(0)){
                    String val= list.get(i).charAt(0)+" EO "+list.get(j).charAt(6);
                    BWList.add(val);
                }
            }
        }

        return BWList;

    }

    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getFollowBy(List<String>EO,List<String>FDB,List<String>BW) {
        List<String> FBList = new ArrayList<>();

        for(int i=0;i<EO.size();i++){
            for (int j=0;j<FDB.size();j++){
                for (int k=0;k<BW.size();k++){
                    if(EO.get(i).charAt(5)==FDB.get(j).charAt(0)
                            && FDB.get(j).charAt(6)==BW.get(k).charAt(0)){
                        String val=EO.get(i).charAt(0) +" FB "+BW.get(k).charAt(5);
                        FBList.add(val);
                    }

                }
            }
        }
        return FBList;

    }

    //---------------------------------------------------------------------------------------------------------------------------------


    public List<String> getExtendFollowedBy(List <String> eoList){
        List<String> extendFB = new ArrayList<>();

        for (int i = 0; i<eoList.size(); i++){
            if (Character.isUpperCase(eoList.get(i).charAt(0)) && eoList.get(i).charAt(5)=='S'){
                String val = eoList.get(i).charAt(0) + " FB ←";
                extendFB.add(val);
            }
        }
        return extendFB;

    }

    //-----------------------------------------------------------------------------------------------------------------------------------------



    public List<String> getFollowSet(List<String> fbList, Character nullableNonTerminal){
        List<String> followSet = new ArrayList<>();

        for (int i =0; i<fbList.size(); i++){
            if (fbList.get(i).charAt(0) == nullableNonTerminal && !Character.isUpperCase(fbList.get(i).charAt(5))){
                String val = "Fol(" +nullableNonTerminal+") = "+fbList.get(i).charAt(5);
                followSet.add(val);
            }
        }

        return followSet;

    }

    //--------------------------------------------------------------------------------------------------------------------------------------

    public List<String> getSelectionSet(List<String> firstOfRHS,List<String> FOL ,int nullableRule,String [] rule,Character nullableNonTerminal) {
        List<String> selectionSet = new ArrayList<>();

        for(int i =0;i<rule.length;i++){
            if(i==nullableRule){
                for(int k=0;k<FOL.size();k++) {
                    if (nullableNonTerminal == FOL.get(k).charAt(4)){
                        String val="Sel("+(i+1)+") = "+FOL.get(k).charAt(9);
                        selectionSet.add(val);
                    }
                }
            }else {
                for (int j = 0; j < firstOfRHS.size(); j++) {
                    if (rule[i].charAt(2) == firstOfRHS.get(j).charAt(6)){
                        String val = "Sel(" +(i+1)+") = " +firstOfRHS.get(j).charAt(firstOfRHS.get(j).length()-1);
                        selectionSet.add(val);
                    }
                }
            }

        }

        return selectionSet;


    }

        /**
         * S → ABc
         * A → bA
         * A → є
         * B → c
         * @param args
         */

    public static void main(String[] args) {
        String rule[] = new String[]{"S→ABc", "A→bA",
                "A→#"  , "B→c"};
//        String rule[] = new String[]{"S→XYa", "X→a",
//                "X→Yb"  , "Y→c","Y→#"};
        //System.out.println(rule[2].length());
        Algorithm algorithm = new Algorithm();

        int nullableRuleIndex = algorithm.getNullableRuleIndex(rule);
        System.out.println(" ");
        System.out.println(" --------Step 1--------");
        System.out.println(" ");
        System.out.println("The Nullable Rule is : "+nullableRuleIndex);

        Character nullableNonTerminial = algorithm.getNullableNonTerminalSymbol(rule[nullableRuleIndex]);
        System.out.println("The Nullable Non Terminal is : "+nullableNonTerminial);

        List<String> BDW = algorithm.getBeginDirectlyWith(rule,nullableRuleIndex,nullableNonTerminial);
        System.out.println(" ");
        System.out.println(" --------Step 2--------");
        System.out.println(" ");
        System.out.println(BDW);

        List<String> BW = algorithm.getBeginWith(rule,nullableRuleIndex,nullableNonTerminial);
        //System.out.println(BW);
        List<String> BWReflexsive= algorithm.getBeginWithReflexive(BDW);
        //System.out.println(BWReflexsive);
        List<String> BWTransitive=algorithm.getBeginWithTrasitive(BDW);
        //System.out.println(BWTransitive);
        List<String> BW_All = new ArrayList<>();
        BW_All.addAll(BW);
        BW_All.addAll(BWReflexsive);
        BW_All.addAll(BWTransitive);
        System.out.println(" ");
        System.out.println(" --------Step 3--------");
        System.out.println(" ");
        System.out.println(BW_All);

        List<String> First=algorithm.getFirstOf(rule,BW_All);
        System.out.println(" ");
        System.out.println(" --------Step 4--------");
        System.out.println(" ");
        System.out.println(First);

        List<String> FirstOfRight=algorithm.getFirstOfRighHandSide(rule,First,nullableNonTerminial);
        System.out.println(" ");
        System.out.println(" --------Step 5--------");
        System.out.println(" ");
        System.out.println(FirstOfRight);

        List<String> FDB=algorithm.getFollowedDirectlyBy(rule);
        System.out.println(" ");
        System.out.println(" --------Step 6--------");
        System.out.println(" ");
        System.out.println(FDB);

        List<String> DirectEndOf=algorithm.getDirectEndOf(rule,nullableNonTerminial);
        System.out.println(" ");
        System.out.println(" --------Step 7--------");
        System.out.println(" ");
        System.out.println(DirectEndOf);

        List<String> EndOf=algorithm.getEndOf(rule,nullableNonTerminial);
        System.out.println(" ");
        System.out.println(" --------Step 8--------");
        System.out.println(" ");
        List<String> EndOfReflexsive=algorithm.getEndOfReflexsive(DirectEndOf);
        //System.out.println(DirectEndOfReflexsive);
        List<String> EndOfTransitive=algorithm.getEndOfTrasitive(DirectEndOf);
        //System.out.println(DirectEndOfTransitive);
        List<String> EO_ALL = new ArrayList<>();
        List<String> EO_ALL2 = new ArrayList<>();
        EO_ALL.addAll(EndOf);
        EO_ALL.addAll(EndOfReflexsive);
        EO_ALL.addAll(EndOfTransitive);
        for (int i = 0; i < EO_ALL.size(); i++) {
            if (!EO_ALL2.contains(EO_ALL.get(i))) {
                EO_ALL2.add(EO_ALL.get(i));
            }
        }
        System.out.println(EO_ALL2);


        List<String> FB=algorithm.getFollowBy(EO_ALL,FDB,BW_All);
        List<String> FB2 = new ArrayList<>();
        System.out.println(" ");
        System.out.println(" --------Step 9--------");
        System.out.println(" ");
        //System.out.println(FB);
        for (int i = 0; i < FB.size(); i++) {
            if (!FB2.contains(FB.get(i))) {
                FB2.add(FB.get(i));
            }
        }
        System.out.println(FB2);




        //------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println(" ");
        System.out.println(" --------Step 10--------");
        System.out.println(" ");
        List<String> extendFB=algorithm.getExtendFollowedBy(EO_ALL2);
        System.out.println(extendFB);

        System.out.println(" ");
        System.out.println(" --------Step 11--------");
        System.out.println(" ");
        List<String> followSet=algorithm.getFollowSet(FB2, nullableNonTerminial);
        System.out.println(followSet);


        List<String> selectionSetList=algorithm.getSelectionSet(FirstOfRight,followSet,nullableRuleIndex,rule,nullableNonTerminial);
        System.out.println(" ");
        System.out.println(" --------Step 12--------");
        System.out.println(" ");
        System.out.println(selectionSetList);



    }

}