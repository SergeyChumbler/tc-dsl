package DslTestProj.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

object DslTestProj_GithubKasper : GitVcsRoot({
    name = "Github Kasper"
    url = "https://github.com/SergeyChumbler/kaspersky.git"
    branch = "master"
    authMethod = password {
        userName = "SergeyChumbler"
        password = "credentialsJSON:cc48eb6f-869c-4e23-bdcc-4affc24505ae"
    }
})
