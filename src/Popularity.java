import java.util.ArrayList;

public class Popularity {
    public static void main(String[] args) throws Exception {
        String url= "https://courses.cs.duke.edu/compsci201/fall25/data/p0/compsci201.log";
        Person201[] all= PeopleDownloader.loadData(url);

        CountEateries counter= new CountEateries();
        ArrayList<String> eat= new ArrayList<>();

        for (Person201 p: all) {
            String eatery= p.eatery();
            if (!eat.contains(eatery)) { eat.add(eatery);
            }
        }

        for (int i= 0; i< eat.size(); i++) {
            for (int j= i + 1; j< eat.size(); j++) {
                int x= counter.countEateries(all, eat.get(i));
                int y= counter.countEateries(all, eat.get(j));
                if (y> x) {
                    String temp= eat.get(i);
                    eat.set(i, eat.get(j));
                    eat.set(j, temp);
                }
            }
        }

        System.out.println("Top 5 eateries:");
        for (int i= 0; i < Math.min(5, eat.size()); i++) {
            String eatery= eat.get(i);
            int count= counter.countEateries(all, eatery);
            System.out.printf("%s with %d people\n", eatery, count);
        }
    }
}
