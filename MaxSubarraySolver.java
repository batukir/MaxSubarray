import java.io.*;

// Dynamic Programming solver for the Maximum Subarray Problem:
// for a given array of integers, find the largest sum of any contiguous subarray.
// 
// Step 1: v(i) = maximum value of any contiguous subarray that "ends with" a[i]
//         and
//         maxV(i) = largest sum of any contiguous subarray between a[0] and a[i], inclusive
//
//                { 0                when i is -1 // artificial base case, for starters
// Step 2: v(i) = { 0                when a[i] < -v(i-1) // start anew, with the empty subarray
//                { v(i-1) + a[i]    otherwise
//                  
//         maxV (i) = max ( v(i), maxV(i-1) ) // max subarray so far either ends with a[i] or doesn't
//
// Limitation: assumes the input array contains at least one value > 0


public class MaxSubarraySolver {

  public MaxSubarraySolver () {
  }  

  // prints the value of a maximum subarray in a, and this subarray's starting and ending positions
  // PRECONDITIONS: at least one element in a is > 0
  //
  // Implementation note: 
  // v(i) only depends on the immediately preceeding v(i-1).
  // Similarly, maxV(i-1) i only depends on the immediately preceeding maxV(i-1).
  // Therefore, we do not need an entire array of "persistent storage."
  
  public void solve (int[] a) {
    int i; // an index into a
    int v_i; // the maximum value for any subarray ending with position i
    int start_of_working_subarray; // starting position of the the subarray that we are working with
 
    int maxV_i; // maximum value for any subarray found so far (positions 0 through i)
    int start_max_subarray_so_far, 
        end_max_subarray_so_far; // starting and ending positions of a max subarray found so far

    i = 0;
    v_i = 0; 
    start_of_working_subarray = 0;

    maxV_i = 0;
    start_max_subarray_so_far = 0;
    end_max_subarray_so_far = 0;
 
    while (i < a.length) {
 
      // now that we are at position i, there are only two possible options for v_i: 
      // 0, corresponding to the empty subarray when a[i] is too negative,
      // or 
      // v(i-1) + a[i] 
      if (v_i + a[i] < 0) {
        start_of_working_subarray = i + 1; // *****
        v_i = 0;
      }
      else {
        v_i = v_i + a[i];
        // update maxV_i, as needed
        if (v_i > maxV_i) {
          start_max_subarray_so_far = start_of_working_subarray; // *****
          end_max_subarray_so_far = i; // *****
          maxV_i = v_i;
        }  
      }
      i++;
    } // while 
    
    System.out.println ("A maximum subarray value is " + maxV_i + 
                        " from positions " + start_max_subarray_so_far +
                        " to " + end_max_subarray_so_far);        
  } // solve

  public static void main (String [] args) {
    MaxSubarraySolver solver = new MaxSubarraySolver();    

    int [] test0 = new int [] {-4, -2, -6, 5, -6, 9};
    // A maximum subarray value is 9 from positions 5 to 5

    int [] test1 = new int [] {-4,  2, -6, 5, -6, 4}; 
    // A maximum subarray value is 5 from positions 3 to 3
    
    int [] test2 = new int [] { 9, -2, -6, 5, -6, 4};
    // A maximum subarray value is 9 from positions 0 to 0

    int [] test3 = new int [] {-4, -2, -6, 8, -6, 9};
    // A maximum subarray value is 11 from positions 3 to 5

    int [] test4 = new int [] {-4, 2, -6, 8, -2, 4, 2, -9, 8};
    // A maximum subarray value is 12 from positions 3 to 6

    int [] test5 = new int [] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    // A maximum subarray value is 6 from positions 3 to 6

    int [] test6 = new int [] {-4, 2, -6, 8, -2, 4, 2, -9, 8, -2, 1, -3, 4, -1, 2, 1, -5, 4};
    // A maximum subarray value is 13 from positions 3 to 15

    solver.solve (test0);
    solver.solve (test1);
    solver.solve (test2);
    solver.solve (test3);
    solver.solve (test4);
    solver.solve (test5);
    solver.solve (test6);
  } // main

} // class
