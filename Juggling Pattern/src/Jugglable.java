import java.util.ArrayList;
import java.util.HashMap;

public class Jugglable {
    
    public static void main (String args[]) {
        int[] pattern1 = {5, 3, 1};
        int[] pattern2 = {4, 6};
        int[] pattern3 = {4, 2, 3};
        int[] pattern4 = {1, 1, 1, 5};
        int[] pattern5 = {13, 8, 9};
        int[] pattern6 = {9, 4, 11};
        int period1 = jugglable(pattern1);
        int period2 = jugglable(pattern2);
        int period3 = jugglable(pattern3);
        int period4 = jugglable(pattern4);
        int period5 = jugglable(pattern5);
        int period6 = jugglable(pattern6);
        System.out.println("Pattern 1: " + period1);
        System.out.println("Pattern 2: " + period2);
        System.out.println("Pattern 3: " + period3);
        System.out.println("Pattern 4: " + period4);
        System.out.println("Pattern 5: " + period5);
        System.out.println("Pattern 6: " + period6);
    }

    
    
    public static int jugglable(int[] pattern) {
        int time = 0;
        int collision = 0;
        int patt = 0;
        int names = 2;
        ArrayList<Ball> balls = new ArrayList<Ball>();
        Ball first = new Ball(0, 1);
        balls.add(first);
        ArrayList<Integer> record = new ArrayList<Integer>();
        HashMap<Integer, Ball> mapping = new HashMap<Integer, Ball>();
        mapping.put(1, first);
        while (time < 1000) {
            if (patt == pattern.length) {
                patt = 0;
            }
            collision = 0;
            Ball land = first;
            for (Ball b : balls) {
                if (b.get_land() == time) {
                    collision++;
                    land = b;
                }
            }
            if (collision > 1) {
                return 0;
            } else if (collision == 0) {
                Ball ball = new Ball(time + pattern[patt], names);
                balls.add(ball);
                ball.add_value(pattern[patt]);
                names++;
                time++;
                patt++;
                record.add(ball.get_name());
            } else {
                land.add_value(pattern[patt]);
                land.set_land(time + pattern[patt]);
                time++;
                patt++;
                record.add(land.get_name());
            }
        }
        boolean found_last = false;
        ArrayList<Integer> curr_period = new ArrayList<Integer>();
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int k = 0;
        while (k < 500) {
            curr_period.add(record.get(k));
            for (int i = k + 1; i < record.size(); i++) {
                if (record.get(i) == names - 1) {
                    found_last = true;
                    curr_period.add(record.get(i));
                } else if (found_last && record.get(i) == 1) {
                    k = i;
                    break;
                } else {
                    curr_period.add(record.get(i));
                }
            }
            boolean found = true;
            freq.clear();
            for (int i = k; i < k + curr_period.size(); i++) {
                if (curr_period.get(i - k) != record.get(i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        System.out.println("Number of balls: " + balls.size());
        int remainder = 567 % curr_period.size();
        System.out.println("Ball # " + curr_period.get(remainder) + " is in your hand");
        return curr_period.size();
    }

}