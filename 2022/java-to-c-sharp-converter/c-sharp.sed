# Fake C# converter
# usage: sed -f c-sharp.sed input.txt
s/Java/C#/ig
s/main(String\[\] args)/Main()/ig
s/System.out.println/Console.WriteLine/ig
s/.length()/.Length/ig
s/.charAt(0)/[0]/ig
s/boolean/Boolean/ig
