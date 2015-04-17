LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under)

LOCAL_STATIC_JAVA_LIBRARIES := \
	libv4 \

LOCAL_PROGUARD_ENABLED := disabled

LOCAL_PACKAGE_NAME := TabFragment 

#LOCAL_CERTIFICATE := shared

include $(BUILD_PACKAGE)


include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := libv4:libs/android-support-v4.jar

include $(BUILD_MULTI_PREBUILT)
include $(call all-makefiles-under,$(LOCAL_PATH))
