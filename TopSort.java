
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;

public class TopSort {
	private HashMap<String, Integer> treeMap = new HashMap<String, Integer>();
	private Stack<String> stack = new Stack<String>();
	private Stack<String> inOrder = new Stack<String>();
	private ArrayList<String> a = new ArrayList<String>();
	private ArrayList<String> allEdgeList = new ArrayList<String>();
	private int count = 0;

	public ArrayList<String> dfsTopSort(Graph b) {
		for(int i = 0; i < b.getVertices().size(); ++i) {
			treeMap.put(b.getVertices().get(i),0);
		}
		for(int i = 0; i < b.getVertices().size(); ++i) {
			if(treeMap.get(b.getVertices().get(i)) == 0) {
				dfs(b.getVertices().get(i),b);
			}
		}
		if(compareVericesToAllEdgeLists(b) == null) {
			return null;
		}
		return addArrayList(inOrder);
	}
	
	public ArrayList<String> addArrayList(Stack<String> a) {
		while(!a.isEmpty()) {
			this.a.add(a.pop());
		}
		return this.a;
	}

	public void dfs(String vertex, Graph b) {
		count += 1;
		treeMap.replace(vertex,count);
		stack.push(vertex);
		for(int i = 0; i < b.getEdgeList(vertex).size();++i) {
			if(treeMap.get(b.getEdgeList(vertex).get(i)) == 0) {
				dfs(b.getEdgeList(vertex).get(i),b);
			}
		}
		inOrder.push(stack.pop());
	}
	public ArrayList<String> sourceTopSort(Graph b) {
		return compareVericesToAllEdgeLists(b);
	}
	
	public ArrayList<String> compareVericesToAllEdgeLists(Graph b) {
		ArrayList <String> vertices = new ArrayList<String>();
		for(int i = 0; i < b.getVertices().size();i ++) {
			vertices.add(b.getVertices().get(i));
		}
		
		ArrayList<String> returnedList = new ArrayList<String>();
		ArrayList <String> allEdges = addAllEdgeLists(b);
		Boolean inList = false;
		int count = 0;
		int interalCount = 0;
		while(vertices.size() != 0) {
			for(int i = 0; i < b.getVertices().size(); i++) {
				for(int j = 0; j < allEdges.size(); j++) {
					if(b.getVertices().get(i) == allEdges.get(j)) {
						inList = true;
						count ++;
						if(count > b.getVertices().size()) {
							return null;
						}
						break;
					}
				}
				if(inList == false) {
					returnedList.add(vertices.remove(count));
					count = 0;
					
					System.out.println(b.getEdgeList(b.getVertices().get(i)));
					for(int k = 0; k < b.getEdgeList(b.getVertices().get(i)).size(); ++k) {
						if(k >= allEdges.size()) {
							k -= 1;
							allEdges.remove(k);
						}
						else {
							
							allEdges.remove(interalCount);
						}
					}					
				}
				else {
					inList = false;;
				}
				
			}
		}
		return returnedList;
	}
	public ArrayList<String> addAllEdgeLists(Graph b) {
		for(int i = 0; i < b.getVertices().size(); ++i) {
			for(int j = 0; j < b.getEdgeList(b.getVertices().get(i)).size(); ++j) {
				allEdgeList.add(b.getEdgeList(b.getVertices().get(i)).get(j));
			}
		}
		return allEdgeList;
	}
}