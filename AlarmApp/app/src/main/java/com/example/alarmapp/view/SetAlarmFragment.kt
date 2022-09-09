package com.example.alarmapp.view

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.alarmapp.AlarmReceiver
import com.example.alarmapp.R
import com.example.alarmapp.databinding.FragmentSetAlarmBinding

class SetAlarmFragment : Fragment() {

    private val binding by lazy {
        FragmentSetAlarmBinding.inflate(layoutInflater)
    }

    private val alarmManager by lazy {
        requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.startAlarm.setOnClickListener {
            settingAlarm(10000)
            findNavController().navigate(R.id.action_SetAlarmFragment_to_ActionFragment)
        }

        binding.cancelAlarm.setOnClickListener {
            cancelAlarm()
        }

        return binding.root
    }

    private fun settingAlarm(timeMilliseconds: Int) {
        val setTime = System.currentTimeMillis() + timeMilliseconds
        alarmManager.set(
            RTC_WAKEUP,
            setTime,
            AlarmReceiver.getAlarmPendingIntent(
                requireActivity().applicationContext,
                AlarmReceiver.getIntent(
                    requireActivity().applicationContext,
                    "This is a new event",
                    "This is the info"
                )
            )
        )
    }

    private fun cancelAlarm() {
        alarmManager.cancel(AlarmReceiver.getAlarmPendingIntent(
            requireActivity().applicationContext,
            AlarmReceiver.getIntent(
                requireActivity().applicationContext,
                "This is a new event",
                "This is the info"
            )
        )
        )
    }
}