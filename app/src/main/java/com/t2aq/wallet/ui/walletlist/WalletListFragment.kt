package com.t2aq.wallet.ui.walletlist

import com.t2aq.wallet.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WalletListFragment :Fragment(){

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_walletlist,container,false)
    }

}