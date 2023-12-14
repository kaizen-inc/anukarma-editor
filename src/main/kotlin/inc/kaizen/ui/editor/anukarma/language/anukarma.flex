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
NUMBER=[0-9]+
IDENTIFIER=[a-zA-Z_0-9]+
COMMENT=;.*
WHITESPACES=[\n\r\s]*

%%
<YYINITIAL> {
  {WHITE_SPACE}       { return WHITE_SPACE; }

  ";"                 { return EOL; }
  "="                 { return SEPARATOR; }
  "{"                 { return OPEN_BRACKET; }
  "}"                 { return CLOSE_BRACKET; }
  "("                 { return LPAR; }
  ")"                 { return RPAR; }
  "."                 { return DOT; }
  "\""                { return QUOTE; }
  "stopOnFail"        { return STOPONFAIL; }
  "incremental"       { return INCREMENTAL; }
  "generateCode"      { return GENERATECODE; }
  "plugins"           { return PLUGINS; }
  "id"                { return ID; }

  {BOOLEAN}           { return BOOLEAN; }
  {NUMBER}            { return NUMBER; }
  {IDENTIFIER}        { return IDENTIFIER; }
  {COMMENT}           { return COMMENT; }
  {WHITESPACES}       { return WHITESPACES; }

}

[^] { return BAD_CHARACTER; }
