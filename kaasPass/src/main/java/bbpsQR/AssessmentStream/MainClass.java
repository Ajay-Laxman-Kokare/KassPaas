package bbpsQR.AssessmentStream;

public class MainClass {

	public static void main(String[] args) {
		
	
	MathOperation addition=(a,b)->a+b;
	MathOperation substract=(a,b)->a-b;
	MathOperation multiply=(a,b)->a*b;
	MathOperation divide=(a,b)->a/b;
	 
	
	
	 System.out.println(addition.mathOperations(5, 10));
	 System.out.println(substract.mathOperations(50, 10));
	 System.out.println(multiply.mathOperations(5, 10));
	 System.out.println(divide.mathOperations(50, 10));
		}
	
	
	
}
