import java.util.Comparator;

class CompareNodeAngle implements Comparator<Node>{

	@Override
	public int compare(Node first, Node second) {
		// TODO Auto-generated method stub
		if(first.effStart > second.effStart)
		{
			// ������������ �����ؾ��ϹǷ�,
			// �̰� �ٲ����Ѵ�
			return 1;
			// ��ü�� �Ͼ���ϸ� 1 ����
		}
		else if(first.effStart < second.effStart)
		{
			return -1;
		}
		else
		{
			// ���� ���, end������ �۴ٴ°� �� �ڿ��ִ� �༮, �� �켱������ ����
			if(first.effEnd < second.effEnd)
			{
				// �켱 ������ �����ָ� �տ��ٰ� �־߰ڴ�...
				return -1;
			}
			else
			{
				return 1;
			}
				
		}
	}

}
