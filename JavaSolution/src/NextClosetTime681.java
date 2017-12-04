class NextClosetTime681 {
    static int[] mins = { 600, 60, 10, 1 };
    public static String nextClosestTime(String time) {
        int colon = time.indexOf(':');
        int cur = Integer.valueOf(time.substring(0, colon)) * 60 + Integer.valueOf(time.substring(colon + 1));
        char[] next = new char[4];
        for (int i = 1, d = 0; i <= 1440 && d < 4; i++) {
            int m = (cur + i) % 1440;
            for (d = 0; d < 4; d++) {
                next[d] = (char)('0' + m / mins[d]); m %= mins[d];
                if (time.indexOf(next[d]) == -1) break;
            }
        }
        return new String(next, 0, 2) + ':' + new String(next, 2, 2);
    }

    public static void main(String[] args)
    {
        String res = nextClosestTime("21:01");
        System.out.println(res);
    }

}