package io.hostilerobot.protag.ast;

/**
 * represents a string-based node.
 * String based nodes may be used as raw values, or used for path resolution.
 *
 * TODO: add path resolution methods. Basically want to have
 *      (Context, existingPath, CStringNode) -> newPath
 */
public sealed interface CStringNode extends ProtagNode permits
        IProtagRegex, LProtagLiteral, // regex, literal - common among all paths
        LFileName,                    // file paths
        LJavaBody, LJavaName,         // java paths
        LProtagBody, LProtagName {    // protag paths

        /**
         * @return the raw value
         */
        public String getRawValue();
}
