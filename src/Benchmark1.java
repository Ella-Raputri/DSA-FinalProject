//import Java Scanner and IO utilities
import java.io.*;
import java.util.Scanner;


public class Benchmark1 {
    //initialize String arrays for questions and answers
    static String[] questions = new String[1000];
    static String[] answers = new String[1000];

    static int count = 0; //to track count of questions when inputting
    static Scanner scanner = new Scanner(System.in); //scanner for user input

    public static void inputQuestion() {
        try {
            //read the ExampleQuestions file
            FileReader fr =new FileReader("src/ExampleQuestions.txt");    
            BufferedReader reader = new BufferedReader(fr);
            String eachLine;
            //while reading each line
            while ((eachLine = reader.readLine()) != null) {
                //split each line by the '_' symbol
                String[] part = eachLine.split("_");
                questions[count] = part[0]; //the first part is the question
                answers[count] = part[1]; //the second part is the answer
                count++;
            }
            reader.close();

        } catch (IOException e) {
            //exception if file is not available
            System.out.println("Cannot find file. Please try again."); 
        }
    }


    static void getTime(long startTime, long endTime) {
        //get runtime by substracting endtime with startime
        double nanoSeconds = endTime - startTime;
        //convert it to miliseconds
        double milliSeconds = nanoSeconds/1000000;
        
        //print the result
        System.out.println("Time used: " + milliSeconds + " milliseconds");
        System.out.println();
    }

    static void getSpace(){
        //get space by first access the total memory and free memory
        Runtime rt = Runtime.getRuntime();
        long total_mem = rt.totalMemory();
        long free_mem = rt.freeMemory();
        //count the used memory
        long used_mem = total_mem - free_mem;

        //print the result
        String str = String.format("%,d", used_mem);
        System.out.println("Amount of used memory: " + str);
    }


    public void add(ArrayListBenchmark arrayList){
        int n;
        long startTime, endTime;
        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");
        System.out.println("Array List");

        startTime = System.nanoTime(); //get start time

        //perform n times adding Questions based on user input
        for (int i = 0; i < n; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }

        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }


    public void delete(ArrayListBenchmark arrayList){
        int n;
        long startTime, endTime;
        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");
        System.out.println("Array List");

        startTime = System.nanoTime(); //get start time

        //perform n times deleting Question number 1 based on user input
        int iterate = 0;
        while (iterate < n) {
            arrayList.deleteQuestion(arrayList.quiz.get(0).getQuestionID());
            iterate++;
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }


    public void edit(ArrayListBenchmark arrayList){
        int n;
        long startTime, endTime;
        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");
        System.out.println("Array List");

        startTime = System.nanoTime(); //get start time

        //perform n times editing Question based on user input
        for (int i = 0; i < n; i++) {
            arrayList.editQuestion(arrayList.quiz.get(i).getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used

    }


    public void changeOrder(ArrayListBenchmark arrayList){
        int n, randomNumber;
        long startTime, endTime;

        //ask user to input how many question numbers that user wants to test
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        //ask user to input which number that user wants to change all the number to it
        System.out.print("Enter random number (1-1000): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");
        System.out.println("Array List");

        startTime = System.nanoTime(); //get start time
        //perform n times changing Question order based on user input
        for (int i = 0; i < n; i++) {
            arrayList.changeOrder(arrayList.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime(); //get end time
        getTime(startTime, endTime); //get time used
        getSpace(); //get space used
    }


    public void print(ArrayListBenchmark arrayList){
        long startTime, endTime;
        int n;

        //ask user to input how many question numbers that user wants to test
        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");
        startTime = System.nanoTime();//get start time

        //perform n times printing Questions based on user input
        for (int i = 0; i < n; i++) {
            arrayList.printQuestions();
        }
        endTime = System.nanoTime();//get end time

        
        System.out.println("Array List");
        getTime(startTime, endTime);//get time used
        getSpace();//get space used
    }


    public void search(ArrayListBenchmark arrayList){
        long startTime, endTime;

        //Ask user for string that want to be searched
        System.out.print("Search for string or question ID: ");
        String str = scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");
        System.out.println("Array List");

        startTime = System.nanoTime(); //get start time
        arrayList.questionSearch(str); //search the string 
        endTime = System.nanoTime(); //get end time

        getTime(startTime, endTime);//get time used
        getSpace();//get space used

    }

    
    public static void main(String[] args) {
        //initialize benchmark1 to benchmark ArrayList
        Benchmark1 benchmark = new Benchmark1();
        inputQuestion(); //input all questions and answers to the String arrays

        ArrayListBenchmark arrayList = new ArrayListBenchmark();
        //add all 1000 questions to the ArrayList
        for (int i=0; i<1000 && i<count; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }
        
        //print the menus
        System.out.println("\n************************************");
        System.out.println("\nSpeed Testing");
        System.out.println("(A)dd");
        System.out.println("(D)elete");
        System.out.println("(E)dit Question");
        System.out.println("(C)hange Question's Order");
        System.out.println("(P)rint List of Questions");
        System.out.println("(S)earch");
        System.out.println("(Q)uit");
        System.out.println("************************************");

        //ask user to input a command to be counted
        System.out.print("Please enter a command: ");
        String command = scanner.nextLine();
        command = command.toLowerCase();

        switch (command) {
            //add question
            case "a":
                benchmark.add(arrayList);
                break;
    
            //delete question
            case "d":
                benchmark.delete(arrayList);
                break;
            
            //edit question
            case "e":
                benchmark.edit(arrayList);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(arrayList);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(arrayList);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(arrayList);
                break;
            
            //quit the program
            case "q":
                return;

            //invalid command
            default:
                System.out.println("Invalid command. Please try again");
                break;
        }

        
        
    }

    

    

}