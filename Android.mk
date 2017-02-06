LOCAL_PATH := $(call my-dir)

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

LOCAL_STATIC_ANDROID_LIBRARIES := \
    theme-core-res

LOCAL_SHARED_ANDROID_LIBRARIES := \
    android-support-v4

LOCAL_JAR_EXCLUDE_FILES := none
LOCAL_JAVA_LANGUAGE_VERSION := 1.7
include $(BUILD_STATIC_JAVA_LIBRARY)
