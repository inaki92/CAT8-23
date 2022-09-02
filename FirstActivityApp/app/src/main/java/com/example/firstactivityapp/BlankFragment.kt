package com.example.firstactivityapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firstactivityapp.MainActivity.Companion.TAG

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: Fragment attached to activity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d(TAG, "onCreate: FragmentCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Inflating Fragment")
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Fragment started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Fragment ready")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: Activity ready for fragment interaction")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: fragment pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Frag stop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: fragment view destroyed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: view on destroy fragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: Fragment detached from activity")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}