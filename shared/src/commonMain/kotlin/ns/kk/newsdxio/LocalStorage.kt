package ns.kk.newsdxio

class LocalStorage(val sPref: SPref) {

    fun getOnbord(key: String) : Int{
        return sPref.getOnBoard(key)
    }

    fun putOnbord(key: String, value: Int) {
        sPref.setOnBoard(key,value)
    }

    fun getNightMode(key: String) : Int{
        return sPref.getOnBoard(key)
    }

    fun putNightMode(key: String, value: Int) {
        sPref.setOnBoard(key,value)
    }
}