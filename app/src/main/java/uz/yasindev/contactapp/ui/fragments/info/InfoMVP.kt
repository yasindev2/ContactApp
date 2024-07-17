package uz.yasindev.contactapp.ui.fragments.info

interface InfoMVP {

    interface View {
        fun successDelete()
        fun deleteError(error: String)
    }

    interface Presenter {
        fun deleteUser(id: Int)
    }

}