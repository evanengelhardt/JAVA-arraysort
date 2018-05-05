package main.java;
import java.util.Random;

public class ArraySort {
    private int[] arrToSort;
    private char sortType;


    public ArraySort(int[] newArray, char sortType){
        this.arrToSort = newArray;
        this.sortType = sortType;
    }

    /*
    sort
    Utilizes the class variable sortType to determine which sorting algorithm to use
    Then Prints out the sorted array as a string
     */
    private void sort(){
        switch(sortType){
            case 'q' :
                System.out.println("\nQuicksort\n");
                this.quicksort(0, this.arrToSort.length - 1);
                break;
            case 'b' :
                System.out.println("\nBubblesort\n");
                this.bubbleSort();
                break;
            case 'm' :
                System.out.println("\nMergesort\n");
                this.mergesort();
                break;
            default :
                System.out.println("Did not enter a valid sort");
        }

        System.out.println(this.toString(this.arrToSort));
    }


    /*
    mergesort
    primary merges sort method
    creates a temporary array and feeds it to its helper along with the first and last index
     */
    private void mergesort(){
        int size =this.arrToSort.length;
        int temp[] = new int[size];

        this.mergesort(temp, 0 , size-1);
    }

    /*
    mergesort
    helper function called by the primary mergesort function
    input:
        takes in the temporary array along with the start and end of the segment
        of the array which needs to be sorted at the moment
     output:
        sorts the arrToSort in place, no return value
     */
    private void mergesort(int[] temp, int start, int end){
        if(start < end){
            int mid = (start + end)/ 2;

            this.mergesort(temp, start, mid);
            this.mergesort(temp, mid+1, end);
            this.merge(temp, start, mid, end);
        }
    }

    /*
    merge
    merges two sorted list, the first one is [leftStart, mid] and the second one is [mid+1, rightEnd]
     */
    private void merge(int[] temp, int leftStart, int mid, int rightEnd){
        int rightStart = mid + 1;

        int lIndex = leftStart;
        int rIndex = rightStart;

        int index = leftStart;

        // selects the smaller element from the left or right array to populate
        // the temporary array at the index
        while(lIndex <= mid && rIndex <= rightEnd) {
            if (this.arrToSort[lIndex] < this.arrToSort[rIndex]) {
                temp[index] = this.arrToSort[lIndex];
                lIndex++;
            } else {
                temp[index] = this.arrToSort[rIndex];
                rIndex++;
            }
            index++;
        }

        // copies over any remaining memebers of left array
        while(lIndex <= mid){
            temp[index] = this.arrToSort[lIndex];
            lIndex++;
            index++;
        }
        // copies over remaining members of right array
        while(rIndex <= rightEnd){
            temp[index] = this.arrToSort[rIndex];
            rIndex++;
            index++;
        }

        // copy temporary array into the array to sort
        index = leftStart;
        while(index <= rightEnd){
            this.arrToSort[index] = temp[index];
            index++;
        }

    }




    /*
    quicksort
    choses pivot to be last element in the array
     */
    private void quicksort(int start, int end){

        if(start < end) {
            int pivot = this.arrToSort[end];
            int swapPos = start - 1;

            for (int i = start; i < end; i++) {
                if (this.arrToSort[i] < pivot) {
                    swapPos++;
                    this.swap(swapPos, i);
                }
            }
            swapPos++;
            swap(swapPos, end);
            quicksort(start, swapPos - 1);
            quicksort(swapPos + 1, end);
        }
    }


    // Bubblesort
    private void bubbleSort(){
        int length  = this.arrToSort.length;
        for(int i = 0 ; i < length ; i++){
            for(int j = 0 ; j < length - 1 ; j++) {
                if(this.arrToSort[j] > this.arrToSort[j+1]){
                    swap(j, j + 1);
                }
            }
        }
    }


    // Helper Functions
    private void swap(int x, int y){
        int temp = this.arrToSort[x];
        this.arrToSort[x] = this.arrToSort[y];
        this.arrToSort[y] = temp;
    }

    private String toString(int[] arr) {
        String arrayString = "";
        arrayString = arrayString + "[";
        for(int i = 0 ; i < this.arrToSort.length ; i++){
            arrayString = arrayString + this.arrToSort[i];
            if(i != this.arrToSort.length - 1){
                arrayString = arrayString + ", ";
            }
        }
        arrayString = arrayString + "]";
        return arrayString;
    }


    public static void main(String[] args){
        Random rand = new Random();
        int[] exampleArr = new int[25];
        // bubblesort
        for(int i = 0 ; i < exampleArr.length ; i++) {
            exampleArr[i] = rand.nextInt(1000);
        }
        ArraySort test1 = new ArraySort(exampleArr, 'b');
        test1.sort();

        // quicksort
        for(int i = 0 ; i < exampleArr.length ; i++) {
            exampleArr[i] = rand.nextInt(1000);
        }
        ArraySort test2 = new ArraySort(exampleArr, 'q');
        test2.sort();

        // mergesort
        for(int i = 0 ; i < exampleArr.length ; i++) {
            exampleArr[i] = rand.nextInt(1000);
        }
        ArraySort test3 = new ArraySort(exampleArr, 'm');
        test3.sort();

    }

}