## put on pause
### AST generation
* java cup builds from the bottom down (exclusively)
* this makes certain things pretty tough, like setting the parent node etc
* Makes storing meta information also tough
#### Meta Table
* We want to have a table that we can use to get the location 
  of protag nodes, so that if a runtime error occurs we can see 
  where the problems and nodes originate
* This is also used to attach comments and documentation to individual nodes
* Problem: java cup builds from the bottom up. So, we don't have access 
  to a given symbol until a rule is evaluated and its action is completed. 
* Thus, a protag node will be built with the symbol completely unavailable till later
* The solution: continuations

***current state*** - meta table was in production, but removed temporarily. 
check commits.
#### Continuations
* Keep the protagnode type, but in cup parsing declare a continuation type
* Have relevant nodes pass up a function with the following signature:
  * (parent: ProtagNode, parentSymbol: ProtagSymbol) -> child:ProtagNode'
  * this may add an entry for parent to the meta table
  * this may add symbol for the parent in the meta table
  * this may add comments for the meta table entry
  * this may instantiate the child, such that its parent is know upon creation
* the final result of parsing will be a continuation, which will generate the full tree by
  * (parent: null, parentSymbol: $START) where $START is the result of the parser.

*** current state*** - continuations not implemented. Had a partial implementation of 
passing items upward via a map. However, the logic is voodoo and would be better with continuations.
Will remove existing code before eventually adding back.

