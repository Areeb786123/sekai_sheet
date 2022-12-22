LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := sekaiSheet_key
LOCAL_SRC_FILES := sekaiSheet.c

include $(BUILD_SHARED_LIBRARY)