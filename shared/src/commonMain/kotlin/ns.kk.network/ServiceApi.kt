package ns.kk.network

import ConstantItem.URL_HOME
import ConstantItem.URL_ONBOARDING
import ConstantItem.URL_SECTION
import ConstantItem.URL_SECTION_ARTICLE
import ConstantItem.propertyKeyToken
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ServiceApi {

    private val client = HttpClient()
    suspend fun getOnBoarding(): String {
        val response = client.get(URL_ONBOARDING) {
            headers {
//                append(HttpHeaders.Accept, "text/html")
                append(HttpHeaders.Authorization, propertyKeyToken)
//                append(HttpHeaders.UserAgent, "ktor client")
            }
        }
        return response.bodyAsText()
    }

    suspend fun getSection(): String {
        val response = client.get(URL_SECTION) {
            headers {
//                append(HttpHeaders.Accept, "text/html")
                append(HttpHeaders.Authorization, propertyKeyToken)
//                append(HttpHeaders.UserAgent, "ktor client")
            }
        }
        return response.bodyAsText()
    }

    suspend fun getHome(): String {
        val response = client.get(URL_HOME) {
            headers {
                append(HttpHeaders.Authorization, propertyKeyToken)
            }
        }
        return response.bodyAsText()
    }

    suspend fun getSectionArticle(): String {

        val response = client.post(URL_SECTION_ARTICLE) {
//            contentType(ContentType.Application.Json)
//            setBody(SectionData("4","1"))
            headers {
                append(HttpHeaders.Authorization, propertyKeyToken)
            }
        }
        return response.bodyAsText()
    }
}

data class SectionData(
    var sectionId: String,
    var pageNumber : String
    )

