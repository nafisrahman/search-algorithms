package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

  /**
   * QueueBasedBreadthFirstSearcher.
   * @param searchProblem : search problem
   */
  public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
    Queue<T> frontier = new LinkedList<>();
    frontier.add(searchProblem.getInitialState());
    HashMap<T, T> predecessor = new HashMap<>();
    predecessor.put(searchProblem.getInitialState(), null);
    List<T> path = new ArrayList<>();
    while (!frontier.isEmpty()) {
      T current = frontier.remove();
      for (T next : searchProblem.getSuccessors(current)) {
        if (!predecessor.containsKey(next)) {
          frontier.add(next);
          predecessor.put(next, current);
        }
      }
      if (searchProblem.isGoal(current)) {
        path.add(current);
        T previous = predecessor.get(current);
        while (previous != null) {
          path.add(0, previous);
          previous = predecessor.get(previous);
        }
        break;
      }
    }
    return path;
  }
}
