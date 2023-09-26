# ***ProTag:*** Property Record Object | Tree Automata Grammar
## In one sentence: 
<h3 align="center"><i>A homoiconic property language that may define restricted replacement rules for tree grammars</i></h3>

## In an analogy:
> Imagine you have an ***initial tree*** in your back yard. You recently hired an ***arborist*** to help spruce it up a bit. So, you give them a ***list of instructions*** to ***graft*** your tree. 
> 
> Here's an example instruction: whenever you encounter a pear branch followed by two orange branches, replace that with an apple branch followed by an orange branch.
> 
> How long will your arborist take to execute your instructions? Will it be *fast* or *slow* based on the initial size of your tree? Or will your arborist continue replacing branches *forever*? Or is it *undecidable* if they will ever stop?
>
> The key is in ***restricting*** the ***valid set of instructions*** you may give your arborist so you can mathematically guarantee they will complete the job.

Now instead of using an actual tree, imagine that you could do this on JSON. ***This is the basis of ProTag.***
## In three words: *A new paradigm*.

### Ok, what does this *really* mean?
- ***homoiconic:*** every valid structure is data. The base file is just a tree much like JSON
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
  - Example replacement: `[x, y, z] -> @javax.vecmath.Vector3d(args: x y z)` defines a rule to replace a tree pattern matching `[x, y, z]` with an instance of a java object
  - Objects are not a first-class defined datatype in ProTag, but we may utilize them from Java. Plus, I needed something for the 'o' in the acronym

## Cool, show me an example!

### Hello world 
```
import: 
  !protag/debug.pt
in: 
  print( "Hello World" )
```

The structure of this file is as follows: 
```
  Properties[
    Section("import", 
      FilePath[ "protag", debug.pt" ]
    ), 
    Section("in", 
      ProtagPath[print, "Hello World"]
    )
  ]
```
When evaluated, we import the `debug` plugin which defines a rule that matches `print` 
followed by other items in a path. The subsequent items in the path are printed, and the `ProtagPath` node is removed from the tree

Based on these replacement rules, there are multiple ways to say hello world
```
import: 
  !protag/debug.pt
section: 
  print"Hello ""World"       # ProtagPath[ print, "Hello ", "World" ]
  print["Hello ", "World"]   # ProtagPath[ print, ["Hello ", "World"]]
  (print: "Hello World")     # Properties[Section(print, "Hello World")] 
```
The first two follow the same replacement rules, and are essentially equivalent. 

The third opens the `print` environment which was imported. Whenever the name of a section resolves to 
an environment, a list of environments, or a map of environments, we use the replacement rules the 
environment defines. 

Note that this may just be parsed as a structured file, or we can evaluate it. Evaluation only requires one
plugin, the `ProtagImport` plugin, which allows all other functionality to be imported and defined.

### Defining a replacement rule

```
# define a replacement rule, and apply it!
import:
  # imports a plugins that define 
  # * assigning/reading variables
  # * writing replacement rules
  (!protag/base.pt).[vars, rules]   
  boxMesh = !meshes/box.pt
  # define a replacement rule
  vertices = [x, y, z] <-                      # match [x, y, z]
      [_ <- &number]                           # each element is a number
      -> @javax.vecmath.Vector3d(args: x y z)  # replace with Vector3d instance
vertices: # use vertices rule
    [0, 3 / 4, 0.33334]   # base number types: integer, quotient, real 
    boxMesh.vertices      # use boxMesh.vertices import
```
We allow replacement rules to be defined in protag itself, and it essentially consists 
of three parts
1. matching - will our current node match the one we're looking at?
2. rewriting - what will our resulting matched node structure look like when rewritten?
3. labeling - additional information for matching and rewriting. Are resulting nodes terminal,
   non-terminal, adjunction sites, etc? Are we matching in an unordered or ordered manner in the tree?

Note that in our example, the nodes defining a replacement rule is itself processed by a replacement rule
that needs to be imported. Since we can't write this replacement rule using replacement rules, it is written in Java.
User-based Java based replacement rules are supported.

### Functions

Using imports can be useful for providing arbitrary functionality, for example we can define functions using
```
import: 
  !protag/functional.pt !protag/math.pt
  intList = !myValues.pt # defines a series of integers
in:
      # via functional, defines a function
      # importing functional.pt will mark this file as turing complete!
      negateAll = # match a sequence where first item is a pair vName = vItem
                  # rest of items in the sequence are placed into 'rest' identifier 
                  fun: vName = vItem rest <- +  
                         # bind "neg"vName to negated vItem, then recurse
                         is: ("neg"vName = neg: vItem) (negateAll: rest)
                  fun: vName = vItem
                         is: ("neg"vName = neg: vItem);
      
      vertices: (negateAll: intList) 
```
This defines a `negateAll` closure, which runs through a sequence and negates the items, producing a new 
sequence of the negated items.

