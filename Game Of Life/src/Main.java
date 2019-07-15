import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


//assuming infinite array means if its at the left edge the neighbours to its left is on the right side
//and bottom's neighbour below it is at the top


//How to use:
//The program will ask for how many columns you want
//You enter a number and thats how many columns there will be
//The program will ask for how many rows you want
//You enter a number and thats how many rows there will be
//The program will ask for how many Generations you want
//You enter a number and thats how many Generations there will be
//Generation 0 will be randomly generated and printed out
//The table will have the same dimensions as you input
//Depending on how many Generations you entered more tables will be printed



public class Main {


	public static void main(String[] args){

		int col, row, gen;

		System.out.println("Welcome to the Game of Life");
		System.out.println("Enter the number of columns for your table");
//get the number of columns
		col = GetAnInteger();

		System.out.println("Enter the number of rows for your table");
//get the number of rows
		row = GetAnInteger();

		System.out.println("Enter the number of Generations you would like to see");
//get the number of generations
		gen = GetAnInteger();


		//Create 2 2D arrays so you can update the current one will future generations
		int[][] grid = new int[row][col];
		int[][] gridNew = new int[row][col];

		makeArray(grid, col,row);

		//for loop for as many generations required
		for(int i = 1; i <= gen; i++){
			countNeighbours(grid, col, row, gridNew);
			System.out.println("Generation " + i + ":");
			drawGrid(grid, col, row);
		}  
	}

	//method to validate the users input for ints
	public static int GetAnInteger()
	{
		Scanner input = new Scanner( System.in );
		while (true)
		{
			try
			{
				input.close();
				return input.nextInt();		
			}
			catch (InputMismatchException e)
			{
				input.next();
				System.out.print("That’s not an integer. Try again: ");
			}
		}
	}

	//Creates the array and fills it with random values.
	//Also prints out Ganeration 0
	public static void makeArray(int[][] grid, int col, int row){
		
		Random rand = new Random();

		int ran;

		for(int i = 0; i < row;i++){
			for(int j = 0; j<col; j++){
				ran = rand.nextInt(2);
				grid[i][j] = ran;
			}
		}
		System.out.println("Gneration 0: ");
		drawGrid(grid, col, row);


	}

	//Method to print out grid
	public static void drawGrid(int[][] grid, int col, int row){
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				//printf to print formated data
				System.out.printf("%5d ", grid[i][j]);
			}
			System.out.println();
		}
	}

	//method to apply rules to create the next generation
	public static void countNeighbours(int[][] grid, int col, int row, int[][] gridNew){
		int count = 0;
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{

				for(int k = -1; k<=1; k++){
					for(int l = -1; l<=1; l++){

						count += grid[(i + k + row) % row][(j + l + col) % col];

					}
				}
				count -= grid[i][j];

				//Underpopulation
				if(grid[i][j] == 1 && count < 2){
					gridNew[i][j] = 0;
				}
				//Overpopulation
				else if(grid[i][j] == 1 && count > 3){
					gridNew[i][j] = 0;
				}
				//Creation of Life
				else if(grid[i][j] == 0 &&  count == 3){
					gridNew[i][j] = 1;
				}
				//Stays the same (No interaction and Survival)
				else{
					gridNew[i][j] = grid[i][j];
				}
				count = 0;
			}
		}

		//Fill the original Array with new updated values
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				grid[i][j] = gridNew[i][j];
			}
		}

	}

}