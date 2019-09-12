# Anti-copy-paster task

## AST shower

For this part of the task I used Actions mechanism of Intellij.
With this API it's easy to create a button in the tool bar and set on press
handler to it that will print the AST. In addition, this button hides
if the user haven't selected any code.
 
 For drawing AST I find 
the first parent of selected code in AST that contains all this text, and after that
I print all the children of this parent, their children and so on.

## Paste hater

I implemented `CopyPastePreProcessor` interface that allows you to do something
on copy or on paste. Than I register my `PasteHandler` class in xml file in the 
`extentions` section.