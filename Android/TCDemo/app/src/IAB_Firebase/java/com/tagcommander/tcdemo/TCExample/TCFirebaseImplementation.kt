package com.tagcommander.tcdemo.TCExample

import android.content.Context
import com.google.firebase.FirebaseApp
import com.tagcommander.lib.firebasedestination.TCFirebase

object TCFirebaseImplementation
{
    fun initFirebaseDestination(appContext: Context) {
        FirebaseApp.initializeApp(appContext)

        TCFirebase.getInstance().initialize(appContext)
    }
}