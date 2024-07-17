package uz.yasindev.contactapp.ui.fragments.edit

import uz.yasindev.contactapp.core.models.RvContactModel

interface EditMVP {

    interface View {
        fun successEdit(message: String)
        fun errorEdit(message: String)
    }

    interface Presenter {
        fun editUserInfo(id: Int, rvContactModel: RvContactModel)
    }

}