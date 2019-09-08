import java.util.ArrayList;
import java.util.HashMap;
public class Graph {
    private ArrayList<String> vertex = new ArrayList<String>();
    private HashMap<String, String> edge = new HashMap<String, String>();
    
    public Graph(java.lang.String[][] graphIn) {
        String first = "";
        String listOfValues = "";
        for(Integer i = 0; i < graphIn.length; i++) {
            for(Integer j = 0; j < graphIn[i].length; j++) {
                if(j == 0) {
                    first = graphIn[i][j];
                    vertex.add(graphIn[i][j]);
                }
                else{
                    listOfValues += graphIn[i][j] + " ";
                }
            }
            edge.put(first,listOfValues);
            listOfValues = "";
        }
    }
    public java.util.ArrayList<java.lang.String> getEdgeList(java.lang.String v){
    	ArrayList<String> d = getVertices();
        ArrayList<java.lang.String> EdgeList = new ArrayList<java.lang.String>();
        boolean inlist = false;
        for(int i = 0; i < d.size(); i++) {
            if(d.get(i).equals(v)) {
                inlist = true;
                break;
            }
        }
        if(inlist == true) {

            String a = edge.get(v);

            String[] split = a.split("\\s+");
            
            for(int j = 0; j < split.length; j++) {
            	String ad = split[j];
            	if(ad.equals( "null")) {
            		
            	}
            	else if(ad.equals("")) {
            		
            	}
            	else {
            		EdgeList.add(split[j]);
            	}
               // EdgeList.add(split[j]);
            }
        }
        
        return EdgeList;
        
    }
    public java.util.ArrayList<java.lang.String> getVertices(){
        return vertex;      
    }
    public static void main(java.lang.String[] args) {
        java.lang.String[][] graphIn = new java.lang.String[5][3];
        graphIn[0][0] = "C1";
        graphIn[1][0] = "C2";
        graphIn[2][0] = "C3";
        graphIn[3][0] = "C4";
        graphIn[4][0] = "C5";
        
        graphIn[0][1] = "C3";
        
        graphIn[1][1] = "C3";
        
        graphIn[2][1] = "C4";
        graphIn[2][2] = "C5";
        
        graphIn[3][1] = "C5";
        String[] [] graphin = {{"C1", "C3"},
                {"C3", "C4", "C5"},
                {"C4", "C5"}, {"C5"}};
        
        String[] [] graphin2 = {{"c1", "c3"},
                {"c3", "c4", "c5"},
                {"c4", "c1", "c5"}, {"c5"}};

        
        Graph a = new Graph(graphIn);
        System.out.print(a.toString());
        
    }
    @Override
    public java.lang.String toString(){
        String total = "";
        java.util.ArrayList<java.lang.String> edges = new java.util.ArrayList<java.lang.String>();
        for(int i = 0; i< vertex.size(); i++) {
            
            total += vertex.get(i) + ": ";
            
            edges = getEdgeList(vertex.get(i));
            
            for(int j = 0; j < edges.size(); j++) {
                if(j + 1 == edges.size()) {
                        total += edges.get(j);
                }
                else {
                        total += edges.get(j)+", ";
                }
            }
            total += '\n';
            edges.clear();
        }
        return total;     
    }
}