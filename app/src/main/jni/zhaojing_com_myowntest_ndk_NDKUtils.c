#include <jni.h>
//for native audio
#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>
#include <SLES/OpenSLES_AndroidConfiguration.h>
#include <SLES/OpenSLES_AndroidMetadata.h>

//for native assetManager

//
// Created by zhaojing on 2018-07-10.
//
#ifndef _Included_zhaojing_com_myowntest_ndk_NDKUtils
#define _Included_zhaojing_com_myowntest_ndk_NDKUtils
#ifdef __cplusplus
extern "C" {
#endif
/***
 * function pointer
 * @param a
 * @param b
 */
/*int (* p)(int a,int b){

    int result;

    return result;
}*/

int intialize(int a,int b){

    return a+b;
}

/**
 *  int to string   by stream
 *
 * */
JNIEXPORT jstring JNICALL Java_zhaojing_com_myowntest_ndk_NDKUtils_getStringFromNative
  (JNIEnv *env, jobject obj, jint key, jint name){
//    p=intialize;//function pointer
//    jint result=p(key,name);
    jint r=intialize(key,name);
    jstring res="get from ndk!";
  return res;

  }

#ifdef __cplusplus
}
#endif
#endif


