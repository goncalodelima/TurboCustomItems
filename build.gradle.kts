allprojects {
    group = "pt.gongas"
    version = "1.0.0"
    description = "CustomItems plugin system"
    ext.set("id", "turbocustomitems")
    ext.set("website", "https://github.com/goncalodelima")
    ext.set("author", "ReeachyZ_")
}

val platforms = setOf(
    projects.bukkit,
    projects.bukkit.v18R3
).map { it.dependencyProject }

val specials = setOf(
    projects.shared,
).map { it.dependencyProject }