package io.hostilerobot.protag.ast;

/**
 * package for providing general structures to our AST - e.g. for base nodes, list-like traversable nodes,
 * pair-based nodes, number-based nodes, etc.
 *
 * Enum types are prefixed with "T" for type, leaf nodes are prefixed with "L", node implementations are
 * prefixed with "N", and interfaces for nodes are prefixed with "I".
 *
 * Structural interfaces
 *
 * Prefixes:
 *      "ProtagNode" - all nodes extend from here
 *      "E" enum types
 *      "L" leaf nodes
 *      "I" node interfaces (one level above node implementation)
 *      "T" tag - used to specify a node is of a particular type - bottom up
 *      "C" common - used to specify node classification/data - top down
 */

