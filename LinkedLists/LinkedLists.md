# Singly and Doubly Linked Lists

A linked list is a sequential list of nodes that holds data and a pointer to the next node.
They are often used for Queues and Stacks or for circular lists.

The *HEAD* is the first node of the list. The *TAIL* is the last element in the list.

A doubly linked list simply adds another pointer to every node, a pointer to the previous node. It uses more memory but makes traversal more flexible.

Inserting a node will need a traversal to the right position and then adjusting of the pointers.
For removing we will need to look ahead of our current traversal node so we can save the right node for adjusting the pointers. When using doubly linked lists we only need to traverse and then remove the node by changing the previous and next pointer.

## Complexity

+ Search: O(n)
+ Insert at Head/Tail: O(1)
+ Remove tail: O(n) for singly linked and O(1) for doubly linked
+ Remove: O(n)