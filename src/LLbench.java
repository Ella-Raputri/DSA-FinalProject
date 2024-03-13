import java.util.Scanner;

public class LLbench {
    class Node{
        public Question data;
        public Node next;

        public Node(Question data){
            this.data = data;
            this.next = null;
        }
    }

    public Node head = null;
    public Node tail = null;


    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        return false;
    }

    public Node getNode(String ID){
        Node current = head;
        while(current != null){
            if(current.data.getQuestionID().equals(ID)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public String getIDFromNumber(int questionNumber){
        Node current = head;
        while(current != null){
            if(current.data.getQuestionNumber() == questionNumber){
                return current.data.getQuestionID();
            }
            current = current.next;
        }
        return null;
    }

    public void resetNumber(){
        Node current = head;
        int tracker = 1;
        while(current!= null){
            current.data.setQuestionNumber(tracker);
            current = current.next;
            tracker++;
        }
    }

    public int nodeAmount(){
        Node current = head;
        int tracker = 0;
        while(current!= null){
            tracker++;
            current = current.next;
        }
        return tracker;
    }


    //add node method
    public void addNode(Question data){
        //create a new node
        Node newNode = new Node(data);

        //check if the list is empty
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;    //new node will be linked to the previous tail node
            tail = newNode;         //new node becomes the new tail node
        }
    }


    //print the linked list
    public void printLinkedList(){
        //current node will point to head first
        Node current = head;
        
        if(head == null){
            return;
        }

        System.out.println("\nYour current quiz: ");
        while(current!=null){
            System.out.println("Question " + current.data.getQuestionNumber());
            System.out.println("Question ID: "+ current.data.getQuestionID());
            System.out.println("Question: "+ current.data.getQuestion());
            System.out.println("Answer: "+ current.data.getCorrectAnswer());
            System.out.println();
            current = current.next;
        }
    }


    //delete the node
    public void deleteNode(String id){
        Node current = getNode(id);
        Node tracker = head;

        if(current!=null){
            //delete the data if the node on head
            if(head.data == current.data){
                head = current.next;
            }
            else{
                Node temp = null;
            
                //check the data based linked list
                while(tracker!=null && tracker.data!=current.data){
                    temp = tracker;
                    tracker = tracker.next;
                }

                //if the data exists
                if(tracker!=null){
                    temp.next = tracker.next;
                }
            }
            return;
        }
        
        
    }


    public void editNode(String id, Scanner scanner, String questionChange, String newQuestion, String answerChange, String newAnswer){
        Node current = getNode(id);

        if (current != null){
            if(questionChange.equals("y")){
                current.data.setQuestion(newQuestion);
            }
            else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                return;
            }

           
            if(answerChange.equals("y")){
                current.data.setCorrectAnswer(newAnswer);
            }
            else if(!(questionChange.equals("y")) && !(questionChange.equals("n"))){
                return;
            }

            return;
        }

                
    }


    public void changeNodeOrder(String id, Scanner scanner, int newNumber){
        Node current = getNode(id);

        if(current != null){
            int currentNumber = current.data.getQuestionNumber();

            int nodeAmount = nodeAmount();

            if(currentNumber == newNumber){
                return;
            }

            else if(newNumber <= nodeAmount && newNumber > 0){
                if(newNumber == 1){
                    String previousid = getIDFromNumber(currentNumber-1);
                    Node previous = getNode(previousid);

                    previous.next = current.next;
                    current.next = head;
                    head = current;
                }
                else if(newNumber == nodeAmount){
                    String previousid = getIDFromNumber(currentNumber-1);
                    Node previous = getNode(previousid);

                    if(previous !=null){
                        previous.next = current.next;
                    }
                    else{
                        head = current.next;
                    }

                    current.next = null;
                    Node last = head;
                    while (last.next != null){
                        last = last.next;
                    }
                    last.next = current;
                    
                }
                else{
                    String previousid = getIDFromNumber(currentNumber-1);
                    Node previous = getNode(previousid);

                    if(previous != null){
                        previous.next = current.next;
                    }
                    else{
                        head = current.next;
                    }

                    String newprevid = getIDFromNumber(newNumber);
                    Node newprev = getNode(newprevid);

                    current.next = newprev.next;
                    newprev.next = current;

                }

                resetNumber();
            }

            else if(newNumber > nodeAmount || newNumber < 0){
                return;
            }

            return;
        }
    }
            
        


    public void searchNode(String id){
        Node current = getNode(id);
        
        if(current != null){
            System.out.println("Question " + current.data.getQuestionNumber());
            System.out.println("Question ID: "+ current.data.getQuestionID());
            System.out.println("Question: "+ current.data.getQuestion());
            System.out.println("Answer: "+ current.data.getCorrectAnswer());
            System.out.println();
            return;
        }
        System.out.println("ID invalid. Please try again.");
    }



}
