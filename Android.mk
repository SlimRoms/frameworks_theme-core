# Copyright (C) 2017 SlimRoms Project
# Copyright (C) 2017 Victor Lapin
# Copyright (C) 2017 Griffin Millender
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
