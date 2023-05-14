package server;

import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.security.DigestException;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
	private float num1, num2;
	private String op;
	
	private static final Map<String, BinaryOperator<Float>> operationMap = new HashMap<>();
    
    static {
        operationMap.put("+", (a, b) -> a + b);
        operationMap.put("-", (a, b) -> a - b);
        operationMap.put("*", (a, b) -> a * b);
        operationMap.put("/", (a, b) -> a / b);
    }
	
	public Calculator(String statement) {
		Map<String, Object>tokens_of_operations = Calculator.get_statment_parts(statement);
		this.num1 = (float) tokens_of_operations.get("num1");
		this.num2 = (float) tokens_of_operations.get("num2");
		this.op = tokens_of_operations.get("op").toString();
	}
	
	
	public float getResult() {
		BinaryOperator<Float> operation = operationMap.get(this.op);
        if (operation == null) {
            throw new IllegalArgumentException("Invalid operator: " + op);
        }
        return operation.apply(this.num1, this.num2);        	
		
	}
	
	static Map<String, Object> get_statment_parts(String statement) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		StringTokenizer st = new StringTokenizer(statement);
		result.put("num1", Float.parseFloat(st.nextToken()));
		result.put("op",  st.nextToken());
		result.put("num2", Float.parseFloat(st.nextToken()));
		return result;
	}


}
