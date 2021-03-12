#!/bin/bash
# 
# Creates a new android release from current branch
#

export BASEDIR=$(pwd)
export PACKAGE_DIRECTORY="${BASEDIR}/../../prebuilt/android-aar/mobile-ffmpeg"
export CUSTOM_OPTIONS="--lts --enable-android-zlib --enable-android-media-codec"
export CUSTOM_OPTIONS_DEBUG="--disable-arm-v7a --disable-arm-v7a-neon --disable-x86 --disable-x86-64 --lts --enable-android-zlib --enable-android-media-codec"

create_package() {
    local NEW_PACKAGE="${PACKAGE_DIRECTORY}/mobile-ffmpeg-$1-$2.LTS.aar"

    local CURRENT_PACKAGE="${PACKAGE_DIRECTORY}/mobile-ffmpeg.aar"
    rm -f ${NEW_PACKAGE}

    mv ${CURRENT_PACKAGE} ${NEW_PACKAGE} || exit 1
}

enable_gradle_build() {
    rm -f ${BASEDIR}/../../android/app/build.gradle || exit 1
    cp ${BASEDIR}/android/build.gradle ${BASEDIR}/../../android/app/build.gradle || exit 1
}

enable_gradle_release() {
    rm -f ${BASEDIR}/../../android/app/build.gradle || exit 1
    cp ${BASEDIR}/android/release.github.template.gradle ${BASEDIR}/../../android/app/build.gradle || exit 1
}

if [ $# -ne 2 ]; then
    echo "Usage: android.sh <version code> <version name>"
    exit 1
fi

# VALIDATE VERSIONS
MOBILE_FFMPEG_VERSION=$(grep '#define MOBILE_FFMPEG_VERSION' ${BASEDIR}/../../android/app/src/main/cpp/mobileffmpeg.h | grep -Eo '\".*\"' | sed -e 's/\"//g')
if [[ "${MOBILE_FFMPEG_VERSION}" != "$2" ]]; then
    echo "Error: version mismatch. v$2 requested but v${MOBILE_FFMPEG_VERSION} found. Please perform the following updates and try again."
    echo "1. Update docs"
    echo "2. Update android/app/build.gradle file versions"
    echo "3. Update tools/release scripts' descriptions"
    echo "4. Update mobileffmpeg.h versions for both android and ios"
    echo "5. Update versions in Doxyfile"
    exit 1
fi

# MIN RELEASE
enable_gradle_build
cd ${BASEDIR}/../.. || exit 1
./android.sh ${CUSTOM_OPTIONS} || exit 1
cd ${BASEDIR}/../../android/app || exit 1
enable_gradle_release
../gradlew -p ${BASEDIR}/../../android -PreleaseVersionCode=$1 -PreleaseVersionName=$2.LTS -PreleaseMinSdk=21 -PreleaseTargetSdk=29 -PreleaseProject=mobile-ffmpeg-min-atlasv clean publish || exit 1
create_package "min-atlasv" "$2" || exit 1




