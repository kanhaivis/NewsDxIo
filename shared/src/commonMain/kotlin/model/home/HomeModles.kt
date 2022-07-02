package model.home

import model.home.ARTICLES

data class HomeModles(val type: String, val widget_type: String, val articles: ARTICLES, val articleslist: List<ARTICLES>)
