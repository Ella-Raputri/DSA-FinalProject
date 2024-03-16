public class Linkedlist {
    static int nodeAmount = 0;
    public class Node{
        public Question data;
        public Node next;

        public Node(Question data){
            this.data = data;
            this.next = null;
            nodeAmount++;
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
                temp.next = tracker.next;
                if(tracker.next == null){
                    tail = temp;
                }
            }
        }
        return;    
        
    }



    public void changeNodeOrder(int newNumber, Node current, int currentNumber){
        if(newNumber == 1){
            String previousid = getIDFromNumber(currentNumber-1);
            Node previous = getNode(previousid);
            if(current.next == null){
                tail = previous;
            }

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
            tail = current;
        }
        else{
            String previousid = getIDFromNumber(currentNumber-1);
            Node previous = getNode(previousid);

            if(previous != null){
                previous.next = current.next;
                if(current.next == null){
                    tail = previous;
                }
            }
            else{
                head = current.next;
            }

            if(currentNumber < newNumber){
                String newprevid = getIDFromNumber(newNumber);
                Node newprev = getNode(newprevid);
                current.next = newprev.next;
                newprev.next = current;
            }
            else{
                String newprevid = getIDFromNumber(newNumber-1);
                Node newprev = getNode(newprevid);
                current.next = newprev.next;
                newprev.next = current;
            }                    
        }

    }
            
        



}
