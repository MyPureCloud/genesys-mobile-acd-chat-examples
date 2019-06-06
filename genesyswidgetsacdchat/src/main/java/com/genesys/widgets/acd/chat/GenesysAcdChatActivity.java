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

    public static void launchChat(Activity activity, String dataUrl, String deploymentKey, String orgGuid, String queueName, Integer priority, String firstName, String lastName, String emailAddress) {
        Intent i = new Intent(activity, GenesysAcdChatActivity.class);
        i.putExtra(EXTRAS_CHAT_DATA_URL, dataUrl);
        i.putExtra(EXTRAS_CHAT_DEPLOYMENT_KEY, deploymentKey);
        i.putExtra(EXTRAS_CHAT_ORG_GUID, orgGuid);
        i.putExtra(EXTRAS_CHAT_QUEUE_NAME, queueName);
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
