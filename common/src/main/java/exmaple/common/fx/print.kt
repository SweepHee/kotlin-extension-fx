package exmaple.common.fx

fun print(vararg args: Any) = println(args.toList().joinToString(",", "[", "]"))
