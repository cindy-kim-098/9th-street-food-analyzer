public class Person201Farthest {
    public static void main(String[] args) throws Exception {
        String file = "data/foodlarge.txt";
        double max = 0;
        Person201 a = null;
        Person201 b = null;
        Person201[] all = Person201Utilities.readFile(file);
        for (int i= 0; i< all.length; i++) {
            for (int j= i+ 1; j< all.length; j++) {  
                double distance = Person201Utilities.distance(all[i], all[j]);
                if (distance > max) {
                    max = distance;
                    a = all[i];
                    b = all[j];
                }
            }
        }

        System.out.printf("farthest distance is %3.2f between %s and %s\n",
                          max, a.name(), b.name());
    }
}
