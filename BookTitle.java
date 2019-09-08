package filetemp;
import java.util.ArrayList;

public  class BookTitle implements Comparable<BookTitle> {

private String rawTitle;
private String highlightedTitle;
private String searchTerm;

public BookTitle(String rawTitle, String searchTerm){
    String RT = rawTitle.toLowerCase();
    String ST = searchTerm.toLowerCase();
    this.rawTitle = RT;
    this.searchTerm = ST;
    String [] parts = rawTitle.split(" ");
    ArrayList <String> temp = new ArrayList<String>();
for(int i = 0; i < parts.length;i++){
    if(parts[i].equals(searchTerm)){
        parts[i].toUpperCase();
    }
    
    temp.add(parts[i]);
    
}
highlightedTitle = String.join(" ", temp);

}
public String getSearchTerm(){
    return searchTerm;
}
public String getRawTitle(){
    return rawTitle;
}
public String getHighlightedTitle(){
    return highlightedTitle;
}
public int compareTo(BookTitle title){

    return rawTitle.compareTo(title.getRawTitle());
    
    
}

@Override
public boolean equals(Object f){
    boolean result = false;
    if(f == null){
        result = false;
    }
if(((BookTitle) f).getHighlightedTitle().equals(getHighlightedTitle())){
    result = true;
}
    
    return result;
}

@Override
public String toString(){
    return highlightedTitle;
    
}



}

