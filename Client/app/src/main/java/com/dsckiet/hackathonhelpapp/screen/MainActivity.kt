package com.dsckiet.hackathonhelpapp.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dsckiet.hackathonhelpapp.Constants
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.databinding.ActivityMainBinding
import com.dsckiet.hackathonhelpapp.model.UpdateUserBody
import com.dsckiet.hackathonhelpapp.model.UpdateUserResponse
import com.dsckiet.hackathonhelpapp.network.AppNetwork
import com.dsckiet.hackathonhelpapp.services.LocationService
import com.google.android.gms.location.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val intent = Intent(this, LocationService::class.java)
        intent.action = LocationService.ACTION_START_FOREGROUND_SERVICE


        if (!Constants.foregroundService) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else
                startService(intent)
            Constants.foregroundService = true

        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (!isLocationEnabled()) {
            Toast.makeText(
                this,
                "Your location provider is turned off, please turn it on",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            // START
            Dexter.withActivity(this)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION

                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (report!!.areAllPermissionsGranted()) {
                            requestLocationData()
                        }

                        if (report.isAnyPermissionPermanentlyDenied) {
                            Toast.makeText(
                                this@MainActivity,
                                "You have denied permission. Please allow it is mandatory.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }
                }).onSameThread()
                .check()
            // END
        }

    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )

    }

    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage("It Looks like you have turned off permissions required for this feature. It can be enabled under Application Settings")
            .setPositiveButton(
                "GO TO SETTINGS"
            ) { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog,
                                           _ ->
                dialog.dismiss()
            }.show()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 20000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        @SuppressLint("LogNotTimber")
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            Constants.latitude = mLastLocation.latitude
            Log.d("Current Latitude", "${Constants.latitude}")

            Constants.longitude = mLastLocation.longitude
            Log.d("Current Longitude", "${Constants.longitude}")
            updateUser()

        }
    }
fun updateUser()
{
    val uid="5f66098e68d68139f8156ba2"
    val updateUserResponse=UpdateUserBody(uid,Constants.latitude,Constants.longitude)
    val retrofitService= AppNetwork.getClient(this@MainActivity)
    val callApi=retrofitService.updateUser(updateUserResponse)
    callApi.enqueue(object : Callback<UpdateUserResponse>{
        override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
            Log.d("Failure",t.message.toString())
            Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(
            call: Call<UpdateUserResponse>,
            response: Response<UpdateUserResponse>
        ) {
            Log.d("Success",response.toString())
            if(response.code()==200)
           Toast.makeText(this@MainActivity,"Coordinates updated",Toast.LENGTH_SHORT).show()
        }

    })
}


}