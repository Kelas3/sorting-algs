
import java.util.*;
public class FractionBubbleKhan
{
    private static class Fraction implements Comparable<Fraction>
    {

        private int numerator;
        private int denomerator;

        public Fraction(int a, int b)
        {
            numerator = a;
            denomerator = b;
        }

        public int toLowestTerms(int a, int b)
        {
            while(b != 0)
            {
                int c = b;
                b = a % b;
                a = c;
            }
            return a;
        }

        public int reducedNumerator(int a, int b)
        {
            numerator = a / toLowestTerms(a,b);
            return (int) numerator;
        }

        public int reducedDenom(int a, int b)
        {
            denomerator = b / toLowestTerms(a,b);
            return (int) denomerator;
        }

        public int compareTo(Fraction x)
        {
            if (((double)this.numerator / (double)this.denomerator) < ((double)x.numerator / (double)x.denomerator))
            {
                return -1;
            }
            else if (((double)this.numerator / (double)this.denomerator) == ((double)x.numerator / (double)x.denomerator))
            {
                return 0;
            }
            else 
            {
                return 1;
            }
        }

        public String toString()
        {
            return this.numerator + "/" + this.denomerator; 
        }
    }

    static ArrayList <Fraction> fracs = new ArrayList <Fraction>();
    public static void bubbleSort()
    {
        for(int current = 0; current < fracs.size(); current++)
        {
            for(int i = fracs.size() - 1;i > current; i--)
            {
                if(fracs.get(i).compareTo (fracs.get(i-1)) == -1)
                {
                    swap(i, i-1);
                }   
            }
        }
    }

    public static void swap(int indexA, int indexB)
    {
        Fraction temp = fracs.get(indexA);
        fracs.set(indexA, fracs.get(indexB));
        fracs.set(indexB, temp);
    }

    public static void main(String[] args)
    {
        boolean isSorted = false;
        for(int i = 0; i < 10; i++)
        {
            int num = (int)((Math.random() * 9) + 1);
            int denom = (int)((Math.random() * 9) + 1);
            Fraction a = new Fraction(num, denom);
            fracs.add(a);
            isSorted = false;
        }
        for(int i = 0; i < 10; i++)
        {
                System.out.print(fracs.get(i).toString() + " ");
        }
        System.out.print("\nValues are sorted:"  + isSorted);
        bubbleSort();
        System.out.print("\n\n");
        
        for(int i = 0; i < 10; i++)
        {
            System.out.print(fracs.get(i).toString() + " ");
            isSorted = true;
        }
        System.out.print("\nValues are sorted:"  + isSorted);
    }
}
