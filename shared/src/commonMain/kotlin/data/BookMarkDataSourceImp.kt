package data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ns.kk.newsdxio.BookMarkEntity
import ns.kk.newsdxio.MyAppDb

class BookMarkDataSourceImp(db : MyAppDb) : BookMarkDataSource {
    private val queries = db.myAppDbQueries

    override suspend fun getBookMarsArticleById(aid: String): BookMarkEntity? {
        return withContext(Dispatchers.Default) {
            queries.getBookmarkArticleById(aid).executeAsOneOrNull()
        }
    }

    override fun getBookmarkAll(): List<BookMarkEntity> {
        return queries.getAllBookmarArticle().executeAsList()
    }

    override suspend fun deleteBookmarkById(aid: String) {
        withContext(Dispatchers.Default) {
            queries.deleteBookmarkArticle(aid)
        }
    }

    override suspend fun insertBookmarkArticle(id: Long, aid: String) {
       withContext(Dispatchers.Default){
           queries.insertBookmarkArticle(id,aid)
       }
    }
}