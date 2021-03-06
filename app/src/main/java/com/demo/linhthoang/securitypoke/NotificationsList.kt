package com.demo.linhthoang.securitypoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_noti, container, false)
        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(context)

            // specify an viewAdapter (see also next example)
            activity?.let {
                adapter = FriendRequestAdapter(
                    ViewModelProvider(it)[AppViewModel::class.java]
                )
                ViewModelProvider(it)[AppViewModel::class.java].friendRequest.observe(it,
                    Observer {
                    })
                ViewModelProvider(it)[AppViewModel::class.java].messages.observe(it,
                    Observer {
                        adapter?.notifyDataSetChanged()

                    })
            }
        }
        return view
    }
}