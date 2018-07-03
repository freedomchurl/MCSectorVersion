import java.util.ArrayList;

public class Simulator {

	public static double MoveEnergy = 5;
	// 일단은 5J로 설정

	public static int NodeNum = 100;
	Node[] myNodes = new Node[NodeNum];
	// 노드 40개를 만들고

	Node[] myNodes2 = new Node[NodeNum];
	// 멀티를 위한 복사

	public ArrayList<Circle> SingleCircle = new ArrayList<Circle>();
	// Single은 그냥 그대로 Circle을 넣으면된다.
	public ArrayList<Cluster> SingleCluster = new ArrayList<Cluster>();
	// Single은 그대로 Cluster로 옮긴다.

	public ArrayList<Circle> CandidateCircle = new ArrayList<Circle>();
	// 하지만, Multi방식의 경우 둘다, 일단 CandidateCircle을 만들고, 그중에서 골라야한다
	public ArrayList<Cluster> multiCluster = new ArrayList<Cluster>();

	public ArrayList<Circle> CandidateCircle_2 = new ArrayList<Circle>();
	// 위에꺼를 만들고 복사하는 식으로 취한다. Circle은 위에걸 그대로 복사하고 후처리만 하면된다.
	public ArrayList<Cluster> newmultiCluster = new ArrayList<Cluster>();
	// 아래는 new 방식

	public TSP myTSP = null;

	public double singleMoveDistance = 0;
	public double multiMoveDistance = 0;
	public double newMultiMoveDistance = 0;

	public double singleChargingEnergy = 0;
	public double multiChargingEnergy = 0;
	public double newMultiChargingEnergy = 0;

	public double singleTotalEnergy = 0;
	public double multiTotalEnergy = 0;
	public double newMultiTotalEnergy = 0;

	Simulator() {
		for (int i = 0; i < NodeNum; i++) {
			myNodes[i] = new Node();
		}
	}

	public void Run() {
		RunSingle();
		RunMulti();
	}

	public void RunMulti() {
		Welz();
		System.out.println("Candidate Cluster 개수 = " + this.multiCluster.size());
		// 여기까지는 Candidate 후보를 만드는것
		// 어제 고려해본 결과, 90도보다 작은 Sector에 대해서는 원으로 그리는게 더 낫다.
	}

	public void Welz() {
		// 일단 순서대로 Single Circle을 추가하고,
		// 2개짜리 Circle을 추가
		// 3개짜리 Circle을 추가하자
		// 4개부터는 너무 멀거나, 현실적인 부분이 부족하므로 스킵
		// 근데 논문에서 이 부분을 적을 수도 있을 것 같다.
		// 4개짜리 Circle을 만들기가 쉽지않은데, 몰려있는 경우 그러면? -> 이부분을 기존 논문처럼 그림으로 보여주는건 어떨까?

		for (int i = 0; i < NodeNum; i++) {
			Circle tmp = new Circle(myNodes[i]);
			this.CandidateCircle.add(tmp);
			// Single Circle을 만들고
			// CandidateCircle에 추가하고
			SingleCluster tmpCluster = new SingleCluster(tmp);
			this.multiCluster.add(tmpCluster);
			// Cluster에 다 담는다.
			// 다형성으로 인해 이렇게 담아도 상관없다.
		}
		// 이 과정 이후에, Single Cluster인 노드들이 들어가게 된다.

		for (int i = 0; i < NodeNum; i++) {
			for (int j = 0; j < NodeNum; j++) {
				if (i < j) {
					// 같은 노드가 아닐 경우만
					Circle tmp = new Circle(myNodes[i], myNodes[j]);
					this.CandidateCircle.add(tmp);
					// 2개짜리 Smallest Circle
					// CandidateCircle에 추가

					MultiCluster tmpCluster = new MultiCluster(tmp);
					this.multiCluster.add(tmpCluster);
				}
			}
		}

		for (int i = 0; i < NodeNum; i++) {
			for (int j = 0; j < NodeNum; j++) {
				if (i < j) {
					for (int k = 0; k < NodeNum; k++) {

						if (j < k) {
							// 같은 노드가 아닐 경우만
							Circle tmp = new Circle(myNodes[i], myNodes[j], myNodes[k]);
							this.CandidateCircle.add(tmp);
							// 3개짜리 Smallest Circle
							// CandidateCircle에 추가

							MultiCluster tmpCluster = new MultiCluster(tmp);
							this.multiCluster.add(tmpCluster);
						}
					}
				}
			}
		}

	}

	public void RunSingle() {
		MakeSingle();
		RoutingSingle();

		this.singleTotalEnergy = this.singleChargingEnergy + this.MoveEnergy * this.singleMoveDistance;
		// 전체 소모에너지는 이동거리 * 미터당 이동 에너지 + 모든 클러스터에서 충전에 소모한 에너지의 합

		System.out.println("이동거리 = " + this.singleMoveDistance + ", 충전 소모에너지 = " + this.singleChargingEnergy);
	}

	public void RoutingSingle() {
		// 앞서서 만들어진 SingleCluster ArrayList를 이용하여 이동에 걸리는 에너지를 뽑아본다.

		// myTSP = new TSP(this.SingleCluster);
		// myTSP.Run();
		// this.singleMoveDistance = myTSP.finalResult;
		// 전체 결과를 가져온다. 아직은 거리만.

	}

	public void MakeSingle() {
		Circle first = new Circle(0, 0);
		// Single Charging에서 첫번째 시작점
		this.SingleCircle.add(first);
		SingleCluster firstCluster = new SingleCluster(first);
		this.SingleCluster.add(firstCluster);
		// 그 다음에 모든 노드들을 넣으면 된다.

		for (int i = 0; i < NodeNum; i++) {
			Circle tmp = new Circle(myNodes[i]);
			this.SingleCircle.add(tmp);
			// Single Circle을 만들고
			SingleCluster tmpCluster = new SingleCluster(tmp);
			this.SingleCluster.add(tmpCluster);
			// Cluster에 다 담는다.
		}

		// System.out.println("Cluster의 사이즈는 = " + this.SingleCluster.size());
		// System.out.println("----------------------------------------------");

		double ChargingEnergy = 0;
		for (int i = 1; i < this.SingleCluster.size(); i++) {
			// System.out.println("Cluster의 노드 개수는 = " +
			// this.SingleCluster.get(i).getNodesNum() + " 노드의 필요 에너지 = " +
			// this.SingleCluster.get(i).myNodes.get(0).requiredEnergy +
			// " MC의 소모에너지 = " + this.SingleCluster.get(i).CalRequiredEnergy());
			ChargingEnergy += this.SingleCluster.get(i).CalRequiredEnergy();
		}

		System.out.println("Total 에너지 필요 = " + ChargingEnergy);
		this.singleChargingEnergy = ChargingEnergy;
	}

}
