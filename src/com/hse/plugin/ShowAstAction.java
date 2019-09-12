package com.hse.plugin;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.TokenSet;

import java.util.Arrays;

/**
 * The action that shows AST of the selected code
 */
public class ShowAstAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final PsiFile psi = e.getRequiredData(CommonDataKeys.PSI_FILE);

        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        int start = primaryCaret.getSelectionStart();
        int end = primaryCaret.getSelectionEnd();
        TextRange selectedRange = TextRange.create(start, end);

        PsiElement startElement = psi.findElementAt(start);
        if (startElement == null) {
            Messages.showInfoMessage("PSI element in the start of the caret doesn't exists",
                    "Copy Paster");
            return;
        }

        // Finding the first PSI element that contains all the range
        PsiElement curElement = startElement;
        while (!curElement.getTextRange().contains(selectedRange)) {
            curElement = curElement.getParent();
        }

        // Showing the message
        String astStructure = beautifulStringAST(curElement.getNode(), "");
        String message = "AST of the selected code:\n" + astStructure;
        Messages.showInfoMessage(message, "Copy Paster");
    }


    private String beautifulStringAST(ASTNode node, String depth) {
        StringBuilder builder = new StringBuilder();
        String elementText = node.toString();
        builder.append(depth).append(elementText).append('\n');

        for (ASTNode child : node.getChildren(TokenSet.ANY)) {
            builder.append(beautifulStringAST(child, depth + "--"));
        }
        return builder.toString();
    }

    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);

        //Set visibility only in case of existing project and editor and if a selection exists
        e.getPresentation().setEnabledAndVisible(project != null
                && editor != null
                && editor.getSelectionModel().hasSelection());
    }
}
