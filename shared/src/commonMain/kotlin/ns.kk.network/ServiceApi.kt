package ns.kk.network

import ConstantItem.URL_ARTICLE_BY_ID
import ConstantItem.URL_ARTICLE_DETAILS
import ConstantItem.URL_HOME
import ConstantItem.URL_ONBOARDING
import ConstantItem.URL_PROFILE
import ConstantItem.URL_SECTION
import ConstantItem.URL_SECTION_ARTICLE
import ConstantItem.propertyKeyToken
import ConstantItem.user_token
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.article.ArticleByIdResponse
import model.article.SectinArticleResponse
import model.articledetails.ArticleDeatilsResponse
import model.home.ARTICLES
import model.home.HomeModles
import model.home.HomeResponse
import model.onboarding.OnBoardingResponse
import model.profile.UserProfileResponse
import model.section.SectionResponse


class ServiceApi {


    val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getOnBoarding(): OnBoardingResponse {
        val onBoardingResult : OnBoardingResponse
        coroutineScope {
            onBoardingResult = client.post(URL_ONBOARDING) {
                headers {
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()
        }
        return onBoardingResult
    }

    suspend fun getSection(): SectionResponse {
        val onBoardingResult : SectionResponse
        coroutineScope {
            val client = HttpClient() {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
            onBoardingResult = client.post(URL_SECTION) {
                headers {
//                    append("Accept", "application/json;charset=utf-8")
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()
        }
        return onBoardingResult
    }

    suspend fun getHome(): MutableList<HomeModles> {
        val homeList : MutableList<HomeModles> = mutableListOf()
        val onHomeResult : HomeResponse
        coroutineScope {
            val client = HttpClient() {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }
            onHomeResult = client.post(URL_HOME) {
                headers {
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()


            val banner = onHomeResult.DATA.BANNER
            val homeModles  = banner?.let { HomeModles("BANNER","", ARTICLES(), it) }
            if (homeModles != null) {
                homeList.add(homeModles)
            }

            var count = 0
            val articles = onHomeResult.DATA.ARTICLES
            articles?.forEachIndexed{ index, items ->

                val articleData = articles?.let { HomeModles("ARTICLE", "" ,articles[index], listOf<ARTICLES>()) }
                if (articleData != null) {
                    if (index != 0) {
                        if(index % 4 == 0) {
                            val widget = onHomeResult.DATA.WIDGETS
                            val widgets = widget?.let { HomeModles("WIDGET",widget[count].TYPE,  ARTICLES() , widget[count].ARTICLES) }
                            val ad =   HomeModles("ADS","",  ARTICLES() , listOf())
                            if (widgets != null) {
                                homeList.add(widgets)
                                homeList.add(ad)
                            }
                            count++
                        }
                    }
                    homeList.add(articleData)
                }
            }
        }


        return homeList
    }

    suspend fun getSectionArticle(setcinId : String): SectinArticleResponse {
        val onBoardingResult : SectinArticleResponse
        coroutineScope {
            onBoardingResult = client.get(URL_SECTION_ARTICLE+"/?sectionId=$setcinId") {
                headers {
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()
        }
        return onBoardingResult
    }

    suspend fun getArticleDetails(articleId : String): ArticleDeatilsResponse {
        val onArticleDetailResult : ArticleDeatilsResponse
        coroutineScope {
            onArticleDetailResult = client.get(URL_ARTICLE_DETAILS+"/?articleId=$articleId") {
                headers {
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()
        }
        return onArticleDetailResult
    }

    suspend fun getArticleById(articleId : String): ArrayList<ARTICLES> {
        val onArticleListResult : ArticleByIdResponse
        coroutineScope {
            onArticleListResult = client.get(URL_ARTICLE_BY_ID+"/?articleId=$articleId") {
                headers {
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()
        }
        return onArticleListResult.DATA.ARTICLES
    }


    @OptIn(InternalAPI::class)
    suspend fun getSectionArticleTest(sectionId: String, pageNumber: String): String {
        val onSectionartilceResult : String

        var requestBody = "{\n" +
                "    \"sectionId\": $sectionId,\n" +
                "    \"pageNumber\" :\"$pageNumber\"\n" +
                "}"

        var requestBody1 = "{" +
                "    \"sectionId\": $sectionId,\n" +
                "    \"pageNumber\" :\"$pageNumber\"" +
                "}"


        var res = Json.encodeToString(requestBody)

        coroutineScope {
            val client = HttpClient() {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }

                install(Logging){
                    logger = Logger.DEFAULT
                    level = LogLevel.HEADERS
                }
                install(DefaultRequest) {
                    headers.append("Accept","application/json;q=0.9")
                    headers.append("Authorization",propertyKeyToken)
                }

            }

            onSectionartilceResult = client.post {
                url(URL_SECTION_ARTICLE)
                parameter("sectionId", "4")
                header("Authorization", propertyKeyToken)
//                body = requestBody
//                contentType(ContentType.Application.Json)
//                setBody(requestBody1)
                /*headers {
//                    setBody(rrr)
                    append("Content-Type", "application/json")
//                    contentType(ContentType.Application.Json)
                    append(HttpHeaders.Authorization, propertyKeyToken)
                    *//*append("Content-Type", "application/json")
                    append("Accept", "application/json;charset=utf-8")
                    append(HttpHeaders.Authorization, propertyKeyToken)*//*
                }*/

            }.bodyAsText()
        }
        return onSectionartilceResult
    }

    suspend fun getUserProfile(): UserProfileResponse {
        val onHomeResult : UserProfileResponse
        coroutineScope {
            onHomeResult = client.post(URL_PROFILE) {
                headers {
                    append(HttpHeaders.Authorization, user_token)
                }
            }.body()
        }
        return onHomeResult
    }

    @OptIn(InternalAPI::class)
    suspend fun getBookMarkArticleList(articleid_list: String): String {
        val onSectionartilceResult : String

        val request =BookMark("546")

        var requestBody = "{\n" +
                "    \"articleId\" :\"$articleid_list\"\n" +
                "}"



        coroutineScope {
            onSectionartilceResult = client.post(URL_ARTICLE_BY_ID) {

                headers {
                    setBody(requestBody)
                    contentType(ContentType.Application.Json)
                    append(HttpHeaders.Authorization, propertyKeyToken)
                }
            }.body()
        }
        return onSectionartilceResult
    }







    @OptIn(InternalAPI::class)
    suspend fun getSectionArticleeeee(sectionId: String?, pageNumber: String?): String {

       var requestBody = "{\n" +
                "    \"sectionId\": $sectionId,\n" +
                "    \"pageNumber\" :\"$pageNumber\"\n" +
                "}"

       /* val response = client.post(URL_SECTION_ARTICLE) {
//            contentType(ContentType.Application.Json)
//            setBody(SectionData("4","1"))
//            body = requestBody
            body = {"sectionId": "4" }
            headers {
            contentType(ContentType.Application.Json)
                append(HttpHeaders.Authorization, propertyKeyToken)
            }
        }*/

        val response = client.post {
            url { URL_SECTION_ARTICLE }
            body = {"sectionId:$sectionId"}
            headers {
                append("Content-Type", "application/json")
//                    contentType(ContentType.Application.Json)
                append(HttpHeaders.Authorization, propertyKeyToken)
            }
        }
        return response.bodyAsText()
    }


    @OptIn(InternalAPI::class)
    suspend fun getArticlePage1() : SectionResponse {


        val requestBody ="{\n" +
                "    \"section_id\": false,\n" +
                "    \"article_id\": false,\n" +
                "}"
        val articleModels : SectionResponse
        coroutineScope {
            val client = HttpClient() {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
            articleModels = client.post{
                url(URL_SECTION)
//                body = requestBody
                header(HttpHeaders.Authorization, propertyKeyToken)
            }.body()
            println("$articleModels")
        }
        return articleModels
    }

    suspend fun getSectioKanhai(): SectionResponse {
        val response : SectionResponse = client.post {
            url(URL_SECTION)
            header(HttpHeaders.Authorization, propertyKeyToken)
            /*headers {
//                append(HttpHeaders.Authorization, propertyKeyToken)
                append("Token", propertyKeyToken)
            }*/
        }.body()

        return response
    }






}


@Serializable
data class BookMark(var sectionId: String)


@Serializable
data class RequestModel(
    val sectionId: String,
    val pageNumber: String
)
