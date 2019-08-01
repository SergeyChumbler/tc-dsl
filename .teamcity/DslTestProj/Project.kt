package DslTestProj

import DslTestProj.buildTypes.*
import DslTestProj.vcsRoots.*
import DslTestProj.vcsRoots.DslTestProj_DslSettingsRoot
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2018_1.projectFeatures.versionedSettings

object Project : Project({
    id("DslTestProj")
    parentId("_Root")
    name = "DslTestProj"

    vcsRoot(DslTestProj_DslSettingsRoot)
    vcsRoot(DslTestProj_GithubKasper)

    buildType(DslTestProj_Build)

    features {
        versionedSettings {
            id = "PROJECT_EXT_4"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.USE_CURRENT_SETTINGS
            rootExtId = "${DslTestProj_DslSettingsRoot.id}"
            showChanges = false
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
