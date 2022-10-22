FROM --platform=linux/amd64 rubylang/ruby:3.1.2-focal

ENV ANDROID_SDK_REVISION linux-8512546_latest
ENV ANDROID_SDK_ROOT /opt/android-sdk-linux
ENV ANDROID_BUILD_TOOLS_VERSION 32.0.0
ENV ANDROID_COMPILE_SDK 33
ENV WORKING_DIR /app/

RUN apt-get update -y && \
    apt-get upgrade -y && \
    apt-get install zip unzip wget -y && \
    apt-get install openjdk-11-jdk -y

RUN wget https://dl.google.com/android/repository/commandlinetools-${ANDROID_SDK_REVISION}.zip && \
    unzip commandlinetools-${ANDROID_SDK_REVISION}.zip && \
    mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    mv cmdline-tools ${ANDROID_SDK_ROOT}/cmdline-tools/latest && \
    rm -r commandlinetools-${ANDROID_SDK_REVISION}.zip
    
ENV PATH ${PATH}:${ANDROID_SDK_ROOT}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin

RUN mkdir ~/.android && \
    touch ~/.android/repositories.cfg
RUN yes | sdkmanager --licenses
RUN yes | sdkmanager "platforms;android-${ANDROID_COMPILE_SDK}" \
    "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" 

WORKDIR ${WORKING_DIR}
COPY ./ ${WORKING_DIR}
RUN ./gradlew test -d --stacktrace || exit 0 && \
# ビルドに使ったのはいらないので消す。gradleのキャッシュは/root/.gradle/cachesにある。
    rm -rf *