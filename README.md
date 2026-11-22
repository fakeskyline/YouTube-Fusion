# YouTube Fusion

An experimental NPatch / libxposed module (API 102) for a separately patched YouTube APK.

**Current state: buildable module scaffold; ad blocking and APK package rewriting are not implemented yet.** The selected input is the user's YouTube 21.13.164 APK. The intended output package ID is `youtube.fusion`, and the visible app name is **YouTube Fusion**.

## Important distinction

There are two different Android packages in this project:

- The companion Xposed module remains `io.github.fakeskyline.youtubefusion`.
- The patched YouTube client is intended to become `youtube.fusion`.

The NPatch module is scoped for both the original and intended renamed package. Merely changing the scope does not rewrite the YouTube APK. Package rewriting must update the binary manifest, package references, authorities, permissions and any package-bound integrations; it also changes app-signature identity and may affect Google sign-in, casting, Play services and updates. The current workflow builds the module APK/AAB and downloads the NPatch CLI; it does not yet emit a renamed YouTube APK.

## Build outputs

- **Module APK** — install and enable in NPatch Manager.
- **AAB** — optional app bundle; not installable directly.
- **NPatch CLI JAR** — separate APK/APKS loader-injection tool, not the ad blocker.

## Build

Requires JDK 17 and Android SDK Platform 36.

```bash
gradle assembleRelease bundleRelease
```

Artifacts are generated under `app/build/outputs/`.

## Current verification

The module logs when either `com.google.android.youtube` or `youtube.fusion` is loaded. It currently installs no ad-removal hooks. Do not treat a successful module build or a log entry as proof that ads are blocked.

## Target

| Component | Value |
|---|---|
| Input YouTube version | `21.13.164` |
| Input package | `com.google.android.youtube` |
| Intended patched package | `youtube.fusion` |
| Intended visible name | `YouTube Fusion` |
| Module package | `io.github.fakeskyline.youtubefusion` |
| Framework | NPatch / Vector |
| libxposed API | 102 |
| Minimum Android | 9 (API 28) |

## Planned implementation

1. Inspect exact DEX fingerprints and resource IDs from the supplied 21.13.164 APK.
2. Implement and test version-gated hooks for player ads, feed/search ads, Shorts promotions and Premium upsells.
3. Add SponsorBlock as a separate optional feature.
4. Implement and validate the YouTube APK package/name rewrite.
5. Build and verify the final patched APK, not just the companion module.

Unknown method signatures must fail closed rather than hook guessed methods.

Independent community project; not affiliated with Google or YouTube.
