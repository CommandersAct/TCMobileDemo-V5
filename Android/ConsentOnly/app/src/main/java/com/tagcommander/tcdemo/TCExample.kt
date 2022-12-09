package com.tagcommander.tcdemo

import android.content.Context
import android.util.Log
import com.tagcommander.lib.consent.TCConsent
import com.tagcommander.lib.consent.TCPrivacyCallbacks
import com.tagcommander.lib.core.TCDebug
import com.tagcommander.lib.core.TCLogger
import com.tagcommander.lib.tciab.consent.TCCMPStorage

object TCExample : TCPrivacyCallbacks
{
    val siteID = 3311
    val privacyId = 72

    fun initTCConsent(context: Context)
    {
        TCDebug.setDebugLevel(Log.VERBOSE)
        TCDebug.setNotificationLog(true)
        TCConsent.getInstance().setSiteIDPrivacyIDAppContext(siteID, privacyId, context)
        TCConsent.getInstance().setLanguage("fr")
        TCConsent.getInstance().generatePublisherTC = true
        TCConsent.getInstance().useCustomPublisherRestrictions()
        TCConsent.getInstance().registerCallback(this)
        TCLogger.getInstance().logMessage("saved Consent String: ${TCCMPStorage.getConsentString(context)}", Log.INFO)
    }

    override fun consentUpdated(p0: MutableMap<String, String>?) {
        // Pass information to your other solutions from here.
    }

    override fun consentOutdated() {
        // Use this to know that it's time to display privacy center again.
    }

    override fun consentCategoryChanged() {
    }

    override fun significantChangesInPrivacy() {
        // Use this to perform action when there are significat changes
    }

}