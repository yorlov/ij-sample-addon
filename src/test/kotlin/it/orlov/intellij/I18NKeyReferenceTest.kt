package it.orlov.intellij

import com.intellij.lang.properties.psi.Property
import com.intellij.openapi.util.io.FileUtil.toSystemIndependentName
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.io.File

class I18NKeyReferenceTest : BasePlatformTestCase() {

    fun testEs6() {
        myFixture.configureByFiles("es6.js", "messages.properties")
        val element = myFixture.file.findElementAt(myFixture.caretOffset)!!.parent
        assertEquals("hello", (element.references[0].resolve() as Property).value)
    }

    override fun getTestDataPath() = toSystemIndependentName(File("src/test/resources/i18n").absolutePath)
}