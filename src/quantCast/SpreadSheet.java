package quantCast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SpreadSheet {
	
	private Cell[][] cells;
	private int rows;
	private int cols;
	private Set<Cell> dependencies;
	
	public SpreadSheet() {
		this.rows = 0;
		this.cols = 0;
		this.cells = null;
		this.dependencies = new HashSet<Cell>();
	}

	public static void main(String[] args) throws IOException {
		
		SpreadSheet spreadSheet = new SpreadSheet();
		
		spreadSheet.readInputIntoCell();
		
		for(int i = 0; i < spreadSheet.rows; i++) {
			for(int j = 0; j < spreadSheet.cols; j++) {
				spreadSheet.evalRPN(spreadSheet.cells[i][j], spreadSheet.dependencies);
			}
		}
		spreadSheet.printCalculatedValue(spreadSheet.cells);
	}
	
	public void printCalculatedValue(Cell[][] cells) {
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				if(i == cells.length-1 && j == cells[0].length-1) {
					System.out.printf("%.5f", cells[i][j].getCaculatedVal());
				} else {
					System.out.printf("%.5f%n", cells[i][j].getCaculatedVal());
				}
			}
		}
		
	}

	/**
	 * Process the input from console or read from file, and populate each input into the cell
	 * @throws FileNotFoundException
	 */
	private void readInputIntoCell() throws FileNotFoundException {
		
		try {
			Scanner sc = new Scanner(System.in);
			String[] buffer = null;
			int m = 0, n = 0;
			if(sc.hasNextLine()) {
				buffer = sc.nextLine().split(" ");
				if(buffer.length != 2) {
					throw new IllegalArgumentException("Invalid row value read!");
				} else {
					m = Integer.parseInt(buffer[0]);
					n = Integer.parseInt(buffer[1]);
					
					cells = new Cell[n][m];
					cols = m;
					rows = n;
				}
			}
			
			int row = 0, col = 0, total = 0;
			while(sc.hasNextLine()) {
				String curr = sc.nextLine();
				if(curr.isEmpty()) break;
 				cells[row][col] = new Cell(curr);
				total++;
				col++;
				if(col == cols) {//reach the end of current row
					row++;
					col = 0;
				}
			}
			if(total != m * n) {//total number of input does not match
				throw new IllegalArgumentException("Invalid input size of cell!");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Evalute the Reverse Polish Notation for each cell, check for its dependencies
	 * If detect cyclic dependencies, exit the progroam.
	 * @param cell the current cell being processed
	 * @param dependencies the dependencies of current cell
	 * @return the calculated value of current cell
	 */
	public double evalRPN(Cell cell, Set<Cell> dependencies) {
		
		Stack<Double> number = new Stack<Double>();
		
		if(!dependencies.contains(cell) && !cell.isCalculated()) {
			dependencies.add(cell);
			
			String[] strs = cell.getExpression().split(" ");
			
			for(String str : strs) {
				if(isNumeric(str)) { // if current string is a number, push it into stack
					number.push(Double.parseDouble(str));
				} else if(isOperator(str)){ //if current string is an operator, calculate the top 2 elements in stack with 
					//this operator, and push back the result into stack.
					double d1 = number.pop();
					double d2 = number.pop();
					
					if(str.equals("+")) {
						number.push(d2 + d1);
					} else if(str.equals("-")) {
						number.push(d2 - d1);
					} else if(str.equals("*")) {
						number.push(d2 * d1);
					} else if(str.equals("/")) {
						if(d1 == 0.0) {
							System.out.println("Unsupported operation : dividend by zero");
							System.exit(1);
						} else {
							number.push(d2/d1);
						}
					}
				} else {//if current string is a reference, recursively calculate its evaluation 
					Cell other = checkCell(str);
					number.push(evalRPN(other, dependencies));
				}
			}
			if(!number.isEmpty()) {
				cell.setCalculated(true);
				cell.setCaculatedVal(number.pop());	
			}	
		} else if(cell.isCalculated()){
			
		} else {//the cyclic dependencies are detected, exit the program
			System.out.println("Cyclic dependencies detected: " + cell.getExpression());
			System.exit(1);
		}
		return cell.getCaculatedVal();
	}
	
	/**
	 * check if the string is an operator or not 
	 * @param str
	 * @return true if the string is an operator like + - * /
	 */
	private boolean isOperator(String str) {
		
		if(str.length() != 1) return false;
		switch(str.charAt(0)) {
		case '+':
		case '-':
		case '*':
		case '/':
			return true;
		}
		return false;
	}

	/**
	 * If the the cell is a reference like A1, B2, check for the cell of this reference
	 * @param str
	 * @return the corresponding cell
	 */
	private Cell checkCell(String str) {
		try {
			int row = (int) str.charAt(0) % 65;
			int col =  Integer.parseInt(str.substring(1, str.length())) - 1;
			return cells[row][col];
		} catch (IllegalArgumentException e){
			System.out.println("Invalid format of input row parsed: " + str);
			System.exit(1);
		}
		return null;	
	}

	/**
	 * check if the input is a numeric value or not
	 * @param str the input string
	 * @return check true if it is number
	 */
	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
		
	}
}
