package DslTestProj.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object DslTestProj_Build : BuildType({
    name = "Build"

    vcs {
        root(DslTestProj.vcsRoots.DslTestProj_GithubKasper)
    }

    steps {
        dotnetRestore {
            name = "dotnet restore"
            projects = "Kaspersky.sln"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        script {
            name = "Show current dir"
            scriptContent = "ls -a"
        }
        dotnetBuild {
            name = "dotnet Build"
            projects = "./Kaspersky.Web/Kaspersky.Web.csproj"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        script {
            name = "Command line"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            scriptContent = "echo BUILD STEP 4"
        }
    }

    triggers {
        vcs {
        }
    }
})
