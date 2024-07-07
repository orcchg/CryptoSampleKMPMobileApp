project("CryptoSampleUtils")

cmake_minimum_required(VERSION 3.4.1)

set(OPENSSL_ROOT_DIR ${CMAKE_SOURCE_DIR}/prefix/${CMAKE_ANDROID_ARCH_ABI})
set(LIBCRYPTO ${OPENSSL_ROOT_DIR}/lib/libcrypto.a)

add_library(
    cryptoutils
    SHARED
    src/androidMain/cpp/cryptoutils.cpp
)

include_directories(src/androidMain/cpp/ ${OPENSSL_ROOT_DIR}/include)

# UNCOMMENT the following lines to enable OpenSSL in native code
#link_directories(${OPENSSL_ROOT_DIR}/lib/)
#target_link_libraries(cryptoutils PRIVATE ${LIBCRYPTO} log)
