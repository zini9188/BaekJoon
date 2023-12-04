class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        // attacks는 [공격 시간, 피해량]의 형태
        // bandage는 [시전시간, 1초당 회복량, 추가 회복량]의 형태
        // health는 최대 체력
        int curHealth = health;
        int curTime = 0;
        // 모든 공격을 진행해야 함
        for(int[] attack : attacks){            
            // 공격이 진행되면 해당 공격과 현재 시간을 비교
            int healPerSec = attack[0] - curTime - 1;
            
            // 회복한 시간이 시전 시간보다 크면
            if(healPerSec >= bandage[0]){
                // 추가 회복량 
                curHealth += bandage[2] * (healPerSec / bandage[0]);
            }
            
            // 초당 회복량 적용
            curHealth += bandage[1] * healPerSec;            
            
            // 현재 시간 공격 당한 시간으로 갱신
            curTime = attack[0];
            
            // 최대 체력보다 큰 경우 최대 체력으로 변경
            if(curHealth > health){
                curHealth = health;
            }
            
            // 공격 적용
            curHealth -= attack[1];
            
            // 진행 중 체력이 0이되면 -1 리턴
            if(curHealth <= 0){
                return -1;
            }
        }
        
        return curHealth;
    }
}