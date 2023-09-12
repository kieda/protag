## operational semantics: basic library
- [ ] create library for runtime data
- [ ] create library to parse number-based regexes (todo)
- [ ] separate out into java side data, plugin types of data, and protag node data itself
- [ ] create java-side functionality for AST replacement
  - [ ] function to match node, function to replace node
  - [ ] what types of labels will we want to apply to a node after transformation? 
    - E.g. is it terminal/non-terminal? 
    - Is it adjunction labeled?
    - Todo - flesh out mathematics component
  - [ ] is there anything that we can do to define replacements that only would occur... 
    - At the top level only?
    - At the leafs only? 
    - Something else?
      - `symbol1 VALUE1 symbol2 VALUE2` might be evaluated like `eval(symbol1 eval(VALUE1) symbol2 eval(VALUE2))` in bottom up
      - We might want to have `eval(symbol1 VALUE1 symbol2 VALUE2); eval(VALUE2'); eval(VALUE1');`
        Where we evaluate the full clause first, then evaluate the resulting expressions later. Example: defining functions.
      - Possibly EVAL and STOP. Evaluate the expression, get the result, then stop evaluating sub-expressions.
      
- [ ] create functionality for fleshing out identifiers in scope, binding identifiers to names, etc
  - [ ] defining paths and scopes of items in java vs defining paths and scopes of items in protag
  - [ ] what definitions would be brought in scope when opening up an environment
  - [ ] if we store a node to be parsed later, keep the environment but only apply in scope?


## operational and denotational semantics of replacement rules
- [ ] to think about: how do we represent a tree-based node?
  - possible for generic representation: 
    ```
    a <- unordered  # top level node we're matching is unordered
      => b <- &leaf # b is a leaf type
      => (c => d <- &int
            => f)
      => (e => *)   # e is a node with some number of nodes 
      => rest <- *  # matches the rest of the nodes 
    ```
    Can match arbitrary non-leaf nodes
- [ ] how do we represent its type? Possible `&type`
- [ ] how do we represent multiple values in a map, list, sequence, etc?
  - possible: `[a, b, * -> value]`. Bind first value to `a`, bind second value to `b`, rest of values are bound to `value`
  - another: `[a, b, value <- *]` Where `<- *` specifies the `*` label to `value` which will be bound
  - types: possibly `x <: &real`, or `[a, b, c] <: [ &real ]`
  - Would split into data specification vs type specification. Different rules for evaluating
- [ ] Type heirarchy-ish. `&int < &number < &leaf < &protag`
  - note that `&int` can be used for `java.lang.Integer`. 
  - Can build a graph of different data types and autocast and automatch. 
  - However, if we want to match `&string`, allowing a `&int` might not be acceptable even if they can be cast
  - Weak matching vs strong matching. 
    - Weak matching: types may be converted to one another "casting" and "duck typing". Can be used to target parameters of a function, etc
    - Strong matching: types converted to one another with no loss

## put on pause
### AST generation
* [x] note: moved to javacc. Following does not matter 
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
#### ~~Continuations~~
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

