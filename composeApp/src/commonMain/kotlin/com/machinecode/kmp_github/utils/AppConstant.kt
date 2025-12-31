package com.machinecode.kmp_github.utils

import androidx.compose.ui.graphics.Color

//@SuppressLint("DefaultLocale")
fun formatCountDynamic(count: Int): String {
    return when {
        count >= 1_000_000_000 -> formatDecimal(count / 1_000_000_000.0) + "B"
        count >= 1_000_000 -> formatDecimal(count / 1_000_000.0) + "M"
        count >= 1_000 -> formatDecimal(count / 1_000.0) + "k"
        else -> count.toString()
    }
}

private fun formatDecimal(value: Double): String {
    val rounded = (value * 10).toInt() / 10.0
    return if (rounded % 1.0 == 0.0) {
        rounded.toInt().toString()
    } else {
        rounded.toString()
    }
}

private val languageColorMap = mapOf(
    "Kotlin" to Color(0xFFA97BFF),
    "Java" to Color(0xFFB07219),
    "JavaScript" to Color(0xFFF1E05A),
    "TypeScript" to Color(0xFF2B7489),
    "Python" to Color(0xFF3572A5),
    "C++" to Color(0xFFF34B7D),
    "C" to Color(0xFF555555),
    "C#" to Color(0xFF178600),
    "Go" to Color(0xFF00ADD8),
    "Swift" to Color(0xFFFFAC45),
    "PHP" to Color(0xFF4F5D95),
    "Ruby" to Color(0xFF701516),
    "Dart" to Color(0xFF00B4AB),
    "Shell" to Color(0xFF89E051),
    "Rust" to Color(0xFFDEA584),
    "Scala" to Color(0xFFDC322F),
    "HTML" to Color(0xFFE34C26),
    "CSS" to Color(0xFF563D7C),
    "Objective-C" to Color(0xFF438EFF),
    "Perl" to Color(0xFF0298C3),
    "Haskell" to Color(0xFF5E5086),
    "Elixir" to Color(0xFF6E4A7E),
    "Lua" to Color(0xFF000080),
    "R" to Color(0xFF198CE7),
    "Visual Basic" to Color(0xFF945DB7),
    "CoffeeScript" to Color(0xFF244776),
    "PowerShell" to Color(0xFF012456),
    "TeX" to Color(0xFF3D6117),
    "Vim script" to Color(0xFF199F4B),
    "Groovy" to Color(0xFF4298B8),
    "OCaml" to Color(0xFF3BE133),
    "Matlab" to Color(0xFFE16737),
    "Assembly" to Color(0xFF6E4C13),
    "Erlang" to Color(0xFFB83998),
    "D" to Color(0xFFBA595E),
    "F#" to Color(0xFFB845FC),
    "Julia" to Color(0xFFA270BA),
    "Fortran" to Color(0xFF4D41B1),
    "V" to Color(0xFF5D87BF),
    "Nim" to Color(0xFFFFE953),
    "Crystal" to Color(0xFF000100),
    "Pascal" to Color(0xFFE3F171),
    "COBOL" to Color(0xFF9A7611),
    "Common Lisp" to Color(0xFF3FB68B),
    "Scheme" to Color(0xFF1E4AEC),
    "Smalltalk" to Color(0xFF596706),
    "Prolog" to Color(0xFF74283C),
    "Haxe" to Color(0xFFDF7900),
    "Elm" to Color(0xFF60B5CC),
    "Racket" to Color(0xFF22228F),
    "Vala" to Color(0xFFFBE5CD),
    "Ada" to Color(0xFF02F88C),
    "Makefile" to Color(0xFF427819),
    "Clojure" to Color(0xFFDB5855),
    "Tcl" to Color(0xFFE4CC98),
    "Puppet" to Color(0xFF302B6D),
    "ActionScript" to Color(0xFF882B0F),
    "QML" to Color(0xFF44A51C),
    "HCL" to Color(0xFF844FBA),
    "Nix" to Color(0xFF7E7EFF),
    "Verilog" to Color(0xFFB2B7F8),
    "VHDL" to Color(0xFFADB2CB),
    "Dockerfile" to Color(0xFF384D54),
    "YAML" to Color(0xFFCB171E),
    "JSON" to Color(0xFF292929),
    "GraphQL" to Color(0xFFE10098),
    "Markdown" to Color(0xFF083FA1),
    "INI" to Color(0xFFD1D1D1),
    "ApacheConf" to Color(0xFFD12127),
    "Batchfile" to Color(0xFFC1F12E),
    "PowerBuilder" to Color(0xFF8F0F8D),
    "Visual Basic .NET" to Color(0xFF945DB7),
    "XSLT" to Color(0xFFEB8CEB),
    "ASP.NET" to Color(0xFF9400FF)
)

fun getLanguageColor(language: String): Color {
    return languageColorMap[language] ?: Color(0xFFD1D1D1)
}