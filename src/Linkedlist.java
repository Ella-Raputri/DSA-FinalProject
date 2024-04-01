public class Linkedlist {
    //total nodes of linked list
    static int nodeAmount = 0;
    public class Node{
        //attributes of the node in the tree
        public Question data; //value of the node in the linked list
        public Node next; //the next node of the node

        //constructor
        public Node(Question data){
            this.data = data;
            this.next = null;
            nodeAmount++; //increment the node amount
        }
    }

    //the head and tail will initially be null
    public Node head = null;
    public Node tail = null;

    public boolean isEmpty(){
        //to check whether the linked list is empty or not
        if(head == null){
            //if head is null, then the list is empty
            return true;
        }
        return false; //if not, then the list is not empty
    }

    public Node getNode(String ID){
        //get node based on the ID
        Node current = head; //start from the head node
        while(current != null){ //while current node is not null
            //if current node ID equals to inputted ID
            if(current.data.getQuestionID().equals(ID)){
                //return current node
                return current;
            }
            //proceed to next node if not
            current = current.next;
        }
        //return null if no matching node is found
        return null;
    }

    public String getIDFromNumber(int questionNumber){
        //start from the head node
        Node current = head;
        //while node is not null
        while(current != null){
            //if current node question number is the same as the inputted one
            if(current.data.getQuestionNumber() == questionNumber){
                //return the question ID
                return current.data.getQuestionID();
            }
            //if not, proceed to the next node
            current = current.next;
        }
        return null; //if no matching node is found
    }

    public void resetNumber(){
        //start from the head node
        Node current = head;
        int tracker = 1;
        while(current!= null){
            //set all the question number in order
            current.data.setQuestionNumber(tracker);
            //proceed to next node
            current = current.next;
            tracker++;
        }
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


    //delete the node
    public void deleteNode(Node current){;
        Node tracker = head;

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
                //set the next node
                temp.next = tracker.next;
                if(tracker.next == null){
                    //if the next node is null, then set the temp as tail
                    tail = temp;
                }
            }
        }
        return;    
        
    }


    public void changeNodeOrder(int newNumber, Node current, int currentNumber){
        if(newNumber == 1){
            //get the node that contains the current number-1 or is the previous of the want to change node
            String previousid = getIDFromNumber(currentNumber-1);
            Node previous = getNode(previousid);

            //if the next node is null, then set the tail to the previous node
            if(current.next == null){
                tail = previous;
            }

            //the original previous node will now connected to the original next node
            previous.next = current.next;
            //the current or original node will be the head and its next node now is the old head
            current.next = head;
            head = current;
        }
        else if(newNumber == nodeAmount){
            //if new number is the last number
            //get the node that is the previous of the want to change node
            String previousid = getIDFromNumber(currentNumber-1);
            Node previous = getNode(previousid);

            //connect the original previous node to the original next node
            if(previous !=null){
                previous.next = current.next;
            }
            //if previous is null, then original node is head
            //so, we have to set the original next node to be the new head
            else{
                head = current.next;
            }

            //the want to change node will be the tail now
            current.next = null;
            Node last = head;
            //set all the remaining nodes to move forward by 1
            while (last.next != null){
                last = last.next;
            }
            last.next = current;
            tail = current;
        }
        else{
            //get the node that is the previous of the want to change node
            String previousid = getIDFromNumber(currentNumber-1);
            Node previous = getNode(previousid);

            //if previous is not null
            if(previous != null){
                //connect the original previous node to the original next node
                previous.next = current.next;

                //if the original next node is null or the original node is the tail
                //then set the original previous node to be the tail
                if(current.next == null){
                    tail = previous;
                }
            }
            else{
                //if previous is null (original node is head)
                //then set the original next node to be the head
                head = current.next;
            }

            if(currentNumber < newNumber){
                //backward case 
                //get new previous node for the want to change node
                String newprevid = getIDFromNumber(newNumber);
                Node newprev = getNode(newprevid);

                //set the want to change (original) node to the new position
                current.next = newprev.next;
                newprev.next = current;
            }
            else{
                //forward case
                //get new previous node for the want to change node
                String newprevid = getIDFromNumber(newNumber-1);
                Node newprev = getNode(newprevid);

                //set the want to change node to the new position
                current.next = newprev.next;
                newprev.next = current;
            }                    
        }

    }
            
        



}
