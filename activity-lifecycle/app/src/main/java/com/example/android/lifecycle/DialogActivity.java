/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;


public class DialogActivity extends Activity {
    private static int counter_C=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        counter_C+=1;
        Log.d("msg", "onRestart_C: " + counter_C);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        counter_C=0;
        Intent intent = new Intent(getApplicationContext(), ActivityA.class);
        intent.putExtra("counter_C",counter_C);
        startActivity(intent);
        DialogActivity.this.finish();
        Log.d("msg", "onDestroy_C: " + counter_C);
    }
    /**
     * Callback method defined by the View
     * @param v
     */
    public void finishDialog(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityA.class);
        intent.putExtra("counter_C",counter_C);
        startActivity(intent);
        DialogActivity.this.finish();
    }
}
