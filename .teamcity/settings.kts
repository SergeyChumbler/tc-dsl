import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.1"

project {

    vcsRoot(GithubKasper)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(GithubKasper)
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

object GithubKasper : GitVcsRoot({
    name = "Github Kasper"
    url = "https://github.com/SergeyChumbler/kaspersky.git"
    branch = "master"
    authMethod = password {
        userName = "SergeyChumbler"
        password = "credentialsJSON:cc48eb6f-869c-4e23-bdcc-4affc24505ae"
    }
})
