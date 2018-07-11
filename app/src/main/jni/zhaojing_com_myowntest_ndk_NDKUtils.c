//
// Created by zhaojing on 2018-07-10.
//
#include "zhaojing_com_myowntest_ndk_NDKUtils.h"


int intialize(int a,int b){

    return a+b;
}
JNIEXPORT jint JNICALL Java_zhaojing_com_myowntest_ndk_NDKUtils_getStringFromNative
  (JNIEnv *env, jobject obj, jint key, jint name){
    jint res=intialize(key,name);
  return res;

  }


