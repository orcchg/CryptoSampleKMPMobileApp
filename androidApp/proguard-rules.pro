# Keep `Companion` object fields of serializable classes.
# This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}

# Keep `serializer()` on companion objects (both default and named) of serializable classes.
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep `INSTANCE.serializer()` of serializable objects.
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# @Serializable and @Polymorphic are used at runtime for polymorphic serialization.
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

# Serializer for classes with named companion objects are retrieved using `getDeclaredClasses`.
# If you have any, uncomment and replace classes with those containing named companion objects.
-keepattributes InnerClasses # Needed for `getDeclaredClasses`.
-if @kotlinx.serialization.Serializable class
{
    static **$* *;
}
-keepnames class <1>$$serializer { # -keepnames suffices; class is kept when serializer() is kept.
    static <1>$$serializer INSTANCE;
}

# For SQLCipher: https://github.com/sqlcipher/android-database-sqlcipher#using-sqlcipher-for-android-with-room
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

# For CryptoUtils
#-keep,includedescriptorclasses class com.orcchg.crypto.sample.mobileapp.util.CryptoUtils {
#     keep jdk binding methods, ex: private void doSomething(java.lang.String, java.lang.String, java.lang.String);
#    native <methods>;
#}

# For Amazon
-dontwarn com.amazon.**
-keep class com.amazon.** {*;}
-keepattributes *Annotation*

# For Huawei
-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.hms.**{*;}
-keep class com.huawei.hms.ads.** { *; }
-keep class com.huawei.openalliance.ad.** { *; }
