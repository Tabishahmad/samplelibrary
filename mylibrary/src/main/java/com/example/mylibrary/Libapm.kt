package com.example.mylibrary

import android.content.Context
import io.sentry.Sentry
import io.sentry.android.core.SentryAndroid
import io.sentry.protocol.User


object Libapm {
    fun init(context:Context){
        var deviceID : String = ""
        // Create a configuration for Sentry



        try {
            SentryAndroid.init(context) {
                it.dsn =
                    "https://4b0f8a6d2fa589b23bcc1000176eb621@o4506070196289536.ingest.sentry.io/4506070198779904"
                it.enableTracing = true
                it.isAttachScreenshot = true
                it.isAttachViewHierarchy = true
                it.sampleRate = 1.0
                it.profilesSampleRate = 1.0
            }

            val className = "semusi.activitysdk.ContextSdk"
            val myClass = Class.forName(className)
            val method = myClass.getDeclaredMethod("getInternalId", Context::class.java)
            deviceID = method.invoke(null, context).toString()
            println(deviceID)
        }catch (e:ClassNotFoundException){
            e.printStackTrace()
            //Should I show alert that libsdk not found or get GAID directly
        }
        catch (e:Exception){
            e.printStackTrace()
            //Should I show alert that libsdk not found or get GAID directly
        }
        val sentryUser = User()
        sentryUser.id = deviceID
        Sentry.setUser(sentryUser)
    }
}