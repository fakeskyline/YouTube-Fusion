# Target build baseline

## Selected target: YouTube 21.13.164

| Field | Value |
|---|---|
| Package | `com.google.android.youtube` |
| Version name | `21.13.164` |
| Version code | `1561063732` |
| Release date | 3–5 April 2026 |
| Recommended variant | Universal, nodpi, Android 9+ |
| Architectures | arm64-v8a, armeabi-v7a, x86, x86_64 |

Why this version: the current [Morphe / ReVanced Extended patches source](https://github.com/anddea/revanced-patches) README explicitly lists YouTube patch compatibility from `21.13.164` down to `20.05.46`. The source includes a real Hide Ads patch, a runtime AdsPatch implementation, Litho component filters, and SponsorBlock bytecode/runtime code. This makes 21.13.164 a documented upstream baseline. It is **not** the same as saying Google's original YouTube source code is public: it is reverse-engineered patch source.

Your installed YouTube `21.46.522` is newer than this published compatibility range. We are selecting 21.13.164 to get a known, source-supported baseline rather than guessing at undocumented fingerprints.

## Download

Recommended for broad device compatibility:
- [YouTube 21.13.164 universal nodpi APK (Android 9+)](https://www.apkmirror.com/apk/google-inc/youtube/youtube-21-13-164-release/youtube-21-13-164-4-android-apk-download/)

APKMirror lists this variant as package `com.google.android.youtube`, version code `1561063732`, Android 9+, universal ABIs, nodpi, approximately 173.75 MB. Verify the signer is Google LLC and SHA-256 is `3d7a1223019aa39d9ea0e3436ab7c0896bfb4fb679f4de5fe7c23f326c8f994a` before installing.

If your device currently has a newer YouTube version, Android normally will not install this older version as an update. Back up anything important first; downgrading may require uninstalling the current YouTube updates and can remove local app data. Do not uninstall until you are ready and have confirmed you can restore your current setup.

## Upstream source research

Reference project: [anddea/revanced-patches](https://github.com/anddea/revanced-patches).

Relevant files:
- `patches/src/main/kotlin/app/morphe/patches/youtube/ads/general/AdsPatch.kt` — DEX-level patch logic for general ads, Premium promotion, player overlay ads, end-screen store banners, Shorts ads and paid-promotion labels.
- `extensions/shared/src/main/java/app/morphe/extension/youtube/patches/ads/AdsPatch.java` — runtime methods called by injected DEX hooks.
- `extensions/shared/src/main/java/app/morphe/extension/youtube/patches/components/AdsFilter.java` — feed/Litho component filters for ad and promotion layouts.
- `patches/src/main/kotlin/app/morphe/patches/youtube/utils/sponsorblock/SponsorBlockPatch.kt` — SponsorBlock integration patch.
- `patches/src/main/kotlin/app/morphe/patches/youtube/utils/sponsorblock/Fingerprints.kt` — SponsorBlock method fingerprints.

Important architecture distinction: these upstream Kotlin patches modify YouTube DEX at build time and inject a companion Java extension. Our YouTube Fusion project is a separate NPatch/libxposed runtime module. We can use upstream source as a technical reference, but cannot simply copy its Kotlin patcher classes into the Xposed module. The NPatch module needs verified runtime hooks for the exact APK.

## NPatch toolchain

The current official stable release observed on 2026-11-22 is **NPatch v1.1.0**, version code **857**:

- CLI: [jar-v1.1.0-857-release.jar](https://github.com/7723mod/NPatch/releases/download/v1.1.0/jar-v1.1.0-857-release.jar)
- Manager: [manager-v1.1.0-857-release.apk](https://github.com/7723mod/NPatch/releases/download/v1.1.0/manager-v1.1.0-857-release.apk)
- Official usage guide: https://npatch.nkbe.top/en/guide/usage

The JAR repacks an APK/APKS to inject the NPatch loader. It does not contain YouTube-specific ad-removal logic. YouTube Fusion is a separate libxposed module APK; NPatch loads it in Local mode or embeds it in Integrated mode.

## Required next input

After downloading, upload the **original YouTube 21.13.164 APK** here. If you instead download an APK bundle/APKS, upload the complete archive including base and all split APKs. Do not upload app data, account data, or a pre-patched APK.

We will inspect the exact DEX signatures and resources, then implement version-gated runtime hooks. Unknown signatures must fail closed rather than hook guessed methods.
