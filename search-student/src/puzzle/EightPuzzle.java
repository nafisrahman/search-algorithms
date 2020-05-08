package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * The spaces in an 8-puzzle are indexed as follows:
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

  public List<Integer> startingValues;
  /**
   * Creates a new instance of the 8 puzzle with the given starting values.
   * The values are indexed as described above, and should contain exactly the
   * nine integers from 0 to 8.
   * 
   * @param startingValues
   *            the starting values, 0 -- 8
   * @throws IllegalArgumentException
   *             if startingValues is invalid
   */
  public EightPuzzle(List<Integer> startingValues) {
    if (startingValues.size() > 9) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i <= 8; i++) {
      if (!startingValues.contains(i)) {
        throw new IllegalArgumentException();
      }
    }
    this.startingValues = new ArrayList<Integer>(startingValues);
  }

  @Override
  public List<Integer> getInitialState() {
    // TODO
    return startingValues;
  }

  @Override
  public List<List<Integer>> getSuccessors(List<Integer> currentState) {
    // TODO
    List<Integer> curr = new ArrayList<>(currentState);
    List<List<Integer>> successors = new ArrayList<>();
    int index = currentState.indexOf(0);
    if (index > 3) {
      Collections.swap(curr, index, index - 3);
      successors.add(curr);
    } 
    if (index < 6) {
      Collections.swap(curr, index, index + 3);
      successors.add(curr);
    } 
    if ((index % 3) != 0) {
      Collections.swap(curr, index, index - 1);
      successors.add(curr);
    }
    if (index != 2 && index != 5 && index != 8) {
      Collections.swap(curr, index, index + 1);
      successors.add(curr);
    }
    return successors;
  }

  @Override
  public boolean isGoal(List<Integer> state) {
    // TODO
    if (!(state.get(state.size() - 1) == 0)) {
      return false;
    }
    for (int i = 0; i < state.size() - 2; i++) {
      if (!(state.get(i) < state.get(i + 1))) {
        return false;
      }
    }
    return true;
  }

  /**
   * supporting man method.
   */
  public static void main(String[] args) {
    EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
        4, 0, 6, 7, 5, 8 }));

    List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
    for (List<Integer> l : r) {
      System.out.println(l);
    }
  }
}