### Basic Types
Every node resolves to a type. Here are examples:
```
# properties: a list of sections
yapPrimitives:   # begin properties
   123           # int
   123.456       # real
   1 & 3/4       # quotient
   myName        # identifier
   `regex~flags` # regex
   "name"        # literal
protagConstructs:# (another section)
   name1 = 1     # pair
   2 = name2     # pair
   a <- b        # left transition
   a -> b        # right transition
   [item1, 0]    # list
   item1"abc"2`foo`bar    # segment - consists of multiple items concatenated
   item1"abc".2.`foo`bar  # path - consists of multiple segments
   {myName1 = name1, myName2 = name2} # map
   # properties - to contain properties within properties use ( .. )
   ( hello: val1 
     world: val2 )
   # properties may end with ';' for pair-like nodes
   a = hello: val1 val2;
   a -> hello: val1 val2;
combineConstructs:
   name1 = name2 = 1  # equivalent to (name1 = (name2 = 1))
   (l, r) = (1, 2)
   {a = b, c = d} = (section: 1 2 section: 3 4)
       # pair( map( pair(a, b), pair(c, d) ), properties((section, 1 2), (section, 3 4)) )
```

For disambiguation, the following constructs are only permitted directly in segments
* Natural  `"hello"123`
* Identifier `"hello"world`
* Regex ``123`[0-9]+`world ``
* Literal `"hello""world"`
* List `"hello"["world", "foo"]`
* Map `"hello"{"foo" = plugin1, "baz" = plugin2}`
* Precedence `"hello"(1 & 3/4)(section: val)(a -> b)`

Note that non-primitive constructs allow us to place arbitrary data in a segment, but may need to be wrapped
in parentheses `( ... )` for precedence.

### Another example: map rewriting
```
# user/mymap.pt
import: 
  !protag/rules.pt
  !protag/export.pt
in: 
  to-unique = { 
    before <- *,
    key = value1,
    mid <- *,
    key = value2,
    after <- * 
  } -> { before, mid, key = value2, after } <- result: post-term type: &uniquemap;
export: 
  to-unique
```
* This rule matches an existing map that might have duplicate entries. 
* Then, it finds instances where the keys are the same. 
* Then, it rewrites the existing map node such that `value2` is used over `value1` while the rest of the structure is the same.
* Finally, after no more rules can be applied, the node is replaced and cast to `&uniquemap`, which is essentially a java `HashMap`
  which throws if a duplicate entry is added


### Set-based type system
Typing is based on _sets_. We check to see if an item belongs in a set, and we 
have basic constructs for non-turing complete set generation.

For example, a regex is really just a set of strings that we can test for inclusion with a regular automaton.
Or, `^natural` is really just a set defined by all the integers `>=0`, and 
`^integer` is just a superset of these numbers. `^number` defines a set that's the union of 
`^integer`, `^quotient`, `^real`, etc.

We write `x <: Y` to match when an item `x` is in the set `Y`. For example, 
`6 <: ^natural`, ``aaabc <: `a+bc` ``. We may union and intersect sets 
using `|` and `&` operations. 


We write `a (= B` to represent 
`a` is a subset of `B`. For example `a (= ^integer` can match when `a` is `^natural`. Similarly, 
we write `a =) B` to represent `a` is a superset of `B`. 

However, this can be more powerful than For many use cases, this is analogous
to `a extends B` where `B` represents a class. 

What is a class anyway? Let's start with objects. Objects are essentially a strict map 
from a string identifier to a value or method. The values are typed, and all the possible 
representation of its values can be represented as a set.


#### Usage
```
import: !user/mymap.pt
to-unique: 
  { a = 1, 
    b = 2, 
    a = 3, 
    c = 4, 
    c = 5 }
  # resolves to {b = 2, a = 3, c = 5}
```

This opens the door to multiple ways of merging duplicate entries on map types. For example, we could
define a rule that accepts a rule and a map, then uses the rule to rewrite `[key, val1, val2]` into 
some new `val3`, then modifies the tree to merge the to the new value.

### An airbag with a gun
> *The airbag is here for your safety. The gun is here to shoot yourself in the foot.*

The replacement rules allow you to know that a given section of code is turing complete or not based on the rule
classification, and allows you to know the complexity of the automata to perform this operation. 

As a result, we can also know what different expressions will resolve to in evaluation, because we know it will terminate.
Knowing this during evaluation time provides many great guarantees in safety. 

However, evaluation is highly context-sensitive, as the same data can evaluate to different things
based on the replacement rules in scope. This may make code difficult to grok

```
import: !protag/scope.pt
in: print["Hello ", "World"] # resolves to ["printHello ", "printWorld"]
```

## Implications: a more useful abstract rewriting system and a new programming paradigm
- There are some higher-order logic systems that use rewrite systems to prove properties on higher-order logic.  
  Maude is a good example of this. 
- However, these systems more towards formal verification and analyzing other systems. 
- ProTag is just about replacing nodes of its own files in a deterministic manner that can be verfied
  that its own turing completeness is curtailed
- May be read as a properties file, or as a system that may rewrite itself with termination.  

## Practical Implications
- This is a really useful tool to define advanced properties files for your java program and know it isn't turing complete
- This is also very useful for playing around with data, functionality, and even some scripting without having to restart your java program
  - Use case example: sync a visualization to a piece of music. You may define sections, filters, and data to define how a visualization will occur through time. Updating individual nodes will restart the visualization and music to that particular time for rapid testing and modifications
- Running replacement rules on nodes may be parallelized and distributed, allowing for replacements on distributed unstructured data. For example, operations on Hadoop that we know will terminate. 
 
## Future improvements: JTag
Convert a JSON file to a valid subset of the ProTag abstract syntax tree, so JSON may be processed and self-generating in the same manner without turing completeness, and without using my bespoke DSL.

## Future improvements: Security
We'll probably want to have a security system in place so ProTag can only access designated Java files, 
or access no java files at all
  
