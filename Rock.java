
public class Rock {
	public Rock rock;
	public boolean beats(Object a) {//throws TieException{
		if(a instanceof Scissors){
			return true;
		}
		else if(a instanceof Paper){
			return false;
		}
		else if(a instanceof Rock){
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
			else if(o instanceof Scissors){
				return false;
			}
			Rock other = (Rock) o;
			if(rock == other.rock){
				return true;
			}
			else{
				return false;
			}
		}
	}
}
