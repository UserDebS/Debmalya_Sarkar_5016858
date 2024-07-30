public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        Test test = new Test();
        test.createDiffDocument();
    }
}


interface WordDocument {
    void display();
}

interface PdfDocument {
    void display();
}

interface ExcelDocument {
    void display();
}

class ConcreteWordDocument implements WordDocument{
    public void display() {
        System.out.println("This is a word file");
    }
}

class ConcretePdfDocument implements PdfDocument{
    public void display() {
        System.out.println("This is a pdf file");
    }
}

class ConcreteExcelDocument implements ExcelDocument{
    public void display() {
        System.out.println("This is an excel file");
    }
}

abstract class DocumentFactory {
    public abstract void createDocument();
}

class ConcreateWordFactory extends DocumentFactory {
    @Override
    public void createDocument() {
        WordDocument wordDocument = new ConcreteWordDocument();
        wordDocument.display();
    }
}

class ConcreatePdfFactory extends DocumentFactory {
    @Override
    public void createDocument() {
        PdfDocument pdfcDocument = new ConcretePdfDocument();
        pdfcDocument.display();
    }
}

class ConcreateExcelFactory extends DocumentFactory {
    @Override
    public void createDocument() {
        ExcelDocument excelDocument = new ConcreteExcelDocument();
        excelDocument.display();
    }
}

class Test{
    void createDiffDocument() {
        DocumentFactory wFactory = new ConcreateWordFactory();
        DocumentFactory pFactory = new ConcreatePdfFactory();
        DocumentFactory eFactory = new ConcreateExcelFactory();
        wFactory.createDocument();
        pFactory.createDocument();
        eFactory.createDocument();
    }
}