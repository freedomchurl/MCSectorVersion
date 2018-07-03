import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class TSP {

	public ArrayList<Cluster> myList = null;
	public double result = 0;
	// 처음에는 거리, 그러나 나중에 기존알고리즘처럼 진행하려면 새로운 Parameter를 추가하여야 한다.

	public int N = 0;

	public double finalResult = 0;

	public final double NO = 0;

	//
	public int index;
	public Vector<Double> v = new Vector<Double>();
	public int cities;
	public int[] visited = null;
	//
	public double Dis[][] = null;

	TSP() {

	}

	TSP(ArrayList<Cluster> input) {
		this.result = 0;
		this.cities = 1;
		this.index = 0;
		this.myList = input;
		this.N = myList.size();
		//System.out.println("N size = " + N);
		this.Dis = new double[this.N][this.N];
		this.visited = new int[this.N];
		//System.out.println("visited length = " + visited.length);
		// 여기서 초기 셋팅이 들어가야한다 거리 등.

		// 거리 배열을 채워넣어야한다.

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 여기서 거리벡터를 채워넣을것
				this.Dis[i][j] = Math.sqrt(Math.pow(myList.get(i).centerX - myList.get(j).centerX, 2)
						+ Math.pow(myList.get(i).centerY - myList.get(j).centerY, 2));
			}
		}

	}

	public void Run() {
		result = 0;
		Travel(0);
		// 여기서 실행하고
		this.getValue();
		// 이걸 실행하면 나면, v.elementAt(0); 이 전체 이동거리

		this.finalResult = v.elementAt(0);
	}

	// We need to change this function!
	// Parameter가 추가되어야 한다
	public void Travel(int cur) // int cur == 현재 도시 인덱스
	{
		int i, j;

		if (this.cities == N && this.Dis[cur][0] != 0) {
			this.v.addElement(result + this.Dis[cur][0]);
			return;
		}

		for (i = 1; i < N; ++i) {
			if (i == cur) {
				continue;
			}

			// System.out.println("cur = " + cur + " i = " + i);
			if (this.Dis[cur][i] != this.NO && visited[i] == 0) {
				this.visited[i] = 1;
				cities++;
				result += Dis[cur][i];
				Travel(i);

				cities--;
				visited[i] = 0;
				result -= this.Dis[cur][i];
			}
		}
	}

	public void getValue() {
		int i;

		Collections.sort(this.v); // vector 오름차순 정렬
		System.out.println("벡터 크기 " + v.size());
		System.out.println("이동거리 " + v.elementAt(0));
	}

}
