import java.util.Objects;

public interface ResizableArrayUtility {
    /**
     * Checks if an array is full.
     * @param arr array to check the size of.
     * @return true if the array is full, and false otherwise.
     */
    public static boolean bIsArrayFull(Object[] arr){
        if(arr[arr.length - 1] != null){
            return true;
        }
        return false;
    }

    /**
     * doubles an array's size.
     * @param arr array to increase the size of.
     * @return the array with increased size.
     */
    public static void doubleArraySize(Object[] arr){
        String[] newArr = new String[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    /**
     * Checks if an object is in an array.
     * @param arr array in which the element is searched for.
     * @param check string which is searched for.
     * @return the index of the element in the array, -1 if not found.
     */
    public static int elementIndex(Object[] arr, Object check){
        for(int i = 0; i < arr.length; i++){
            if(Objects.equals(arr[i], check)){
                return i;
            }
        }
        return -1;
    }
}
