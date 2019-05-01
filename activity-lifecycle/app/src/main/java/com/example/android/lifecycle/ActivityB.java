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
import android.widget.TextView;

import com.example.android.lifecycle.util.StatusTracker;
import com.example.android.lifecycle.util.Utils;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityB extends Activity {

    private String mActivityName;
    private static int counter_B=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mActivityName = getString(R.string.activity_b_label);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("msg", "onStart_B: " + counter_B);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        counter_B+=1;
        Log.d("msg", "onRestart_B: "+counter_B);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("msg", "onResume_B: " + counter_B);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("msg", "pause_B: " + counter_B);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("msg", "stop_B: " + counter_B);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        counter_B=0;
        Intent intent = new Intent(getApplicationContext(), ActivityA.class);
        intent.putExtra("counter_B",counter_B);
        Log.d("B return value", "finishActivityB: "+counter_B);
        startActivity(intent);
        Log.d("msg", "onDestroy_B: " + counter_B);
    }

    public void finishActivityB(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityA.class);
        intent.putExtra("counter_B",counter_B);
        Log.d("B return value", "finishActivityB: "+counter_B);
        startActivity(intent);
    }

}
