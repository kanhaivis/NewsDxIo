package model.section

data class DATA(
    val ID: String,
    val PROP_ID: String,
    val SECTION_NAME: String,
    val SECTION_TYPE: String,
    val SHOW_IN_HAMBURGER: String,
    val SHOW_IN_TOP_MENU: String,
    val SUBSECTIONS: List<Any>,
    val WIDGET: WIDGET
)