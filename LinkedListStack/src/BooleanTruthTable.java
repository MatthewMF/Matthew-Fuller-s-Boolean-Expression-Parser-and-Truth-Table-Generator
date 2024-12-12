public class BooleanTruthTable {
    private LinkedListStack table;
    //Method used to get the precedence of operators
    private static boolean isLetterOrDigit(char c)
    {
            if(Character.isLetterOrDigit(c)) {return true;}
            else {return false;}
    }

    //Operators will be returned depending on its precedence
    static int getPrecedence(char p)
    {
        if(p == '+' || p == '-') {return 1;}
        else if(p == '*' || p == '/') {return 2;}
        else if(p == '^') {return 3;}
        else {return -1;}
    }
    
    //Check if opperator has lef to right associativity
    static boolean hasLeftAssociativity(char ch)
    {
        if(ch == '+' || ch == '-' || ch == '/' || ch == '*'){return true;}
        else {return false;}
    }

    static String infixTopostfix(String e)
    {
        LinkedListStack postfix = new LinkedListStack(); 
        String output = "";

        for(int i = 0; i < e.length(); i++)
        {
            char c = e.charAt(i);

            if(isLetterOrDigit(c)) {output += c;}
            else if(c == '(') {postfix.push(c);}
            else if(c == ')')
            {
                while(!postfix.isEmpty() && postfix.peek() != '(') {output += postfix.pop();}
                postfix.pop();
            }
            else
            {
                while(!postfix.isEmpty() && getPrecedence(c) <= getPrecedence(postfix.peek()) && hasLeftAssociativity(c)) 
                {
                    output += postfix.pop();
                }
                postfix.push(c);
            }
        }
        
        while(!postfix.isEmpty())
        {
            //if(postfix.peek() == '('){return "This expresion is invalid";}
            output += postfix.pop();
        }
        return output;
    }

    public static boolean postfixTF(String postfix, String v, String TF)
    {
        LinkedListStack evalStack = new LinkedListStack();  

        for(int i = 0; i < postfix.length(); i++)
        {
            char c = postfix.charAt(i);

            if(isLetterOrDigit(c))
            {
                int vIndex = v.indexOf(c);
                char value  = TF.charAt(vIndex);
                evalStack.push(value);
            }
            else
            {
                if(c == '!')
                {
                    if(!evalStack.isEmpty())
                    {
                        char operator1 = evalStack.pop();
                        boolean result = (operator1 == 'F');
                        if(result) 
                        {
                            evalStack.push('T');
                        }
                        else 
                        {
                            evalStack.push('F');
                        }
                    }
                    else
                    {
                        System.out.println("a");
                        return false;
                    }
                }
                else
                {
                    if(evalStack.isEmpty())
                    {
                        System.out.println("b");
                        return false;
                    }
                    char operator1 = evalStack.pop();
                    if(evalStack.isEmpty())
                    {
                        System.out.println("c");
                        return false;
                    }
                    char operator2 = evalStack.pop();

                    boolean result = false;

                    if(c == '&')
                    {
                        if(operator1 == 'T' && operator2 == 'T')
                        {
                            result = true;
                        }
                        else 
                        {
                            result = false;
                        }
                    }
                    else if(c == '|')
                    {
                        if (operator1 == 'T' || operator2 == 'T')
                        {
                            result = true;
                        }
                        else
                        {
                            result = false;
                        }
                    }
                    if(result)
                    {
                        evalStack.push('T');
                    }
                    else 
                    {
                        evalStack.push('F');
                    }
                }
            }
        }
    if(!evalStack.isEmpty() && evalStack.pop() == 'T')
    {
        return true;
    }
    else 
    {
        return false;
    }
}
    public static void TruthTable(String function )
    {
        String posfix = infixTopostfix(function);

        String v = "";
        for(int i = 0; i < function.length(); i++)
        {
            char c = function.charAt(i);
            if(Character.isLetter(c) && v.indexOf(c) == -1) {v += c;}
        }

        System.out.print("Truth Table for the Expression: " + function + "\n");
        for(int i = 0; i < v.length(); i++) {System.out.print(v.charAt(i) + " | ");}
        System.out.println(function);

        int numv = v.length();
        int numRows = (int) Math.pow(2, numv);

        for(int i = 0; i < numRows; i++) 
        {
            String truthv = "";
            for(int j = 0; j < numv; j++)
            {
                boolean TorF = ((i / (int)Math.pow(2, numv - j - 1)) % 2 == 1);
                if(TorF == true) 
                {
                    truthv += "T";
                    System.out.print("T ");
                }
                else
                {
                    truthv += "F";
                    System.out.print("F ");
                } 
            }
            boolean result = postfixTF(posfix, v, truthv);
            if(result){System.out.println("| T");}
            else{System.out.println("| F");}
        }
    }
}