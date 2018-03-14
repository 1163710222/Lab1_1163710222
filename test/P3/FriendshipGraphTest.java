package P3;
import static org.junit.Assert.*;
import org.junit.Test;
public class FriendshipGraphTest {

	@Test
	public void test() {
		FriendshipGraph graph = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		Person d = new Person("d");
		Person e = new Person("e");
		Person f = new Person("f");
		Person g = new Person("g");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(f);
		graph.addVertex(e);
		graph.addVertex(g);
		graph.addEdge(a,b);
		graph.addEdge(a, c);
		graph.addEdge(a,d);
		graph.addEdge(b, e);
		graph.addEdge(b, f);
		int result1 = graph.getDistance(a, c);
		int result2 = graph.getDistance(g, e);
		int result3 = graph.getDistance(e, f);
		assertTrue(result1 == 1);
		assertTrue(result2 == -1);
		assertTrue(result3==2);
	}

}
