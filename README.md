# ProTag: Property Record Object|Tree Automata Grammar
## In one sentence: a homoiconic property language that may define turing and non-turing complete replacement rules for tree grammars
## In an analogy:
> Imagine you have an ***initial tree*** in your back yard. You recently hired an ***arborist*** to help spruce it up a bit. So, you give them a ***list of instructions*** to ***graft*** your tree. 
> 
> Here's an example instruction: whenever you encounter a pear branch that branches out to two orange branches, replace that with an apple branch followed by an orange branch. Whenever you see a pear branch attached to an apple branch, replace that with orange branch.
> 
> How long will your arborist take to execute your instructions? Will it be *fast* or *slow* based on the initial size of your tree? Or will your arborist continue replacing branches *forever*? Or is it *undecidable* if they will ever stop?
The key is in ***restricting the valid set of potential instructions*** you may give your arborist so you can guarantee that they will complete the job.

Now instead of using a an actual tree, imagine that you could do this on JSON. ***This is the basis of ProTag.***

### Ok, what does this *really* mean?
- ***homoiconic:*** every valid structure is data. The base file, when read, is a tree much like JSON
- ***property language:*** like JSON, we may read the file as just a tree structure. Or, we may specify adjunctions/replacements/macros so the tree produces itself, much like a lisp macro
- ***turing and non-turing complete:*** the user may specify the complexity of the automoton that processes the file.
  - `TYPE-0` is turing complete, but we place no restrictions on the tree grammar
  - `TYPE-1` is an adjunction-based automata, context sensitive, and is guaranteed to complete in $$O(n^6)$$ time complexity
  - `TYPE-2` is a substitution-based automata, context-free, and is guaranteed to complete in $$O(n^3)$$ time complexity
  - `TYPE-3` is a regular tree-grammar automata, is guaranteed to complete in $$O(n)$$ time complexity
- ***replacement rules for tree grammars:***
  - This is hinted at above. Much like a grammar classification accepts a string, a tree grammar is a generalization that accepts a tree
  - A string may be represented as a tree with n leaf nodes
  - Much like we may define replacement rules on a string (from a sequence of non-terminal nodes to terminal-nodes), we may do the same on a tree
  - This may act like a lisp macro, but differs in two ways:
    1. We have more expressibility to match trees and to define how adjunctions and replacements may occur. Lisp is a special case where only a leaf defined by a name is replaced with a list-like tree
    2. We allow more restrictions on how replacements may occur. We can ensure that a file and all references are non-turing complete and we can determine the set of all potential outputs even with arguments. This is particularly useful for code completion
- ***Property Record Object*** some data types you may encounter when using ProTag. Since this is written in Java, we allow the user to define replacements that define Java object instances
  - Example: `[x, y, z] -> @javax.vecmath.Vector3d.new[x, y, z]` may define a rule to replace a tree pattern matching `[x, y, z]` with an instance of a java object
    
## Implications: this is a brand new branch of metaprogramming and an entirely ***new programming paradigm***.
- Currently tree adjunction grammars are largely theoretical and have yet to be implemented as first-class features of a programming language
- Tree adjunction grammars are also mainly used for NLP to generate a tree from a sentence. Mine uses the principles to take an initial tree to some terminal tree based on the rules
- I have not found any examples of tree grammars or tree automata being used as the basis for a programming language. However if you know of any, I would love to learn more. Feel free to email me. My AndrewID is ***zkieda***, an ***alumni cmu edu**.
  
