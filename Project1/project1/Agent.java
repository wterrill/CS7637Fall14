package project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Your Agent for solving Raven's Progressive Matrices. You MUST modify this
 * file.
 * 
 * You may also create and submit new files in addition to modifying this file.
 * 
 * Make sure your file retains methods with the signatures:
 * public Agent()
 * public char Solve(RavensProblem problem)
 * 
 * These methods will be necessary for the project's main method to run.
 * 
 */
public class Agent {
    /**
     * The default constructor for your Agent. Make sure to execute any
     * processing necessary before your Agent starts solving problems here.
     * 
     * Do not add any variables to this signature; they will not be used by
     * main().
     * 
     */
	Integer index = 1;
	
    public Agent() {
        
    }
    /**
     * The primary method for solving incoming Raven's Progressive Matrices.
     * For each problem, your Agent's Solve() method will be called. At the
     * conclusion of Solve(), your Agent should return a String representing its
     * answer to the question: "1", "2", "3", "4", "5", or "6". These Strings
     * are also the Names of the individual RavensFigures, obtained through
     * RavensFigure.getName().
     * 
     * In addition to returning your answer at the end of the method, your Agent
     * may also call problem.checkAnswer(String givenAnswer). The parameter
     * passed to checkAnswer should be your Agent's current guess for the
     * problem; checkAnswer will return the correct answer to the problem. This
     * allows your Agent to check its answer. Note, however, that after your
     * agent has called checkAnswer, it will *not* be able to change its answer.
     * checkAnswer is used to allow your Agent to learn from its incorrect
     * answers; however, your Agent cannot change the answer to a question it
     * has already answered.
     * 
     * If your Agent calls checkAnswer during execution of Solve, the answer it
     * returns will be ignored; otherwise, the answer returned at the end of
     * Solve will be taken as your Agent's answer to this problem.
     * 
     * @param problem the RavensProblem your agent should solve
     * @return your Agent's answer to this problem
     */
    public String Solve(RavensProblem problem) {
        //Variables
    	ArrayList<Transformation> transformations = new ArrayList<>();
    	Transformation goal;
    	String answer="0";
    	ArrayList<String> options = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6"));
    	
    	
    	
    //Tell console what you're doing
    	System.out.println("Agent.java iteration = " + index + " Problem Name = " + problem.getName() + "");
    	
    //Get initial figures and make the goal transformation difference object
    //At the end of this section, we will have 
    // <Transforamtion> goal
    //						<ArrayList>AllTrans
    //									<ObjectTrans> objectTrans1
    //									<ObjectTrans> objectTrans2
    //									...
    //where maxObjects is the maximum number of objects from both figures.
    	
    	HashMap<String, RavensFigure> figures = problem.getFigures();
    	RavensFigure A = figures.get("A");
    	RavensFigure B = figures.get("B");
    	
    	goal = new Transformation(A,B);
    	ArrayList<ObjectTrans> temptransList = goal.getTrans();
    	
    	//Let's see if we got the right information. Print it to the screen.
    	System.out.println("-------GOAL-------");
    	for(int y=0; y< temptransList.size();y++)
    	{
    		ObjectTrans workingObject = temptransList.get(y);
    		
    		for(int x=0; x<workingObject.getDiffArray().size(); x++)
    		{
    			System.out.println(workingObject.getDiffArray().get(x));
    		}
    	}
    	
    //Get figure C and prep for looping through the 6 guesses.
    	RavensFigure C = figures.get("C");
    	
    	Transformation temp1 = new Transformation(A,B);
    	temptransList = temp1.getTrans();
    	
    	for(int guesses = 0; guesses < 6; guesses++)
    	{
    	ObjectTrans temp = new ObjectTrans(C.getObjects().get(0),figures.get(options.get(guesses)).getObjects().get(0));
    	transformations.add(temp1);
    	System.out.println("-------Guess #"+ guesses + "------");
    	for(int x=0; x<temp.getDiffArray().size(); x++)
    		{
    			System.out.println(temp.getDiffArray().get(x));
    		}
    	}
    
    //Now that the difference array is built, compare them and make a guess at the answer
    	for(int guesses = 0; guesses < 6; guesses++)
    	{
    		boolean match = false;
    		ArrayList<Integer> check = new ArrayList<Integer>();
    		Integer temp = 0;
    		
    		
    		for(int x=0;x < goal.getTrans().size();x++)
    		{
    			if (goal.getTrans().get(x).getDiffArray().equals(transformations.get(guesses).getTrans().get(x).getDiffArray()))
				{
    				check.add(1);
				}
    			else
    			{
    				check.add(0);
    			}
    			for(int j=0; j < check.size(); j++)
    			{
    				temp = temp + check.indexOf(j);
    			}
    			if (temp == check.size())
    			{
    				answer = options.get(guesses);
    			}
    		}
    	}
    	
        index = index + 1;
        
    //Print the answer in the console to stroke the ego.
        String correctAnswer = problem.checkAnswer(answer);
        if (answer.equals(correctAnswer)==true)
        {
        	System.out.println("CORRECT!");
        }
        else
        {
	        System.out.println("DAMN!");
        }
        return answer;   
    }
}
