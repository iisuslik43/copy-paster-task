package com.hse.plugin;

import com.intellij.codeInsight.editorActions.CopyPastePreProcessor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.RawText;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.DefaultEditorKit;
import java.awt.event.InputEvent;

/**
 * The class that shows the special message on ctrl v
 */
public class PasteHandler implements CopyPastePreProcessor {

    @Nullable
    @Override
    public String preprocessOnCopy(PsiFile psiFile, int[] ints, int[] ints1, String s) {
        return null;
    }

    @NotNull
    @Override
    public String preprocessOnPaste(Project project, PsiFile psiFile, Editor editor, String s, RawText rawText) {
        Messages.showInfoMessage("Копипаста — зло!","Copy Paster");
        return s;
    }
}
