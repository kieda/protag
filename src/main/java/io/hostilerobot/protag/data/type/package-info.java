/**
 * <h2>Types available to ProTag during evaluation</h2>
 *
 * <h3>This can be one of the following:</h3>
 * <ul>
 *    <li> {@link io.hostilerobot.protag.data.type.ProtagPluginType}
 *      - a plugin definition
 *    <li> {@link io.hostilerobot.protag.data.type.ProtagEnvironmentType}
 *      - an environment definition
 *    <li> {@link io.hostilerobot.protag.data.type.ProtagNodeType}
 *      - a protag node
 *    <li> {@link io.hostilerobot.protag.data.type.JavaValueType}
 *      - a java value
 *    <li> {@link io.hostilerobot.protag.data.type.NoneType}
 *      - {@code void}/{@code null} type - used in place of {@code null} when returned from java
 * </ul>
 */
package io.hostilerobot.protag.data.type;