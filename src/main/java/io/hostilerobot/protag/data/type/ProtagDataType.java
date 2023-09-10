package io.hostilerobot.protag.data.type;

/**
 * represents the type of data that can be used by protag during evaluation
 *
 * This can be one of the following:
 *    ProtagPluginType      - a plugin definition
 *    ProtagEnvironmentType - an environment definition
 *    ProtagNodeType        - a protag node
 *    JavaValueType         - a java value
 *    None                  - void/null type - used in place of null when returned from java
 */
public sealed interface ProtagDataType permits
    ProtagNodeType,
    ProtagPluginType,
    ProtagEnvironmentType,
    JavaValueType,
    NoneType
    {}
