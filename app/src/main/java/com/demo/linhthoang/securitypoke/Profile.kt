package com.demo.linhthoang.securitypoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.linhthoang.humtum.HumtumApp


class Profile : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        HumtumApp.currentInstance?.getSelf({ string ->
            activity?.let {

                val profile = ViewModelProvider(it)[AppViewModel::class.java].profile
                profile.postValue(string)

            }
        })

        activity?.let {

            ViewModelProvider(it)[AppViewModel::class.java].profile.observe(it,
                Observer { str ->
                    view.findViewById<TextView>(R.id.id).text = str
                        .replace(',', '\n')
                        .replace("""([{}])""".toRegex(), "")
                }
            )
        }

        return view
    }
}