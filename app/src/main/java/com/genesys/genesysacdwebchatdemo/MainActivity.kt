package com.genesys.genesysacdwebchatdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.genesys.widgets.acd.chat.GenesysAcdChatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {

            // This customer data can be populated by either authenticated data provided elsewhere or
            // an unauthenticated form for help prior to the user having an authenticated session.
            GenesysAcdChatActivity.launchChat(this,
                BuildConfig.GENESYS_ACD_CHAT_DATA_URL,
                BuildConfig.GENESYS_ACD_CHAT_DEPLOYMENT_KEY,
                BuildConfig.GENESYS_ACD_CHAT_ORG_GUID,
                BuildConfig.GENESYS_ACD_CHAT_QUEUE_NAME,
                2,
                "Jimmy",
                "Chat",
                "jimmy.chat@chat.com")
        }
    }
}
