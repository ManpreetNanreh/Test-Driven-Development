package tdd;

public class GraphIsFullException extends Exception{
	public GraphIsFullException(){
		super();
	}
  
	public GraphIsFullException(String msg){
		super(msg);
	}
}