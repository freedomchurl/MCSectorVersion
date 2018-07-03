
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
	

	public void PrintLocation()
	{
		System.out.println("x = " + x + " y = " + y);
	}
	
}
