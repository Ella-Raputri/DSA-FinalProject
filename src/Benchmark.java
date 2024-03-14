import java.io.*;
import java.util.Scanner;


public class Benchmark {
    static String[] questions = new String[250];
    static String[] answers = new String[250];
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void inputQuestion() {
        try {
            FileReader fr =new FileReader("src/ExampleQuestions.txt");    
            BufferedReader reader = new BufferedReader(fr);
            String eachLine;
            while ((eachLine = reader.readLine()) != null) {
                String[] part = eachLine.split("_");
                questions[count] = part[0];
                answers[count] = part[1];
                count++;
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Cannot find file. Please try again.");
        }
    }


    static void getTime(long startTime, long endTime, int nullCount, int n) {
        double nanoSeconds = endTime - startTime;
        double milliSeconds = nanoSeconds/1000000;

        if(nullCount != 0){
            milliSeconds = ((nullCount/n) * milliSeconds) + milliSeconds;
        }
        
        System.out.println("Time used: " + milliSeconds + " milliseconds");
        System.out.println();
    }


    public void add(ArrayListBenchmark arrayList, HashMapBenchmark hashMap, LinkedlistBenchmark linkedList, TreeRedBlackBenchmark tree){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nADDING QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Linked list
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedList.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tree.addQuestion(questions[i], answers[i]);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);

    }


