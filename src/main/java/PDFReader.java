import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.*;

/**
 * Created by govind.bhone on 6/5/2017.
 */

class SearchTermInformation {
    private int pageNm;
    private int lineNumer;
    private String nextWords;

    public SearchTermInformation(int pageNm, int lineNumber, String nextWords) {
        this.pageNm = pageNm;
        this.lineNumer = lineNumber;
        this.nextWords = nextWords;
    }

    @Override
    public String toString() {
        return "[ Page Nm:" + pageNm + " , Line Number:" + lineNumer + " , nextWords:" + nextWords + " ]";
    }
}

public class PDFReader {

    public static final String SEARCH_TERM="I give special thanks to Doug Lea";
    public static final String FILENAME ="C:\\Users\\govind.bhone\\Desktop\\Effective Java.pdf";

    public static SearchTermInformation CheckSearchTermInEachPage(int pageNr, String page, String term) {
        String[] lines = page.split("\n");
        SearchTermInformation status = null;

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].indexOf(term) != -1) {
                status = new SearchTermInformation(pageNr, i, lines[i].substring(lines[i].indexOf(term)+term.length(), lines[i].length()));
                break;
            }
        }
        return status;
    }

    public static SearchTermInformation searchAllPagesForSearchTerm(PdfReader pdfreader, String term) throws IOException {
        SearchTermInformation status = null;
        for (int i = 1; i <= pdfreader.getNumberOfPages(); i++) {
            status = CheckSearchTermInEachPage(i, PdfTextExtractor.getTextFromPage(pdfreader, i), term);
            if (status != null) {
                break;
            }
        }
        return status;
    }

    public static void main(String[] args) throws IOException {

        PdfReader reader = new PdfReader(FILENAME);
        System.out.println(searchAllPagesForSearchTerm(reader, SEARCH_TERM));
    }

}
