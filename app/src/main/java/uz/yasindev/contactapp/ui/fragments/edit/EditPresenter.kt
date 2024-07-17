package uz.yasindev.contactapp.ui.fragments.edit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.yasindev.contactapp.core.api.ApiClient
import uz.yasindev.contactapp.core.models.RvContactModel

class EditPresenter(val view: EditMVP.View) : EditMVP.Presenter {

    private val service = ApiClient.getService()

    override fun editUserInfo(id: Int, rvContactModel: RvContactModel) {
        service.updateUserData(id, rvContactModel).enqueue(object : Callback<RvContactModel>{
            override fun onResponse(p0: Call<RvContactModel>, p1: Response<RvContactModel>) {
                if (p1.isSuccessful){
                    view.successEdit("Edited successfully")
                }
                else view.errorEdit("Failed ${p1.code()}")
            }

            override fun onFailure(p0: Call<RvContactModel>, p1: Throwable) {
                view.errorEdit("Internet is not working")
            }

        })
    }
}