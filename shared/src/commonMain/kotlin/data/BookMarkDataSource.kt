package data

import kotlinx.coroutines.flow.Flow
import ns.kk.newsdxio.BookMarkEntity


interface BookMarkDataSource {
    suspend fun getBookMarsArticleById(aid: String) : BookMarkEntity?
    fun getBookmarkAll() : List<BookMarkEntity>
    suspend fun deleteBookmarkById(aid : String)
    suspend fun insertBookmarkArticle(id: Long, aid : String)
}