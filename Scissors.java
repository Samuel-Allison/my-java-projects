
public class Scissors {
	public Choice scissors;
	public boolean beats(Object a) {//throws TieException{
		if(a instanceof Rock){
			return false;
		}
		else if(a instanceof Paper){
			return true;
		}
		else if(a instanceof Scissors){
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
			if(o instanceof Paper){
				return false;
			}
			else if(o instanceof Rock){
				return false;
			}
			Scissors other = (Scissors) o;
			if(scissors == other.scissors){
				return true;
			}
			else{
				return false;
			}
		}
	}
}
