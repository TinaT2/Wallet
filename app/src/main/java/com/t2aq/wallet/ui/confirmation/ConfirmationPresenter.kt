package com.t2aq.wallet.ui.confirmation

import android.content.Intent
import android.util.Log
import com.t2aq.wallet.data.model.ConfirmationModel
import com.t2aq.wallet.data.remote.APIClient
import com.t2aq.wallet.ui.mainpage.MainPageActivity
import com.t2aq.wallet.utils.LoginUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmationPresenter(val confirmationView: ConfirmationContract.View) :
    ConfirmationContract.Presenter {
    override fun sendVerificationCode(phone: String,
                                      udid: String,
                                      deviceName: String,
                                      activationCode: Int) {
        APIClient.getService()?.bind(phone, udid, deviceName, activationCode)
            ?.enqueue(object : Callback<ConfirmationModel> {
                override fun onFailure(call: Call<ConfirmationModel>, t: Throwable) {
                    val result = "failed: " + t.message
                    confirmationView.showResult(result)
                }

                override fun onResponse(call: Call<ConfirmationModel>,
                                        response: Response<ConfirmationModel>) {
                    val result = "responsed: " + response.message()
                    confirmationView.showResult(result)
                    if (response.code()==200 && response.body() != null) {
                        val token = response.body()!!.token
                        LoginUtils.saveTokenInSharedPreferences(token)
                        confirmationView.startMainPage()
                    }
                }

            })
    }


}