package uz.yasindev.contactapp.ui.fragments.add

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.yasindev.contactapp.core.api.ApiClient
import uz.yasindev.contactapp.core.models.RvContactModel

class AddPresenter(val view: AddMVP.View) : AddMVP.Presenter {

    private val service = ApiClient.getService()


    override fun addUser(rvContactModel: RvContactModel) {
        service.addUser(rvContactModel).enqueue(object : Callback<RvContactModel> {
            override fun onResponse(p0: Call<RvContactModel>, p1: Response<RvContactModel>) {
                if (p1.isSuccessful) {
                    view.successAdd("Successfully added ")
                }
                else view.setError("Failed")
            }

            override fun onFailure(p0: Call<RvContactModel>, p1: Throwable) {
                view.setError("Internet is not working")
            }
        })
    }
}