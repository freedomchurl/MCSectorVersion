
public class Node {

	public static final double Gr = 3;
	// 3dBi의 수신 안테나
	public static final double Beta = 0.2316;
	// Beta 거리보정 상수
	
	public static int nodeNum = 1;
	//
	public static final double MAX_ENERGY = 3260;
	// 3260J의 최대 에너지
	
	public double requiredEnergy;
	// requiredEnergy는 40~60%의 랜덤한 상황으로 생성한다.
	
	public double x;
	public double y;
	public int index = -1;
	
	public double MaxX = 20;
	public double MaxY = 20;
	// 40*40m의 범위
	
	public double angle = 0;
	// 이건 이 노드의 중심 위치가 양의x축과 이루는 각도
	public double effectiveAngle;
	// 이건, effectiveAngle이 이 클러스터에서 만드는각도
	// 삼각형이라고 가정한다.
	
	public double effStart;
	public double effEnd;
	// Eff의 시작각도와 끝나는 각도
	
	Node()
	{
		double rate = (Math.random() * 2 + 4)/10;
		// 0.4~0.6의 값
		
		this.requiredEnergy = rate * MAX_ENERGY;
		// 노드 생성과 동시에 requiredEnergy가 정해지게 된다.
		
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
