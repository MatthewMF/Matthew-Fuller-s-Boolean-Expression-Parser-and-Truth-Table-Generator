public class ShuntingYardAlgo {
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
        String output = new String("");

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
            if(postfix.peek() == '('){return "This expresion is invalid";}
            output += postfix.pop();
        }
        return output;
    }
    
}