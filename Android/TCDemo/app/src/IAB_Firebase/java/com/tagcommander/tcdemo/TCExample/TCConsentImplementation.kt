package com.tagcommander.tcdemo.TCExample

import android.content.Context
import android.content.Intent
import android.util.Log
import com.tagcommander.lib.consent.TCConsent
import com.tagcommander.lib.consent.TCPrivacyCallbacks
import com.tagcommander.lib.consent.TCPrivacyCenter
import com.tagcommander.lib.core.TCLogger
import com.tagcommander.lib.tciab.consent.TCCMPStorage

object TCConsentImplementation : TCPrivacyCallbacks
{
    val siteID =4183
    val privacyId = 74342

    fun initTCConsent(context: Context, languageCode: String?=null)
    {
        TCConsent.getInstance().useAcString(true)
        TCConsent.getInstance().generatePublisherTC = true
        TCConsent.getInstance().registerCallback(this)

        languageCode?.let {
            if (it.isNotEmpty()) {
                TCConsent.getInstance().setLanguage(it)
            }
        }

        // ═══════════════════════════════════════════════════════════
        //   setSiteIDPrivacyIDAppContext will initialize everything
        // ═══════════════════════════════════════════════════════════
        TCConsent.getInstance().setSiteIDPrivacyIDAppContext(siteID, privacyId, context)

        TCConsent.getInstance().useCustomPublisherRestrictions()

        TCLogger.getInstance().logMessage("saved Consent String: ${TCCMPStorage.getConsentString(context)}", Log.INFO) // Just printing IAB Consent String if exists !
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

    fun showPrivacyCenter(context: Context) {
        val PCM = Intent(context, TCPrivacyCenter::class.java)
        context.startActivity(PCM)
    }

    fun refuseAllConsent() {
        TCConsent.getInstance().refuseAllConsent()
    }

    fun acceptAllConsent() {
        TCConsent.getInstance().acceptAllConsent()
    }
}