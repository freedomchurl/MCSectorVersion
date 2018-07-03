import java.util.ArrayList;

public class Circle {

	public double centerX;
	public double centerY;
	public double Radius;
	
	// ������ Smallest Circle�� �̿��� ����� ��尡 3���̻��� Circle��
	// �¾ƶ������� ��찡 �߾���.. �̰͵� �ϳ��� ������
	
	public ArrayList<Node> myNodes = new ArrayList<Node>();
	// ����ٰ� ������ �����Ѵ�.
	
	Circle()
	{
		
	}
	
	Circle(Node input)
	{
		// Single Node�� ���� ������
		this.centerX = input.x;
		this.centerY = input.y;
		this.Radius = 0; // single charging �̴ϱ� �Ÿ��� 0
		myNodes.add(input);
		// input ��带 ArrayList�� �ִ´�. �̶�, Node ��ü�� �����ǰ������� �������
	}
	Circle(int x,int y)
	{
		// ó�� ��带 �ֱ� ����
		this.centerX = x;
		this.centerY = y;
		this.Radius = 0;
		// �̶���, myNodes�� ����� ������ 0�̴� �̰� �̿�����
	}
	
	Circle(Node n1, Node n2)
	{
		// 90�� ���� �������� ������, �糡�ΰ� ����
		
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
