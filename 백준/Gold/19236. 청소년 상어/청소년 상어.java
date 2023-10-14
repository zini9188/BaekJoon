import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int eat = 0;

	public static void main(String[] args) throws IOException {
		int[][] space = new int[4][4];
		ArrayList<Fish> fishes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			tokenizer = new StringTokenizer(br.readLine());

			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(tokenizer.nextToken());
				int b = Integer.parseInt(tokenizer.nextToken()) - 1;

				Fish fish = new Fish(i, j, a, b);
				fishes.add(fish);
				space[i][j] = fish.id;
			}
		}

		Collections.sort(fishes);
		Fish sf = fishes.get(space[0][0] - 1);
		Shark shark = new Shark(0, 0, sf.dir, sf.id);
		sf.isAlive = false;
		space[0][0] = -1;

		dfs(space, shark, fishes);
		System.out.println(eat);
	}

	private static void dfs(int[][] space, Shark shark, ArrayList<Fish> fishes) {
		if (eat < shark.eat) {
			eat = shark.eat;
		}

		fishes.forEach(e -> moveFish(e, space, fishes));

		for (int dist = 1; dist < 4; dist++) {
			int nx = shark.x + dx[shark.dir] * dist;
			int ny = shark.y + dy[shark.dir] * dist;

			if (isOut(nx, ny)) {
				continue;
			}

			if (space[nx][ny] > 0) {
				int[][] copySpace = copy(space);
				ArrayList<Fish> copyFishes = copyFishes(fishes);

				copySpace[shark.x][shark.y] = 0;
				Fish f = copyFishes.get(space[nx][ny] - 1);
				Shark newShark = new Shark(f.x, f.y, f.dir, shark.eat + f.id);
				f.isAlive = false;
				copySpace[f.x][f.y] = -1;

				dfs(copySpace, newShark, copyFishes);
			}
		}
	}

	private static int[][] copy(int[][] arr) {
		int[][] copy = new int[4][4];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy[i][j] = arr[i][j];
			}
		}

		return copy;
	}

	private static void moveFish(Fish fish, int[][] space, ArrayList<Fish> fishes) {
		if (!fish.isAlive) {
			return;
		}

		for (int i = 0; i < 8; i++) {
			int nd = (fish.dir + i) % 8;
			int nx = fish.x + dx[nd];
			int ny = fish.y + dy[nd];

			if (isOut(nx, ny)) {
				continue;
			}

			if (space[nx][ny] > -1) {
				space[fish.x][fish.y] = 0;

				if (space[nx][ny] == 0) {
					fish.x = nx;
					fish.y = ny;
				} else {
					Fish temp = fishes.get(space[nx][ny] - 1);
					temp.x = fish.x;
					temp.y = fish.y;
					space[fish.x][fish.y] = temp.id;
					fish.x = nx;
					fish.y = ny;
				}

				space[nx][ny] = fish.id;
				fish.dir = nd;
				return;
			}
		}
	}

	private static ArrayList<Fish> copyFishes(ArrayList<Fish> fishes) {
		ArrayList<Fish> copy = new ArrayList<>();
		fishes.forEach(fish -> copy.add(new Fish(fish)));
		return copy;
	}

	private static boolean isOut(int x, int y) {
		return x < 0 || y < 0 || x >= 4 || y >= 4;
	}

	static class Shark {
		int x, y, dir, eat;

		public Shark(int x, int y, int dir, int eat) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.eat = eat;
		}
	}

	static class Fish implements Comparable<Fish> {
		int x, y, id, dir;
		boolean isAlive;

		public Fish(int x, int y, int id, int dir) {
			this.x = x;
			this.y = y;
			this.id = id;
			this.dir = dir;
			this.isAlive = true;
		}

		public Fish(Fish fish) {
			this.x = fish.x;
			this.y = fish.y;
			this.id = fish.id;
			this.dir = fish.dir;
			this.isAlive = fish.isAlive;
		}

		@Override
		public int compareTo(Fish o) {
			return this.id - o.id;
		}
	}
}