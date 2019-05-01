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
import android.util.Log;

/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityA extends Activity {

    private String mActivityName;
    private TextView mCounterView;
    private static int counter_A;
    private static int thread_counter_B=0;
    private static int thread_counter_C=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mActivityName = getString(R.string.activity_a);
        mCounterView = (TextView)findViewById(R.id.counter_value);
        Log.d("msg", "onCreate_A: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        thread_counter_C=thread_counter_B=0;
        Log.d("msg", "onStart_A: "+counter_A);
        }

    @Override
    protected void onRestart() {
        super.onRestart();
        counter_A+=1;
        Log.d("msg", "onRestart_A: " + counter_A);
        }
    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null)
            setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Resume_A", "onResume: "+counter_A+thread_counter_B+thread_counter_C);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null) {
            thread_counter_B = extras.getInt("counter_B", 0);
            thread_counter_C = extras.getInt("counter_C", 0);
        }
        Log.d("check", "onResume: "+thread_counter_B+thread_counter_C+counter_A);
        counter_A = counter_A + thread_counter_B + thread_counter_C;
        mCounterView.setText(String.valueOf(counter_A));
    }

    @Override
    protected void onPause() {
        super.onPause();
        thread_counter_C=0;
        thread_counter_B=0;
        Log.d("msg", "onPause_A: " + counter_A);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("msg", "onStop_A: "+counter_A );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("msg", "onDestroy_A: "+counter_A);
    }

    public void startDialog(View v) {
        Intent intent = new Intent(ActivityA.this, DialogActivity.class);
        startActivity(intent);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(ActivityA.this, ActivityB.class);
        startActivity(intent);
    }

    public void finishActivityA(View v) {
        ActivityA.this.finish();
    }

}
