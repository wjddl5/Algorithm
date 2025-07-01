class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int now = 1;
        int success = 0;
        int healTime = bandage[0];
        int heal = bandage[1];
        int healBouns = bandage[2];
        int maxHealth = health;
        
        for(int[] att : attacks) {
            int attTime = att[0];
            int attPower = att[1];
            
            while(now++ < attTime) {
                health += heal;
                success++;
                if(success == healTime) {
                    health += healBouns;
                    success = 0;
                } 
                if(health > maxHealth) health = maxHealth;
            }
            
            health -= attPower;
            success = 0;
            if(health < 1) {
                health = -1;
                break;
            }
        }
        return health;
    }
}