public class Main {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Detect cycle and return the node where the cycle begins
    public static Node detectCycle(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Step 1: Detect if there is a cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer by 1 step
            fast = fast.next.next;    // Move fast pointer by 2 steps

            if (slow == fast) {       // Cycle detected
                // Step 2: Find the start of the cycle
                Node pointer1 = head;
                Node pointer2 = slow;

                while (pointer1 != pointer2) {
                    pointer1 = pointer1.next;
                    pointer2 = pointer2.next;
                }

                return pointer1; // Start of the cycle
            }
        }

        return null; // No cycle
    }

    // Helper method to create a sample linked list with a cycle
    public static Node createLinkedListWithCycle() {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        // Creating a cycle (fifth node points back to second)
        fifth.next = second;

        return head;
    }

    public static void main(String[] args) {
        Node head = createLinkedListWithCycle();

        Node cycleStart = detectCycle(head);

        if (cycleStart != null) {
            System.out.println("Cycle detected at node with value: " + cycleStart.value);
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
