package io.github.fakeskyline.youtubefusion;

import android.util.Log;

import io.github.libxposed.api.XposedModule;

public final class ModuleMain extends XposedModule {
    private static final String TAG = "YouTubeFusion";
    private static final String TARGET_PACKAGE = "com.google.android.youtube";

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
        if (!TARGET_PACKAGE.equals(param.getPackageName())) {
            return;
        }

        log(Log.INFO, TAG, "YouTube process attached: "
                + param.getProcessName()
                + "; classLoader=" + param.getDefaultClassLoader());

        // Ad-removal hooks will be installed here only after the exact
        // YouTube 21.46.522 DEX signatures have been inspected and verified.
    }
}
