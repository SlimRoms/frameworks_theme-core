# Copyright (C) 2017 SlimRoms Project
# Copyright (C) 2017 Victor Lapin
# Copyright (C) 2017 Griffin Millender
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := \
    apache-commons-io:libs/commons-io-2.5.jar \
    zipsigner:libs/zipsigner-lib-1.17.jar \
    zipio:libs/zipio-lib-1.8.jar \
    kellinwood-logging-android:libs/kellinwood-logging-android-1.4.jar \
    kellinwood-logging-lib:libs/kellinwood-logging-lib-1.1.jar \
    kellinwood-logging-log4j:libs/kellinwood-logging-log4j-1.0.jar \
	gson:libs/gson-2.8.0.jar

include $(BUILD_MULTI_PREBUILT)

include $(CLEAR_VARS)
LOCAL_USE_AAPT2 := true
LOCAL_MODULE := theme-core-res
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_STATIC_JAVA_LIBRARY)

include $(CLEAR_VARS)
LOCAL_USE_AAPT2 := true
LOCAL_MODULE := theme-core

LOCAL_AIDL_INCLUDES := $(LOCAL_PATH)/aidl

LOCAL_SRC_FILES := \
    $(call all-java-files-under,src) \
    $(call all-Iaidl-files-under,aidl)

LOCAL_STATIC_JAVA_LIBRARIES := \
    apache-commons-io

LOCAL_STATIC_ANDROID_LIBRARIES := \
    theme-core-res

LOCAL_SHARED_ANDROID_LIBRARIES := \
    android-support-v4

LOCAL_JAR_EXCLUDE_FILES := none
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_STATIC_JAVA_LIBRARY)
