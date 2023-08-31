# ***ProTag:*** Property Record Object | Tree Automata Grammar
## In one sentence: 
<h3 align="center"><i>A homoiconic property language that may define restricted replacement rules for tree grammars</i></h3>

## In an analogy:
> Imagine you have an ***initial tree*** in your back yard. You recently hired an ***arborist*** to help spruce it up a bit. So, you give them a ***list of instructions*** to ***graft*** your tree. 
> 
> Here's an example instruction: whenever you encounter a pear branch followed by two orange branches, replace that with an apple branch followed by an orange branch.
> 
> How long will your arborist take to execute your instructions? Will it be *fast* or *slow* based on the initial size of your tree? Or will your arborist continue replacing branches *forever*? Or is it *undecidable* if they will ever stop?
The key is in ***restricting the valid set of potential instructions*** you may give your arborist so you can guarantee that they will complete the job.

Now instead of using an actual tree, imagine that you could do this on JSON. ***This is the basis of ProTag.***
## In three words: *A new paradigm*.

### Ok, what does this *really* mean?
- ***homoiconic:*** every valid structure is data. The base file, when read, is a tree much like JSON
- ***property language:*** like JSON, we may read the file as just a tree structure. Or, we may specify adjunctions/replacements/macros so the tree produces itself, much like a lisp macro
- ***restricted:*** the user may specify the complexity of the automoton that processes the file. May be turing complete or non-turing complete.
  - `TYPE-0` is turing complete, we place no restrictions on the tree grammar
  - `TYPE-1` is an adjunction-based automata, context sensitive, and is guaranteed to complete in $O(n^6)$ time complexity
  - `TYPE-2` is a substitution-based automata, context-free, and is guaranteed to complete in $O(n^3)$ time complexity
  - `TYPE-3` is a regular tree-grammar automata, is guaranteed to complete in $O(n)$ time complexity
- ***replacement rules for tree grammars:***
  - This is hinted at above. Much like a grammar classification accepts a string, a tree grammar is a generalization that accepts a tree
  - A string of length $n$ may be represented as a tree with $n$ leaf nodes
  - Just like defining replacements on a string (from a sequence of non-terminal nodes to terminal-nodes), we may do the same on a tree
  - This may act like a lisp macro, but differs in two ways:
    1. We have more expressibility to match trees and to define how adjunctions and replacements may occur. Lisp is a special case where only a leaf defined by a name is replaced with a list-like tree
    2. We express more restrictions on how replacements may occur. We can ensure that a file and all references are non-turing complete and we can determine the set of all potential outputs even with arguments. This is particularly useful for code completion
- ***Property Record Object*** some data types you may encounter when using ProTag. Properties and records are datatypes defined by the language, and objects are instances that come from Java.
  - Example properties: `foo: a b c baz: d e f`. Properties are used to utilize automata grammar for a given section, like `if: clause then: something else: other`
  - Example record: `{a = b, c = d, a = f}`. Simple key-value storage. Permits the same key to be defined multiple times; tree grammar may define how to deal with the situation
  - Example object: `@javax.vecmath.Vector3d` defines a path to the java object, and we may replace nodes with java objects as well like below
  - Example replacement: `[x, y, z] -> @javax.vecmath.Vector3d.new[x, y, z]` defines a rule to replace a tree pattern matching `[x, y, z]` with an instance of a java object
  - Objects are not a first-class defined datatype in ProTag, but we may utilize them from Java. Plus, I needed something for the 'o' in the acronym
    
## Implications: this is a brand new branch of metaprogramming and an entirely ***new programming paradigm***.
- Currently, tree adjunction grammars are primarily theoretical and have yet to be implemented as first-class features of a programming language
- Tree adjunction grammars are also mainly used for NLP to generate a tree from a sentence. Mine uses the principles to take an initial tree to some terminal tree based on the rules
- I have not found any examples of tree grammars or tree automata being used as the basis for a programming language. However if you know of any, I would love to learn more. Feel free to email me. My AndrewID is ***zkieda***, an ***alumni cmu edu**.
  
