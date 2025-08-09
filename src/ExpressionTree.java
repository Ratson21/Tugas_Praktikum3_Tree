import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

class ExpressionTree {
    private ExprNode root;

    public ExpressionTree(String expression) {
        this.root = buildTree(expression);
    }

    private ExprNode buildTree(String expression) {
        Stack<ExprNode> stack = new Stack<>();
        String postfix = infixToPostfix(expression);
        for (String token : postfix.split(" ")) {
            if (isOperator(token)) {
                ExprNode node = new ExprNode(token);
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            } else {
                stack.push(new ExprNode(token));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String c) {
        return "+-*/".contains(c);
    }

    private String infixToPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/() ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;
            char c = token.charAt(0);

            if (Character.isLetterOrDigit(c) || token.matches("[0-9.]+")) {
                output.append(token).append(" ");
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }
        return output.toString().trim();
    }

    private int precedence(char ch) {
        return (ch == '+' || ch == '-') ? 1 :
                (ch == '*' || ch == '/') ? 2 : -1;
    }

    public double evaluate(Map<String, Double> variables) {
        return eval(root, variables);
    }

    private double eval(ExprNode node, Map<String, Double> variables) {
        if (node == null) return 0;
        if (!isOperator(node.value)) {
            if (variables.containsKey(node.value)) {
                return variables.get(node.value);
            }
            return Double.parseDouble(node.value);
        }
        double leftVal = eval(node.left, variables);
        double rightVal = eval(node.right, variables);
        return switch (node.value) {
            case "+" -> leftVal + rightVal;
            case "-" -> leftVal - rightVal;
            case "*" -> leftVal * rightVal;
            case "/" -> leftVal / rightVal;
            default -> 0;
        };
    }
}