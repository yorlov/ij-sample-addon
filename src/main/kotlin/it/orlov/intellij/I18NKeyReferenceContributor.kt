package it.orlov.intellij

import com.intellij.lang.javascript.psi.JSCallExpression
import com.intellij.lang.javascript.psi.JSLiteralExpression
import com.intellij.lang.javascript.psi.JSReferenceExpression
import com.intellij.patterns.StandardPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.util.ProcessingContext

class I18NKeyReferenceContributor : PsiReferenceContributor() {

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        val methodName = psiElement<JSReferenceExpression>().withText(StandardPatterns.string().contains("I18n.getText"))
        val call = psiElement<JSCallExpression>().withChild(methodName)
        val i18nKey = psiElement<JSLiteralExpression>().withSuperParent(2, call)

        registrar.registerReferenceProvider(i18nKey, object : PsiReferenceProvider() {
            override fun getReferencesByElement(key: PsiElement, context: ProcessingContext) = arrayOf(I18NKeyReference(key))
        })
    }
}