package uz.yasindev.contactapp.ui.fragments.info

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.yasindev.contactapp.core.api.ApiClient

class InfoPresenter(val view: InfoMVP.View) : InfoMVP.Presenter {

    private val service = ApiClient.getService()

    override fun deleteUser(id: Int) {

        service.deleteUser(id).enqueue(object : Callback<Void> {
            override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                if (p1.isSuccessful) {
                    view.successDelete()
                } else view.deleteError("Failed")
            }

            override fun onFailure(p0: Call<Void>, p1: Throwable) {
                view.deleteError("Internet is not working")
            }

        })
    }



}