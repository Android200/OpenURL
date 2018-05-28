package com.umarauna.openurl;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CopyURL extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getDataString();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("URL", url));
        Toast.makeText(context, "Link is copied",Toast.LENGTH_SHORT).show();
    }
}
