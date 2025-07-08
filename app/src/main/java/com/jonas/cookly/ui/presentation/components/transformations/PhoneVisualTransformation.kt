package com.jonas.cookly.ui.presentation.components.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Formato esperado:
 * - Ao digitar: `11987654321`
 * - Exibição: `(11) 98765-4321`
 */

class PhoneVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val phoneMask = text.text.mapIndexed { index, c ->
            when (index) {
                1, 2 -> "$c "
                6 -> "$c-"
                else -> "$c"
            }
        }.joinToString(separator = "")

        return TransformedText(
            AnnotatedString(phoneMask),
            PhoneOffSetMapping
        )
    }

    object PhoneOffSetMapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset >= 7 -> offset + 3
                offset >= 3 -> offset + 2
                offset >= 2 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset >= 10 -> offset - 3
                offset >= 5 -> offset - 2
                offset >= 3 -> offset - 1
                else -> offset
            }
        }
    }
}