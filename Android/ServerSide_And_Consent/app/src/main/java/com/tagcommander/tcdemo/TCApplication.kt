package com.tagcommander.tcdemo

import androidx.multidex.MultiDexApplication

class TCApplication : MultiDexApplication()
{
    override fun onCreate()
    {
        super.onCreate()
        TCExample.initTCServerSide(this.applicationContext)
        TCExample.initTCConsent(this.applicationContext)
    }
}