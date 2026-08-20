package com.jobfitshield.backend.resume;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PdfTextExtractor {

    public String extractText(byte[] fileBytes) {

        try (PDDocument document = Loader.loadPDF(fileBytes)) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document).trim();

        } catch (IOException exception) {

            throw new IllegalArgumentException(
                    "Could not read PDF file",
                    exception
            );
        }
    }
}