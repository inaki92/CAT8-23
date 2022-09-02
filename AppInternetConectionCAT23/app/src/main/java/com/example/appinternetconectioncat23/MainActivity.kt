package com.example.appinternetconectioncat23

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appinternetconectioncat23.adapter.FlowersAdapter
import com.example.appinternetconectioncat23.databinding.ActivityMainBinding
import com.example.appinternetconectioncat23.model.Flower
import com.example.appinternetconectioncat23.rest.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val flowerAdapter by lazy {
        FlowersAdapter {
            // TODO operation for the click
        }
    }

    private val disposable by lazy {
        CompositeDisposable()
    }

    private lateinit var networkState: ConnectivityState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ).forEach {
            if (checkSelfPermission(it) != PERMISSION_GRANTED) {
                requestPermissions(arrayOf(it), 321)
            } else {
                Toast.makeText(baseContext, "Permissions granted", Toast.LENGTH_LONG).show()
            }
        }

        binding.flowerRV.apply {
            layoutManager = GridLayoutManager(
                baseContext,
                2,
                GridLayoutManager.VERTICAL,
                false
            )

            adapter = flowerAdapter
        }

        networkState = ConnectivityState(getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    override fun onResume() {
        super.onResume()

        if (networkState.checkInternetConnection()) {
            // makeNetworkConnectionWithCallbackEnqueue()
            networkConnectionWithRx()
        } else {
            showError("NO INTERNET CONNECTION")
        }
    }

    private fun networkConnectionWithRx() {
        Service.retrofitService.getFlowersRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                 { response -> flowerAdapter.updateFlowers(response) },
                 { error -> showError(error.localizedMessage) }
            )
            .also { disposable.add(it) }
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 321) {
            grantResults.forEach {
                if (it == PERMISSION_GRANTED) {
                    Toast.makeText(baseContext, "Permissions granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, "Permissions denied by user", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun makeNetworkConnectionWithCallbackEnqueue() {

        Service.retrofitService.getFlowers().enqueue(object: Callback<List<Flower>> {

            override fun onResponse(call: Call<List<Flower>>, response: Response<List<Flower>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d(TAG, "Flowers response: $it")
                        flowerAdapter.updateFlowers(it)
                    }
                } else {
                    showError(response.errorBody()?.string() ?: " ERROR")
                }
            }

            override fun onFailure(call: Call<List<Flower>>, t: Throwable) {
                showError(t.localizedMessage)
            }
        }
        )
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Error Occurred")
            .setMessage(message)
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}