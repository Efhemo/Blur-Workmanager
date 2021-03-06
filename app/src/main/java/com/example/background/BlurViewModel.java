/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.example.background;

import android.arch.lifecycle.ViewModel;
import android.net.Uri;
import android.text.TextUtils;

import com.example.background.workers.BlurWorker;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class BlurViewModel extends ViewModel {

    private Uri mImageUri;

    WorkManager mWorkManager;

    public BlurViewModel() {

        mWorkManager = WorkManager.getInstance();
    }

    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    void applyBlur(int blurLevel) {

        /*
        * There are two types of WorkRequests:
            OneTimeWorkRequest: A WorkRequest that will only execute once.
            PeriodicWorkRequest: A WorkRequest that will repeat on a cycle.
        */
        mWorkManager.enqueue(OneTimeWorkRequest.from(BlurWorker.class));
    }

    private Uri uriOrNull(String uriString) {
        if (!TextUtils.isEmpty(uriString)) {
            return Uri.parse(uriString);
        }
        return null;
    }

    /**
     * Setters
     */
    void setImageUri(String uri) {
        mImageUri = uriOrNull(uri);
    }

    /**
     * Getters
     */
    Uri getImageUri() {
        return mImageUri;
    }

}