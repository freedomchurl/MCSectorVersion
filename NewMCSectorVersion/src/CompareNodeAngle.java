import java.util.Comparator;

class CompareNodeAngle implements Comparator<Node>{

	@Override
	public int compare(Node first, Node second) {
		// TODO Auto-generated method stub
		if(first.effStart > second.effStart)
		{
			// 오름차순으로 정렬해야하므로,
			// 이건 바뀌어야한다
			return 1;
			// 교체가 일어나야하면 1 리턴
		}
		else if(first.effStart < second.effStart)
		{
			return -1;
		}
		else
		{
			// 같을 경우, end각도가 작다는건 더 뒤에있는 녀석, 즉 우선순위가 낮아
			if(first.effEnd < second.effEnd)
			{
				// 우선 순위가 낮은애를 앞에다가 둬야겠다...
				return -1;
			}
			else
			{
				return 1;
			}
				
		}
	}

}
