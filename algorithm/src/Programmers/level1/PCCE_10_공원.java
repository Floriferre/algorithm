package Programmers.level1;

public class PCCE_10_공원 {
    public static void main(String[] args) {
        int [] mats = {5,3,2};
        String [][] park = {{"A", "A", "-1", "B", "B", "B", "B", "-1"}, {"A", "A", "-1", "B", "B", "B", "B", "-1"}, {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}, {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}};

        int result = solution(mats, park);
        System.out.println(result);
    }

    public static int solution(int[] mats, String[][] park) {
        int answer = 0;

        boolean check = true;
        for(int m : mats){
            for(int i=0; i <= park.length -m; i++){
                if(answer > m){
                    break;
                }
                for(int j=0; j <= park[0].length -m; j++){
                    if(answer > m){
                        break;
                    }
                    if(park[i][j].equals("-1")){    // 빈 칸을 찾으면
                        check = true;
                        for(int r=i; r < i + m; r++ ){
                            for(int c=j; c< j+m; c++){
                                if(!park[r][c].equals("-1")){
                                    check = false;
                                    break;
                                }
                            }
                        }
                        // 다 체크했는데 check가 true면
                        if(check){
                            if(answer < m){
                                answer = m;
                            }
                        }
                    }
                }

            }
        }
        if(answer == 0){
            return -1;
        }
        return answer;
    }
}
