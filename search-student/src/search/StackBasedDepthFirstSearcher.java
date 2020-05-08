package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

  /**
   * StackBasedDepthFirstSearcher.
   * @param searchProblem : search problem
   */
  public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
    super(searchProblem);
  }

  @Override
  public List<T> findSolution() {
    // TODO
    List<T> path = new ArrayList<>();
    Stack<T> frontier = new Stack<T>();
    frontier.push(searchProblem.getInitialState());
    visited.add(searchProblem.getInitialState());
    while (!frontier.isEmpty()) {
      T current = frontier.peek();
      visited.add(current);
      if (searchProblem.isGoal(current)) {
        while (!frontier.isEmpty()) {
          path.add(0, frontier.pop());
        }
        return path;
      }
      List<T> next = searchProblem.getSuccessors(current);
      if (next.isEmpty()) {
        frontier.pop();
      } else {
        int count = 0;
        for (int i = 0; i < next.size(); i++) {
          if (!visited.contains(next.get(i))) {
            frontier.push(next.get(i));
            break;
          } else {
            count++;
          }
        }
        if (count == next.size()) {
          frontier.pop();
        }
      }
    }
    while (!frontier.isEmpty()) {
      path.add(0, frontier.pop());
    }
    return path;
  }
}
