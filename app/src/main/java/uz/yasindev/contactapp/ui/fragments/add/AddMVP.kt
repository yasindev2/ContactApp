package uz.yasindev.contactapp.ui.fragments.add

import uz.yasindev.contactapp.core.models.RvContactModel

interface AddMVP {

    interface View {
        fun successAdd(message: String)
        fun setError(message: String)
    }

    interface Presenter {
        fun addUser(rvContactModel: RvContactModel)
    }


}