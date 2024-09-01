package com.example.collegeapp.ui.ebook;

public class EbookData {

    private String PDFTitle, pdfUrl;

    public EbookData() {
    }

    public EbookData(String PDFTitle, String pdfUrl) {
        this.PDFTitle = PDFTitle;
        this.pdfUrl = pdfUrl;
    }

    public String getPDFTitle() {
        return PDFTitle;
    }

    public void setPDFTitle(String PDFTitle) {
        this.PDFTitle = PDFTitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
