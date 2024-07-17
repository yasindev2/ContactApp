package uz.yasindev.contactapp.ui.fragments.main

import uz.yasindev.contactapp.core.models.RvContactModel

interface MainMVP {


    interface View {
        fun setDataToAdapter(data:List<RvContactModel>)
        fun setError(message:String)
    }

    interface Presenter {
        fun getAllContacts()
    }

}