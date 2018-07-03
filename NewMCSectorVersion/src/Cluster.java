import java.util.ArrayList;

public abstract class Cluster {

	public static double lambda = 0.33;
	// 0.33m라고 가정
	
	public static int SectorN = 4;
	// 우선 Sector는 4개가 존재한다고 가정한다.
	public boolean []isOn = null;
	// 어떤 Sector가 켜져있는지 저장하기 위한 배열
	public int checkSector = 4;
	// 몇개의 Sector가 켜져있는지 확인하기 위한 변수, 기존적으로 4의값을 가지고 모든 섹터를 키게된다.
	public static double Pt = 3;
	// Transfer power 3W
	public double Gt = 1;
	// Transfer Antenna Gain
	
	
	public double centerX;
	public double centerY;
	public double Radius;
	
	// 기존의 Smallest Circle을 이용한 방법은 노드가 3개이상인 Circle이
	// 맞아떨어지는 경우가 잘없어.. 이것도 하나의 문제지
	
	public ArrayList<Node> myNodes = new ArrayList<Node>();
	// 여기다가 노드들을 저장한다.
	
	public double ConsumedCharging = 0;
	
	Cluster(Circle input)
	{
		this.centerX = input.centerX;
		this.centerY = input.centerY;
		this.Radius = input.Radius;
		this.myNodes = input.myNodes;
		// 그대로 데이터를 Circle에서 Cluster로 옮겨온다. 선택된 아이들이니까.
		
		isOn = new boolean[SectorN];
		// Sector의 개수만큼 isOn을 설정한다.
		Gt = SectorN / checkSector; 
		// 기존적으로 SectorN의 개수가 4이고, checkSector의 값이 4 -> Omni한 상황이라면 4/4 = 1이 될것이고 2개만 켜져있으면 2의 값이 생성될 것이다
	}
	
	Cluster()
	{
		
	}
	
	public int CheckSector()
	{
		int count = 0;
		
		for(int i=0;i<this.SectorN;i++) {
			if(isOn[i]==true)
				count++;
		}
		
		checkSector = count;
		return checkSector;
	}
	
	public int getNodesNum()
	{
		return myNodes.size();
		// Circle에 담겨있는 노드의 개수를 리턴한다.
	}
	
	public abstract double CalRequiredEnergy();
	// 이 메소드 내에서 MC의 Static Pt랑 Gt등을 이용하여, MC가 이 Cluster를 충전하는데에 쓰이는 에너지를 계산해야한다.
	
}
