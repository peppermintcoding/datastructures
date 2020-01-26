# Static and Dynamic Arrays

A static array is a fixed length container of *indexable* elements. They are a contigues block of memory.
They are often used for buffers, storing temporary data, lookup tables or to return multiple values from a function.

## Complexity

+ Access: O(1)
+ Search: O(n)
+ Insertion, Appending, Deletion: O(1)

A dynamic array can *grow* and *shrink* in size. We can implement a dynamic array with a static array with an initial capacity and resize the underlaying array if needed and copy the data.

