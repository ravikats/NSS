/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.staging.service.PDFService
 *  com.lowagie.text.Chunk
 *  com.lowagie.text.Font
 *  com.lowagie.text.HeaderFooter
 *  com.lowagie.text.Image
 *  com.lowagie.text.Paragraph
 *  com.lowagie.text.Phrase
 *  com.lowagie.text.pdf.PdfPTable
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Service
 */
package com.empay.staging.service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PDFService {
    private final Environment env;

    public HeaderFooter setHeader(int institutionCode, String sharingPartner) {
        HeaderFooter header = null;
        try {
            String clientUrl = this.env.getProperty("RECON_IMAGE") + "client" + institutionCode + sharingPartner + ".png";
            Image clientImage = Image.getInstance((String)clientUrl);
            clientImage.setAlignment(2);
            clientImage.scaleAbsoluteHeight(20.0f);
            clientImage.scaleAbsoluteWidth(20.0f);
            clientImage.scalePercent(80.0f);
            Chunk chunk1 = new Chunk(clientImage, 0.0f, 0.0f);
            header = new HeaderFooter(new Phrase(chunk1), false);
            header.setAlignment(0);
            header.setBorder(2);
        }
        catch (Exception e) {
            header = null;
        }
        return header;
    }

    public HeaderFooter setFooter(int institutionCode) {
        HeaderFooter footer = null;
        try {
            String providerUrl = this.env.getProperty("RECON_IMAGE") + "provider.png";
            Image providerImage = Image.getInstance((String)providerUrl);
            providerImage.setAlignment(2);
            providerImage.scaleAbsoluteHeight(10.0f);
            providerImage.scaleAbsoluteWidth(10.0f);
            providerImage.scalePercent(80.0f);
            Chunk chunk = new Chunk(providerImage, 0.0f, -45.0f);
            footer = new HeaderFooter(new Phrase(chunk), false);
            footer.setAlignment(2);
            footer.setBorder(0);
        }
        catch (Exception e) {
            footer = null;
        }
        return footer;
    }

    public PdfPTable defineHeadImageTable(int column) {
        PdfPTable pdfPTable = new PdfPTable(column);
        try {
            pdfPTable.setHorizontalAlignment(1);
            pdfPTable.setSpacingBefore(1.0f);
            pdfPTable.setSpacingAfter(0.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            if (column == 2) {
                pdfPTable.setWidths(new float[]{80.0f, 280.0f});
            } else if (column == 3) {
                pdfPTable.setWidths(new float[]{80.0f, 200.0f, 80.0f});
            }
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public PdfPTable defineHeadImageTableTest() {
        PdfPTable pdfPTable = new PdfPTable(3);
        try {
            pdfPTable.setHorizontalAlignment(1);
            pdfPTable.setSpacingBefore(1.0f);
            pdfPTable.setSpacingAfter(5.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            pdfPTable.setWidths(new float[]{30.0f, 70.0f, 60.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public void WriteHeaderImage(PdfPTable pdfPTable, Image image1, String str1, Image image2) {
        pdfPTable.getDefaultCell().setBorder(0);
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(image1);
        pdfPTable.getDefaultCell().setHorizontalAlignment(1);
        pdfPTable.addCell(new Phrase(str1));
        pdfPTable.getDefaultCell().setHorizontalAlignment(2);
        pdfPTable.addCell(image2);
    }

    public PdfPTable WriteHeaderImage(PdfPTable pdfPTable, String img1, String str1, String img2) {
        if (img1 != null) {
            try {
                pdfPTable.getDefaultCell().setBorder(0);
                pdfPTable.getDefaultCell().setHorizontalAlignment(0);
                String clientUrl1 = this.env.getProperty("RECON_IMAGE") + img1;
                Image image1 = Image.getInstance((String)clientUrl1);
                image1.setAlignment(2);
                pdfPTable.addCell(image1);
            }
            catch (Exception e) {
                pdfPTable.addCell("");
            }
        } else {
            pdfPTable.addCell("");
        }
        if (str1 != null) {
            Font f1 = new Font(2, 16.0f, 1);
            f1.setColor(0, 0, 0);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.getDefaultCell().setHorizontalAlignment(1);
            pdfPTable.getDefaultCell().setPaddingTop(7.0f);
            pdfPTable.getDefaultCell().setBackgroundColor(new Color(192, 192, 192));
            pdfPTable.addCell(new Phrase(str1, f1));
        } else {
            pdfPTable.addCell("");
        }
        if (img2 != null) {
            try {
                String clientUrl2 = this.env.getProperty("RECON_IMAGE") + img2;
                Image image2 = Image.getInstance((String)clientUrl2);
                image2.setAlignment(2);
                image2.scaleAbsoluteHeight(1.0f);
                image2.scaleAbsoluteWidth(1.0f);
                image2.scalePercent(1.0f);
                pdfPTable.getDefaultCell().setHorizontalAlignment(2);
                pdfPTable.addCell(image2);
            }
            catch (Exception e) {
                pdfPTable.addCell("");
            }
        }
        return pdfPTable;
    }

    public PdfPTable defineFooterTable() {
        PdfPTable pdfPTable = new PdfPTable(1);
        try {
            pdfPTable.setHorizontalAlignment(1);
            pdfPTable.setSpacingBefore(1.0f);
            pdfPTable.setSpacingAfter(0.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            pdfPTable.setWidths(new float[]{100.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public void WriteHeaderImageTest(PdfPTable pdfPTable, Image image1, String str1, String str2, Font font) {
        pdfPTable.getDefaultCell().setBorder(0);
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(image1);
        pdfPTable.getDefaultCell().setHorizontalAlignment(1);
        pdfPTable.addCell(new Phrase(str1, font));
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str2, font));
    }

    public PdfPTable defineHeadTable() {
        PdfPTable pdfPTable = new PdfPTable(3);
        try {
            pdfPTable.setHorizontalAlignment(0);
            pdfPTable.setSpacingBefore(20.0f);
            pdfPTable.setSpacingAfter(10.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(90.0f);
            pdfPTable.setWidths(new float[]{65.0f, 25.0f, 25.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public PdfPTable defineStatementHeadTable() {
        PdfPTable pdfPTable = new PdfPTable(3);
        try {
            pdfPTable.setHorizontalAlignment(1);
            pdfPTable.setSpacingBefore(0.0f);
            pdfPTable.setSpacingAfter(0.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            pdfPTable.setWidths(new float[]{50.0f, 20.0f, 40.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public PdfPTable defineStatementNoteTable() {
        PdfPTable pdfPTable = new PdfPTable(2);
        try {
            pdfPTable.setHorizontalAlignment(1);
            pdfPTable.setSpacingBefore(0.0f);
            pdfPTable.setSpacingAfter(0.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            pdfPTable.setWidths(new float[]{4.0f, 110.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public PdfPTable defineStatementHeadTableTest() {
        PdfPTable pdfPTable = new PdfPTable(2);
        try {
            pdfPTable.setHorizontalAlignment(1);
            pdfPTable.setSpacingBefore(0.0f);
            pdfPTable.setSpacingAfter(0.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            pdfPTable.setWidths(new float[]{20.0f, 70.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public PdfPTable defineStatementTable() {
        PdfPTable pdfPTable = new PdfPTable(5);
        try {
            pdfPTable.setHorizontalAlignment(0);
            pdfPTable.setSpacingBefore(5.0f);
            pdfPTable.setSpacingAfter(2.0f);
            pdfPTable.getDefaultCell().setBorder(0);
            pdfPTable.setWidthPercentage(100.0f);
            pdfPTable.setWidths(new float[]{95.0f, 50.0f, 60.0f, 30.0f, 30.0f});
        }
        catch (Exception e) {
            pdfPTable = null;
        }
        return pdfPTable;
    }

    public void WriteHeadUnderLine(PdfPTable pdfPTable, String textToWrite, Font font) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(textToWrite, font));
        pdfPTable.addCell(new Phrase(Chunk.NEWLINE));
        pdfPTable.addCell(new Phrase(Chunk.NEWLINE));
    }

    public void WriteText(PdfPTable pdfPTable, String textToWrite, String count, String amount, Font font) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(textToWrite, font));
        pdfPTable.getDefaultCell().setHorizontalAlignment(2);
        pdfPTable.addCell(new Phrase(count, font));
        pdfPTable.getDefaultCell().setHorizontalAlignment(2);
        pdfPTable.addCell(new Phrase(amount, font));
    }

    public void WriteStatementText(PdfPTable pdfPTable, String str1, String str2, String str3, String str4, String str5, Font font) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str1, font));
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str2, font));
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str3, font));
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str4, font));
        pdfPTable.getDefaultCell().setGrayFill(1.0f);
        pdfPTable.getDefaultCell().setHorizontalAlignment(2);
        pdfPTable.addCell(new Phrase(str5, font));
    }

    public void WriteStatementTextTest(PdfPTable pdfPTable, String str1, String str2, Font font1, Font font2) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str1, font1));
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(str2, font2));
    }

    public Paragraph writeParagraphLeft(String textToWrite, Font font) {
        Paragraph paragraph = new Paragraph(textToWrite, font);
        paragraph.setAlignment(0);
        return paragraph;
    }

    public Paragraph writeParagraphCenter(String textToWrite, Font font) {
        Paragraph paragraph = new Paragraph(textToWrite, font);
        paragraph.setAlignment(1);
        return paragraph;
    }

    public void AddLeftCell(PdfPTable pdfPTable, String textToWrite, Font font) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(0);
        pdfPTable.addCell(new Phrase(textToWrite, font));
    }

    public void AddRightCell(PdfPTable pdfPTable, String textToWrite, Font font) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(2);
        pdfPTable.addCell(new Phrase(textToWrite, font));
    }

    public void AddCenterCell(PdfPTable pdfPTable, String textToWrite, Font font) {
        pdfPTable.getDefaultCell().setHorizontalAlignment(1);
        pdfPTable.addCell(new Phrase(textToWrite, font));
    }

    public PDFService(Environment env) {
        this.env = env;
    }
}

