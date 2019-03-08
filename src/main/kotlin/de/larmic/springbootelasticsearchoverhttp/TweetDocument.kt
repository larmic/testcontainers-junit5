package de.larmic.springbootelasticsearchoverhttp

class TweetDocument(val message: String) {
    companion object {
        const val documentIndex = "twitter"
        const val documentType = "tweet"
    }
}