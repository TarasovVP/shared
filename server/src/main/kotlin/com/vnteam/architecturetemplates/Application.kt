package com.vnteam.architecturetemplates

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = 8081, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        anyHost()

        allowCredentials = true
        allowNonSimpleContentTypes = true
    }
    routing {
        get("/repos/octocat/Spoon-Knife/forks") {
            call.respondText(forksJson(), ContentType.Application.Json, HttpStatusCode.OK)
        }
    }
}

fun forksJson() = "[\n" +
        "{\n" +
        "\"id\":800991676,\n" +
        "\"node_id\":\"R_kgDOL74pvA\",\n" +
        "\"name\":\"Fork from server\",\n" +
        "\"full_name\":\"21Vijeth/Fork from server\",\n" +
        "\"private\":false,\n" +
        "\"owner\":{},\n" +
        "\"html_url\":\"https://github.com/21Vijeth/Spoon-Knife\",\n" +
        "\"description\":\"This repo is for demonstration purposes only.\",\n" +
        "\"fork\":true,\n" +
        "\"url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife\",\n" +
        "\"forks_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/forks\",\n" +
        "\"keys_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/keys{/key_id}\",\n" +
        "\"collaborators_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/collaborators{/collaborator}\",\n" +
        "\"teams_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/teams\",\n" +
        "\"hooks_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/hooks\",\n" +
        "\"issue_events_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/issues/events{/number}\",\n" +
        "\"events_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/events\",\n" +
        "\"assignees_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/assignees{/user}\",\n" +
        "\"branches_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/branches{/branch}\",\n" +
        "\"tags_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/tags\",\n" +
        "\"blobs_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/git/blobs{/sha}\",\n" +
        "\"git_tags_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/git/tags{/sha}\",\n" +
        "\"git_refs_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/git/refs{/sha}\",\n" +
        "\"trees_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/git/trees{/sha}\",\n" +
        "\"statuses_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/statuses/{sha}\",\n" +
        "\"languages_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/languages\",\n" +
        "\"stargazers_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/stargazers\",\n" +
        "\"contributors_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/contributors\",\n" +
        "\"subscribers_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/subscribers\",\n" +
        "\"subscription_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/subscription\",\n" +
        "\"commits_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/commits{/sha}\",\n" +
        "\"git_commits_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/git/commits{/sha}\",\n" +
        "\"comments_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/comments{/number}\",\n" +
        "\"issue_comment_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/issues/comments{/number}\",\n" +
        "\"contents_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/contents/{+path}\",\n" +
        "\"compare_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/compare/{base}...{head}\",\n" +
        "\"merges_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/merges\",\n" +
        "\"archive_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/{archive_format}{/ref}\",\n" +
        "\"downloads_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/downloads\",\n" +
        "\"issues_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/issues{/number}\",\n" +
        "\"pulls_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/pulls{/number}\",\n" +
        "\"milestones_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/milestones{/number}\",\n" +
        "\"notifications_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/notifications{?since,all,participating}\",\n" +
        "\"labels_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/labels{/name}\",\n" +
        "\"releases_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/releases{/id}\",\n" +
        "\"deployments_url\":\"https://api.github.com/repos/21Vijeth/Spoon-Knife/deployments\",\n" +
        "\"created_at\":\"2024-05-15T11:47:03Z\",\n" +
        "\"updated_at\":\"2024-05-15T11:47:03Z\",\n" +
        "\"pushed_at\":\"2024-05-15T09:58:19Z\",\n" +
        "\"git_url\":\"git://github.com/21Vijeth/Spoon-Knife.git\",\n" +
        "\"ssh_url\":\"git@github.com:21Vijeth/Spoon-Knife.git\",\n" +
        "\"clone_url\":\"https://github.com/21Vijeth/Spoon-Knife.git\",\n" +
        "\"svn_url\":\"https://github.com/21Vijeth/Spoon-Knife\",\n" +
        "\"homepage\":\"\",\n" +
        "\"size\":2,\n" +
        "\"stargazers_count\":0,\n" +
        "\"watchers_count\":0,\n" +
        "\"language\":null,\n" +
        "\"has_issues\":false,\n" +
        "\"has_projects\":true,\n" +
        "\"has_downloads\":true,\n" +
        "\"has_wiki\":true,\n" +
        "\"has_pages\":false,\n" +
        "\"has_discussions\":false,\n" +
        "\"forks_count\":0,\n" +
        "\"mirror_url\":null,\n" +
        "\"archived\":false,\n" +
        "\"disabled\":false,\n" +
        "\"open_issues_count\":0,\n" +
        "\"license\":null,\n" +
        "\"allow_forking\":true,\n" +
        "\"is_template\":false,\n" +
        "\"web_commit_signoff_required\":false,\n" +
        "\"topics\":[\n" +
        "],\n" +
        "\"visibility\":\"public\",\n" +
        "\"forks\":0,\n" +
        "\"open_issues\":0,\n" +
        "\"watchers\":0,\n" +
        "\"default_branch\":\"main\"\n" +
        "},\n" +
        "{\n" +
        "\"id\":800943769,\n" +
        "\"node_id\":\"R_kgDOL71umQ\",\n" +
        "\"name\":\"Spoon-Knife2\",\n" +
        "\"full_name\":\"MT-Alex/Spoon-Knife2\",\n" +
        "\"private\":false,\n" +
        "\"owner\":{\n" +
        "\"login\":\"MT-Alex\",\n" +
        "\"id\":124154025,\n" +
        "\"node_id\":\"U_kgDOB2ZwqQ\",\n" +
        "\"avatar_url\":\"https://avatars.githubusercontent.com/u/124154025?v=4\",\n" +
        "\"gravatar_id\":\"\",\n" +
        "\"url\":\"https://api.github.com/users/MT-Alex\",\n" +
        "\"html_url\":\"https://github.com/MT-Alex\",\n" +
        "\"followers_url\":\"https://api.github.com/users/MT-Alex/followers\",\n" +
        "\"following_url\":\"https://api.github.com/users/MT-Alex/following{/other_user}\",\n" +
        "\"gists_url\":\"https://api.github.com/users/MT-Alex/gists{/gist_id}\",\n" +
        "\"starred_url\":\"https://api.github.com/users/MT-Alex/starred{/owner}{/repo}\",\n" +
        "\"subscriptions_url\":\"https://api.github.com/users/MT-Alex/subscriptions\",\n" +
        "\"organizations_url\":\"https://api.github.com/users/MT-Alex/orgs\",\n" +
        "\"repos_url\":\"https://api.github.com/users/MT-Alex/repos\",\n" +
        "\"events_url\":\"https://api.github.com/users/MT-Alex/events{/privacy}\",\n" +
        "\"received_events_url\":\"https://api.github.com/users/MT-Alex/received_events\",\n" +
        "\"type\":\"User\",\n" +
        "\"site_admin\":false\n" +
        "},\n" +
        "\"html_url\":\"https://github.com/MT-Alex/Spoon-Knife\",\n" +
        "\"description\":\"This repo is for demonstration purposes only.\",\n" +
        "\"fork\":true,\n" +
        "\"url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife\",\n" +
        "\"forks_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/forks\",\n" +
        "\"keys_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/keys{/key_id}\",\n" +
        "\"collaborators_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/collaborators{/collaborator}\",\n" +
        "\"teams_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/teams\",\n" +
        "\"hooks_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/hooks\",\n" +
        "\"issue_events_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/issues/events{/number}\",\n" +
        "\"events_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/events\",\n" +
        "\"assignees_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/assignees{/user}\",\n" +
        "\"branches_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/branches{/branch}\",\n" +
        "\"tags_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/tags\",\n" +
        "\"blobs_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/git/blobs{/sha}\",\n" +
        "\"git_tags_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/git/tags{/sha}\",\n" +
        "\"git_refs_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/git/refs{/sha}\",\n" +
        "\"trees_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/git/trees{/sha}\",\n" +
        "\"statuses_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/statuses/{sha}\",\n" +
        "\"languages_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/languages\",\n" +
        "\"stargazers_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/stargazers\",\n" +
        "\"contributors_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/contributors\",\n" +
        "\"subscribers_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/subscribers\",\n" +
        "\"subscription_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/subscription\",\n" +
        "\"commits_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/commits{/sha}\",\n" +
        "\"git_commits_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/git/commits{/sha}\",\n" +
        "\"comments_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/comments{/number}\",\n" +
        "\"issue_comment_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/issues/comments{/number}\",\n" +
        "\"contents_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/contents/{+path}\",\n" +
        "\"compare_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/compare/{base}...{head}\",\n" +
        "\"merges_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/merges\",\n" +
        "\"archive_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/{archive_format}{/ref}\",\n" +
        "\"downloads_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/downloads\",\n" +
        "\"issues_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/issues{/number}\",\n" +
        "\"pulls_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/pulls{/number}\",\n" +
        "\"milestones_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/milestones{/number}\",\n" +
        "\"notifications_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/notifications{?since,all,participating}\",\n" +
        "\"labels_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/labels{/name}\",\n" +
        "\"releases_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/releases{/id}\",\n" +
        "\"deployments_url\":\"https://api.github.com/repos/MT-Alex/Spoon-Knife/deployments\",\n" +
        "\"created_at\":\"2024-05-15T09:49:11Z\",\n" +
        "\"updated_at\":\"2024-05-15T09:49:11Z\",\n" +
        "\"pushed_at\":\"2024-05-15T09:43:48Z\",\n" +
        "\"git_url\":\"git://github.com/MT-Alex/Spoon-Knife.git\",\n" +
        "\"ssh_url\":\"git@github.com:MT-Alex/Spoon-Knife.git\",\n" +
        "\"clone_url\":\"https://github.com/MT-Alex/Spoon-Knife.git\",\n" +
        "\"svn_url\":\"https://github.com/MT-Alex/Spoon-Knife\",\n" +
        "\"homepage\":\"\",\n" +
        "\"size\":2,\n" +
        "\"stargazers_count\":0,\n" +
        "\"watchers_count\":0,\n" +
        "\"language\":null,\n" +
        "\"has_issues\":false,\n" +
        "\"has_projects\":true,\n" +
        "\"has_downloads\":true,\n" +
        "\"has_wiki\":true,\n" +
        "\"has_pages\":false,\n" +
        "\"has_discussions\":false,\n" +
        "\"forks_count\":0,\n" +
        "\"mirror_url\":null,\n" +
        "\"archived\":false,\n" +
        "\"disabled\":false,\n" +
        "\"open_issues_count\":0,\n" +
        "\"license\":null,\n" +
        "\"allow_forking\":true,\n" +
        "\"is_template\":false,\n" +
        "\"web_commit_signoff_required\":false,\n" +
        "\"topics\":[\n" +
        "],\n" +
        "\"visibility\":\"public\",\n" +
        "\"forks\":0,\n" +
        "\"open_issues\":0,\n" +
        "\"watchers\":0,\n" +
        "\"default_branch\":\"main\"\n" +
        "}\n" +
        "]"