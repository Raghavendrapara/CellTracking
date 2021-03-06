package jGraphT;
    import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.*;
import org.jgrapht.alg.cycle.*;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;
	
	public class Demo2 {

	
	   public static void test(boolean createCycles) {
	      CycleDetector<String, DefaultEdge> cycleDetector;
	      Graph<String, DefaultEdge> g;

	      g = new DefaultDirectedGraph<String, DefaultEdge>(
	         DefaultEdge.class);

	      // Add vertices
	      g.addVertex("a");
	      g.addVertex("b");
	      g.addVertex("c");
	      g.addVertex("d");
	      g.addVertex("e");

	      // Add edges
	      
	      g.addEdge("b", "a");
	      g.addEdge("c", "b");
	      if (createCycles) {
	         g.addEdge("a", "c");
	      }
	      g.addEdge("e", "d");
	      if (createCycles) {
	         g.addEdge("d", "e");
	      }

              // Printing the vetrices and the edges
	      System.out.println(g.toString());

	      // Checking for cycles in the dependencies
	      cycleDetector = new CycleDetector<String, DefaultEdge>(g);

	      // Cycle(s) detected.
	      if (cycleDetector.detectCycles()) {
	         Iterator<String> iterator;
	         Set<String> cycleVertices;
	         Set<String> subCycle;
	         String cycle;

	         System.out.println("Cycles detected.");

	         // Get all vertices involved in cycles.
	         cycleVertices = cycleDetector.findCycles();

	         // Loop through vertices trying to find disjoint cycles.
	         while (! cycleVertices.isEmpty()) {
	            System.out.println("Cycle:");

	            // Get a vertex involved in a cycle.
	            iterator = cycleVertices.iterator();
	            cycle = iterator.next();

	            // Get all vertices involved with this vertex.
	            subCycle = cycleDetector.findCyclesContainingVertex(cycle);
	            for (String sub : subCycle) {
	               System.out.println("   " + sub);
	               // Remove vertex so that this cycle is not encountered again
	               cycleVertices.remove(sub);
	            }
	         }
	      }

	      // If no cycles are detected, output vertices topologically ordered
	      else {
	         String v;
	         TopologicalOrderIterator<String, DefaultEdge> orderIterator;

	         orderIterator =
	            new TopologicalOrderIterator<String, DefaultEdge>(g);
	         System.out.println("\nTopological Ordering:");
	         while (orderIterator.hasNext()) {
	            v = orderIterator.next();
	            System.out.println(v);
	         }
	      }
	   }

	  
	   public static void main(String [] args) {
	      System.out.println("\nCase 1: There are cycles.");
	      test(true);

	      System.out.println("\nCase 2: There are no cycles.");
	      test(false);

	    
	   }
	}