    public void delete(ArrayListBenchmark arrayList, HashMapBenchmark hashMap, LinkedlistBenchmark linkedlist, TreeRedBlackBenchmark tree){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nDELETING QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.deleteQuestion(arrayList.quiz.get(i).getQuestionID());
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        Question[] setofkey = hashMap.returnKeySet();
        for (int i = 0; i < n; i++) {
            Question key = setofkey[i];
            hashMap.deleteQuestion(key.getQuestionID());
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedlist.deleteQuestion(linkedlist.quiz.getIDFromNumber(i+1));
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);


        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            TreeNode treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), i+1);
            String id = treenode.data.getQuestionID();
            tree.deleteQuestion(id);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);
    }


    public void edit(ArrayListBenchmark arrayList, HashMapBenchmark hashMap, LinkedlistBenchmark linkedlist, TreeRedBlackBenchmark tree){
        int n;
        long startTime, endTime;
        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEDITING QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.editQuestion(arrayList.quiz.get(i).getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);


        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        Question[] setofkey = hashMap.returnKeySet();
        for (int i = 0; i < n; i++) {
            Question key = setofkey[i];
            hashMap.editQuestion(key.getQuestionID(), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedlist.editQuestion(linkedlist.quiz.getIDFromNumber(i), "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);


        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            TreeNode treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), i+1);
            String id = treenode.data.getQuestionID();
            tree.editQuestion(id, "y", "a", "y", "a");
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);
        
    }


    public void changeOrder(ArrayListBenchmark arrayList, HashMapBenchmark hashMap, LinkedlistBenchmark linkedlist, TreeRedBlackBenchmark tree){
        int n, randomNumber;
        long startTime, endTime;

        System.out.print("Enter number of questions: ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter random number (1-250): ");
        randomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nCHANGING ORDER OF QUESTIONS");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.changeOrder(arrayList.quiz.get(i).getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0,n);


        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        Question[] setofkey = hashMap.returnKeySet();
        for (int i = 0; i < n; i++) {
            Question key = setofkey[i];
            hashMap.changeOrder(key.getQuestionID(), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0, n);


        //Linked List 
        System.out.println("Linked List");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            // int randomNumber = rand.nextInt(n);
            linkedlist.changeOrder(linkedlist.quiz.getIDFromNumber(i), randomNumber);
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime,0, n);


        //Red Black Tree
        int nullCount=0;
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            TreeNode treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), i+1);
            if(treenode != null){
                String id = treenode.data.getQuestionID();
                tree.changeOrder(id, randomNumber);
            }
            else{
                nullCount++;
                System.out.println("null");
            }
            
        }
        endTime = System.nanoTime();
        getTime(startTime, endTime, nullCount, n);
        
    }


    public void print(ArrayListBenchmark arrayList, HashMapBenchmark hashMap, LinkedlistBenchmark linkedlist, TreeRedBlackBenchmark tree){
        long startTime1, endTime1, startTime2, endTime2, startTime3, endTime3, startTime4, endTime4;
        int n;

        System.out.print("How many times you want to print all the questions? ");
        n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPRINTING QUESTIONS");

        //Array List
        startTime1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            arrayList.printQuestions();
        }
        endTime1 = System.nanoTime();

        //Hashmap
        startTime2 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            hashMap.printQuestions();
        }
        endTime2 = System.nanoTime();

        //Linked List
        startTime3 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linkedlist.printQuestions();
        }
        endTime3 = System.nanoTime();

        //Red Black Tree
        startTime4 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            tree.printQuestions();
        }
        endTime4 = System.nanoTime();


        System.out.println("Array List");
        getTime(startTime1, endTime1,0,n);
        System.out.println("Hash Map");
        getTime(startTime2, endTime2,0,n);
        System.out.println("Linked List");
        getTime(startTime3, endTime3, 0,n);
        System.out.println("Red Black Tree");
        getTime(startTime4, endTime4, 0,n);
    }


    public void search(ArrayListBenchmark arrayList, HashMapBenchmark hashMap, LinkedlistBenchmark linkedlist, TreeRedBlackBenchmark tree){
        long startTime, endTime;
        int n = 250;

        System.out.print("Search question number: ");
        int testingNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nSEARCHING A QUESTION");

        //Array List
        System.out.println("Array List");
        startTime = System.nanoTime();
        arrayList.questionSearch(arrayList.quiz.get(testingNumber-1).getQuestionID());
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);

        //Hashmap
        System.out.println("Hash Map");
        startTime = System.nanoTime();
        String id = hashMap.returnID(testingNumber);
        hashMap.questionSearch(id);
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Linked List
        System.out.println("Linked List");
        startTime = System.nanoTime();
        linkedlist.questionSearch(linkedlist.quiz.getIDFromNumber(testingNumber));
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);


        //Red Black Tree
        System.out.println("Red Black Tree");
        startTime = System.nanoTime();
        TreeNode treenode = tree.quiz.searchNodeBasedonNumber(tree.quiz.getRoot(), testingNumber);
        String id_tree = treenode.data.getQuestionID();
        tree.questionSearch(id_tree);
        endTime = System.nanoTime();
        getTime(startTime, endTime, 0, n);
        
    }

    
    public static void main(String[] args) {
        Benchmark benchmark = new Benchmark();
        inputQuestion();

        ArrayListBenchmark arrayList = new ArrayListBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            arrayList.addQuestion(questions[i], answers[i]);
        }

        HashMapBenchmark hashMap = new HashMapBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            hashMap.addQuestion(questions[i], answers[i]);
        }

        LinkedlistBenchmark linkedlist = new LinkedlistBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            linkedlist.addQuestion(questions[i], answers[i]);
        }

        TreeRedBlackBenchmark tree = new TreeRedBlackBenchmark();
        for (int i=0; i<250 && i<count; i++) {
            tree.addQuestion(questions[i], answers[i]);
        }

        
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
        System.out.print("Please enter a command: ");
        String command = scanner.nextLine();
        command = command.toLowerCase();

        switch (command) {
            //add question
            case "a":
                benchmark.add(arrayList, hashMap, linkedlist, tree);
                break;
    
            //delete question
            case "d":
                benchmark.delete(arrayList, hashMap, linkedlist, tree);
                break;
            
            //edit question
            case "e":
                benchmark.edit(arrayList, hashMap, linkedlist, tree);
                break;
            
            //change order
            case "c":
                benchmark.changeOrder(arrayList, hashMap, linkedlist, tree);
                break;
            
            //printing all questions
            case "p":
                benchmark.print(arrayList, hashMap, linkedlist, tree);
                break;
            
            //searching a particular question
            case "s":
                benchmark.search(arrayList, hashMap, linkedlist, tree);
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