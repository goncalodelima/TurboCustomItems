allprojects {
    group = "pt.gongas"
    version = "1.0.3"
    description = "CustomItems plugin system"
    ext.set("id", "turbocustomitems")
    ext.set("website", "https://github.com/goncalodelima/TurboCustomItems")
    ext.set("author", "ReeachyZ_")
}

val platforms = setOf(
    projects.bukkit
).map { it.dependencyProject }

val specials = setOf(
    projects.shared,
).map { it.dependencyProject }