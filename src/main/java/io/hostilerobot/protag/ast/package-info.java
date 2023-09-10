/**
 * <h1>AST</h1>
 * <p>
 *     package for providing general structures to our AST - e.g. for base nodes, list-like traversable nodes,
 *     pair-based nodes, number-based nodes, etc.
 * </p>
 * <p>
 *     All classes in the AST implement {@link io.hostilerobot.protag.ast.CProtagNode}
 * </p>
 * <br>
 * <p>
 *     Generated via {@link io.hostilerobot.protag.parser.ProtagParser ProtagParser}
 * </p>
 * <h2>Prefixes:</h2>
 * <ul>
 *     <li> {@code "L"} - leaf nodes, all are implementations
 *     <li> {@code "I"} - node interfaces (one level above node implementation)
 *     <li> {@code "C"} - common interfaces. Used to specify node classification/data. Top down
 *     <li> {@code "T"} - tags. Used to specify a node is of a particular type. Bottom up
 *     <li> {@code "E"} - enum types.
 *     <li> {@code "Abstract"} - abstract classes to define common data
 *     <ul>
 *         <li> {@link io.hostilerobot.protag.ast.AbstractProtagNode} - common abstract node in the AST
 *         <li> {@link io.hostilerobot.protag.ast.AbstractListLikeNode} - common abstract node in the AST to define list-like data
 *     </ul>
 * </ul>
 * <h2>Suffixes:</h2>
 * In the {@link io.hostilerobot.protag.ast.impl impl} package, the {@code Impl} suffix are implementations for node interfaces
 * (things that begin with the {@code "I"} prefix.
 */

package io.hostilerobot.protag.ast;