package it.orlov.intellij

import com.intellij.lang.properties.psi.PropertyKeyIndex
import com.intellij.openapi.util.text.StringUtil.unquoteString
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.search.ProjectScope

class I18NKeyReference(i18nKey: PsiElement) : PsiReferenceBase.Poly<PsiElement>(i18nKey, true) {

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = element.project
        val searchScope = ProjectScope.getAllScope(project)
        val index = PropertyKeyIndex.getInstance()
        return PsiElementResolveResult.createResults(index.get(unquoteString(element.text), project, searchScope))
    }

    override fun getVariants() = emptyArray<Any>()
}