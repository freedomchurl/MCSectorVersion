import java.util.Collections;

public class MultiCluster extends Cluster {

	
	
	MultiCluster(Circle input)
	{
		super(input);
		
	}
	
	
	@Override
	public double CalRequiredEnergy() {
		// TODO Auto-generated method stub
		
		// 1. � Sector�� ��� �������ϴ��� �����Ѵ�.
		// 2. Gt�� ����Ѵ�
		// 3. �׸��� �ʿ信������ ����Ѵ�.
		// ������ ��ũź��Ʈ�� ������.
		
		// �ϴ� ��� ����� ������ ���ؾ��Ѵ�
		// �̸� ����, ��尡 ��ī�ǰ� �Ǿ��־���Ѵ�.
		
		CalNodeAngle();
		// �ϴ� ��� ����� ������ Effective ������ ���ߴ�.
		// �׸��� ����
		
		CompareNodeAngle comp = new CompareNodeAngle();
		Collections.sort(myNodes,comp);
		
		/*
		for(int i=0;i<myNodes.size();i++)
		{
			System.out.println(i + "��° ����� start = " + myNodes.get(i).effStart
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
				// cx -> Nodes, �� �����ʿ� ��尡 �ִ� ���
				// �׳� ������ ���ϸ�ȴ�.
				
				double dx = myNodes.get(i).x - cx;
				double dy = myNodes.get(i).y - cy;
				
				myNodes.get(i).angle = Math.atan(dy/dx);
				
				if(myNodes.get(i).angle<0)
				{
					// 4 ��и�
					myNodes.get(i).angle += (2*Math.PI);
					// �� ����� ������ �ٲٱ� ����
				}
			}
			else // 2,3 ��и��� ���,
			{
				double dx = cx - myNodes.get(i).x;
				double dy = myNodes.get(i).y - cy;
				
				double tempAngle = Math.atan(dy/dx);
				
				if(tempAngle>=0) // 2��и��� ���� ���;��ϹǷ� Pi/2�� ���Ѵ�
				{
					myNodes.get(i).angle = tempAngle + Math.PI/2;
				}
				else // 3��и��ε�, -���̹Ƿ�,
				{
					myNodes.get(i).angle = Math.PI + Math.abs(tempAngle);
				}
			}
			
			// Effective Angle�� ���ؾ��Ѵ�. 
			// �ﰢ���̰�, Effective area�� ���� ������ ���� �غ��� �߽ɿ� ��������, �̶��� ���̰� Distance
			double dis = Math.sqrt(Math.pow(cx - myNodes.get(i).x, 2) + Math.pow(cy - myNodes.get(i).y, 2));
			// (Effective/2) / dis = tan(���հ�/2)
			
			myNodes.get(i).effectiveAngle = 2 * Math.atan((Cluster.EffectiveArea/2) / dis );
			
			// �̰� center�� EffectiveAngle�� �̷�� ���հ�
			
			// �׷��ٸ�, myNodes.get(i).angle - myNodes.get(i).effectiveAngle/2 -> ������ ����
			
			myNodes.get(i).effStart = myNodes.get(i).angle - myNodes.get(i).effectiveAngle/2;
			myNodes.get(i).effEnd = myNodes.get(i).angle + myNodes.get(i).effectiveAngle/2;
		}
	}


	@Override
	public void MakeAngleBased(Node[] input) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<input.length;i++)
		{
			// ��ü ������ ��ȸ�ϸ鼭, �� �����ȿ� �ִ� ������� Ȯ���Ѵ�.
			// center���� �Ÿ��� R���� ������ȴ�.
			int nodeindex = input[i].index;
			
			boolean check = true;
			
			for(int j=0;j<this.myNodes.size();j++)
			{
				if(this.myNodes.get(j).index == nodeindex)
				{
					// ���� ��尡 �̹� ������, ��ŵ
					check = false;
					break;
				}
			}
			
			if(check == true)
			{
				// ������ ���� �����
				double dis = Math.pow(this.centerX-input[i].x, 2) + Math.pow(this.centerY-input[i].y, 2);
				// x^2 + y^2;
				
				if(dis <= (this.Radius * this.Radius))
				{
					// ���� �̳��� 
					this.myNodes.add(new Node(input[i]));
					// �߰��Ѵ�
				}
			}
		}
		
	}

}
