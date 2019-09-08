package filetemp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public  class SearchTermHighlighter {

    private TreeSet<BookTitle>  input   = new TreeSet<BookTitle>();
    private TreeSet<BookTitle>  output  = new TreeSet<BookTitle>();

    public SearchTermHighlighter(String fileName) throws FileNotFoundException {
        TreeSet <BookTitle> BookTitle = new TreeSet<BookTitle>();
        Scanner scan = new Scanner(new File(fileName));

        try {

            while (scan.hasNextLine()) {
                //BookTitle n = new BookTitle(scan.hasNextLine());
        //      input.add(scan.hasNextLine());
            }

            
        } catch (Exception e) {
            System.out.println("File cannot be open");
        }finally{
            scan.close();
        }

    }

    public void parseContents(ArrayList<String> commonWords) {

        for(int i = 0; i < commonWords.size();i++){
            commonWords.add(commonWords.remove(0).toLowerCase());
            }
        
        
    }

    public ArrayList<String> getInputFileContents() {
        ArrayList<String> clone = new ArrayList<String>();

        for (int i = 0; i < input.size(); i++) {
    //      clone.add(input.get(i));
        }

        return clone;
    }

    public ArrayList<String> getOutputFileContents() {
        ArrayList<String> clone = new ArrayList<String>();

        for(Object BookTitle: clone) {
            clone.add(((BookTitle) BookTitle).getHighlightedTitle());
        }

        return clone;
    }

    public void writeFile(String path) throws FileNotFoundException {
        
        PrintWriter pw = new PrintWriter(path);
        ArrayList<String> list = getOutputFileContents();
        
        try{
            for(int i = 0; i < list.size(); i++){
                pw.println(list.get(i));
            }
        }catch(Exception e){
            System.out.println("Could not open file");
        }finally {
            pw.close();
        }
    }
}
    
    
    
    
