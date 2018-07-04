
public class Node {

	public static final double Gr = 3;
	// 3dBi�� ���� ���׳�
	public static final double Beta = 0.2316;
	// Beta �Ÿ����� ���
	
	public static int nodeNum = 1;
	//
	public static final double MAX_ENERGY = 3260;
	// 3260J�� �ִ� ������
	
	public double requiredEnergy;
	// requiredEnergy�� 40~60%�� ������ ��Ȳ���� �����Ѵ�.
	
	public double x;
	public double y;
	public int index = -1;
	
	public double MaxX = 20;
	public double MaxY = 20;
	// 40*40m�� ����
	
	public double angle = 0;
	// �̰� �� ����� �߽� ��ġ�� ����x��� �̷�� ����
	public double effectiveAngle;
	// �̰�, effectiveAngle�� �� Ŭ�����Ϳ��� ����°���
	// �ﰢ���̶�� �����Ѵ�.
	
	public double effStart;
	public double effEnd;
	// Eff�� ���۰����� ������ ����
	
	Node()
	{
		double rate = (Math.random() * 2 + 4)/10;
		// 0.4~0.6�� ��
		
		this.requiredEnergy = rate * MAX_ENERGY;
		// ��� ������ ���ÿ� requiredEnergy�� �������� �ȴ�.
		
		this.x = (Math.random() * 40) - 20;
		// -20~20
		this.y = (Math.random() * 40) - 20;
		// -20~20
		
		index = nodeNum++;
	}
	

	public Node(Node node) {
		// TODO Auto-generated constructor stub
		this.requiredEnergy = node.requiredEnergy;
		this.x = node.x;
		this.y = node.y;
		this.index = node.index;
	}


	public void PrintLocation()
	{
		System.out.println("x = " + x + " y = " + y);
	}
	
}
