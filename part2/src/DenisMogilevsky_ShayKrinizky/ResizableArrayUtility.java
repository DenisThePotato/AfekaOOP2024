package DenisMogilevsky_ShayKrinizky;

public interface ResizableArrayUtility<T>{
    /**
     * Checks if an array is full.
     *
     * @param arr array to check the size of.
     * @return true if the array is full, and false otherwise.
     */
    boolean bIsArrayFull(T[] arr);

    /**
     * doubles an array's size.
     * @param arr array to increase the size of.
     */
    T[] doubleArraySize(T[] arr);
}
