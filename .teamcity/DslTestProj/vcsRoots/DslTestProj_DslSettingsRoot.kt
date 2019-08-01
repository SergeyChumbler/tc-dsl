package DslTestProj.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object DslTestProj_DslSettingsRoot : GitVcsRoot({
    uuid = "8b348556-0fd6-464f-a85c-8f40cf05a360"
    name = "DSL Settings Root"
    url = "https://github.com/SergeyChumbler/tc-dsl"
    branch = "master"
    authMethod = password {
        userName = "SergeyChumbler"
        password = "credentialsJSON:cc48eb6f-869c-4e23-bdcc-4affc24505ae"
    }
})
