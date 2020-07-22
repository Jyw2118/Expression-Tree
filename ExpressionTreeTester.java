//Joanne Wang

public class ExpressionTreeTester {

	public static void main(String[] args) {


		ExpressionTree tree= new ExpressionTree("34 2 + 5 *");
		System.out.println(tree.infix());
		System.out.println(tree.prefix());
		System.out.println(tree.postfix());
        System.out.println(tree.eval());


	}

}
