package tdd;

public class VertexExistsException extends Exception{
	public VertexExistsException(){
		super();
	}
  
	public VertexExistsException(String msg){
		super(msg);
	}
}
