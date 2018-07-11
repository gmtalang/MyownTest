LOCAL_PATH :=$(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := TestNativeLib
LOCAL_SRC_FILES :=zhaojing_com_myowntest_ndk_NDKUtils.c

include $(BUILD_SHARED_LIBRARY)

