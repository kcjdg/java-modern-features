/**
 *
 * To run: `java --enable-preview --source 21 JEP455_UnnamedClasses.java`
 * @see https://openjdk.org/jeps/445
 */

String featureName = "Unnamed Classes";
String greeting() {return "Hello, World"; }

static String staticField = "Static field";
static String staticMethod(){ return "Static method"; }

private static void privateStaticMethod(){System.out.println("Can use also any modifier");}
static void instanceMethod(){System.out.println("Can also have any instance method");}

void main(){
   System.out.println(greeting() + " using " + featureName);
   System.out.println("Declaring " + staticField + " and " + staticMethod());
   privateStaticMethod();
   this.instanceMethod();
}