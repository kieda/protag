/**
 * package base which contains runtime data
 *
 * sub-packages:
 * <ul>
 *  <li> {@link io.hostilerobot.protag.data.node node} package:
 *      <p>
 *          Represents tree-like node structures used in ProTag.
 *          This tree's initial value is generated from the AST,
 *          but can be subsequently modified during runtime.
 *      </p>
 *      <p>
 *          As a result, the protag nodes have fewer restrictions to
 *          permit more expressive replacements
 *      </p>
 *  <li> {@link io.hostilerobot.protag.data.plugin plugin} package:
 *      <p>
 *          Represents plugins and environments that may be applied
 *          to runtime data.
 *      </p>
 *      <p>
 *          A plugin represents a set of environments that we can apply in scope.
 *          An environment represents a replacement rule we can use to match and apply in the scope.
 *      </p>
 *      <p>
 *          We may apply multiple environments and multiple plugins simultaneously.
 *      </p>
*   <li> {@link io.hostilerobot.protag.data.java java} package:
 *      <p>
 *          Used to interface to and from ProTag scripts and Java. Used to bring java objects
 *          into the protag runtime (by reference or by replacements), and can be used to make calls in
 *          java (if permitted)
 *      </p>
 *      <p>
 *          Thing to think about - Security. How could we prevent protag from referencing java that is out of scope
 *          of what we intended?
 *      </p>
 *  <li> {@link io.hostilerobot.protag.data.type type} package:
 *      <p>
 *          Used to describe the basic data types used in ProTag.
 *      </p>
 * </ul>
 *
 * <pre>
 * Runtime types:
 *
 * Plugin -- plugin definition
 * | Environment -- environment definition
 * | Node -- node in the tree
 * | Value -- java value
 * | None -- void type
 *
 * Node :=
 *  | List
 *  | Map
 *  | Pair
 *  | TransitionL
 *  | TransitionR
 *  | FilePath
 *  | JavaPath
 *  | ProtagPath
 *  -- Should just be a Path node. Should have a "source descriptor" to describe where the data is coming from
 *     and a payload "path"
 *  | Properties
 * Leaf:
 *  | Ident (file, java, protag)
 *  | Integer
 *  | Literal
 *  | Quotient
 *  | Real
 *  | StringRegex
 *  | NumberRegex
 *  
 * </pre>
 */
package io.hostilerobot.protag.data;
