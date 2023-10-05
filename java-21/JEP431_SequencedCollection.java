import java.util.*;

public class JEP431_SequencedCollection {
    public static void main(String[] args) {
        collection();
        sets();
        maps();
    }

    static void collection(){
        System.out.println("*********** List ***********");

        List<String> list = new ArrayList<>(); // List extends SequencedCollection
        //SequencedCollection<String> list = new ArrayList<>(); //no functionality of a List interface

        // add operations
        list.add("stringElement");

        System.out.printf("Initial collection: %s%n", list);

        //add first (head)
        list.addFirst("first");
        System.out.printf("Push the first elem: %s%n", list);
        System.out.printf("Get the first elem:  %s%n", list.getFirst());


        //add last (tail)
        list.addLast("last");
        System.out.printf("Push the last elem: %s%n", list);
        System.out.printf("Get the last elem:  %s%n", list.getLast());

        //remove first (head)
        list.removeFirst();
        System.out.printf("After removing first elem: %s%n", list);

        //remove last (tail)
        list.removeLast();
        System.out.printf("After removing last elem: %s%n", list);


        //reverse the collection
        list.addFirst("first");
        list.addLast("last");
        System.out.printf("Before reversing the collection: %s%n", list);
        System.out.printf("After reversing the collection: %s%n", list.reversed());

    }

    static void sets(){
        System.out.println("*********** Set ***********");
        Set<String> set = new LinkedHashSet<>();
        SequencedSet<String> sequenced = new LinkedHashSet<>();

        //add element from set via order
        set.add("first");
        set.add("stringElem");
        set.add("last");

        //add element from sequencedSet without order
        sequenced.add("stringElem");
        sequenced.addFirst("first");
        sequenced.addLast("last");

        System.out.printf("Initial collection of Set %s and SequencedSet %s%n", set, sequenced);

        //get first
        System.out.printf("Get first element of Set %s and SequencedSet %s%n", set.iterator().next(), sequenced.getFirst());

        //get last
        Iterator<String> iterator = set.iterator();
        String last  = "";
        while (iterator.hasNext())
            last = iterator.next();
        System.out.printf("Get last element of Set %s and SequencedSet %s%n", last, sequenced.getLast());

        // reverse
        List<String> reversetList = new ArrayList<>(set);
        Collections.reverse(reversetList);
        System.out.printf("Reversed element of Set %s and SequencedSet %s%n", reversetList, sequenced.reversed());
    }


    static void maps(){
        System.out.println("*********** Map ***********");
        Map<String, String> map = new LinkedHashMap<>();
        SequencedMap<String, String> sequencedMap = new LinkedHashMap<>();

        map.put("elem", "val1");
        sequencedMap.put("elem", "val1");

        //put first
        map.put("first", "val2");
        sequencedMap.putFirst("first", "val2");
        System.out.printf("Put first using Map %s and SequencedMap %s%n", map, sequencedMap);
        System.out.printf("Get first key/val in Map %s and SequencedMap %s%n",  map.entrySet().iterator().next(), sequencedMap.firstEntry());

        //put last
        map.put("last", "val3");
        sequencedMap.putLast("last", "val3");
        System.out.printf("Put last using Map %s and SequencedMap %s%n", map, sequencedMap);

        var entry = map.entrySet().iterator().next();
        var iterator = map.entrySet().iterator();
        while (iterator.hasNext())
            entry = iterator.next();
        System.out.printf("Get last key/val in Map %s and SequencedMap %s%n", entry, sequencedMap.lastEntry());


        //reverse
        System.out.printf("Reversed key/val of sequencedMap  %s", sequencedMap.reversed());

    }


}
