package r13_kolekcje.p01_linked_list;

import java.util.*;

/**
 * Program demonstrujący działania na listach powiązanych
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 */
public class LinkedListTest
{
   public static void main(String[] args)
   {
      List<String> a = new LinkedList<>();
      a.add("Ania");
      a.add("Karol");
      a.add("Eryk");

      List<String> b = new LinkedList<>();
      b.add("Bartek");
      b.add("Daniel");
      b.add("Franek");
      b.add("Gosia");

      // Scalenie list a i b

      ListIterator<String> aIter = a.listIterator();
      Iterator<String> bIter = b.iterator();

      while (bIter.hasNext())
      {
         if (aIter.hasNext()) aIter.next();
         aIter.add(bIter.next());
      }

      System.out.println(a);

      // Usunięcie co drugiego słowa z listy b

      bIter = b.iterator();
      while (bIter.hasNext())
      {
         bIter.next(); // opuszczenie jednego elementu
         if (bIter.hasNext())
         {
            bIter.next(); // opuszczenie następnego elementu
            bIter.remove(); // usunięcie elementu
         }
      }

      System.out.println(b);

      // Usunięcie wszystkich słów znajdujących się w liście b z listy a

      a.removeAll(b);

      System.out.println(a);
   }
}
