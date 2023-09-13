## ImportPlugin

This is where all the sauce happens, so it's a good idea to flesh it out completely.

`ImportPlugin = {Scope Resolution, Import Items}`

Basically, when we're in an import block, we also need to be able to keep track of basic scopes

This allows us to both traverse a path to find out what items are available, but also to perform resolution
to generate multiple items. 

e.g. `(@com.user.MyPlugin).[environment1, environment2]`

will resolve to `{(@com.user.MyPlugin).environment1 = <env1>, `

Rules for scoping:
1.  all top level identifiers in scope will not be replaced by another value.
    This means that bound names will not be processed
    ```
    import: !protag/vars.pt
    x = 1
    (import: 
        @com.user.x     # x is not replaced, equivalent to "com"."user"."x"
        @com.user.(x)   # x is replaced as it's now part of a segment
        @com.user.[x]   # x is replaced, as it's part of a segment
        @com.user."z"x  # x is not replaced
        !com/user/x     # x is not replaced
        !x/com/user     # same as above.
        !x              # same as above.
        my.val.x        # x is not replaced
        x.val           # DEVIATION: x is replaced when it's the first value in protag path
        x"asdf"         # DEVIATION 2: x is NOT replaced.
        print"asdf"     # would we replace print with an environment for print? probably not. We'd just match segment(print, "")
        print"asdf".value # how would this factor in? (print"asdf").value or print("asdf".value). Probably the latter.
    )
    ```
