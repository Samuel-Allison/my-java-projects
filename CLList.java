/**
 * Implementation of a linked list using link
 * 
 * @author Sam-School
 * @version 20170219
 *
 */
public class CLList {
    private Link head;
    private Link tail;
    private Link curr;
    private int listSize;
    private int cutAmont;
    //private Link head2;
   // private Link tail2;

    /**
     * constructor from the book
     * 
     * @param size
     *            takes a size does nothing with it
     */
    public CLList(int size) {
        this();
    }

    /**
     * another constructor calls the clear method that creates the initializes
     * the current tail and head and listsize
     */
    public CLList() {
        clear();
    }

    /**
     * initializes the curr, tail, head and listSize
     */
    private void clear() {
        curr = tail = new Link(null);
        head = new Link(tail);
        listSize = 0;
    }

    /**
     * inserts a new node after the current node
     * 
     * @param it
     *            takes a character and creates a new node with that character
     * @return a boolean of true stating that the new node was made
     */
    public boolean insert(java.lang.Character it) {
        curr.setNext(new Link(curr.element(), curr.next()));
        curr.setElement(it);
        if (tail == curr) {
            tail = curr.next();
        }
        listSize++;
        return true;
    }

    /**
     * removes the current node and returns the value that was removed
     * 
     * @return the value that was removed
     */
    public Character remove() {
        if (curr == tail)
            return null;
        Character it = curr.element();
        curr.setElement(curr.next().element());
        if (curr.next() == tail)
            tail = curr;
        curr.setNext(curr.next().next());
        listSize--;
        return it;
    }

    /**
     * moves the current pointer to the head pointer
     * 
     */
    public void moveToStart() {
        curr = head.next();
    }

    // private void moveToEnd(){
    // curr = tail;
    // }
    /**
     * moves the current pointer back one node
     */
    public void prev() {
        if (head.next() == curr)
            return;
        Link temp = head;
        while (temp.next() != curr) {
            temp = temp.next();
        }
        curr = temp;
    }

    /**
     * moves the current pointer to the next node as long as not pointing
     * already towards the tail
     */
    public void next() {
        if (curr != tail) {
            curr = curr.next();
        }
    }

    /**
     * returns the size of the list
     * 
     * @return a integer that states how many nodes are in the list
     */
    public java.lang.Integer length() {
        return listSize;
    }

    /**
     * tells us where the curr pointer is pointing at this time
     * 
     * @return the integer number of the node we are currently at
     */
    public java.lang.Integer currPos() {
        Link temp = head.next();
        int i;
        if (length() == 0) {
            return -1;
        }
        for (i = 0; curr != temp; i++) {
            temp = temp.next();
        }
        return i;
    }

    /**
     * private method moves the curr pointer to the disiered point in the list
     * 
     * @param pos
     *            is a int that tells us where we want to move to in the array
     * @return true if pos is in the bounds of the list false otherwise
     */
    private boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        curr = head.next();
        for (int i = 0; i < pos; i++) {
            curr = curr.next();
        }
        return true;
    }

    // private boolean isAtEnd(){
    // return curr == tail;
    // }
    /**
     * returns the character found at the current element
     * 
     * @return the character found at the current element
     */
    public java.lang.Character get() {
        if (curr.element() == null) {
            throw new java.lang.RuntimeException();
        }
        else
            return curr.element();
    }

    /**
     * removes the current selected elements from this lest
     */
    public void cut() {
        int i = 0;
        while (i < cutAmont) {
            // System.out.println(get());
            remove();
            i++;
            //
        }
    }

    /**
     * selects n elements starting from the current position of this list
     * current element included
     * 
     * @param n
     *            - number of elements to select
     */
    public void select(java.lang.Integer n) {
        int a = currPos();
        cutAmont = n;
        //head2 = curr;
        int total = a + n;
        if (moveToPos(total) == true) {
            // head2 = curr;
            // tail2 = curr;
            // head2 = curr;
            // curr.setElement((char) ( curr.element()));
            // System.out.println(get());
            moveToPos(total - n);
        }
    }

}
