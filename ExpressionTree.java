//Joanne Wang
//creates the expression tree class using the expression tree interface

import java.util.*;

public class ExpressionTree implements ExpressionTreeInterface{
    
    private ExpressionNode root;
	private static LinkedList <ExpressionNode> stackList= new LinkedList<ExpressionNode>();
    
    //constructor that creates the tree using a stack linked list
    //prints invalid if the string is an invalid equation
    public ExpressionTree(String expression){
		String [] split = expression.split(" ");
		
		for (String s: split) {
			if (isOperator(s)) {
                if (stackList.size()<=1){
                    System.out.println("Invalid equation: there are too many operators in the equation");
					break;
                }
                
                else{
				ExpressionNode right= stackList.pop();
				ExpressionNode left=stackList.pop();
				stackList.push(new ExpressionNode(s, left, right));
                }
			}
			else 
				stackList.push(new ExpressionNode(s, null, null));
		}

		root=stackList.pop();
        if (!isOperator(root.data))
        {
            System.out.println("Invalid equation: there are too many operands in the equation");
        }
	}
    
    //class that creates the nodes
    static class ExpressionNode {

		String data;
		ExpressionNode left;
		ExpressionNode right;

		public ExpressionNode(String d, ExpressionNode l, ExpressionNode r) {
			this.data=d;
			this.left=l;
			this.right=r;
		}

	}
    
    //checks if the string is one of the four operators
    public boolean isOperator(String s) {
		if (s.equals("+")||s.equals("-")||s.equals("/")||s.equals("*"))
			return true;
		return false;
	}
    
    //calls the private eval
    public int eval(){
        return eval(root);
    }
	
    //evaluates the tree
    private int eval(ExpressionNode r) {
		if (!isOperator(r.data)) {
			return Integer.parseInt(r.data);
		}

		else {
			if(r.data.equals("+")) {
				return eval(r.left)+eval(r.right);
			}
			else if (r.data.equals("-")) {
				return eval(r.left)-eval(r.right);
			}
			else if (r.data.equals("/")) {
				return eval(r.left)/eval(r.right);
			}
			else {
				return eval(r.left)*eval(r.right);
			}
		}
	}
    
    //calls the private postFix method
    public String postfix(){
        return postfix(root);
    }
    
    //returns as a postfix string
	private String postfix(ExpressionNode r) {
		if (isOperator(r.data)) {
			return postfix(r.left)+" "+ postfix(r.right)+ " "+r.data;
		}
		return (r.data);
	}
    
    //calls the private prefix method
    public String prefix(){
        return prefix(root);
    }
    
    //returns as a prefix string
    private String prefix(ExpressionNode r) {
		if (isOperator(r.data)) {
			return r.data + " " +prefix(r.left)+" "+ prefix(r.right);
		}
		return (r.data);
	}
	
    //calls the private infix method
    public String infix(){
        return infix(root);
    }
    
    //returns as an infix string
    private String infix(ExpressionNode r) {
		if (isOperator(r.data)) {
			return "("+infix(r.left)+" "+ r.data +" "+ infix(r.right)+")";
		}
		return (r.data);
	}
    
}