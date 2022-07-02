package ns.kk.newsdxio

expect class SPref

expect fun SPref.getOnBoard(key: String) : Int
expect fun SPref.setOnBoard(key: String, value: Int)

expect fun SPref.getNightMode(key: String) : Int
expect fun SPref.setNightMode(key: String, value: Int)

expect fun SPref.getToken(key: String) : String?
expect fun SPref.setToken(key: String, value: String)