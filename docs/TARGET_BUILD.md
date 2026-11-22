# Target build baseline

Target: official YouTube Android package.

| Field | Value |
|---|---|
| Package | `com.google.android.youtube` |
| Version name | `21.46.522` |
| Version code | `1561314006` |
| First observed upload | 2026-11-17 UTC |
| Universal variant | nodpi, Android 10+ (API 29+) |
| Architectures in universal variant | arm64-v8a, armeabi-v7a, x86, x86_64 |

The public APK catalogue lists this version as a 205.37 MB universal APK. Split variants also exist, so a device-installed copy may consist of a base APK plus configuration splits. Hook compatibility must be checked against the actual installed artifact, not inferred from the version string alone.

Reference: [YouTube 21.46.522 package listing](https://www.apkmirror.com/apk/google-inc/youtube/youtube-21-46-522-release/).

## NPatch toolchain

The current official stable release observed on 2026-11-22 is **NPatch v1.1.0**, version code **857**:

- CLI: [jar-v1.1.0-857-release.jar](https://github.com/7723mod/NPatch/releases/download/v1.1.0/jar-v1.1.0-857-release.jar)
- Manager: [manager-v1.1.0-857-release.apk](https://github.com/7723mod/NPatch/releases/download/v1.1.0/manager-v1.1.0-857-release.apk)
- Official usage guide: https://npatch.nkbe.top/en/guide/usage

The JAR is the NPatch command-line app repacker. It does not contain YouTube-specific ad-removal logic. YouTube Fusion is a separate libxposed module APK; NPatch can load it in Local mode or embed it in Integrated mode.

The workflow downloads the latest official NPatch CLI JAR at build time and publishes it alongside the APK/AAB as a CI artifact. The binary is intentionally not committed to source control.

## Required test input

Before implementing ad-removal hooks, provide the exact installed YouTube 21.46.522 base APK and all split APKs (if installed as a split package), or an APK/APKS export from a trusted package backup tool. Do not upload account data or other app data.

We will inspect DEX class/method signatures, resource IDs and package metadata, then write version-gated hooks. No guessed class names or placeholder hooks will be treated as a working ad blocker.
