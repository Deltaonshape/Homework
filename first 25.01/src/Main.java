import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Условие для бессмертных кроликов:");
        Scanner sc1 = new Scanner(System.in);
        int month = sc1.nextInt();
        int k = sc1.nextInt();

        System.out.println("Условие для смертных кроликов:");
        Scanner sc2 = new Scanner(System.in);
        int mon = sc2.nextInt();
        int lmon = sc2.nextInt();

        long adult1 = Living(month,k);
        System.out.println(adult1);

        long adult2 = Dying(mon,lmon);
        System.out.println(adult2);

    }


    public static long Living(int m, int k){
        long adult;
        if(m == 1){
            return (adult = 1);
        }
        else{
            if(m == 2){
                return (adult = 1);
            }
            else{
                    adult = Living(m-1,k) + k*Living(m-2,k);
                    return adult;
                }
            }
        }


        public static long Dying(int m, int lm){
            long adult;
            long [] rab = new long[lm];
            long [] sum = new long[m+1];
            rab[0] = 1;

            if (m == 1){
                return adult = 1;
            }
            else{
                if(m == 2){
                    return (adult = 1);
                }
                else{
                    for(int i = 2; i < m+1; i++){
                        long [] newrab = new long[lm];
                        for(int j = 1; j < lm; j++){
                            newrab[0] = newrab[0] + rab[j];
                            newrab[j] = rab[j-1];
                        }
                        for (int q = 0; q < lm; q++){
                            rab[q] = newrab[q];
                            sum[i] += newrab[q];

                        }

                    }
                    return adult = sum[m];

                }
            }

        }
}
