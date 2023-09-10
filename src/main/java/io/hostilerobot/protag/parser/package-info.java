/**
 * Contains classes for Lexing and Parsing the ProTag language.
 *
 * <h2>Lexing</h2>
 * The {@link io.hostilerobot.protag.parser.ProtagLexer ProtagLexer} is a
 * {@link io.hostilerobot.protag.parser.TokenSource TokenSource} which produces a
 * {@link io.hostilerobot.protag.parser.Token} sequence.
 *
 * The tokens produced have several different types:
 * <ul>
 *     <li> {@link io.hostilerobot.protag.parser.ProtagStringToken}
 *          represents a token that represents a string or identifier that may have different values
 *     <li> {@link io.hostilerobot.protag.parser.ProtagNaturalToken}
 *          represents a token that has a natural value (e.g. some integer >= 0)
 *     <li> {@link io.hostilerobot.protag.parser.ProtagSpecialToken}
 *          represents a token that as a pre-defined value, e.g. operators
 *          <ul>
*           <li> <i>See: {@link io.hostilerobot.protag.parser.ProtagTokenType}</i> for an enum defining the types of special tokens and their definitions
 *          </ul>
 *     <li> {@link io.hostilerobot.protag.parser.StartToken}
 *          the start token
 *     <li> {@link io.hostilerobot.protag.parser.EOFToken}
 *          the EOF token
 * </ul>
 * <h3>Metadata</h3>
 * <p> Metadata, like the location and range, are stored with the tokens. </p>
 * <p>Todo - this will be stored in the AST as well to enable better tracing </p>
 * <p><i>See: {@link io.hostilerobot.protag.parser.Location}</i> line/column location in file</p>
 * <p><i>See: {@link io.hostilerobot.protag.parser.Range}</i> range for a token, consists of a start and stop location</p>
 *
 * <h3>Generation</h3>
 * Lexing is produced via <a href="https://www.jflex.de/">JFlex</a>, file for generating the lexer is {@code protag.flex}
 * in the {@code jflex} folder. This is generated in maven via the {@code jflex:generate} target.
 *
 * <h2>Parsing</h2>
 * <p>
*      Parsers inheirit from the {@link io.hostilerobot.protag.parser.Parser} class, which allows us to have multiple
*      implementations as sources to define the same AST.
 * </p>
 * <p>
*      The default implementation {@link io.hostilerobot.protag.parser.ProtagParser} accepts a {@link io.hostilerobot.protag.parser.ProtagLexer}
 * </p>
 * <p>
 *     The parser produces an AST as defined in the {@link io.hostilerobot.protag.ast} package.
 * </p>
 * <p>
 *     See this package for more information on the AST.
 * </p>
 * <p>
 *     A few classes are useful middle-steps while generating the AST. These are prefixed with {@code X}, as in
 *     {@link io.hostilerobot.protag.parser.XDecimal} and {@link io.hostilerobot.protag.parser.XInteger}.
 * </p>
 * <h3>Generation</h3>
 * Parsing is produced via <a href="https://javacc.github.io/javacc/">JavaCC</a>, file for generating
 * the parser is {@code protag.jj} in the {@code javacc} folder. This is generated in maven via the {@code javacc:javacc} target.
 *
 * @see io.hostilerobot.protag.parser.Parser
 * @see io.hostilerobot.protag.parser.ProtagParser
 * @see io.hostilerobot.protag.parser.ProtagLexer
 */
package io.hostilerobot.protag.parser;