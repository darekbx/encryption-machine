package com.darekbx.encryptionmachine.printing

import android.content.Context
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.pdf.PrintedPdfDocument
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import java.io.FileOutputStream
import java.io.IOException

class PlainTextPrintDocumentAdapter(
    val context: Context,
    val text: String
) : PrintDocumentAdapter() {

    private var pdfDocument: PrintedPdfDocument? = null

    override fun onLayout(
        oldAttributes: PrintAttributes,
        newAttributes: PrintAttributes,
        cancellationSignal: CancellationSignal,
        callback: LayoutResultCallback,
        extras: Bundle
    ) {

        pdfDocument = PrintedPdfDocument(context, newAttributes)

        if (cancellationSignal.isCanceled == true) {
            callback.onLayoutCancelled()
            return
        }

        PrintDocumentInfo.Builder("print_output.pdf")
            .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
            .setPageCount(1)
            .build()
            .also { info ->
                callback.onLayoutFinished(info, true)
            }
    }

    override fun onWrite(
        pages: Array<out PageRange>,
        destination: ParcelFileDescriptor,
        cancellationSignal: CancellationSignal,
        callback: WriteResultCallback
    ) {
        try {
            pdfDocument?.run {
                val page: PdfDocument.Page = startPage(0)
                drawPage(page)
                finishPage(page)
                writeTo(FileOutputStream(destination.fileDescriptor))
            }
        } catch (e: IOException) {
            callback.onWriteFailed(e.toString())
            return
        } finally {
            pdfDocument?.close()
            pdfDocument = null
        }

        callback.onWriteFinished(pages)
    }

    private fun drawPage(page: PdfDocument.Page) {
        val padding = 64F
        val topMargin = 64F
        val leftMargin = 48F

        val textPaint = TextPaint().apply {
            color = Color.BLACK
            textSize = 14F
            isAntiAlias = true
        }

        val staticLayout = StaticLayout.Builder
            .obtain(text, 0, text.length, textPaint, (page.canvas.width - 2 * padding).toInt())
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .build()

        page.canvas.translate(leftMargin, topMargin)
        staticLayout.draw(page.canvas)
    }
}