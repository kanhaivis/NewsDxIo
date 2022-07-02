package ns.kk.utils

import ns.kk.utils.ConstantItem.BASE_IMAGE_URL
import ns.kk.utils.ConstantItem.IMAGE_BIG_THUMB
import ns.kk.utils.ConstantItem.IMAGE_THUMB

object ImagePath {
    fun getThumbImageUlr(image_id: String) : String{
        return  BASE_IMAGE_URL+image_id+ IMAGE_THUMB
    }

    fun getBigThumbImageUlr(image_id: String) : String{
        return  BASE_IMAGE_URL+image_id+IMAGE_BIG_THUMB
    }
}