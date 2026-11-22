# Target build baseline

## Supplied input APK

| Field | Value |
|---|---|
| Original package | `com.google.android.youtube` |
| Version name | `21.13.164` |
| Version code | `1561063732` |
| Variant | Universal, nodpi |
| Architectures | arm64-v8a, armeabi-v7a, x86, x86_64 |
| DEX files | 7 |
| Intended output package | `youtube.fusion` |
| Intended visible name | `YouTube Fusion` |

The uploaded APK is present and readable. Its observed SHA-256 is:

```
c193daf879d36e04886c34dcc44580b1c98c20534165d306c2a04b8025fc3f46
```

This differs from the previously listed APKMirror hash, so the supplied file must be treated as its own exact input. The filename and internal version metadata identify it as 21.13.164, but do not assume it is byte-identical to the APKMirror copy.

## Package rewrite constraints

The desired `youtube.fusion` ID is the package of the patched YouTube client, not the package of the Xposed companion module. A correct package rewrite is more than changing one manifest string: the final APK must have a matching binary manifest, application/component references, authorities, permission names and any package-bound integrations. The APK will also have a new signing identity. Google sign-in, casting, Play services, updates and other package-bound features may stop working or need compatibility adjustments.

The companion module therefore keeps its own ID `io.github.fakeskyline.youtubefusion` and declares both original and intended target IDs in its scope. The current CI does not yet produce a renamed YouTube APK.

## Upstream technical reference

Morphe / ReVanced Extended is reverse-engineered patch source, not Google's original YouTube source code. Its patcher modifies DEX at build time and injects a companion runtime extension. A libxposed runtime module is a different architecture; upstream patcher classes cannot simply be copied into this project.

Relevant upstream areas to study include ad patch fingerprints, runtime ad helpers, Litho component filtering and SponsorBlock fingerprints. Only signatures verified against this uploaded APK should be hooked.

## NPatch

NPatch CLI injects its loader into an APK/APKS. It does not itself rename YouTube's package or implement ad removal. The separate YouTube Fusion module must supply runtime logic.

Official guide: https://npatch.nkbe.top/en/guide/usage
