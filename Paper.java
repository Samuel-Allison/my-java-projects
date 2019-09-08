
public class Paper {
	private Choice paper;
	public boolean beats(Object a) {//throws TieException{
		if(a instanceof Scissors){
			return false;
		}
		else if(a instanceof Rock){
			return true;
		}
		else if(a instanceof Paper){
			//throw new TieException();
			return true;
		}
		return true;
	}
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		else{
			if(o instanceof Rock){
				return false;
			}
			else if(o instanceof Scissors){
				return false;
			}
			Paper other = (Paper) o;
			if(paper == other.paper){
				return true;
			}
			else{
				return false;
			}
		}
	}
}
