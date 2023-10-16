public class JEP440_RecordPatterns {

    public static void main(String[] args){
        instanceExample();
        switchExample();
        genericInterfaceTest();
    }

    private static void genericInterfaceTest() {
        System.out.println("=== generic type inference example ===");
        var point = new GeographicalPoint( 14.599512, 120.984222);
        var decoratedPoint = new Decorator(new Location(point, "Manila Philippines"));
        var anotherDecorated = new Decorator(decoratedPoint);

        // JDK 20: type inference in Decorator<T>(Decorator<T>(T))
        if (anotherDecorated instanceof Decorator(Decorator(Location(GeographicalPoint(double x, double y), String location)))) {
            System.out.printf("latitude=%f, longitude=%f; location=%s%n%n", x, y, location);
        }

    }

    private static void switchExample() {
        System.out.println("=== switch example ===");
        var list = new Decorator[] {
                new Decorator(new GeographicalPoint( 38.8951, -77.0364)),
                new Decorator(new Location(new GeographicalPoint( 14.599512, 120.984222), "Manila Philippines")),
                new Decorator("test")
        };

        for (Decorator d : list) {
            switch (d) {
                case Decorator(GeographicalPoint(var x, var y)) -> {
                    System.out.printf("GeographicalPoint [%f,%f]%n", x, y);
                }
                case Decorator(Location(GeographicalPoint(var x, var y), String location)) -> {
                    System.out.printf("%s has latitude of [%f] and longitude of [%f]%n", location, x, y);
                }
                case Decorator(String s) -> {
                    System.out.println("Decorated string: " + s);
                }
                // required to be exhaustive
                default -> System.out.println("None matched");
            }
        }
    }

    private static void instanceExample() {
        System.out.println("=== instanceof example ===");
        var geo = new GeographicalPoint(38.8951, -77.0364);
        var loc = new Location(geo, "Washington DC");
        Object obj = loc;

        //java 16
        if(obj instanceof Location loc1){
            System.out.println("obj is a Location "+ loc1);
        }

        if(obj instanceof  GeographicalPoint geo1){
            System.out.println("obj is a GeographicalPoint" + geo1);
        }else{
            System.out.println("obj is not a GeographicalPoint");
        }


        //jdk20
        if(obj instanceof Location(GeographicalPoint(double x, double y), String location)){
            System.out.printf("%s has latitude of [%f] and longitude of [%f]%n", location, x, y);
        }

    }


    record GeographicalPoint(double latitude, double longitude){}
    record Location(GeographicalPoint point, String address){}
    record Decorator<T>(T t) {}

}
