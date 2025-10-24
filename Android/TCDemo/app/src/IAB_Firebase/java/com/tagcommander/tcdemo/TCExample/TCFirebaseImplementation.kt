package com.tagcommander.tcdemo.TCExample

import android.content.Context
import com.tagcommander.lib.firebasedestination.TCFirebase

object TCFirebaseImplementation
{
    fun initFirebaseDestination(appContext: Context) {
        TCFirebase.getInstance().initialize(appContext)
    }
}