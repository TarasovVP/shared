<<<<<<<< HEAD:app/src/androidTest/java/com/vnstudio/cleanarchitecturedemo/ExampleInstrumentedTest.kt
package com.vnstudio.cleanarchitecturedemo
========
package com.vnteam.architecturetemplates
>>>>>>>> 49c2f929 (Init branch):app/src/androidTest/java/com/vnteam/architecturetemplates/ExampleInstrumentedTest.kt

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.vnstudio.architecturetemplates", appContext.packageName)
    }
}