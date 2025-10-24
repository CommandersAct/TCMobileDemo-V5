package com.tagcommander.tcdemo

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.tagcommander.lib.core.TCDebug
import com.tagcommander.tcdemo.TCExample.TCConsentImplementation
import com.tagcommander.tcdemo.TCExample.TCFirebaseImplementation
import com.tagcommander.tcdemo.TCExample.TCServerSideImplementation

class TCApplication : MultiDexApplication()
{
    override fun onCreate()
    {
        super.onCreate()

        initCommandersActLibraries()
    }

    private fun initCommandersActLibraries() {
        TCDebug.setDebugLevel(Log.VERBOSE) // Optional : for logs
        TCDebug.setNotificationLog(true) // Optional : for inner notifications

        TCServerSideImplementation.initTCServerSide(this.applicationContext)
        TCConsentImplementation.initTCConsent(this.applicationContext)
        TCFirebaseImplementation.initFirebaseDestination(this.applicationContext)
    }
}