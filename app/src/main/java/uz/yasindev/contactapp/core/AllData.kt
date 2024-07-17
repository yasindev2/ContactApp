package uz.yasindev.contactapp.core

import uz.yasindev.contactapp.core.models.RvContactModel

object AllData {

    private val data = ArrayList<RvContactModel>()

    fun setData(data:ArrayList<RvContactModel>){
        this.data.clear()
        this.data.addAll(data)
    }

    fun getAllData():ArrayList<RvContactModel>{
        return data
    }

}