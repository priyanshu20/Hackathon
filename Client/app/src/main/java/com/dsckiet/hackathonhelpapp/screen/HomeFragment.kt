package com.dsckiet.hackathonhelpapp.screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.adapter.EmergencyHelpAdapter
import com.dsckiet.hackathonhelpapp.databinding.FragmentHomeBinding
import com.dsckiet.hackathonhelpapp.model.EmergencyResponse
import com.dsckiet.hackathonhelpapp.network.AppNetwork
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home, container, false
            )
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val retrofitService=AppNetwork.getClient(requireContext()).getEmergency("priyanshus.edu@gmail.com")
        retrofitService.enqueue(object :Callback<EmergencyResponse>{
            override fun onFailure(call: Call<EmergencyResponse>, t: Throwable) {
                Log.d("Error",t.message.toString())
                Snackbar.make(binding.coordinatorLayout,"Network Problem",Snackbar.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<EmergencyResponse>,
                response: Response<EmergencyResponse>
            ) {
                Log.d("Success",response.toString())
                if(response.code()==200) {
                    binding.totalEmergency.text = response.body()?.data?.size.toString()



                    if (response.body()?.data != null) {
                        binding.emergencyRecyclerView.adapter =
                            EmergencyHelpAdapter(requireContext(), response.body()?.data!!)
                        if(response.body()?.data?.size!!>0)
                        {
                           showNotification()

                        }
                    }
                }
            }

        })
    }

    private fun showNotification() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val channelId = "default"
        val channelName = "Covid Tracker"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
            .setColor(ContextCompat.getColor(requireContext(), R.color.textColor))
            .setSmallIcon(R.drawable.ic_baseline_location_on_24)
            .setContentTitle("Someone nearby you is in danger")
            .setContentText("Do help")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

}