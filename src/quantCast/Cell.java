package quantCast;

/**
 * 
 * @author Tian Xia
 * A wrapper class to represent the data of each string
 */
public class Cell {

	private String expression;
	private boolean isCalculated;
	private double caculatedVal;
	
	public Cell(String expression) {
		this.expression = expression;
		this.caculatedVal = 0.0;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public boolean isCalculated() {
		return isCalculated;
	}

	public void setCalculated(boolean isCalculated) {
		this.isCalculated = isCalculated;
	}

	public double getCaculatedVal() {
		return caculatedVal;
	}

	public void setCaculatedVal(double caculatedVal) {
		this.caculatedVal = caculatedVal;
	}	
}
