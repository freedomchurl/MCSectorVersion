
public class SingleCluster extends Cluster{
	
	SingleCluster(Circle input)
	{
		super(input);
	}

	@Override
	public double CalRequiredEnergy() {
		// TODO Auto-generated method stub
		// 이건 SingleCharging이니까, 노드의 개수도 한개고 결국 Charging에 필요한 에너지는 그냥 그대로 Friis Eq.
		
		this.checkSector = 1; // 1개의 Sector만 켜지면된다.
		this.Gt = this.SectorN / this.checkSector;
		
		if(this.myNodes.size()==0)
		{
			// 첫번쨰 노드를 위한 제거
			
			this.ConsumedCharging = 0;
			
			return 0;
		}
		else
		{
			double re = this.myNodes.get(0).requiredEnergy;
			// 필요에너지
			
			double receive = this.Gt * Node.Gr * Math.pow((Cluster.lambda / (4*Math.PI * (Node.Beta))),2) * Cluster.Pt;
			
			double rate = re / receive;
			// 필요한 에너지의 비율 -> 시간이라고도 볼 수 있다.
			
			this.ConsumedCharging = (rate * Cluster.Pt);
			
			return (rate * Cluster.Pt);
			// 총 소모 에너지
		}
	}
	
	

}
