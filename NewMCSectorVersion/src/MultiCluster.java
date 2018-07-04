import java.util.Collections;

public class MultiCluster extends Cluster {

	
	
	MultiCluster(Circle input)
	{
		super(input);
		
	}
	
	
	@Override
	public double CalRequiredEnergy() {
		// TODO Auto-generated method stub
		
		// 1. 어떤 Sector가 몇개가 켜져야하는지 봐야한다.
		// 2. Gt를 계산한다
		// 3. 그리고 필요에너지를 계산한다.
		// 각도는 아크탄젠트로 구하자.
		
		// 일단 모든 노드의 각도를 구해야한다
		// 이를 위해, 노드가 딥카피가 되어있어야한다.
		
		CalNodeAngle();
		// 일단 모든 노드의 각도와 Effective 각도를 구했다.
		// 그리고 정렬
		
		CompareNodeAngle comp = new CompareNodeAngle();
		Collections.sort(myNodes,comp);
		
		/*
		for(int i=0;i<myNodes.size();i++)
		{
			System.out.println(i + "번째 노드의 start = " + myNodes.get(i).effStart
					+ " end = " + myNodes.get(i).effEnd);
		}*/
		
		
		
		this.ConsumedCharging = 0;
		return 0;
	}
	
	public void CalNodeAngle()
	{
		for(int i=0;i<this.myNodes.size();i++)
		{
			double cx = this.centerX;
			double cy = this.centerY;
			
			if(myNodes.get(i).x >= cx) 
			{
				// cx -> Nodes, 즉 오른쪽에 노드가 있는 경우
				// 그냥 각도를 구하면된다.
				
				double dx = myNodes.get(i).x - cx;
				double dy = myNodes.get(i).y - cy;
				
				myNodes.get(i).angle = Math.atan(dy/dx);
				
				if(myNodes.get(i).angle<0)
				{
					// 4 사분면
					myNodes.get(i).angle += (2*Math.PI);
					// 다 양수의 각도로 바꾸기 위함
				}
			}
			else // 2,3 사분면의 경우,
			{
				double dx = cx - myNodes.get(i).x;
				double dy = myNodes.get(i).y - cy;
				
				double tempAngle = Math.atan(dy/dx);
				
				if(tempAngle>=0) // 2사분면의 값이 나와야하므로 Pi/2를 더한다
				{
					myNodes.get(i).angle = tempAngle + Math.PI/2;
				}
				else // 3사분면인데, -값이므로,
				{
					myNodes.get(i).angle = Math.PI + Math.abs(tempAngle);
				}
			}
			
			// Effective Angle을 구해야한다. 
			// 삼각형이고, Effective area에 내린 수선의 발이 밑변의 중심에 내려오고, 이때의 길이가 Distance
			double dis = Math.sqrt(Math.pow(cx - myNodes.get(i).x, 2) + Math.pow(cy - myNodes.get(i).y, 2));
			// (Effective/2) / dis = tan(사잇각/2)
			
			myNodes.get(i).effectiveAngle = 2 * Math.atan((Cluster.EffectiveArea/2) / dis );
			
			// 이게 center와 EffectiveAngle이 이루는 사잇각
			
			// 그렇다면, myNodes.get(i).angle - myNodes.get(i).effectiveAngle/2 -> 각도의 시작
			
			myNodes.get(i).effStart = myNodes.get(i).angle - myNodes.get(i).effectiveAngle/2;
			myNodes.get(i).effEnd = myNodes.get(i).angle + myNodes.get(i).effectiveAngle/2;
		}
	}


	@Override
	public void MakeAngleBased(Node[] input) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<input.length;i++)
		{
			// 전체 노드들을 순회하면서, 내 영역안에 있는 노드인지 확인한다.
			// center와의 거리가 R보다 작으면된다.
			int nodeindex = input[i].index;
			
			boolean check = true;
			
			for(int j=0;j<this.myNodes.size();j++)
			{
				if(this.myNodes.get(j).index == nodeindex)
				{
					// 같은 노드가 이미 있으면, 스킵
					check = false;
					break;
				}
			}
			
			if(check == true)
			{
				// 기존에 없는 노드라면
				double dis = Math.pow(this.centerX-input[i].x, 2) + Math.pow(this.centerY-input[i].y, 2);
				// x^2 + y^2;
				
				if(dis <= (this.Radius * this.Radius))
				{
					// 원의 이내면 
					this.myNodes.add(new Node(input[i]));
					// 추가한다
				}
			}
		}
		
	}

}
