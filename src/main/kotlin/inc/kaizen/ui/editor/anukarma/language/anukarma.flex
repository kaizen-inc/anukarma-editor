package inc.kaizen.ui.editor.anukarma.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static inc.kaizen.ui.editor.anukarma.language.IAnukarmaLanguageFileType.*;

%%

%{
  public _AnukarmaLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _AnukarmaLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

BOOLEAN=([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee])
NUMBER=([0-9]+)
IDENTIFIER=([a-zA-Z_0-9]+)
GROUP_NAME=([a-zA-Z0-9_]+)
FILE_PATH=([a-zA-Z0-9_./\\]+.karma)
IMPLEMENTATION_CLASS=([a-z][a-zA-Z0-9_]*(\.[a-zA-Z0-9_]+)+[0-9A-Za-z_])
COMMENT=("//".*)
BLOCK_COMMENT=("/"\*{1,2} [^*/]* \*"/")
WHITESPACES=[\n\r\s]*

%%
<YYINITIAL> {
  {WHITE_SPACE}                { return WHITE_SPACE; }

  ";"                          { return EOL; }
  "="                          { return SEPARATOR; }
  "{"                          { return OPEN_BRACKET; }
  "}"                          { return CLOSE_BRACKET; }
  "("                          { return LPAR; }
  ")"                          { return RPAR; }
  "."                          { return DOT; }
  "\""                         { return QUOTE; }
  "stopOnFail"                 { return STOPONFAIL; }
  "incremental"                { return INCREMENTAL; }
  "generateCode"               { return GENERATECODE; }
  "plugins"                    { return PLUGINS; }
  "id"                         { return ID; }
  "extensions"                 { return EXTENSIONS; }
  "file"                       { return FILE; }
  "task"                       { return TASK; }
  "implementation"             { return IMPLEMENTATION; }
  "group"                      { return GROUP; }
  "strictDependency"           { return STRICTDEPENDENCY; }
  "dependsOn"                  { return DEPENDSON; }
  "feature"                    { return FEATURE; }
  "priority"                   { return PRIORITY; }

  {BOOLEAN}                    { return BOOLEAN; }
  {NUMBER}                     { return NUMBER; }
  {IDENTIFIER}                 { return IDENTIFIER; }
  {GROUP_NAME}                 { return GROUP_NAME; }
  {FILE_PATH}                  { return FILE_PATH; }
  {IMPLEMENTATION_CLASS}       { return IMPLEMENTATION_CLASS; }
  {COMMENT}                    { return COMMENT; }
  {BLOCK_COMMENT}              { return BLOCK_COMMENT; }
  {WHITESPACES}                { return WHITESPACES; }

}

[^] { return BAD_CHARACTER; }
