package com.brbw.codemash;

import android.test.AndroidTestCase;

public class CodeMashTestCase extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());
    }
}
