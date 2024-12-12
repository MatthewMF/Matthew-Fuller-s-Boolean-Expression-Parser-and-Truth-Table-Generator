//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*LinkedListStack lstack = new LinkedListStack();
        lstack.pop();
        System.out.println(lstack.peek());*/

        String expresion = "5+2/(3-8)^5^2";

        System.out.print(ShuntingYardAlgo.infixTopostfix(expresion));
    }
}