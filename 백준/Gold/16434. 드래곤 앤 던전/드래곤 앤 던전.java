import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, initAtk;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        initAtk = Integer.parseInt(st.nextToken());

        Room[] rooms = new Room[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(a, h, t);
        }

        long left = 1, right = (long) 9e18;

        while (left <= right) {
            long mid = (left + right) >> 1;

            Hero hero = new Hero(initAtk, mid);
            for (int i = 0; i < N; i++) {
                Room room = rooms[i];
                if (!room.Active(hero)) {
                    left = mid + 1;
                    break;
                }
            }

            if (hero.cHp > 0) {
                right = mid - 1;
            }
        }

        sb.append(left);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Room {

        Monster monster;
        Portion portion;

        public Room(int a, int h, int t) {
            if (t == 1) {
                this.monster = new Monster(a, h);
            } else if (t == 2) {
                this.portion = new Portion(a, h);
            }
        }

        public boolean Active(Hero hero) {
            if (monster != null) {
                return hero.fight(monster);
            }
            hero.heal(portion);
            return true;
        }
    }

    static class Hero {

        private long atk, cHp;
        private final long mHp;

        public Hero(int atk, long mHp) {
            this(atk, mHp, mHp);
        }

        public Hero(int atk, long cHp, long mHp) {
            this.atk = atk;
            this.cHp = cHp;
            this.mHp = mHp;
        }

        public boolean fight(Monster monster) {
            long cnt = monster.health % atk;
            long turn = cnt == 0 ? monster.health / atk - 1 : monster.health / atk;
            long totalDamage = turn * monster.atk;
            cHp -= totalDamage;
            return cHp > 0;
        }

        public void heal(Portion portion) {
            cHp += portion.heal;
            atk += portion.atkBuff;
            if (cHp > mHp) {
                cHp = mHp;
            }
        }
    }

    static class Monster {

        long atk, health;

        public Monster(long atk, long health) {
            this.atk = atk;
            this.health = health;
        }
    }

    static class Portion {

        int atkBuff, heal;

        public Portion(int atkBuff, int heal) {
            this.atkBuff = atkBuff;
            this.heal = heal;
        }
    }
}