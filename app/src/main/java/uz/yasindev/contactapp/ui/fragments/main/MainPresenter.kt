package uz.yasindev.contactapp.ui.fragments.main

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.yasindev.contactapp.core.AllData
import uz.yasindev.contactapp.core.api.ApiClient
import uz.yasindev.contactapp.core.models.RvContactModel

class MainPresenter(val view: MainMVP.View) : MainMVP.Presenter {


    private val service = ApiClient.getService()

    override fun getAllContacts() {
        service.getAllUser().enqueue(object : Callback<List<RvContactModel>> {
            override fun onResponse(
                p0: Call<List<RvContactModel>>,
                p1: Response<List<RvContactModel>>
            ) {
                if (p1.isSuccessful) {
                    view.setDataToAdapter(p1.body()!!)
                    AllData.setData(p1.body()!! as ArrayList<RvContactModel>)
                } else view.setError("Failed")
            }

            override fun onFailure(p0: Call<List<RvContactModel>>, p1: Throwable) {
                view.setError("Internet is not working")
            }

        })
    }
}