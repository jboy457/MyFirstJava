public class Test {
   public static void main(String[] args) {
      binarySearch(null, 0, 0);
   }

   static int binarySearch(int[] list, int size, int key) {
      int lo = 0, hi = size - 1;
      int index = -1;
      boolean found = false;
      while (lo <= hi && !found) {
         int mid = (lo + hi) / 2;
         if (list[mid] == key) {
            index = mid;
            found = true;
         } else if (list[mid] < key)
            lo = mid + 1;
         else
            hi = mid - 1;
      } // while
      return index;
   }// binarySearch
}