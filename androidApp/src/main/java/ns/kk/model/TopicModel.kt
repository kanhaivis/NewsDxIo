package ns.kk.model

data class TopicModel(
    val `data`: ArrayList<Data>,
    val status: Boolean
)

data class Data(
    val topic: String,
    val topic_id: String
)