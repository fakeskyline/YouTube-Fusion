# YouTube Fusion

Direct APK modification project based on the user's YouTube 21.13.164 universal APK.

## Target
- App label: `YouTube Fusion`
- Android applicationId / manifest package: `Fusion.YouTube`
- Input: APK attached to this repository's GitHub Release tag `base`
- Output: signed APK in the GitHub Actions artifact

## Pipeline
1. Download the exact APK from release `base`.
2. Decompile with JADX for readable Java/source analysis.
3. Decode with Apktool for editable manifest, resources and Smali.
4. Check Morphe patch compatibility against the exact YouTube version.
5. Apply Hide ads and Custom branding only when explicitly compatible.
6. Rewrite manifest package and app label, rebuild, align, sign and verify.
7. Upload APK, JADX export and Apktool project as artifacts.

JADX is for analysis; it does not compile Java back into DEX. Smali/Apktool and the bytecode patcher are used for APK modification.

## Safety checks
The workflow must fail if Hide ads is incompatible or if zero patches were applied. It must not publish an unchanged APK as ad-free. Changing package identity can affect Google sign-in, casting, notifications, deep links and Play services.

Run **Actions → Build YouTube Fusion → Run workflow**.
