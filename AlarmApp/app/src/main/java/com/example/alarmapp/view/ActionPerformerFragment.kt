package com.example.alarmapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.alarmapp.AlarmReceiver
import com.example.alarmapp.R
import com.example.alarmapp.databinding.FragmentActionPerformerBinding

private const val TAG = "ActionPerformerFragment"

class ActionPerformerFragment : Fragment() {

    private val binding by lazy {
        FragmentActionPerformerBinding.inflate(layoutInflater)
    }

    private val alarmReceiver by lazy {
        AlarmReceiver()
    }

    private val lbManager by lazy {
        LocalBroadcastManager.getInstance(requireActivity().applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lbManager.registerReceiver(alarmReceiver, AlarmReceiver.intentFilter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        lbManager.unregisterReceiver(alarmReceiver)
    }
}