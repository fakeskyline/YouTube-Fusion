package io.github.fakeskyline.youtubefusion;

import android.util.Log;

import io.github.libxposed.api.XposedModule;

public final class ModuleMain extends XposedModule {
    private static final String TAG = "YouTubeFusion";
    private static final String ORIGINAL_PACKAGE = "com.google.android.youtube";
    private static final String FUSION_PACKAGE = "youtube.fusion";

    @Override
    public void onModuleLoaded(ModuleLoadedParam param) {
        log(Log.INFO, TAG, "Module loaded. Framework="
                + getFrameworkName()
                + ", versionCode=" + getFrameworkVersionCode()
                + ", API=" + getApiVersion()
                + ", process=" + param.getProcessName());
    }

    @Override
    public void onPackageLoaded(PackageLoadedParam param) {
        String packageName = param.getPackageName();
        if (!ORIGINAL_PACKAGE.equals(packageName) && !FUSION_PACKAGE.equals(packageName)) {
            return;
        }

        log(Log.INFO, TAG, "Target package attached: " + packageName
                + "; firstPackage=" + param.isFirstPackage()
                + "; classLoader=" + param.getDefaultClassLoader());

        // Version-specific ad hooks are installed only after their DEX
        // fingerprints have been verified for the exact target APK.
    }
}
