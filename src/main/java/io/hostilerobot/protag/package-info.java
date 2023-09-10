/**
 * <h1>ProTag base package</h1>
 * This package includes packages for denotational semantics (the structure and data of the file), as well
 * as operational semantics (the runtime for ProTag).
 *
 * Though ProTag involves both these static and runtime semantics, they may be separated, so that we can use the same runtime
 * with different source types (like replacing JSON for the denotational semantics yet still targeting the same runtime)
 *
 * <h2>Denotational Semantics</h2>
 *
 * ProTag takes the following pipeline to generate the runtime environment:
 * {@code Reader -> ProtagLexer -> ProtagParser -> AST -> initial runtime data}
 *
 * The runtime data can then be read, evaluated, and modified. Note that
 * we may use any point in this pipeline as a start position to access the protag runtime.
 * For example, the intellij plugin has a modified lexer and parser, but still outputs to the same AST.
 * In future work, JSON may be used as an entry point and will directly translate to the initial runtime data.
 *
 * <ul>
 * <li> {@link io.hostilerobot.protag.parser} Used to generate a ProTag AST
 * <li> {@link io.hostilerobot.protag.ast} Initial target syntax tree. Has additional verifications to
 *          ensure individual paths, regexes, etc are formatted correctly. Used to generate ProTag runtime data
 * <li> {@link io.hostilerobot.protag.data} Contains data used during runtime, this is also the target for the
 *          initial runtime data
 * </ul>
 *
 * <h2>Operational Semantics</h2>
 * todo
 */
package io.hostilerobot.protag;