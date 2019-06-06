//  Created by Brian Dupuis on 5/31/19.
//  Copyright 2019, Genesys
//
//  Permission is hereby granted, free of charge, to any person obtaining a
//  copy of this software and associated documentation files (the "Software"),
//  to deal in the Software without restriction, including without limitation
//  the rights to use, copy, modify, merge, publish, distribute, sublicense,
//  and/or sell copies of the Software, and to permit persons to whom the
//  Software is furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
//  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
//  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
//  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
//  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
//  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
package com.genesys.widgets.acd.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class GenesysAcdChatActivity extends AppCompatActivity {

    public static final String EXTRAS_CHAT_DATA_URL = "genesys_acd_data_url";
    public static final String EXTRAS_CHAT_DEPLOYMENT_KEY = "genesys_acd_deployment_key";
    public static final String EXTRAS_CHAT_ORG_GUID = "genesys_acd_org_guid";
    public static final String EXTRAS_CHAT_QUEUE_NAME = "genesys_acd_queue_name";
    public static final String EXTRAS_CHAT_PRIORITY = "genesys_acd_priority";
    public static final String EXTRAS_CHAT_USER_FIRST_NAME = "genesys_acd_first_name";
    public static final String EXTRAS_CHAT_USER_LAST_NAME = "genesys_acd_last_name";
    public static final String EXTRAS_CHAT_USER_EMAIL_ADDRESS = "genesys_acd_email_address";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        WebView webView = findViewById(R.id.help_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AcdChatInterface(), "AcdChatHostInterface");
        webView.loadUrl("file:///android_res/raw/webchat.html");
    }

    public static void launchChat(Activity activity, Integer priority, String firstName, String lastName, String emailAddress) {
        Intent i = new Intent(activity, GenesysAcdChatActivity.class);
        i.putExtra(EXTRAS_CHAT_DATA_URL, BuildConfig.GENESYS_ACD_CHAT_DATA_URL);
        i.putExtra(EXTRAS_CHAT_DEPLOYMENT_KEY, BuildConfig.GENESYS_ACD_CHAT_DEPLOYMENT_KEY);
        i.putExtra(EXTRAS_CHAT_ORG_GUID, BuildConfig.GENESYS_ACD_CHAT_ORG_GUID);
        i.putExtra(EXTRAS_CHAT_QUEUE_NAME, BuildConfig.GENESYS_ACD_CHAT_QUEUE_NAME);
        i.putExtra(EXTRAS_CHAT_PRIORITY, priority);
        i.putExtra(EXTRAS_CHAT_USER_FIRST_NAME, firstName);
        i.putExtra(EXTRAS_CHAT_USER_LAST_NAME, lastName);
        i.putExtra(EXTRAS_CHAT_USER_EMAIL_ADDRESS, emailAddress);
        activity.startActivity(i);
    }

    private class AcdChatInterface {

        @JavascriptInterface
        public void closeChatWindow() {
            finish();
        }

        @JavascriptInterface
        public String getDataUrl() {
            return getIntent().getStringExtra(EXTRAS_CHAT_DATA_URL);
        }

        @JavascriptInterface
        public String getDeploymentKey() {
            return getIntent().getStringExtra(EXTRAS_CHAT_DEPLOYMENT_KEY);
        }

        @JavascriptInterface
        public String getOrgGuid() {
            return getIntent().getStringExtra(EXTRAS_CHAT_ORG_GUID);
        }

        @JavascriptInterface
        public String getQueueName() {
            return getIntent().getStringExtra(EXTRAS_CHAT_QUEUE_NAME);
        }

        @JavascriptInterface
        public Integer getPriority() {
            return getIntent().getIntExtra(EXTRAS_CHAT_PRIORITY, 0);
        }

        @JavascriptInterface
        public String getChatUserFirstName() {
            return getIntent().getStringExtra(EXTRAS_CHAT_USER_FIRST_NAME);
        }

        @JavascriptInterface
        public String getChatUserLastName() {
            return getIntent().getStringExtra(EXTRAS_CHAT_USER_LAST_NAME);
        }

        @JavascriptInterface
        public String getChatUserEmailAddress() {
            return getIntent().getStringExtra(EXTRAS_CHAT_USER_EMAIL_ADDRESS);
        }
    }

}
