import java.util.ArrayList;

public class Circle {

	public double centerX;
	public double centerY;
	public double Radius;
	
	// 기존의 Smallest Circle을 이용한 방법은 노드가 3개이상인 Circle이
	// 맞아떨어지는 경우가 잘없어.. 이것도 하나의 문제지
	
	public ArrayList<Node> myNodes = new ArrayList<Node>();
	// 여기다가 노드들을 저장한다.
	
	Circle()
	{
		
	}
	
	Circle(Node input)
	{
		// Single Node를 위한 생성자
		this.centerX = input.x;
		this.centerY = input.y;
		this.Radius = 0; // single charging 이니까 거리가 0
		myNodes.add(input);
		// input 노드를 ArrayList에 넣는다. 이때, Node 객체가 공유되고있음을 명심하자
	}
	Circle(int x,int y)
	{
		// 처음 노드를 넣기 위함
		this.centerX = x;
		this.centerY = y;
		this.Radius = 0;
		// 이때는, myNodes의 노드의 개수가 0이다 이걸 이용하자
	}
	
	Circle(Node n1, Node n2)
	{
		// 90도 보다 작을때는 무조건, 양끝인게 좋다
		
		this.centerX = (n1.x + n2.x) / 2;
		this.centerY = (n1.y + n2.y) / 2;
		this.Radius = (Math.sqrt(Math.pow(n1.x-n2.x,2)+ Math.pow(n1.y-n2.y,2)))/2;
		myNodes.add(n1);
		myNodes.add(n2);
	}
	
	Circle(Node n1, Node n2, Node n3)
	{
		double x1 = n1.x;
		double x2 = n2.x;
		double x3 = n3.x;
		
		double y1 = n1.y;
		double y2 = n2.y;
		double y3 = n3.y;
		
		double rd1 = (y2-y1) / (x2-x1);
		double rd2 = (y3-y2) / (x3-x2);
		
		double d1 = 1 / rd1;
		double d2 = 1 / rd2;
		
		double centerXvalue = ( (y3-y1) + (x2+x3)*d2 - (x1+x2)*d1 ) / ( 2*(d2-d1) );
		double centerYvalue = -d1 * (centerXvalue-(x1+x2)/2) + (y1+y2)/2;
		
		this.centerX = centerXvalue;
		this.centerY = centerYvalue;
		
		this.Radius = Math.sqrt(Math.pow(n1.x-centerX,2)+ Math.pow(n1.y-centerY,2));
	}
}
