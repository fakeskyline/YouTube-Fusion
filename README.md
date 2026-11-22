# YouTube Fusion

An experimental Xposed module for the official YouTube Android app, designed for NPatch / Vector (libxposed API 102).

**Current state: project scaffold only. Ad blocking is not implemented yet.** The first target is YouTube `21.46.522` (`com.google.android.youtube`). No YouTube APK is bundled or redistributed.

## Build outputs

- **APK** — installable Xposed module. This is the file to install and enable in NPatch Manager.
- **AAB** — optional Android App Bundle for publishing the companion/module app; it is not installable directly and is not a patch.
- **NPatch JAR** — the separate NPatch command-line patching tool. CI downloads the current official release as a build artifact; it is not a module and is not embedded in the app.
- **AAR** — optional library dependency, only needed if a module settings UI uses NPatch Remote API.

## Build

Requires JDK 17 and Android SDK Platform 36.

```bash
gradle assembleRelease bundleRelease
```

Artifacts are generated under `app/build/outputs/`.

## Install / test

1. Install the generated module APK.
2. In NPatch Manager, enable **YouTube Fusion** and set YouTube as its scope.
3. Force-stop YouTube and launch it again.
4. Inspect NPatch/Xposed logs for the `YouTubeFusion` tag.

The current build only verifies that the module is loaded into the target process. It does not remove ads yet.

## Planned implementation

1. Extract and inspect the user's exact YouTube 21.46.522 APK/split set; record DEX signatures and build fingerprint.
2. Implement version-gated hooks for video ads, feed/search ads, sponsored Shorts and Premium upsells.
3. Add SponsorBlock segment skipping as a separate opt-in feature (community-submitted segments are not the same thing as YouTube's own ads).
4. Add settings, diagnostics and regression checks. Unknown signatures must fail closed rather than hook guessed methods.

## Compatibility

| Component | Target |
|---|---|
| YouTube package | `com.google.android.youtube` |
| YouTube version | `21.46.522` |
| Module API | libxposed 102 |
| Framework | NPatch / Vector |
| Minimum Android | 9 (API 28) |

Compatibility is not certified until the hooks have been tested on the exact APK and device.

## Disclaimer

Independent community project. Not affiliated with Google or YouTube. Use at your own risk.
